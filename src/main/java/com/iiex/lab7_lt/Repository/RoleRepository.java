package com.iiex.lab7_lt.Repository;


import com.iiex.lab7_lt.Model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Long> {

    Role findByName(String name);
}
