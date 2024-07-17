package com.cqin.sms.service;

import com.cqin.sms.model.Product;
import com.cqin.sms.repository.ProductRepository;

public class ProductService {
    private final ProductRepository productRepository;

    public ProductService() {
        this.productRepository = new ProductRepository();
    }

    public boolean addProduct(String prodId, String prodName, String prodCategory, String unitPrice, int remStock) {
        double prodPrice;
        if(!prodId.isEmpty() || !prodName.isEmpty() || !prodCategory.isEmpty() || !unitPrice.isEmpty()) {
            try{
                prodPrice = Double.parseDouble(unitPrice);
            }
            catch (NumberFormatException nfe) {
                return false;
            }
            return productRepository.saveProduct(new Product(prodId, prodName,prodCategory,remStock,prodPrice));
        }
        return false;
    }
}
