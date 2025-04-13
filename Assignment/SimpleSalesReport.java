package util;

public class SimpleSalesReport {
    private double totalSales;
    private int totalOrders;
    private String mostSoldProduct;

    
    public SimpleSalesReport(double totalSales, int totalOrders, String mostSoldProduct) {
        this.totalSales = totalSales;
        this.totalOrders = totalOrders;
        this.mostSoldProduct = mostSoldProduct;
    }

    // Getter methods
    public double getTotalSales() {
        return totalSales;
    }

    public int getTotalOrders() {
        return totalOrders;
    }

    public String getMostSoldProduct() {
        return mostSoldProduct;
    }


    @Override
    public String toString() {
        return "Total Sales: " + totalSales + "\n" +
               "Total Orders: " + totalOrders + "\n" +
               "Most Sold Product: " + mostSoldProduct;
    }
}

