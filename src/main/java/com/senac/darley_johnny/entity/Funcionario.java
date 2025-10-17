package com.senac.darley_johnny.entity;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="funcionario")
public class Funcionario {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "funcionario_id")
  private Integer id;
  @Column(name = "funcionario_matricula")
  private String matricula;
  @Column(name="funcionario_nome")
  private String nome;
  @Column(name="funcionario_data_nascimento")
  private Date dataNascimento;
  @Column(name="funcionario_chave_acesso")
  private String chaveAcesso;
  @Column(name="funcionario_status")
  private Integer status;

  @OneToMany(mappedBy = "funcionario")
  private Set<FolhaPagamento> folhasPagamentos;

  @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
  @JoinTable(name = "funcionario_role",
      joinColumns = @JoinColumn(name= "funcionario_id"),
      inverseJoinColumns = @JoinColumn(name ="role_id"))
  private List<Role> roles;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getMatricula() {
    return matricula;
  }

  public void setMatricula(String matricula) {
    this.matricula = matricula;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public Date getDataNascimento() {
    return dataNascimento;
  }

  public void setDataNascimento(Date dataNascimento) {
    this.dataNascimento = dataNascimento;
  }

  public String getChaveAcesso() {
    return chaveAcesso;
  }

  public void setChaveAcesso(String chaveAcesso) {
    this.chaveAcesso = chaveAcesso;
  }

  public Integer getStatus() {
    return status;
  }

  public void setStatus(Integer status) {
    this.status = status;
  }

  public Set<FolhaPagamento> getFolhasPagamentos() {
    return folhasPagamentos;
  }

  public void setFolhasPagamentos(Set<FolhaPagamento> folhasPagamentos) {
    this.folhasPagamentos = folhasPagamentos;
  }

  public List<Role> getRoles() {
    return roles;
  }

  public void setRoles(List<Role> roles) {
    this.roles = roles;
  }
}