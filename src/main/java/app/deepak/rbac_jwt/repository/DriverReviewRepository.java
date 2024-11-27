package app.deepak.rbac_jwt.repository;

import java.util.List;

import app.deepak.rbac_jwt.model.Driver;
import app.deepak.rbac_jwt.model.DriverReview;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DriverReviewRepository extends JpaRepository<DriverReview, Integer> {
    List<DriverReview> findByDriver(Driver driver);
}