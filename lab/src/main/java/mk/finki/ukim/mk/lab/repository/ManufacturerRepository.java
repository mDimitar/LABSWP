package mk.finki.ukim.mk.lab.repository;

import mk.finki.ukim.mk.lab.model.Manufacturer;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ManufacturerRepository {

    List<Manufacturer> manufacturers;
    public ManufacturerRepository(){
        manufacturers = new ArrayList<>();
        manufacturers.add(new Manufacturer("Manu 1", "Country 1", "Adr 1"));
        manufacturers.add(new Manufacturer("Manu 2", "Country 2", "Adr 2"));
        manufacturers.add(new Manufacturer("Manu 3", "Country 3", "Adr 3"));
        manufacturers.add(new Manufacturer("Manu 4", "Country 4", "Adr 4"));
        manufacturers.add(new Manufacturer("Manu 5", "Country 5", "Adr 5"));
    }

    public List<Manufacturer> findAll(){
        return this.manufacturers;
    }
    public Optional<Manufacturer> findById(Long id){
        return this.manufacturers.stream().filter(manufacturer -> manufacturer.getId().equals(id)).findFirst();
    }

}
