
package app.deepak.rbac_jwt.service;


import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import app.deepak.rbac_jwt.exception.NoAvailableSeatsException;
import app.deepak.rbac_jwt.exception.ResourceNotFoundException;
import app.deepak.rbac_jwt.model.Driver;
import app.deepak.rbac_jwt.model.Passenger;
import app.deepak.rbac_jwt.model.Ride;
import app.deepak.rbac_jwt.model.User;
import app.deepak.rbac_jwt.repository.DriverRepository;
import app.deepak.rbac_jwt.repository.PassengerRepository;
import app.deepak.rbac_jwt.repository.RideRepository;
import app.deepak.rbac_jwt.repository.UserRepository;
import app.deepak.rbac_jwt.request.CreateRideRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RideService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RideRepository rideRepository;
    @Autowired
    private DriverRepository driverRepository;
    @Autowired
    private PassengerRepository passengerRepository;

    public RideService() {
    }

    @PreAuthorize("hasRole('DRIVER')")
    @Transactional
    public Ride createRide(CreateRideRequest createRideRequest) {
        Optional<Driver> driver = this.driverRepository.findByUserUserId(createRideRequest.getDriverUserId());
        if (!driver.isPresent()) {
            throw new RuntimeException("Driver not found with user ID: " + createRideRequest.getDriverUserId());
        } else if (createRideRequest.getAvailableSeats() > createRideRequest.getTotalSeats()) {
            throw new RuntimeException("Available seats cannot be greater than total seats.");
        } else {
            Set<Passenger> passengers = new HashSet();
            Iterator var4 = createRideRequest.getPassengerIds().iterator();

            while(var4.hasNext()) {
                Integer passengerId = (Integer)var4.next();
                Optional<Passenger> passenger = this.passengerRepository.findById(passengerId);
                Objects.requireNonNull(passengers);
                passenger.ifPresent(passengers::add);
            }

            Ride ride = new Ride();
            ride.setDriver((Driver)driver.get());
            ride.setOrigin(createRideRequest.getOrigin());
            ride.setDestination(createRideRequest.getDestination());
            ride.setDepartureTime(createRideRequest.getDepartureTime());
            ride.setArrivalTime(createRideRequest.getArrivalTime());
            ride.setAvailableSeats(createRideRequest.getAvailableSeats());
            ride.setTotalSeats(createRideRequest.getTotalSeats());
            ride.setVehicleType(createRideRequest.getVehicleType());
            ride.setRideStatus(createRideRequest.getRideStatus());
            ride.setPassengers(passengers);
            ride.setCreatedAt(LocalDateTime.now());
            ride.setUpdatedAt(LocalDateTime.now());
            return (Ride)this.rideRepository.save(ride);
        }
    }

    @PreAuthorize("hasRole('DRIVER')")
    @Transactional
    public String addPassengerToRide(int rideId, int passengerId) {
        Optional<Ride> rideOpt = this.rideRepository.findById(rideId);
        Optional<Passenger> passengerOpt = this.passengerRepository.findById(passengerId);
        if (rideOpt.isPresent() && passengerOpt.isPresent()) {
            Ride ride = (Ride)rideOpt.get();
            if (ride.getAvailableSeats() <= 0) {
                throw new NoAvailableSeatsException("No available seats on this ride.");
            } else {
                Passenger passenger = (Passenger)passengerOpt.get();
                ride.getPassengers().add(passenger);
                ride.setAvailableSeats(ride.getAvailableSeats() - 1);
                this.rideRepository.save(ride);
                return "Successfully booked";
            }
        } else {
            throw new RuntimeException("Ride or Passenger not found");
        }
    }

    @PreAuthorize("hasRole('PASSENGER')")
    @Transactional
    public Passenger createPassenger(String email, String phoneNumber) {
        Optional<User> userOpt = this.userRepository.findByEmail(email);
        if (userOpt.isPresent()) {
            User user = (User)userOpt.get();
            Optional<Passenger> passengerOpt = this.passengerRepository.findByUserUserId(user.getUserId());
            if (passengerOpt.isPresent()) {
                throw new RuntimeException("Passenger with email: " + email + " already exists");
            } else {
                Passenger passenger = new Passenger();
                passenger.setUser(user);
                passenger.setName(user.getName());
                passenger.setEmail(user.getEmail());
                passenger.setPhoneNumber(phoneNumber);
                passenger.setPhoneNumber(user.getEmail());
                return (Passenger)this.passengerRepository.save(passenger);
            }
        } else {
            throw new RuntimeException("User not found");
        }
    }

    @PreAuthorize("hasRole('DRIVER')")
    @Transactional
    public String cancelRide(int rideId) {
        Ride ride = (Ride)this.rideRepository.findById(rideId).orElseThrow(() -> {
            return new ResourceNotFoundException("Ride not found with id: " + rideId);
        });
        this.rideRepository.delete(ride);
        return "Ride cancelled successfully";
    }

    @PreAuthorize("hasRole('PASSENGER')")
    public List<Ride> getAllRides() {
        return this.rideRepository.findAll();
    }

    @PreAuthorize("hasRole('PASSENGER')")
    public List<Ride> getRidesByStatus(Ride.RideStatus rideStatus) {
        return this.rideRepository.findByRideStatus(rideStatus);
    }
}
