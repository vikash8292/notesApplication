package com.notesapp.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.notesapp.model.Role;

public interface RoleRepository extends JpaRepository<Role,Long> {

}
