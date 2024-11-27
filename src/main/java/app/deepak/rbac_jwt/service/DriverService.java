

package app.deepak.rbac_jwt.service;

import java.time.LocalDateTime;
import java.util.Optional;
import javax.transaction.Transactional;

import app.deepak.rbac_jwt.model.Driver;
import app.deepak.rbac_jwt.model.User;
import app.deepak.rbac_jwt.repository.DriverRepository;
import app.deepak.rbac_jwt.repository.UserRepository;
import app.deepak.rbac_jwt.request.CreateDriverRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
public class DriverService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    DriverRepository driverRepository;

    public DriverService() {
    }


    @PreAuthorize("hasRole('DRIVER')")
    public Driver getDriverByEmail(String email) {
        Optional<User> user = this.userRepository.findByEmail(email);
        Optional<Driver> driver = this.driverRepository.findByUserUserId(((User)user.get()).getUserId());
        if (driver.isPresent()) {
            return (Driver)driver.get();
        } else {
            throw new RuntimeException("Driver not found with email: " + email);
        }
    }

    @PreAuthorize("hasRole('DRIVER')")
    @Transactional
    public Driver createDriver(CreateDriverRequest createDriverRequest) {
        Optional<User> user = userRepository.findByEmail(createDriverRequest.getUserEmail());
        if (!user.isPresent()) {
            throw new RuntimeException("User not found with email: " + createDriverRequest.getUserEmail());
        } else {
            Optional<Driver> driver1 =driverRepository.findByUserUserId(user.get().getUserId());
            if (driver1.isPresent()) {
                throw new RuntimeException("Driver with email: " + createDriverRequest.getUserEmail() + " already exists");
            } else {
                Float a = 0.0F;
                Driver driver = new Driver();
                driver.setUser((User)user.get());
                driver.setLicenseNumber(createDriverRequest.getLicenseNumber());
                driver.setVehicleDetails(createDriverRequest.getVehicleDetails());
                driver.setVehicleRegistrationNumber(createDriverRequest.getVehicleRegistrationNumber());
                driver.setRating(a);
                driver.setPhoneNumber(createDriverRequest.getPhoneNumber());
                driver.setCreatedAt(LocalDateTime.now());
                driver.setUpdatedAt(LocalDateTime.now());
                return driverRepository.save(driver);
            }
        }
    }

    @PreAuthorize("hasRole('DRIVER')")
    @Transactional
    public String deleteDriverByEmail(String email) {
        Optional<User> user = this.userRepository.findByEmail(email);
        if (!user.isPresent()) {
            throw new RuntimeException("User not found with email: " + email);
        } else {
            Optional<Driver> driver = this.driverRepository.findByUserUserId(((User)user.get()).getUserId());
            if (driver.isPresent()) {
                this.driverRepository.delete((Driver)driver.get());
                return "Driver with " + email + " deleted successfully";
            } else {
                return "User not found with email: " + email;
            }
        }
    }

    @PreAuthorize("hasRole('DRIVER')")
    @Transactional
    public Driver updateDriver(CreateDriverRequest createDriverRequest) {
        Optional<User> user = this.userRepository.findByEmail(createDriverRequest.getUserEmail());
        Optional<Driver> optionalDriver = this.driverRepository.findByUserUserId(((User)user.get()).getUserId());
        if (optionalDriver.isPresent()) {
            Driver driver = (Driver)optionalDriver.get();
            if (createDriverRequest.getLicenseNumber() != null) {
                driver.setLicenseNumber(createDriverRequest.getLicenseNumber());
            }

            if (createDriverRequest.getVehicleDetails() != null) {
                driver.setVehicleDetails(createDriverRequest.getVehicleDetails());
            }

            if (createDriverRequest.getVehicleRegistrationNumber() != null) {
                driver.setVehicleRegistrationNumber(createDriverRequest.getVehicleRegistrationNumber());
            }

            driver.setUpdatedAt(LocalDateTime.now());
            return (Driver)this.driverRepository.save(driver);
        } else {
            throw new RuntimeException("Driver with email " + createDriverRequest.getUserEmail() + " not found.");
        }
    }
}
