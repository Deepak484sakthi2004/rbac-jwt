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
        name = "DriverReview"
)
public class DriverReview {
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
            name = "driver_id",
            nullable = false
    )
    private Driver driver;
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

    public Driver getDriver() {
        return this.driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public Float getRating() {
        return this.rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }

    public DriverReview(Driver driver, Float rating) {
        this.driver = driver;
        this.rating = rating;
    }

    public DriverReview() {
    }
}
