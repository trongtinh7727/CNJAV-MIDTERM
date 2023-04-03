package com.iiex.javamidterm.Repository;


import com.iiex.javamidterm.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    User findByEmail(String email);

}
