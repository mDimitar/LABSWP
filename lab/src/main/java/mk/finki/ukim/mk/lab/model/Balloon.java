package mk.finki.ukim.mk.lab.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Balloon {
    private Long id;
    private String name;
    private String description;
    private Manufacturer manufacturer;
    public Balloon(String name, String description) {
        this.id = (long) (Math.random() * 1000);
        this.name = name;
        this.description = description;
    }

    public Balloon(String name, String description, Manufacturer manufacturer) {
        this.id = (long) (Math.random() * 1000);
        this.name = name;
        this.description = description;
        this.manufacturer = manufacturer;
    }
}
