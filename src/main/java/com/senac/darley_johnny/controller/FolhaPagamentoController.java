package com.senac.darley_johnny.controller;

import com.senac.darley_johnny.entity.dto.FolhaPagamentoDTOResponse;
import com.senac.darley_johnny.service.FolhaPagamentoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/folhapagamento")
public class FolhaPagamentoController {
  private FolhaPagamentoService service;

  public FolhaPagamentoController(FolhaPagamentoService service) {
    this.service = service;
  }

  @PostMapping("/cadastrar")
  public ResponseEntity<FolhaPagamentoDTOResponse> criar(
      @RequestBody FolhaPagamentoDTORequest dtoRequest
  ){
    return ResponseEntity.ok(this.service.criar(dtoRequest));
  }

  @GetMapping("/listar/funcionario/{id}")
  public ResponseEntity<FuncionarioFolhaPagamentoDTO> listarDeFuncionario(
      @PathVariable("id") Integer id
  ){
    FuncionarioFolhaPagamentoDTO dtoResponse = this.service.listarDeFuncionario(id);
    if (dtoResponse != null) {
      return ResponseEntity.ok(dtoResponse);
    } else
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
  }

  @GetMapping("/listar")
  public ResponseEntity<List<FuncionarioFolhaPagamentoDTO>> listar(
      @PathVariable("id") Integer id
  ){
    List<FuncionarioFolhaPagamentoDTO> dtoResponse = this.service.listar();
    if (dtoResponse != null) {
      return ResponseEntity.ok(dtoResponse);
    } else
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
  }
}
