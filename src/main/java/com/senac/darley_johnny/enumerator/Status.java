package com.senac.darley_johnny.enumerator;

public enum Status {
  ATIVO(1),
  BLOQUEADO(0);

  private Integer status;
  private Status(Integer codigo) {
    this.status = codigo;
  }
  public Integer getStatus() {
    return status;
  }
}