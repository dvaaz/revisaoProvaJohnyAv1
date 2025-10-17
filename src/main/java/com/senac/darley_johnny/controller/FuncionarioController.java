package com.senac.darley_johnny.controller;

import com.senac.darley_johnny.entity.Funcionario;
import com.senac.darley_johnny.service.FuncionarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
