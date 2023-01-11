package mk.finki.ukim.mk.lab.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mk.finki.ukim.mk.lab.model.converter.UserFullName;
import mk.finki.ukim.mk.lab.model.converter.UserNameConverter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "user_table")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;

    @Convert(converter = UserNameConverter.class)
    private UserFullName userFullName;

    private String password;
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate dateOfBirth;
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<ShoppingCart> shoppingCarts;

    public User(String username, UserFullName userFullName, String password, LocalDate dateOfBirth, List<ShoppingCart> shoppingCarts) {
        this.username = username;
        this.userFullName = userFullName;
        this.password = password;
        this.dateOfBirth = dateOfBirth;
        this.shoppingCarts = shoppingCarts;
    }
    public User(String username){
        this.username = username;
    }
}
