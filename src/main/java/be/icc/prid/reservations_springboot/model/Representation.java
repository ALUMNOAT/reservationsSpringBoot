package be.icc.prid.reservations_springboot.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
@Table(name = "representations")
public class Representation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "show_id", nullable = false)
    private Show show;

    /**
     * Date de creation de la representation
     */
    private LocalDateTime when;

    /**
     * Lieu de prestation de la representation
     */
    @ManyToOne
    @JoinColumn(name = "location_id", nullable = true)
    private Location location;

}
