package be.icc.prid.reservations_springboot.service;

import be.icc.prid.reservations_springboot.repository.PasswordResetTokenRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class TokenCleanup {
    private final PasswordResetTokenRepository tokerRepo;

    @Scheduled(cron = "0 0 * * * *") // toutes les heures
    @Transactional
    public void removeExpiredTokens() {
        tokerRepo.findAll().stream()
                .filter(token -> token.getExpiryDate().isBefore(LocalDateTime.now()))
                .forEach(tokerRepo::delete);
    }
}
