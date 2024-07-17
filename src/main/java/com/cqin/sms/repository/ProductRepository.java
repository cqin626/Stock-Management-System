package com.cqin.sms.repository;

import com.cqin.sms.model.Product;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ProductRepository {

    private Connection getConnection() throws SQLException {
        String host = "127.0.0.1";
        int port = 3306;
        String dbName = "smsDB";
        String username = "root";
        String password = "root";

        return DriverManager.getConnection(String.format("jdbc:mysql://%s:%d/%s", host, port, dbName),username,password);
    }

    public boolean saveProduct(Product product) {
        String sql = "INSERT INTO product (product_id, product_name, product_category, remaining_stock, unit_price) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql)){

            preparedStatement.setString(1, product.getProductID());
            preparedStatement.setString(2, product.getProductName());
            preparedStatement.setString(3, product.getProductCategory());
            preparedStatement.setInt(4, product.getRemainingStock());
            preparedStatement.setDouble(5, product.getUnitPrice());

            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("First line got issue");
            return false;
        }
    }
}
