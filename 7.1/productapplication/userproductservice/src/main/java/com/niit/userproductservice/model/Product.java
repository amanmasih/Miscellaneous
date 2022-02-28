package com.niit.userproductservice.model;

public class Product {
    private int productCode;
    private String  productName;
    private String productDescription;
    private String isInStock;

    public Product() {
    }

    public Product(int productCode, String productName, String productDescription, String isInStock) {
        this.productCode = productCode;
        this.productName = productName;
        this.productDescription = productDescription;
        this.isInStock = isInStock;
    }

    public int getProductCode() {
        return productCode;
    }

    public void setProductCode(int productCode) {
        this.productCode = productCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public String getIsInStock() {
        return isInStock;
    }

    public void setIsInStock(String isInStock) {
        this.isInStock = isInStock;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productCode=" + productCode +
                ", productName='" + productName + '\'' +
                ", productDescription='" + productDescription + '\'' +
                ", isInStock='" + isInStock + '\'' +
                '}';
    }
}
