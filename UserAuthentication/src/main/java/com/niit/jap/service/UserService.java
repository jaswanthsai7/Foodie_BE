package com.niit.jap.service;

import com.niit.jap.domain.User;
import com.niit.jap.exception.UserEmailAlreadyExistsException;
import com.niit.jap.exception.UserNotFoundException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> getAll();

    User getUserByEmailIdAndPassword(String emailId, String password) throws UserNotFoundException;

    User saveUser(User user, MultipartFile file) throws UserEmailAlreadyExistsException, IOException;

    Optional<User> getUserById(int id);

    boolean getUserByEmailId(String emailId) throws UserEmailAlreadyExistsException;

    User updateUser(User user) throws UserNotFoundException;

    void deleteUserById(int id);

    Optional<User> getUser(String emailId) throws UserNotFoundException;

    void deleteUserByEmailId(String emailId) throws UserNotFoundException;
}
