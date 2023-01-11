package mk.finki.ukim.mk.lab.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@AllArgsConstructor
@Data
public class ShoppingCart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private User user;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime dateCreated;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "shoppingCart")
    private List<Order> orders;

    @Enumerated(EnumType.STRING)
    private Status status;
    public ShoppingCart(){
        this.status = Status.ACTIVE;
    }

    public ShoppingCart(User user, LocalDateTime dateCreated) {
        this.user = user;
        this.dateCreated = dateCreated;
        this.status = Status.ACTIVE;
    }
}
