

package exception;

public class OrderProcessingException extends Exception {

    public OrderProcessingException(String message) {
        super(message);
    }

    public OrderProcessingException(String message, Throwable cause) {
        super(message, cause);
    }

    public OrderProcessingException(Throwable cause) {
        super(cause);
    }
}

