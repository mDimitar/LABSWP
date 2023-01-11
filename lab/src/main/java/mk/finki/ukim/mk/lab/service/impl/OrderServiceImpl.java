package mk.finki.ukim.mk.lab.service.impl;

import mk.finki.ukim.mk.lab.model.Order;
import mk.finki.ukim.mk.lab.model.ShoppingCart;
import mk.finki.ukim.mk.lab.repository.OrderRepository;
import mk.finki.ukim.mk.lab.repository.jpa.OrderRepoJPA;
import mk.finki.ukim.mk.lab.service.OrderService;
import mk.finki.ukim.mk.lab.service.UserService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.beans.Transient;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {


    private final OrderRepoJPA orderRepository;

    private final UserService userService;
    public OrderServiceImpl(OrderRepoJPA orderRepository, UserService userService) {
        this.orderRepository = orderRepository;
        this.userService = userService;
    }

    @Override
    public Order placeOrder(String balloonColor, String clientName, LocalDateTime dateTime) {
        ShoppingCart cart = this.userService.findActiveShoppingCart(clientName);
        return this.orderRepository.save(new Order(balloonColor, clientName, cart, dateTime));
    }

    @Override
    public List<Order> findAll() {
        return this.orderRepository.findAll();
    }


}
