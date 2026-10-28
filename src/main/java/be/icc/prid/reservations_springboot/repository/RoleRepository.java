package be.icc.prid.reservations_springboot.repository;

import be.icc.prid.reservations_springboot.model.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role, Long> {
    Role findByRole(String role);
}
