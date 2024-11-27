
package app.deepak.rbac_jwt.request;

public class CreateDriverRequest {
    private String userEmail;
    private String licenseNumber;
    private String vehicleDetails;
    private String vehicleRegistrationNumber;
    private String phoneNumber;

    public CreateDriverRequest() {
    }

    public CreateDriverRequest(String userEmail, String licenseNumber, String vehicleDetails, String vehicleRegistrationNumber, String phoneNumber) {
        this.userEmail = userEmail;
        this.licenseNumber = licenseNumber;
        this.phoneNumber = phoneNumber;
        this.vehicleDetails = vehicleDetails;
        this.vehicleRegistrationNumber = vehicleRegistrationNumber;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getUserEmail() {
        return this.userEmail;
    }

    public void setUserId(String userEmail) {
        this.userEmail = userEmail;
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
}
