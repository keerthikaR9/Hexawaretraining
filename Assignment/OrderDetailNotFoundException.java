package exception;

public class OrderDetailNotFoundException extends Exception {
    public OrderDetailNotFoundException(String message) {
        super(message);
    }

    public OrderDetailNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
