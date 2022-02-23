package com.niit.product.Model;

public class ProductDescription {

    private String productCode;
    private String productColor;
    private String productType;
    private String inStock;



    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductColor() {
        return productColor;
    }

    public void setProductColor(String productColor) {
        this.productColor = productColor;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getInStock() {
        return inStock;
    }

    public void setInStock(String inStock) {
        this.inStock = inStock;
    }


    @Override
    public String toString() {
        return "ProductDescription{" +
                "productCode='" + productCode + '\'' +
                ", productColor='" + productColor + '\'' +
                ", productType='" + productType + '\'' +
                ", inStock='" + inStock + '\'' +
                '}';
    }
}
