package app.deepak.rbac_jwt.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(
        name = "PassengerReview"
)
public class PassengerReview {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    @Column(
            name = "id"
    )
    private int id;
    @ManyToOne
    @JoinColumn(
            name = "passenger_id",
            nullable = false
    )
    private Passenger passenger;
    @Column(
            name = "rating"
    )
    private Float rating;

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Passenger getPassenger() {
        return this.passenger;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }

    public Float getRating() {
        return this.rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }

    public PassengerReview(Passenger passenger, Float rating) {
        this.passenger = passenger;
        this.rating = rating;
    }

    public PassengerReview() {
    }
}