package be.icc.prid.reservations_springboot.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "localities")
public class Locality {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String postalCode;
    private String locality;

    @OneToMany(targetEntity = Location.class, mappedBy = "locality")
    private List<Location> locations = new ArrayList<>();

    public Locality addLocation(Location location) {
        if (!this.locations.contains(location)) {
            this.locations.add(location);
            location.setLocality(this);
        }

        return this;
    }

    public Locality removeLocation(Location location) {
        if (this.locations.contains(location)) {
            this.locations.remove(location);
            if (location.getLocality().equals(this)) {
                location.setLocality(null);
            }
        }

        return this;
    }
}