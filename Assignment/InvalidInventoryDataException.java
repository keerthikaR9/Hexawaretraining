package exception;

public class InvalidInventoryDataException extends Exception {

    public InvalidInventoryDataException() {
        super("Invalid inventory data provided.");
    }

    public InvalidInventoryDataException(String message) {
        super(message);
    }

    public InvalidInventoryDataException(String message, Throwable cause) {
        super(message, cause);
    }
}
