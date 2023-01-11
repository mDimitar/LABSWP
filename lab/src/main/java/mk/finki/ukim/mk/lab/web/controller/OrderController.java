package mk.finki.ukim.mk.lab.web.controller;

import mk.finki.ukim.mk.lab.model.Order;
import mk.finki.ukim.mk.lab.model.ShoppingCart;
import mk.finki.ukim.mk.lab.service.OrderService;
import mk.finki.ukim.mk.lab.service.ShoppingCartService;
import mk.finki.ukim.mk.lab.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class OrderController {

    private final OrderService orderService;
    private final UserService userService;
    private final ShoppingCartService shoppingCartService;

    public OrderController(OrderService orderService, UserService userService,
                           ShoppingCartService shoppingCartService) {
        this.orderService = orderService;
        this.userService = userService;
        this.shoppingCartService = shoppingCartService;
    }

    @GetMapping("/orders")
    public String showAllOrders(Model model, HttpServletRequest request){
       // List<Order> orders = orderService.findAll();
        ShoppingCart shoppingCart = userService.findActiveShoppingCart(request.getRemoteUser());
        model.addAttribute("orders", shoppingCart.getOrders());

        return "orders";
    }
}
