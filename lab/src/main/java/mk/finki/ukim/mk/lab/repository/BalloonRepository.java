package mk.finki.ukim.mk.lab.repository;

import mk.finki.ukim.mk.lab.model.Balloon;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class BalloonRepository {

    private List<Balloon> balloonList;
    private final ManufacturerRepository manufacturerRepository;
    public BalloonRepository(ManufacturerRepository manufacturerRepository){
        this.manufacturerRepository = manufacturerRepository;
        balloonList = new ArrayList<>();
        balloonList.add(new Balloon("Green Balloon", "This is balloon 1", manufacturerRepository.findAll().get(0)));
        balloonList.add( new Balloon("Red Balloon", "This is balloon 2",manufacturerRepository.findAll().get(1)));
        balloonList.add(new Balloon("Purple Balloon", "This is balloon 3",manufacturerRepository.findAll().get(2)));
        balloonList.add(new Balloon("Yellow Balloon", "This is balloon 4",manufacturerRepository.findAll().get(3)));
        balloonList.add(new Balloon("White Balloon", "This is balloon 5",manufacturerRepository.findAll().get(4)));
        balloonList.add(new Balloon("Black Balloon", "This is balloon 6", manufacturerRepository.findAll().get(0)));
        balloonList.add(new Balloon("Green Balloon", "This is balloon 7", manufacturerRepository.findAll().get(1)));
        balloonList.add(new Balloon("Orange Balloon", "This is balloon 8", manufacturerRepository.findAll().get(2)));
        balloonList.add(new Balloon("Pink Balloon", "This is balloon 9", manufacturerRepository.findAll().get(3)));
        balloonList.add(new Balloon("LightGreen Balloon", "This is balloon 10", manufacturerRepository.findAll().get(4)));


    }

    public List<Balloon> findAllBaloons(){
        return this.balloonList;
    }
    public List<Balloon> findAllByNameOrDescription(String text){
        return this.balloonList.stream().filter(balloon -> balloon.getDescription().contains(text) || balloon.getName().contains(text)).collect(Collectors.toList());
    }
    public Balloon save(Balloon balloon){
        this.balloonList.removeIf(balloon1 -> balloon1.getId().equals(balloon.getId()));
        this.balloonList.add(balloon);
        return balloon;
    }

     public Optional<Balloon> findById(Long id){
        return this.balloonList.stream().filter(balloon -> balloon.getId().equals(id)).findFirst();

    }

    public void delete(Balloon balloon){

        this.balloonList.removeIf(balloon1 -> balloon1.getId().equals(balloon.getId()));

    }
    public void filterByDescription(String description){
        this.balloonList.removeIf(balloon -> balloon.getDescription().toLowerCase().contains(description));
    }
}
