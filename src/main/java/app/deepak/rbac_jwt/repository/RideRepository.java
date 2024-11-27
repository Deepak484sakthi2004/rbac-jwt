package app.deepak.rbac_jwt.repository;


import app.deepak.rbac_jwt.model.Ride;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RideRepository extends JpaRepository<Ride, Integer> {
    List<Ride> findByRideStatus(Ride.RideStatus rideStatus);
}
