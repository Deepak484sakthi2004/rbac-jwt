package app.deepak.rbac_jwt.service;


import java.util.Iterator;
import java.util.List;
import javax.transaction.Transactional;

import app.deepak.rbac_jwt.exception.ResourceNotFoundException;
import app.deepak.rbac_jwt.model.Driver;
import app.deepak.rbac_jwt.model.DriverReview;
import app.deepak.rbac_jwt.model.Passenger;
import app.deepak.rbac_jwt.model.PassengerReview;
import app.deepak.rbac_jwt.repository.DriverRepository;
import app.deepak.rbac_jwt.repository.DriverReviewRepository;
import app.deepak.rbac_jwt.repository.PassengerRepository;
import app.deepak.rbac_jwt.repository.PassengerReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {
    @Autowired
    private DriverReviewRepository driverReviewRepository;
    @Autowired
    private PassengerReviewRepository passengerReviewRepository;
    @Autowired
    private DriverRepository driverRepository;
    @Autowired
    private PassengerRepository passengerRepository;

    public ReviewService() {
    }

    @PreAuthorize("hasRole('PASSENGER')")
    @Transactional
    public DriverReview addDriverReview(int DriverId, Float rating) {
        Driver driver = driverRepository.findById(DriverId).orElseThrow(() -> {
            return new ResourceNotFoundException("Driver not found");
        });
        DriverReview review = new DriverReview();
        review.setDriver(driver);
        review.setRating(rating);
        driverReviewRepository.save(review);
        updateDriverRating(DriverId);
        return review;
    }

    @PreAuthorize("hasRole('DRIVER')")
    @Transactional
    public PassengerReview addPassengerReview(int PassengerId, Float rating) {
        Passenger passenger = (Passenger)this.passengerRepository.findById(PassengerId).get();
        PassengerReview review = new PassengerReview();
        review.setPassenger(passenger);
        review.setRating(rating);
        this.passengerReviewRepository.save(review);
        this.updatePassengerRating(PassengerId);
        return review;
    }

    private void updateDriverRating(int driverId) {
        Driver driver = (Driver)this.driverRepository.findById(driverId).orElseThrow(() -> {
            return new ResourceNotFoundException("Driver not found");
        });
        List<DriverReview> reviews = this.driverReviewRepository.findByDriver(driver);
        float averageRating = this.calculateAverageRating1(reviews);
        driver.setRating(averageRating);
        this.driverRepository.save(driver);
    }

    private void updatePassengerRating(int passengerId) {
        Passenger passenger = (Passenger)this.passengerRepository.findById(passengerId).orElseThrow(() -> {
            return new ResourceNotFoundException("Passenger not found");
        });
        List<PassengerReview> reviews = this.passengerReviewRepository.findByPassenger(passenger);
        float averageRating = this.calculateAverageRating2(reviews);
        passenger.setRating(averageRating);
        this.passengerRepository.save(passenger);
    }

    private float calculateAverageRating1(List<DriverReview> reviews) {
        float sum = 0.0F;

        DriverReview review;
        for(Iterator var3 = reviews.iterator(); var3.hasNext(); sum += review.getRating()) {
            review = (DriverReview)var3.next();
        }

        return (float)Math.round(sum / (float)reviews.size() * 10.0F) / 10.0F;
    }

    private float calculateAverageRating2(List<PassengerReview> reviews) {
        float sum = 0.0F;

        PassengerReview review;
        for(Iterator var3 = reviews.iterator(); var3.hasNext(); sum += review.getRating()) {
            review = (PassengerReview)var3.next();
        }

        return (float)Math.round(sum / (float)reviews.size() * 10.0F) / 10.0F;
    }
}