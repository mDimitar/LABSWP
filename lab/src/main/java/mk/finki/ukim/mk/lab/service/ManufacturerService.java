package mk.finki.ukim.mk.lab.service;

import mk.finki.ukim.mk.lab.model.Manufacturer;

import java.util.List;

public interface ManufacturerService {

     List<Manufacturer> findAll();
     Manufacturer findById(Long id);

     Manufacturer saveManu(String address, String name, String country);

}
