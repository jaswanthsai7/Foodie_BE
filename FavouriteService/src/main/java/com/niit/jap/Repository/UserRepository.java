package com.niit.jap.Repository;

import com.niit.jap.Domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User,String> {

 User findByEmailId(String emailId);
}
