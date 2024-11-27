package app.deepak.rbac_jwt.mutation;



import app.deepak.rbac_jwt.model.*;
import app.deepak.rbac_jwt.request.CreateDriverRequest;
import app.deepak.rbac_jwt.request.CreateRideRequest;
import app.deepak.rbac_jwt.request.CreateUserRequest;
import app.deepak.rbac_jwt.service.*;
import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Mutation implements GraphQLMutationResolver {
    @Autowired
    UserService userService;
    @Autowired
    DriverService driverService;
    @Autowired
    RideService rideService;
    @Autowired
    BookingRequestService bookingRequestService;
    @Autowired
    ReviewService reviewService;

    public Mutation() {
    }

    public User updateUser(CreateUserRequest createUserRequest) {
        return this.userService.updateUser(createUserRequest);
    }

    public String deleteUser(String email) {
        return this.userService.deleteUser(email);
    }

    public Driver createDriver(CreateDriverRequest createDriverRequest) {
        return this.driverService.createDriver(createDriverRequest);
    }

    public String deleteDriver(String email) {
        return this.driverService.deleteDriverByEmail(email);
    }

    public Driver updateDriver(CreateDriverRequest createDriverRequest) {
        return this.driverService.updateDriver(createDriverRequest);
    }

    public Ride createRide(CreateRideRequest createRideRequest) {
        return this.rideService.createRide(createRideRequest);
    }

    public String addPassenger(int RideId, int PassengerId) {
        return this.rideService.addPassengerToRide(RideId, PassengerId);
    }

    public Passenger createPassenger(String email, String phoneNumber) {
        return this.rideService.createPassenger(email, phoneNumber);
    }

    public String cancelRide(int rideId) {
        return this.rideService.cancelRide(rideId);
    }

    public String deleteBookingRequest(int BookingRequestId) {
        return this.bookingRequestService.deleteBookingRequest(BookingRequestId);
    }

    public BookingRequest createBookingRequest(int rideId, int PassengerId) {
        return this.bookingRequestService.createBookingRequest(rideId, PassengerId);
    }

    public BookingRequest acceptBookingRequest(int BookingRequestId) {
        return this.bookingRequestService.acceptBookingRequest(BookingRequestId);
    }

    public BookingRequest rejectBookingRequest(int BookingRequestId) {
        return this.bookingRequestService.rejectBookingRequest(BookingRequestId);
    }

    public PassengerReview createPassengerReview(int PassengerId, float rating) {
        return this.reviewService.addPassengerReview(PassengerId, rating);
    }

    public DriverReview createDriverReview(int DriverId, float rating) {
        return this.reviewService.addDriverReview(DriverId, rating);
    }
}
