package dao;

import java.sql.SQLException;
import java.util.List;

import entity.Sale;
import exception.DatabaseConnectionException;

public interface ReportDAO {
    double getTotalSales() throws SQLException;
    int getTotalOrders() throws SQLException;
    String getMostSoldProduct() throws SQLException;
    List<Sale> generateSalesReport(String startDate, String endDate) throws DatabaseConnectionException;

   

}

