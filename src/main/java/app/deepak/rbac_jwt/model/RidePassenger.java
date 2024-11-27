package app.deepak.rbac_jwt.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "ride_passenger")
public class RidePassenger implements Serializable {

    @EmbeddedId
    private RidePassengerId id;

    @ManyToOne
    @MapsId("rideId")
    @JoinColumn(name = "ride_id")
    private Ride ride;

    @ManyToOne
    @MapsId("passengerId")
    @JoinColumn(name = "passenger_id")
    private Passenger passenger;

    // Constructors, Getters, Setters

    public RidePassenger() {
    }

    public RidePassenger(Ride ride, Passenger passenger) {
        this.ride = ride;
        this.passenger = passenger;
        this.id = new RidePassengerId(ride.getRideId(), passenger.getPassengerId());
    }

    public RidePassengerId getId() {
        return id;
    }

    public void setId(RidePassengerId id) {
        this.id = id;
    }

    public Ride getRide() {
        return ride;
    }

    public void setRide(Ride ride) {
        this.ride = ride;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }

    // Getters and Setters
}
