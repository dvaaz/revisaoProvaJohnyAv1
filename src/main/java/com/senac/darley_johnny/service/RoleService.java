package com.senac.darley_johnny.service;

import com.senac.darley_johnny.entity.Role;
import com.senac.darley_johnny.enumerator.RoleName;
import com.senac.darley_johnny.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleService{

  private final RoleRepository repository;

  @Autowired
  public RoleService(RoleRepository repository) {
    this.repository = repository;
  }

  public List<Role> assertRole(List<RoleName> roleNames) {
    List<Role> todosRoles = repository.listar(); // armazena as roles existentes
    List<Role> roles = new ArrayList<>(); // armazena as roles aprovadas

    for (RoleName roleName : roleNames) {
      for (Role role : todosRoles) {
        if (roleName.equals(role.getNome())) { // Compare role names using equals
          roles.add(role);
        }
      }
    }
    return roles; // Return the list of matching roles
  }

}
