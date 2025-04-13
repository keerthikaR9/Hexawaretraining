package exception;

public class DatabaseConnectionException extends Exception {
    
    // Constructor with message
    public DatabaseConnectionException(String message) {
        super(message);
    }

    // Constructor with message and cause (optional but helpful)
    public DatabaseConnectionException(String message, Throwable cause) {
        super(message, cause);
    }
}

