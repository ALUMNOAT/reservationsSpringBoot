package be.icc.prid.reservations_springboot.service;

import be.icc.prid.reservations_springboot.model.Location;
import be.icc.prid.reservations_springboot.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LocationService {
    @Autowired
    private LocationRepository repository;

    public List<Location> getAll() {
        List<Location> locations = new ArrayList<>();
        repository.findAll().forEach(locations::add);
        return locations;
    }

    public Location get(String id) {
        Long indice = (long) Integer.parseInt(id);
        Optional<Location> location = repository.findById(indice);
        return location.orElse(null);
    }

    public void add(Location location) {
        repository.save(location);
    }

    public void update(String id, Location location) {
        repository.save(location);
    }

    public void delete(String id) {
        Long indice = (long) Integer.parseInt(id);
        repository.deleteById(indice);
    }
}
