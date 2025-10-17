package com.senac.darley_johnny.repository;

import com.senac.darley_johnny.entity.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Integer> {
  @Query("SELECT f FROM Funcionario f WHERE f.matricula = :matricula AND f.status = 1")
  Optional<Funcionario> findByMatriculaAtiva(@Param("matricula") String matricula);

  @Query("SELECT f FROM Funcionario f WHERE f.matricula = :matricula")
  Optional<Funcionario> findByMatricula(@Param("matricula") String matricula);
}
