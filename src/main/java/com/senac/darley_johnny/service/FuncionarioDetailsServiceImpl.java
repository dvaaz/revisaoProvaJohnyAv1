package com.senac.darley_johnny.service;


import com.senac.darley_johnny.entity.Funcionario;
import com.senac.darley_johnny.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class FuncionarioDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Funcionario funcionario = funcionarioRepository.findByMatriculaAtiva(username)
            .orElseThrow(() -> new RuntimeException("Usuário não encontrado."));
        return new FuncionarioDetailsImpl(funcionario);

    }

}