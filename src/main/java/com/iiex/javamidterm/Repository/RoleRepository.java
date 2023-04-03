package com.iiex.javamidterm.Repository;


import com.iiex.javamidterm.Model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Long> {

    Role findByName(String name);
}
