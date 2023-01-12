package com.niit.jap.repository;

import com.niit.jap.domain.User;
import com.niit.jap.exception.UserNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByEmailIdAndPassword(String emailId, String Password) throws UserNotFoundException;

    Optional<User> findUserByEmailId(String emailId);

    boolean deleteUserByEmailId(String emailId) throws UserNotFoundException;
}
