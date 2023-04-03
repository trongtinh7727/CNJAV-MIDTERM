package com.iiex.javamidterm.Service;




import com.iiex.javamidterm.Model.Transaction;
import com.iiex.javamidterm.Model.User;
import com.iiex.javamidterm.DTO.UserDto;

import java.util.List;

public interface UserService  {
    void saveUser(UserDto userDto);

    User findByEmail(String email);

    List<UserDto> findAllUsers();

    Transaction getTransaction();

    String getCurrentUsername();
}
