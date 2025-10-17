package com.senac.darley_johnny.service;

import com.senac.darley_johnny.entity.FolhaPagamento;
import com.senac.darley_johnny.entity.Funcionario;
import com.senac.darley_johnny.entity.dto.FolhaPagamentoDTOSemFuncionarioResponse;
import com.senac.darley_johnny.entity.dto.FuncionarioFolhaPagamentoDTO;
import com.senac.darley_johnny.repository.FolhaPagamentoRepository;
import com.senac.darley_johnny.repository.FuncionarioRepository;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FolhaPagamentoService {
  private final FolhaPagamentoRepository pagamentoRepository;
  private final FuncionarioRepository funcionarioRepository;

  public FolhaPagamentoService(FolhaPagamentoRepository pagamentoRepository, FuncionarioRepository funcionarioRepository) {
    this.pagamentoRepository = pagamentoRepository;
    this.funcionarioRepository = funcionarioRepository;
  }

  @Transactional
  public FolhaPagamento criar(FolhaPagamento dtoRequest){
    Optional<Funcionario> funcionario = this.funcionarioRepository.findById(dtoRequest.getFuncionario().getId());
    if (funcionario.isEmpty()) {return null;}

    FolhaPagamento folha = new FolhaPagamento();
    folha.setAno(dtoRequest.getAno());
    folha.setMes(dtoRequest.getMes());
    folha.setSalario(dtoRequest.getSalario());
    folha.setFuncionario(funcionario.get());

    FolhaPagamento folhaSalva = this.pagamentoRepository.save(folha);


    return folhaSalva;
  }

  public List<FuncionarioFolhaPagamentoDTO> listar() {
    List<Funcionario> funcionarios = this.funcionarioRepository.findAll();

    if (!funcionarios.isEmpty() ) {
      List<FuncionarioFolhaPagamentoDTO> dtoResponse = new ArrayList<>();
      for (Funcionario funcionario : funcionarios) {
        // Busca as folhas de pagamento para o funcionário atual :-D
        List<FolhaPagamento> folhas = this.pagamentoRepository.listarPagamentosFuncionario(funcionario.getId());

        // Cria a lista para armazenar os DTOs das folhas de pagamento
        List<FolhaPagamentoDTOSemFuncionarioResponse> folhasFuncionarioDTO = new ArrayList<>();


        for (FolhaPagamento f : folhas) {
          FolhaPagamentoDTOSemFuncionarioResponse folhaDTO = new FolhaPagamentoDTOSemFuncionarioResponse();
          folhaDTO.setMes(f.getMes());
          folhaDTO.setAno(f.getAno());
          folhaDTO.setSalario(f.getSalario());
          folhaDTO.setId(f.getId());
          folhasFuncionarioDTO.add(folhaDTO);
        }

        // 4. Cria o DTO principal (FuncionarioFolhaPagamentoDTO) para o FUNCIONÁRIO ATUAL
        FuncionarioFolhaPagamentoDTO dtoFuncionario = new FuncionarioFolhaPagamentoDTO();
        dtoFuncionario.setIdFuncionario(funcionario.getId()); // Usa o 'funcionario' atual do loop
        dtoFuncionario.setMatricula(funcionario.getMatricula()); // Usa o 'funcionario' atual do loop

        // Atribui a lista de folhas de pagamento DTO que acabamos de criar
        dtoFuncionario.setFolhasDePagamento(folhasFuncionarioDTO);

        // 5. Adiciona o DTO COMPLETO na lista que será retornada (o ponto chave!)
        dtoResponse.add(dtoFuncionario);
      }
      return dtoResponse;
    }
    return new ArrayList<>();
  }

  public FuncionarioFolhaPagamentoDTO listarDeFuncionario(Integer idFuncionario) {
    Optional<Funcionario> funcionario = this.funcionarioRepository.findById(idFuncionario);

    if (funcionario.isEmpty()) {

      List<FolhaPagamento> folhas = this.pagamentoRepository.listarPagamentosFuncionario(funcionario.get().getId());

      if (!folhas.isEmpty()) {
        List<FolhaPagamentoDTOSemFuncionarioResponse> folhasDTO = new ArrayList<>();

        for (FolhaPagamento f : folhas){
          FolhaPagamentoDTOSemFuncionarioResponse folha = new FolhaPagamentoDTOSemFuncionarioResponse();
          folha.setMes(f.getMes());
          folha.setAno(f.getAno());
          folha.setSalario(f.getSalario());
          folha.setId(f.getId());
          folhasDTO.add(folha);
        }

        FuncionarioFolhaPagamentoDTO dtoResponse = new FuncionarioFolhaPagamentoDTO();
        dtoResponse.setIdFuncionario(funcionario.get().getId());
        dtoResponse.setMatricula(funcionario.get().getMatricula());
        dtoResponse.setFolhasDePagamento(folhasDTO);

        return dtoResponse;
      }
    }
    return   null;
  }



}
