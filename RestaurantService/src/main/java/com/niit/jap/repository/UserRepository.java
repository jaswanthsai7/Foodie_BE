package com.niit.jap.repository;

import com.niit.jap.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User,String> {

    User findUserByEmailId(String emailId);
}
