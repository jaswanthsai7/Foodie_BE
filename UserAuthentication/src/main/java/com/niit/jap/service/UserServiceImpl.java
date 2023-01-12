package com.niit.jap.service;

import com.niit.jap.domain.User;
import com.niit.jap.exception.UserEmailAlreadyExistsException;
import com.niit.jap.exception.UserNotFoundException;
import com.niit.jap.proxy.UserProxy;
import com.niit.jap.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserProxy userProxy;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserProxy userProxy) {
        this.userRepository = userRepository;
        this.userProxy = userProxy;
    }

    @Override
    public List<User> getAll() {
        return this.userRepository.findAll();
    }

    @Override
    public User getUserByEmailIdAndPassword(String emailId, String password) throws UserNotFoundException {
        User user = this.userRepository.findByEmailIdAndPassword(emailId, password);
        if (user == null) {
            throw new UserNotFoundException();
        } else {
            return this.userRepository.findByEmailIdAndPassword(emailId, password);
        }
    }

    @Override
    public User saveUser(User user, MultipartFile file) throws UserEmailAlreadyExistsException, IOException {
        if (this.userRepository.findUserByEmailId(user.getEmailId()).isPresent()) {
            throw new UserEmailAlreadyExistsException();
        } else {
            if (Objects.equals(user.getRole(), "user")) {
                user.setProfilePic(file.getBytes());
                user.setFileName(file.getOriginalFilename());
                this.userProxy.saveUser(user);
            } else if (Objects.equals(user.getRole(), "admin")) {
                user.setProfilePic(file.getBytes());
                user.setFileName(file.getOriginalFilename());
                this.userProxy.saveAdmin(user);
            }
        }
        return this.userRepository.save(user);

    }

    @Override
    public Optional<User> getUserById(int id) {
        Optional<User> user = this.userRepository.findById(id);

        return this.userRepository.findById(id);
    }

    @Override
    public boolean getUserByEmailId(String emailId) throws UserEmailAlreadyExistsException {
        boolean flag = false;
        if (this.userRepository.findUserByEmailId(emailId).isPresent()) {
            flag = true;
            throw new UserEmailAlreadyExistsException();
        } else {
            flag = false;
            return flag;
        }
    }

    @Override
    public User updateUser(User user) throws UserNotFoundException {
        Optional<User> user1 = this.userRepository.findUserByEmailId(user.getEmailId());
        if (user1.isPresent()) {
            return this.userRepository.save(user);
        } else {
            throw new UserNotFoundException();
        }
    }


    @Override
    public void deleteUserById(int id) {
        this.userRepository.deleteById(id);
    }

    @Override
    public void deleteUserByEmailId(String emailId) throws UserNotFoundException {
        this.userRepository.deleteUserByEmailId(emailId);
    }
}
