package be.icc.prid.reservations_springboot.service;

import be.icc.prid.reservations_springboot.dto.UserProfileDto;
import be.icc.prid.reservations_springboot.dto.UserRegistrationDto;
import be.icc.prid.reservations_springboot.model.User;
import be.icc.prid.reservations_springboot.model.UserRole;
import be.icc.prid.reservations_springboot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();

        userRepository.findAll().forEach(users::add);

        users.sort(Comparator.comparing(User::getId));

        return users;
    }

    public User getUser(long id) {
        return userRepository.findById(id);
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }

    public boolean isLoginAndEmailAvailable(String login, String email) {
        return !userRepository.existsByLogin(login) && !userRepository.existsByEmail(email);
    }

    public void registerFromDto(UserRegistrationDto dto) {
        User user = new User();
        user.setFirstname(dto.getFirstName());
        user.setLastname(dto.getLastName());
        user.setLogin(dto.getLogin());
        user.setEmail(dto.getEmail());
        user.setLangue(dto.getLangue());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setRole(UserRole.MEMBER);
        userRepository.save(user);
    }

    public void updateUser(long id, User user) {
        userRepository.save(user);
    }

    public void updateUserFromDto(UserProfileDto dto) {
        User user = userRepository.findById(dto.getId())
                .orElseThrow(() -> new RuntimeException("Utilisateur introuvable"));

        user.setFirstname(dto.getFirstName());
        user.setLastname(dto.getLastName());
        user.setEmail(dto.getEmail());
        user.setLangue(dto.getLangue());

        // Si un nouveau mot de passe est fourni et valide
        if (dto.getPassword() != null && !dto.getPassword().isBlank()) {
            user.setPassword(passwordEncoder.encode(dto.getPassword()));
        }

        userRepository.save(user);
    }

    public void deleteUser(long id) {
        userRepository.deleteById(id);
    }

    public void deleteByLogin(String login) {
        User user = userRepository.findByLogin(login);
        userRepository.delete(user);
    }
}
