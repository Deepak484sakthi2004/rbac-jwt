package app.deepak.rbac_jwt.model;


import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(
        name = "Passenger"
)
public class Passenger {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    @Column(
            name = "id"
    )
    private int passengerId;
    @OneToOne
    @JoinColumn(
            name = "user_id",
            unique = true
    )
    private User user;
    @Column(
            name = "name"
    )
    private String name;
    @Column(
            name = "email"
    )
    private String email;
    @Column(
            name = "phone_number"
    )
    private String phoneNumber;
    @Column(
            name = "rating"
    )
    private Float rating;
    @ManyToMany(
            mappedBy = "passengers",
            fetch = FetchType.EAGER
    )
    private Set<Ride> rides;

    public Passenger() {
    }

    public Passenger(User user, String name, String email, String phoneNumber, Float rating) {
        this.user = user;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.rating = rating;
    }

    public User getUser() {
        return this.user;
    }

    public Float getRating() {
        return this.rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getPassengerId() {
        return this.passengerId;
    }

    public void setPassengerId(int passengerId) {
        this.passengerId = passengerId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Set<Ride> getRides() {
        return this.rides;
    }

    public void setRides(Set<Ride> rides) {
        this.rides = rides;
    }
}
