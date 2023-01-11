package mk.finki.ukim.mk.lab.service.impl;

import mk.finki.ukim.mk.lab.model.Balloon;
import mk.finki.ukim.mk.lab.model.Manufacturer;
import mk.finki.ukim.mk.lab.repository.BalloonRepository;
import mk.finki.ukim.mk.lab.repository.ManufacturerRepository;
import mk.finki.ukim.mk.lab.service.BalloonService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BalloonServiceImpl implements BalloonService {


    private final BalloonRepository balloonRepository;
    private final ManufacturerRepository manufacturerRepository;

    public BalloonServiceImpl(BalloonRepository balloonRepository, ManufacturerRepository manufacturerRepository) {
        this.balloonRepository = balloonRepository;
        this.manufacturerRepository = manufacturerRepository;
    }

    @Override
    public List<Balloon> listAll() {
        return this.balloonRepository.findAllBaloons();
    }

    @Override
    public List<Balloon> searchByNameOrDescription(String text) {
        return this.balloonRepository.findAllByNameOrDescription(text);
    }

    @Override
    public Balloon addBalloon(String name, String description, Long manufacturerId) {
        Manufacturer manufacturer = this.manufacturerRepository.findById(manufacturerId).get();
        Balloon balloon = new Balloon(name, description, manufacturer);
        this.balloonRepository.save(balloon);
        return balloon;
    }

    @Override
    public Balloon findById(Long id) {
        Optional<Balloon> balloon = balloonRepository.findById(id);
        if(balloon.isPresent())
            return balloon.get();
        return null;
    }

    @Override
    public Balloon updateBalloon(Long id, String name, String description, Long manufacturerId) {
        Balloon balloon = balloonRepository.findById(id).get();
        balloon.setName(name);
        balloon.setDescription(description);
        Manufacturer manufacturer = this.manufacturerRepository.findById(manufacturerId).get();
        balloon.setManufacturer(manufacturer);
        this.balloonRepository.save(balloon);
        return balloon;
    }

    @Override
    public Balloon deleteBalloon(Long id) {
        Balloon balloon = this.balloonRepository.findById(id).get();
        this.balloonRepository.delete(balloon);
        return balloon;
    }

    @Override
    public void filterByDescription(String description) {
        this.balloonRepository.filterByDescription(description);
    }
}
