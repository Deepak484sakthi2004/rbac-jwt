package app.deepak.rbac_jwt.model;


import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(
        name = "Driver"
)
public class Driver {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    @Column(
            name = "id"
    )
    private int driverId;
    @OneToOne
    @JoinColumn(
            name = "user_id"
    )
    private User user;
    @OneToMany(
            mappedBy = "driver",
            cascade = {CascadeType.ALL},
            orphanRemoval = true
    )
    private List<Ride> ride;
    @Column(
            name = "phone_number"
    )
    private String phoneNumber;
    @Column(
            name = "driver_license_number"
    )
    private String licenseNumber;
    @Column(
            name = "vehicle_model"
    )
    private String vehicleDetails;
    @Column(
            name = "vehicle_registration_number"
    )
    private String vehicleRegistrationNumber;
    @Column(
            name = "rating"
    )
    private Float rating;
    @Column(
            name = "created_at"
    )
    private LocalDateTime createdAt;
    @Column(
            name = "updated_at"
    )
    private LocalDateTime updatedAt;

    public Float getRating() {
        return this.rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }

    public int getDriverId() {
        return this.driverId;
    }

    public void setDriverId(int driverId) {
        this.driverId = driverId;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getLicenseNumber() {
        return this.licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public String getVehicleDetails() {
        return this.vehicleDetails;
    }

    public void setVehicleDetails(String vehicleDetails) {
        this.vehicleDetails = vehicleDetails;
    }

    public String getVehicleRegistrationNumber() {
        return this.vehicleRegistrationNumber;
    }

    public void setVehicleRegistrationNumber(String vehicleRegistrationNumber) {
        this.vehicleRegistrationNumber = vehicleRegistrationNumber;
    }

    public LocalDateTime getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return this.updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Driver() {
    }

    public Driver(User user, String licenseNumber, String vehicleDetails, String vehicleRegistrationNumber, Float rating, String phoneNumber, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.user = user;
        this.phoneNumber = phoneNumber;
        this.licenseNumber = licenseNumber;
        this.vehicleDetails = vehicleDetails;
        this.vehicleRegistrationNumber = vehicleRegistrationNumber;
        this.rating = rating;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
