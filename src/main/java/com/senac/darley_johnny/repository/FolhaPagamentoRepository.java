package com.senac.darley_johnny.repository;

import com.senac.darley_johnny.entity.FolhaPagamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FolhaPagamentoRepository extends JpaRepository<FolhaPagamento, Integer> {

  @Query("SELECT p FROM FolhaPagamento p WHERE p.funcionario =:id")
  List<FolhaPagamento> listarPagamentosFuncionario(@Param("id") Integer id);
}
