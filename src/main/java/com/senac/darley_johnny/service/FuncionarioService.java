package com.senac.darley_johnny.service;

import com.senac.darley_johnny.config.SecurityConfiguration;
import com.senac.darley_johnny.entity.Funcionario;
import com.senac.darley_johnny.entity.Role;
import com.senac.darley_johnny.entity.dto.FuncionarioDTORequest;
import com.senac.darley_johnny.entity.dto.FuncionarioDTOResponse;
import com.senac.darley_johnny.entity.dto.FuncionarioLoginDTORequest;
import com.senac.darley_johnny.entity.dto.TokenSecurityResponse;
import com.senac.darley_johnny.enumerator.Status;
import com.senac.darley_johnny.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FuncionarioService {
  private final RoleService roleService;
  private final FuncionarioRepository repository;
  private final AuthenticationManager authenticationManager;
  private final JwtTokenService jwtTokenService;
  private final SecurityConfiguration securityConfiguration;

  // enums
  private final Integer ativo = Status.ATIVO.getStatus(),
      bloqueado = Status.BLOQUEADO.getStatus();
@Autowired
  public FuncionarioService(
      FuncionarioRepository repository,
      AuthenticationManager authenticationManager,
      JwtTokenService jwtTokenService,
      SecurityConfiguration securityConfiguration,
      RoleService roleService
  ) {
    this.repository = repository;
    this.authenticationManager = authenticationManager;
    this.jwtTokenService = jwtTokenService;
    this.securityConfiguration = securityConfiguration;
    this.roleService = roleService;
  }

  public List<Funcionario> listarTodos(){
    return repository.findAll();
  }
  public Funcionario listarPorId(Integer id){
    return repository.findById(id).orElse(null);
  }

  public FuncionarioDTOResponse criar(FuncionarioDTORequest dtoRequest){
    Optional<Funcionario> buscaMatricula = repository.findByMatricula(dtoRequest.getMatricula());

    List<Role> roles = roleService.assertRole(dtoRequest.getRoles());

    buscaMatricula.ifPresent(funcionarioExistente -> {
      throw new RuntimeException("Matricula existente.");
    });

    Funcionario funcionario = new Funcionario();
    funcionario.setNome(dtoRequest.getNome());
    funcionario.setDataNascimento(dtoRequest.getDataNascimento());
    funcionario.setChaveAcesso(securityConfiguration.passwordEncoder()
        .encode(dtoRequest.getChaveAcesso()));
    funcionario.setMatricula(dtoRequest.getMatricula());
    funcionario.setRoles(roles);
    funcionario.setStatus(ativo);

    Funcionario salvo = repository.save(funcionario);

    FuncionarioDTOResponse dtoResponse = new FuncionarioDTOResponse();
    dtoResponse.setId(salvo.getId());
    dtoResponse.setNome(salvo.getNome());
    dtoResponse.setMatricula(salvo.getMatricula());
    dtoResponse.setDataNascimento(salvo.getDataNascimento());

    return dtoResponse;
 }

  /** Login de Funcionario
   * @param dtoRequest
   * @return
   */
  public TokenSecurityResponse login(FuncionarioLoginDTORequest dtoRequest){
    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
        new UsernamePasswordAuthenticationToken(dtoRequest.getMatricula(), dtoRequest.getChaveAcesso());

    // Autentica o usuário com as credenciais fornecidas
    Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);

    // Obtém o objeto UserDetails do usuário autenticado
    FuncionarioDetailsImpl funcionarioDetails = (FuncionarioDetailsImpl) authentication.getPrincipal();

    // Gera um token JWT para o usuário autenticado
    TokenSecurityResponse dtoResponse = new TokenSecurityResponse();
    dtoResponse.setToken(jwtTokenService.generateToken(funcionarioDetails));

    return dtoResponse;
  }
}
