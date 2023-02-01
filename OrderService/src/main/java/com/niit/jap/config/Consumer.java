package com.niit.jap.config;

import com.niit.jap.domain.Order;
import com.niit.jap.domain.User;
import com.niit.jap.services.OrderServiceImpl;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class Consumer {
    @Autowired
    private OrderServiceImpl orderServiceImpl;
    @RabbitListener(queuesToDeclare = @Queue("userorderqueue"))
    public void getData(OrderDTO orderDTO) {
        Order order=new Order();
        User user =new User();
        order.setUserEmailId(orderDTO.getUserEmailId());
        order.setOrderId(orderDTO.getOrderId());
        order.setDate(orderDTO.getDate());
        order.setItems(orderDTO.getItems());
        order.setAddress(orderDTO.getAddress());
        order.setTotalAmount(orderDTO.getTotalAmount());
        user.setEmailId(order.getUserEmailId());
        user.setOrders(Arrays.asList(order));
        orderServiceImpl.updateOrder(user);

    }
}
