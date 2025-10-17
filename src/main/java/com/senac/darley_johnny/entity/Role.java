package com.senac.darley_johnny.entity;


import com.senac.darley_johnny.enumerator.RoleName;
import jakarta.persistence.*;

@Entity
@Table(name ="roles")
public class Role {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "role_id")
  private Integer id;
  @Enumerated(EnumType.STRING)
  @Column(name = "role_nome")
  private RoleName nome;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public RoleName getNome() {
    return nome;
  }

  public void setNome(RoleName nome) {
    this.nome = nome;
  }
}