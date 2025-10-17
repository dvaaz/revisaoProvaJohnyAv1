package com.senac.darley_johnny.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "folha_pagamento")
public class FolhaPagamento {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "folha_pagamento_id")
  private Integer id;
  @Column(name = "folha_pagamento_mes")
  private Integer mes;
  @Column(name = "folha_pagamento_ano")
  private Integer ano;
  @Column(name = "folha_pagamento_salario")
  private Integer salario;
  @JsonIgnore
  @ManyToOne
  @JoinColumn(name = "funcionario_id", nullable=false)
  private Funcionario funcionario;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Integer getMes() {
    return mes;
  }

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

  public Funcionario getFuncionario() {
    return funcionario;
  }

  public void setFuncionario(Funcionario funcionario) {
    this.funcionario = funcionario;
  }
}
