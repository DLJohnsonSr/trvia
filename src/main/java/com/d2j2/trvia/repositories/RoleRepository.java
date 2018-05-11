package com.d2j2.trvia.repositories;

import com.d2j2.trvia.entities.AppRole;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<AppRole, Long> {
    AppRole findByRoleName(String role);
}
