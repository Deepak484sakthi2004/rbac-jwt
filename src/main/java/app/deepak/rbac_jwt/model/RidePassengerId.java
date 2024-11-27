package app.deepak.rbac_jwt.model;


import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class RidePassengerId implements Serializable {

    @Column(name = "ride_id")
    private int rideId;

    @Column(name = "passenger_id")
    private int passengerId;

    public RidePassengerId() {
    }

    public RidePassengerId(int rideId, int passengerId) {
        this.rideId = rideId;
        this.passengerId = passengerId;
    }

    public int getRideId() {
        return rideId;
    }

    public void setRideId(int rideId) {
        this.rideId = rideId;
    }

    public int getPassengerId() {
        return passengerId;
    }

    public void setPassengerId(int passengerId) {
        this.passengerId = passengerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RidePassengerId that = (RidePassengerId) o;
        return rideId == that.rideId && passengerId == that.passengerId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(rideId, passengerId);
    }
}
