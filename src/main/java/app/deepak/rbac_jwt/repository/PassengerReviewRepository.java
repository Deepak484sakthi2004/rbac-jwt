package app.deepak.rbac_jwt.repository;

import java.util.List;

import app.deepak.rbac_jwt.model.Passenger;
import app.deepak.rbac_jwt.model.PassengerReview;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PassengerReviewRepository extends JpaRepository<PassengerReview, Integer> {
    List<PassengerReview> findByPassenger(Passenger passenger);
}