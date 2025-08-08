package com.greenloop.model;

public class Products {
    private int productId;
    private int vendorId;
    private String productName;
    private String description;
    private double price;
    private int stockQuantity;
    private String category;
    private String imageUrl;
    private String sustainabilityCertifications;
    private String sustainabilityMetrics;
    private String approvalStatus;

    // Default constructor
    public Products() {
        this.productName = "Unknown Product";
        this.category = "Uncategorized";
        this.sustainabilityCertifications = "None";
        this.sustainabilityMetrics = "Not specified";
        this.approvalStatus = "Pending";
    }

    // Getters and Setters with basic validation
    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getVendorId() {
        return vendorId;
    }

    public void setVendorId(int vendorId) {
        this.vendorId = vendorId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = (productName != null && !productName.trim().isEmpty()) ? 
                          productName : "Unknown Product";
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price > 0 ? price : 0.0;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity >= 0 ? stockQuantity : 0;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = (category != null && !category.trim().isEmpty()) ? 
                        category : "Uncategorized";
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getSustainabilityCertifications() {
        return sustainabilityCertifications;
    }

    public void setSustainabilityCertifications(String sustainabilityCertifications) {
        this.sustainabilityCertifications = (sustainabilityCertifications != null) ? 
                                          sustainabilityCertifications : "None";
    }

    public String getSustainabilityMetrics() {
        return sustainabilityMetrics;
    }

    public void setSustainabilityMetrics(String sustainabilityMetrics) {
        this.sustainabilityMetrics = (sustainabilityMetrics != null) ? 
                                   sustainabilityMetrics : "Not specified";
    }

    public String getApprovalStatus() {
        return approvalStatus;
    }

    public void setApprovalStatus(String approvalStatus) {
        this.approvalStatus = (approvalStatus != null) ? 
                             approvalStatus : "Pending";
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", vendorId=" + vendorId +
                ", productName='" + productName + '\'' +
                ", price=" + price +
                ", stockQuantity=" + stockQuantity +
                ", category='" + category + '\'' +
                ", approvalStatus='" + approvalStatus + '\'' +
                '}';
    }
}