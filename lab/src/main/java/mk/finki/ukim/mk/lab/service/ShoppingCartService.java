package mk.finki.ukim.mk.lab.service;

import mk.finki.ukim.mk.lab.model.ShoppingCart;

public interface ShoppingCartService {

    void changeStatus(Long id);
    ShoppingCart save(ShoppingCart shoppingCart);
}
