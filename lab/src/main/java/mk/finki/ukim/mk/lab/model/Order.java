package mk.finki.ukim.mk.lab.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "order_table")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;
    private String baloonColor;
    private String baloonSize;

    private LocalDateTime dateTime;
    @ManyToOne
    private ShoppingCart shoppingCart;
    private String username;

    public Order(String baloonColor, String username, ShoppingCart shoppingCart) {
        this.baloonColor = baloonColor;
        this.username = username;
        this.shoppingCart = shoppingCart;
    }
    public Order(String baloonColor, String username, ShoppingCart shoppingCart, LocalDateTime dateTime) {
        this.baloonColor = baloonColor;
        this.username = username;
        this.shoppingCart = shoppingCart;
        this.dateTime = dateTime;
    }
}
