package mk.finki.ukim.mk.lab.service.impl;

import mk.finki.ukim.mk.lab.model.Manufacturer;
import mk.finki.ukim.mk.lab.repository.ManufacturerRepository;
import mk.finki.ukim.mk.lab.repository.jpa.ManufacturerRepo;
import mk.finki.ukim.mk.lab.service.ManufacturerService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManufacturerServiceImpl implements ManufacturerService {

    private final ManufacturerRepo manufacturerRepository;

    public ManufacturerServiceImpl(ManufacturerRepo manufacturerRepository) {
        this.manufacturerRepository = manufacturerRepository;
    }

    @Override
    public List<Manufacturer> findAll() {
        return this.manufacturerRepository.findAll();
    }

    @Override
    public Manufacturer findById(Long id) {
        return this.manufacturerRepository.findById(id).get();
    }

    @Override
    public Manufacturer saveManu(String address, String name, String country) {
       return this.manufacturerRepository.save(new Manufacturer(name, country, address));
    }
}
