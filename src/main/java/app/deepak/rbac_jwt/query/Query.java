package app.deepak.rbac_jwt.query;

import app.deepak.rbac_jwt.model.BookingRequest;
import app.deepak.rbac_jwt.model.Driver;
import app.deepak.rbac_jwt.model.Ride;
import app.deepak.rbac_jwt.model.User;
import app.deepak.rbac_jwt.service.BookingRequestService;
import app.deepak.rbac_jwt.service.DriverService;
import app.deepak.rbac_jwt.service.RideService;
import app.deepak.rbac_jwt.service.UserService;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.management.InstanceNotFoundException;
import java.util.List;

@Component
public class Query implements GraphQLQueryResolver {

    @Autowired
    private UserService userService;

    @Autowired
    private DriverService driverService;

    @Autowired
    private RideService rideService;

    @Autowired
    private BookingRequestService bookingRequestService;

    public String demo() {
        return "Hola";
    }


    public List<Ride> getAllRides() {
        return rideService.getAllRides();
    }

    public User getUser(String email) {
        return userService.getUserByEmail(email);
    }

    public Driver getDriver(String email) {
        return driverService.getDriverByEmail(email);
    }
    public List<Ride> getRidesByStatus(Ride.RideStatus status) {
        return rideService.getRidesByStatus(status);
    }
    public BookingRequest.BookingStatus getBookingStatus(int BookingRequestId) throws InstanceNotFoundException {
        return bookingRequestService.getBookingStatus(BookingRequestId);
    }
}
