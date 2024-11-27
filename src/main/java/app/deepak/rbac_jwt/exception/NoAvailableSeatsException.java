package app.deepak.rbac_jwt.exception;


public class NoAvailableSeatsException extends RuntimeException {

    public NoAvailableSeatsException(String message) {
        super(message);
    }
}
