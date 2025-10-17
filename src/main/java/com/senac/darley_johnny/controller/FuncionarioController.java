package com.senac.darley_johnny.controller;

import com.senac.darley_johnny.entity.Funcionario;
import com.senac.darley_johnny.entity.dto.FuncionarioDTORequest;
import com.senac.darley_johnny.entity.dto.FuncionarioDTOResponse;
import com.senac.darley_johnny.entity.dto.FuncionarioLoginDTORequest;
import com.senac.darley_johnny.entity.dto.TokenSecurityResponse;
import com.senac.darley_johnny.service.FuncionarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/funcionario")
public class FuncionarioController {
  private final FuncionarioService service;

  public FuncionarioController(FuncionarioService service) {
    this.service = service;
  }

  @GetMapping("/listar")
  public ResponseEntity<List<Funcionario>> listarTodos() {
    List<Funcionario> resposta = service.listarTodos();
    if (resposta.isEmpty()){
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }
    return ResponseEntity.ok(resposta);
  }

  @GetMapping("/listar/{id}")
  public ResponseEntity<Funcionario> listarPorId(
      @PathVariable("id") Integer id
  ) {
    Funcionario resposta = service.listarPorId(id);
    if (resposta == null){
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }
    return ResponseEntity.ok(resposta);
  }

  @PostMapping("/cadastrar")
  public ResponseEntity<FuncionarioDTOResponse> criar(
      @RequestBody FuncionarioDTORequest dtoRequest
      ) {
    return ResponseEntity.ok(this.service.criar(dtoRequest));
  }

  @PostMapping("/login")
  public ResponseEntity<TokenSecurityResponse> login(
      @RequestBody FuncionarioLoginDTORequest dtoRequest
  ) {
    TokenSecurityResponse dtoResponse = service.login(dtoRequest);
    if (dtoResponse.getToken() != null) {
      return ResponseEntity.ok(dtoResponse);
    }
    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
  }

}
