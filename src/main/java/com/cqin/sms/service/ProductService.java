package com.cqin.sms.service;
import com.cqin.sms.controller.InvalidInputException;
import com.cqin.sms.model.Product;
import com.cqin.sms.repository.ProductRepository;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class ProductService {
    private final ProductRepository productRepository;

    public ProductService() {
        this.productRepository = new ProductRepository();
    }

    public boolean addProduct(String prodName, String prodCat, String unitPrice, String remStock ) throws RuntimeException {
        prodName = prodName.trim();
        prodCat = prodCat.trim();

        if(prodName.isEmpty() || prodCat.isEmpty() || !isValidPrice(unitPrice) || !isValidRemStock(remStock)) {
            throw new InvalidInputException("Invalid form input.");
        } else {
            return productRepository.saveProduct(new Product(prodName, prodCat, Integer.parseInt(remStock), Double.parseDouble(unitPrice)));
        }
    }

    public Product getProductByID(String prodID)  throws RuntimeException{
        if(!isValidProdID(prodID)) {
            throw new InvalidInputException("Invalid product ID.");
        }
        return productRepository.findProductByID(prodID);
    }

    public List<Product> getProducts() throws RuntimeException{
        return productRepository.findAll();
    }

    public boolean updateProduct(String prodID, String prodName, String prodCat, String unitPrice, String remStock ) throws RuntimeException {
        prodName = prodName.trim();
        prodCat = prodCat.trim();

        if(prodName.isEmpty() || prodCat.isEmpty() || !isValidPrice(unitPrice) || !isValidRemStock(remStock)) {
            throw new InvalidInputException("Invalid form input.");
        } else {
            //prodID is verified as a valid ID before been passed to this function
            return productRepository.updateProduct(new Product(Integer.parseInt(prodID), prodName, prodCat, Integer.parseInt(remStock), Double.parseDouble(unitPrice)));
        }
    }

    private boolean isValidProdID(String prodID) {
        try{
            Integer.parseInt(prodID);
            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }

    private boolean isValidPrice (String price){
        try{
            return Double.parseDouble(price) >= 0;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }

    private boolean isValidRemStock(String remStock) {
        try{
            return Integer.parseInt(remStock) >= 0;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }


}
