package exception;

public class InventoryNotFoundException extends Exception {

    public InventoryNotFoundException() {
        super("Inventory not found.");
    }

    public InventoryNotFoundException(String message) {
        super(message);
    }

    public InventoryNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
