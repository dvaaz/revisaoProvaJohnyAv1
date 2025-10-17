package com.senac.darley_johnny.repository;

import com.senac.darley_johnny.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

  @Query("SELECT r FROM Role r")
  public List<Role>  listar();
}
