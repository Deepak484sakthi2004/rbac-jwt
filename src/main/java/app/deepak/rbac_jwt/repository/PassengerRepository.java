package app.deepak.rbac_jwt.repository;

import app.deepak.rbac_jwt.model.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PassengerRepository extends JpaRepository<Passenger, Integer> {
    Optional<Passenger> findByUserUserId(int userId);
}
