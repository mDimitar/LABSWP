package mk.finki.ukim.mk.lab.service;

import mk.finki.ukim.mk.lab.model.Order;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


public interface OrderService {
    Order placeOrder(String balloonColor, String clientName, LocalDateTime dateTime);

    List<Order> findAll();

}