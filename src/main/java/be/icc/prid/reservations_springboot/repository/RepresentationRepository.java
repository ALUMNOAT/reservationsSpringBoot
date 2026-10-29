package be.icc.prid.reservations_springboot.repository;

import be.icc.prid.reservations_springboot.model.Location;
import be.icc.prid.reservations_springboot.model.Representation;
import be.icc.prid.reservations_springboot.model.Show;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface RepresentationRepository extends CrudRepository<Representation, Long> {
    List<Representation> findByShow(Show show);
    List<Representation> findByLocation(Location location);
    List<Representation> findByWhen(LocalDateTime when);
}
