package entity;

public class Sale {
    private int orderId;
    private String productName;
    private int quantity;
    private double totalAmount;
    private String saleDate;

    public Sale(int orderId, String productName, int quantity, double totalAmount, String saleDate) {
        this.orderId = orderId;
        this.productName = productName;
        this.quantity = quantity;
        this.totalAmount = totalAmount;
        this.saleDate = saleDate;
    }

    @Override
    public String toString() {
        return "Sale{" +
               "OrderID=" + orderId +
               ", Product='" + productName + '\'' +
               ", Quantity=" + quantity +
               ", Total=$" + totalAmount +
               ", Date='" + saleDate + '\'' +
               '}';
    }
}
