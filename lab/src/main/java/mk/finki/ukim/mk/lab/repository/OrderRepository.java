package mk.finki.ukim.mk.lab.repository;

import mk.finki.ukim.mk.lab.model.Order;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class OrderRepository {

    private List<Order> orderList = new ArrayList<>();

    public Order save(Order order){
        orderList.add(order);
        return order;
    }

    public List<Order> listAll(){
        return this.orderList;
    }

    public void clear(){
        this.orderList.clear();
    }
}
