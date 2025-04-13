package exception;

public class InvalidOrderDetailException extends Exception {
    public InvalidOrderDetailException(String message) {
        super(message);
    }

    public InvalidOrderDetailException(String message, Throwable cause) {
        super(message, cause);
    }
}
