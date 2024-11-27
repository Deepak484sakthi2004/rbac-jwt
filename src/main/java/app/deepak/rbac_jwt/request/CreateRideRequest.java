package app.deepak.rbac_jwt.request;


import app.deepak.rbac_jwt.model.Ride;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.Set;

public class CreateRideRequest {

    private int driverUserId;
    private String origin;
    private String destination;
    private String departureTime;
    private String arrivalTime;
    private int availableSeats;
    private int totalSeats;
    private String vehicleType;
    @Enumerated(EnumType.STRING)
    private Ride.RideStatus rideStatus;
    private Set<Integer> passengerIds; // Assuming you use passenger IDs to associate passengers

    // Constructors, Getters, Setters

    public CreateRideRequest() {
    }

    public CreateRideRequest(int driverUserId, String origin, String destination, String departureTime,
                             String arrivalTime, int availableSeats, int totalSeats, String vehicleType,
                             Ride.RideStatus rideStatus, Set<Integer> passengerIds) {
        this.driverUserId = driverUserId;
        this.origin = origin;
        this.destination = destination;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.availableSeats = availableSeats;
        this.totalSeats = totalSeats;
        this.vehicleType = vehicleType;
        this.rideStatus = rideStatus;
        this.passengerIds = passengerIds;
    }

    // Getters and Setters

    public int getDriverUserId() {
        return driverUserId;
    }

    public void setDriverUserId(int driverUserId) {
        this.driverUserId = driverUserId;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String  getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String  departureTime) {
        this.departureTime = departureTime;
    }

    public String  getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }

    public int getTotalSeats() {
        return totalSeats;
    }

    public void setTotalSeats(int totalSeats) {
        this.totalSeats = totalSeats;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public Ride.RideStatus getRideStatus() {
        return rideStatus;
    }

    public void setRideStatus(Ride.RideStatus rideStatus) {
        this.rideStatus = rideStatus;
    }

    public Set<Integer> getPassengerIds() {
        return passengerIds;
    }

    public void setPassengerIds(Set<Integer> passengerIds) {
        this.passengerIds = passengerIds;
    }
}
