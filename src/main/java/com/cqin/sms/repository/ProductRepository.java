package com.cqin.sms.repository;

import com.cqin.sms.model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductRepository {

    private Connection getConnectionToDB() throws SQLException {
        String host = "127.0.0.1";
        int port = 3306;
        String dbName = "smsDB";
        String username = "root";
        String password = "root";

        return DriverManager.getConnection(String.format("jdbc:mysql://%s:%d/%s", host, port, dbName),username,password);
    }

    public boolean saveProduct(Product product)  {
        String sql = "INSERT INTO product (product_name, product_category, remaining_stock, unit_price) VALUES (?, ?, ?, ?)";
        try (Connection connection = getConnectionToDB();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)
        ){
            preparedStatement.setString(1, product.getProductName());
            preparedStatement.setString(2, product.getProductCategory());
            preparedStatement.setInt(3, product.getRemainingStock());
            preparedStatement.setDouble(4, product.getUnitPrice());
            return preparedStatement.executeUpdate() > 0;
        } catch(SQLException sqle) {
            throw new RuntimeException("Database encountered an error");
        }
    }

    public Product findProductByID(String prodID) {
        String sql = "SELECT * FROM product WHERE product_id = ?;";
        try (Connection connection = getConnectionToDB();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)
        ) {
            preparedStatement.setString(1, prodID);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) { // Check if there's a result
                return new Product(
                        resultSet.getInt("product_id"),
                        resultSet.getString("product_name"),
                        resultSet.getString("product_category"),
                        resultSet.getInt("remaining_stock"),
                        resultSet.getDouble("unit_price")
                );
            } else {
                return new Product();
            }
        } catch(SQLException sqle) {
            throw new RuntimeException("Database encountered an error");
        }
    }

    public List<Product> findAll() {
        String sql = "SELECT * FROM product";
        try (Connection connection = getConnectionToDB();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)
        ){
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Product> products = new ArrayList<>();
            while(resultSet.next()) {
                products.add(new Product(
                        resultSet.getInt("product_id"),
                        resultSet.getString("product_name"),
                        resultSet.getString("product_category"),
                        resultSet.getInt("remaining_stock"),
                        resultSet.getDouble("unit_price"))
                );
            }
            return products;
        } catch(SQLException sqle) {
            throw new RuntimeException("Database encountered an error.");
        }
    }

    public boolean updateProduct(Product product) {
        String sql = "UPDATE product SET product_name=?, product_category=?, remaining_stock=?, unit_price=? WHERE product_id=?";
        try(Connection connection = getConnectionToDB();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)
        ) {
            preparedStatement.setString(1, product.getProductName());
            preparedStatement.setString(2, product.getProductCategory());
            preparedStatement.setInt(3, product.getRemainingStock());
            preparedStatement.setDouble(4, product.getUnitPrice());
            preparedStatement.setInt(5, product.getProductID());

            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Database encountered an error");
        }
    }

    public boolean deleteProducts(List<Integer> productIDs) {
        String sql = "DELETE FROM product WHERE product_id = ?";
        try(Connection connection = getConnectionToDB();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)
        ) {
            for(int productID : productIDs) {
                preparedStatement.setInt(1, productID);
                preparedStatement.executeUpdate();
            }
            return true;
        } catch(SQLException sqle) {
            throw new RuntimeException("Database encountered an error");
        }
    }
}
