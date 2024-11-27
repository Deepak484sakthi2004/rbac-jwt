package app.deepak.rbac_jwt.model;


import org.springframework.data.redis.core.RedisHash;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "BookingRequest")
@RedisHash("booking")
public class BookingRequest implements Serializable {

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int bookingRequestId;

    @Column(name="ride_id")
    private int rideId;

    @Column(name="passenger_id")
    private int passengerId;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private BookingStatus status;

    @Column(name = "requested_at")
    private LocalDateTime requestedAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    // Constructors, Getters, and Setters

    public BookingRequest() {
    }

    public BookingRequest(int bookingRequestId,int rideId, int passengerId, BookingStatus status, LocalDateTime requestedAt, LocalDateTime updatedAt) {
        this.bookingRequestId = bookingRequestId;
        this.rideId = rideId;
        this.passengerId = passengerId;
        this.status = status;
        this.requestedAt = requestedAt;
        this.updatedAt = updatedAt;
    }

    public int getBookingRequestId() {
        return bookingRequestId;
    }

    public void setBookingRequestId(int bookingRequestId) {
        this.bookingRequestId = bookingRequestId;
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

    public BookingStatus getStatus() {
        return status;
    }

    public void setStatus(BookingStatus status) {
        this.status = status;
    }

    public LocalDateTime getRequestedAt() {
        return requestedAt;
    }

    public void setRequestedAt(LocalDateTime requestedAt) {
        this.requestedAt = requestedAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public enum BookingStatus {
        PENDING, ACCEPTED, REJECTED
    }
}
