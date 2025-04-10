package exception;

public class VehicleNotFoundException extends Exception {
	private static final long serialVersionUID = 1L;

    public VehicleNotFoundException(String message) {
        super(message);
    }

    public VehicleNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}

