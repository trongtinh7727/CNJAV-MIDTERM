package com.iiex.lab7_lt.Service;




import com.iiex.lab7_lt.Model.Transaction;
import com.iiex.lab7_lt.Model.User;
import com.iiex.lab7_lt.DTO.UserDto;

import java.util.List;

public interface UserService  {
    void saveUser(UserDto userDto);

    User findByEmail(String email);

    List<UserDto> findAllUsers();

    Transaction getTransaction();

    String getCurrentUsername();
}
