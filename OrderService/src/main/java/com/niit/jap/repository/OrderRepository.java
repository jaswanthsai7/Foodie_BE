package com.niit.jap.repository;

import com.niit.jap.domain.Order;
import com.niit.jap.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends MongoRepository<User,String> {
    User findByEmailId(String emailId);
}
