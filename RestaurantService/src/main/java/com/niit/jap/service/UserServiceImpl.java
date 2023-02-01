package com.niit.jap.service;

import com.niit.jap.config.OrderDTO;
import com.niit.jap.config.Producer;
import com.niit.jap.domain.Address;
import com.niit.jap.domain.Order;
import com.niit.jap.domain.Restaurant;
import com.niit.jap.domain.User;
import com.niit.jap.proxy.UserFavouriteProxy;
import com.niit.jap.repository.RestaurantRepository;
import com.niit.jap.repository.UserRepository;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final RestaurantRepository restaurantRepository;
    private final UserFavouriteProxy userFavouriteProxy;
    private final RabbitTemplate rabbitTemplate;
    private final DirectExchange directExchange;
    private final UserRepository userRepository;
    @Autowired
    Producer producer;

    @Autowired
    public UserServiceImpl(RestaurantRepository restaurantRepository, UserFavouriteProxy userFavouriteProxy, RabbitTemplate rabbitTemplate, DirectExchange directExchange, UserRepository userRepository) {
        this.restaurantRepository = restaurantRepository;
        this.userFavouriteProxy = userFavouriteProxy;
        this.rabbitTemplate = rabbitTemplate;
        this.directExchange = directExchange;
        this.userRepository = userRepository;
    }

    @Override
    public User saveUser(User user) {
        if (this.userRepository.existsById(user.getEmailId())) {
            return null;
        } else {
            this.userFavouriteProxy.saveUser(user);
            return this.userRepository.save(user);
        }
    }

    @Override
    public Order saveOrder(Order order) {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setOrderId(order.getOrderId());
        orderDTO.setUserEmailId(order.getUserEmailId());
        orderDTO.setDate(order.getDate());
        orderDTO.setItems(order.getItems());
        orderDTO.setAddress(order.getAddress());
        orderDTO.setTotalAmount(order.getTotalAmount());
        producer.sendMessage(orderDTO);
        return order;
    }

    @Override
    public List<Restaurant> getAllRestaurants() {
        return this.restaurantRepository.findAll();
    }

    @Override
    public List<Restaurant> getRestaurantsByCity(String cityName) {
        return this.restaurantRepository.findRestaurantByCity(cityName);
    }

    @Override
    public User saveAddress(Address address, String emailId) {
        User user = this.userRepository.findUserByEmailId(emailId);
        List<Address> addresses = user.getAddress();
        if (addresses == null) {
            user.setAddress(Collections.singletonList(address));
        } else {
            addresses.add(address);
            user.setAddress(addresses);
        }
        return this.updateUser(user);
    }

    @Override
    public User updateUser(User user) {
        return this.userRepository.save(user);
    }

    @Override
    public User getUserByEmail(String emailId) {
        return this.userRepository.findUserByEmailId(emailId);
    }

}
