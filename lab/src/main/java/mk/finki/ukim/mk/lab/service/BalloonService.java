package mk.finki.ukim.mk.lab.service;

import mk.finki.ukim.mk.lab.model.Balloon;

import java.util.List;

public interface BalloonService {
    List<Balloon> listAll();
    List<Balloon> searchByNameOrDescription(String text);
    Balloon addBalloon(String name, String description, Long manufacturerId);
    Balloon findById(Long id);
    Balloon updateBalloon(Long id, String name, String description, Long manufacturerId);

    Balloon deleteBalloon(Long id);
    void filterByDescription(String description);
}
