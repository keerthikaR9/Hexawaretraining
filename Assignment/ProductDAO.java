package dao;

import entity.Product;
import exception.InvalidDataException;
import exception.DatabaseConnectionException;
import exception.InsufficientStockException;

import java.util.List;

public interface ProductDAO {

    void addProduct(Product product) throws InvalidDataException, DatabaseConnectionException;

    Product getProductById(int productID) throws DatabaseConnectionException;

    List<Product> getAllProducts() throws DatabaseConnectionException;

    void updateProduct(Product product) throws InvalidDataException, DatabaseConnectionException;

    void deleteProduct(int productID) throws DatabaseConnectionException;
    List<Product> searchProductsByName(String name) throws DatabaseConnectionException;

    List<Product> searchProductsByCategory(String category) throws DatabaseConnectionException;

    List<Product> getRecommendedProducts() throws DatabaseConnectionException;
    void updateInventory(int productId, int quantity) throws DatabaseConnectionException, InsufficientStockException;

	

}
