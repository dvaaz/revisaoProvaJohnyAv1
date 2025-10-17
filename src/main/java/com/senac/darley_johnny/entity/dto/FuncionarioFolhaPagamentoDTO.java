package com.senac.darley_johnny.entity.dto;

import java.util.List;

public class FuncionarioFolhaPagamentoDTO {
    private Integer idFuncionario;
    private String matricula;

    List<FolhaPagamentoDTOSemFuncionarioResponse> folhasDePagamento;

    public Integer getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(Integer idFuncionario) {
        this.idFuncionario = idFuncionario;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public List<FolhaPagamentoDTOSemFuncionarioResponse> getFolhasDePagamento() {
        return folhasDePagamento;
    }

    public void setFolhasDePagamento(List<FolhaPagamentoDTOSemFuncionarioResponse> folhasDePagamento) {
        this.folhasDePagamento = folhasDePagamento;
    }
}