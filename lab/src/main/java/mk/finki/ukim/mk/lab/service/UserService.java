package mk.finki.ukim.mk.lab.service;

import mk.finki.ukim.mk.lab.model.ShoppingCart;

public interface UserService {

    ShoppingCart findActiveShoppingCart(String username);
    void setCurrentShoppingCartToFinished(String username);
}
