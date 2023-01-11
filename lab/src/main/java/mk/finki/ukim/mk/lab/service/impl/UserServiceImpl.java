package mk.finki.ukim.mk.lab.service.impl;

import mk.finki.ukim.mk.lab.model.ShoppingCart;
import mk.finki.ukim.mk.lab.model.Status;
import mk.finki.ukim.mk.lab.model.User;
import mk.finki.ukim.mk.lab.repository.jpa.UserReposotory;
import mk.finki.ukim.mk.lab.service.ShoppingCartService;
import mk.finki.ukim.mk.lab.service.UserService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserReposotory userReposotory;
    private final ShoppingCartService shoppingCartService;
    public UserServiceImpl(UserReposotory userReposotory, ShoppingCartService shoppingCartService) {
        this.userReposotory = userReposotory;
        this.shoppingCartService = shoppingCartService;
    }

    @Override
    public ShoppingCart findActiveShoppingCart(String username) {

        User user = this.userReposotory.findByUsername(username).get();

        Optional<ShoppingCart> cart = user.getShoppingCarts().stream().filter(shoppingCart -> shoppingCart.getStatus().equals(Status.ACTIVE)
        && shoppingCart.getUser().getUsername().equals(username)).findFirst();
        if(!cart.isPresent()){

            ShoppingCart cart1 = new ShoppingCart(user, LocalDateTime.now());

            return shoppingCartService.save(cart1);
        }

        return cart.get();
    }

    @Override
    public void setCurrentShoppingCartToFinished(String username) {
        User user = this.userReposotory.findByUsername(username).get();
        ShoppingCart cart = user.getShoppingCarts().stream().filter(shoppingCart -> shoppingCart.getStatus().equals(Status.ACTIVE)).findFirst().get();
        this.shoppingCartService.changeStatus(cart.getId());
    }
}
