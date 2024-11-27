package app.deepak.rbac_jwt.repository;


import app.deepak.rbac_jwt.model.Driver;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DriverRepository extends JpaRepository<Driver, Integer> {
    Optional<Driver> findByUserUserId(int userId);
//    Optional<Driver> findByUserEmail(String email);
}
