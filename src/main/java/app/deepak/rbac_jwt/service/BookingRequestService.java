package app.deepak.rbac_jwt.service;

import java.time.LocalDateTime;
import java.util.Optional;
import javax.management.InstanceNotFoundException;

import app.deepak.rbac_jwt.model.BookingRequest;
import app.deepak.rbac_jwt.model.Passenger;
import app.deepak.rbac_jwt.model.Ride;
import app.deepak.rbac_jwt.repository.PassengerRepository;
import app.deepak.rbac_jwt.repository.RideRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
public class BookingRequestService {
    @Autowired
    private RideService rideService;
    @Autowired
    private RideRepository rideRepository;
    @Autowired
    private PassengerRepository passengerRepository;

    @Autowired
    private Increment increment;

    @Autowired
    RedisTemplate redisTemplate;

    public static final String HASH_KEY = "booking";

    public BookingRequestService() {
    }

    @PreAuthorize("hasRole('PASSENGER')")
    public BookingRequest.BookingStatus getBookingStatus(int bookingRequestId) throws InstanceNotFoundException {
        BookingRequest booking = (BookingRequest) redisTemplate.opsForHash().get(HASH_KEY,String.valueOf(bookingRequestId));
        if (booking!=null) {
            return booking.getStatus();
        } else {
            throw new InstanceNotFoundException("id is incorrect");
        }
    }

    @PreAuthorize("hasRole('PASSENGER')")
    public BookingRequest createBookingRequest(int rideId, int passengerId) {
        Optional<Ride> rideOpt = this.rideRepository.findById(rideId);
        Optional<Passenger> passengerOpt = this.passengerRepository.findById(passengerId);
        if (rideOpt.isPresent() && passengerOpt.isPresent()) {
            Ride ride = (Ride) rideOpt.get();
            Passenger passenger = (Passenger) passengerOpt.get();
            BookingRequest bookingRequest = new BookingRequest();
            bookingRequest.setBookingRequestId(increment.getA());
            increment.setA(increment.getA());
            bookingRequest.setRideId(rideId);
            bookingRequest.setPassengerId(passengerId);
            bookingRequest.setStatus(BookingRequest.BookingStatus.PENDING);
            bookingRequest.setRequestedAt(LocalDateTime.now());
            bookingRequest.setUpdatedAt(LocalDateTime.now());
            redisTemplate.opsForHash().put(HASH_KEY, String.valueOf(bookingRequest.getBookingRequestId()), bookingRequest);
            return bookingRequest;
        } else {
            throw new RuntimeException("Ride or Passenger not found");
        }
    }

    @PreAuthorize("hasRole('DRIVER')")
    public BookingRequest acceptBookingRequest(int bookingRequestId) {
        BookingRequest bookingRequestOpt = (BookingRequest) redisTemplate.opsForHash().get(HASH_KEY,String.valueOf(bookingRequestId));
        if (bookingRequestOpt != null) {
            BookingRequest bookingRequest = bookingRequestOpt;
            bookingRequest.setStatus(BookingRequest.BookingStatus.ACCEPTED);
            bookingRequest.setUpdatedAt(LocalDateTime.now());
            redisTemplate.opsForHash().put(HASH_KEY, String.valueOf(bookingRequest.getBookingRequestId()), bookingRequest);
            this.rideService.addPassengerToRide(bookingRequest.getRideId(), bookingRequest.getPassengerId());
            return bookingRequest;
        } else {
            throw new RuntimeException("Booking request not found");
        }
    }

    @PreAuthorize("hasRole('DRIVER')")
    public BookingRequest rejectBookingRequest(int bookingRequestId) {
        BookingRequest bookingRequestOpt = (BookingRequest) redisTemplate.opsForHash().get(HASH_KEY,String.valueOf(bookingRequestId));
        if (bookingRequestOpt != null) {
            BookingRequest bookingRequest = bookingRequestOpt;
            bookingRequest.setStatus(BookingRequest.BookingStatus.REJECTED);
            bookingRequest.setUpdatedAt(LocalDateTime.now());
            redisTemplate.opsForHash().put(HASH_KEY, String.valueOf(bookingRequest.getBookingRequestId()), bookingRequest);
            return bookingRequest;
        } else {
            throw new RuntimeException("Booking request not found");
        }
    }

    @PreAuthorize("hasRole('PASSENGER')")
    public String deleteBookingRequest(int bookingRequestId) {
        redisTemplate.opsForHash().delete(HASH_KEY,String.valueOf(bookingRequestId));
        return bookingRequestId + " deleted";
    }
}
