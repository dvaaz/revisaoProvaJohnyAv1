package com.senac.darley_johnny.entity.dto;

public class FolhaPagamentoDTORequest {
    private Integer mes;

    private Integer ano;

    private Integer salario;

    public Integer getMes() {
        return mes;
    }

    public Integer idFuncionario;

    public void setMes(Integer mes) {
        this.mes = mes;
    }

    public Integer getAno() {
        return ano;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }

    public Integer getSalario() {
        return salario;
    }

    public void setSalario(Integer salario) {
        this.salario = salario;
    }

    public Integer getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(Integer idFuncionario) {
        this.idFuncionario = idFuncionario;
    }
}