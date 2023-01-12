package com.niit.jap.services;

import com.niit.jap.domain.Order;
import com.niit.jap.domain.User;
import com.niit.jap.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

//    @Override
//    public User saveOrder(Order order, String emailId) {
//        User user = this.orderRepository.findByEmailId(emailId);
//
//        List<Order> orders = user.getOrders();
//        if (user==null) {
//            user.setEmailId(order.getUserEmailId());
//            user.setOrders(Collections.singletonList(order));
//            this.orderRepository.save(user);
//        } else {
//            if (user.getOrders() == null) {
//                user.setOrders(Collections.singletonList(order));
//            } else {
//                orders.add(order);
//                user.setOrders(orders);
//            }
//             this.orderRepository.save(user);
//        }
//
//       return user;
//    }

    @Override
    public User saveUser(User user) {
        return this.orderRepository.save(user);
    }

    @Override
    public User updateOrder(User user) {
        List<User> users = this.getAllUsers();
        Optional<User> user1 = users.stream().filter(user2 -> user2.getEmailId().equals(user.getEmailId())).findFirst();

        if (user1.isPresent()) {
            User user2 = user1.get();
            List<Order> order = user1.get().getOrders();
            Optional<Order> order1 = order.stream().filter(order2 -> order2.getOrderId() == 9999).findFirst();
            if (order1.isEmpty()) {
                List<Order> orders = user2.getOrders();
                orders.addAll(user.getOrders());
                user2.setOrders(orders);
            } else {
                System.out.println("ok");
            }
            return this.orderRepository.save(user2);
        } else {
            return this.saveUser(user);

        }
//        User user1 = this.orderRepository.findByEmailId(user.getEmailId());
//
//        if (user1==null) {
//            User user2=new User();
//            List<Order> order1 = new ArrayList<>();
//            user2.setEmailId(user.getEmailId());
//            order1.add(user.getOrders().get(0));
//            user2.setOrders(order1);
//           return saveUser(user2);
//        }else {
//            List<Order> orders = user1.getOrders();
//            orders.add(user.getOrders().get(0));
//            user1.setOrders(orders);
//            this.orderRepository.save(user1);
//        }
    }

    @Override
    public List<User> getAllUsers() {
        return this.orderRepository.findAll();
    }

    @Override
    public User getByEmailId(String emailId) {
        return this.orderRepository.findByEmailId(emailId);
    }


}
