package com.greenloop.dao;

import com.greenloop.model.Products;
import com.greenloop.util.DatabaseUtil;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProductDAO {
    private static final Logger LOGGER = Logger.getLogger(ProductDAO.class.getName());
    
    // Get all products for a vendor
    public List<Products> getProductsByVendor(int vendorId) throws SQLException {
        List<Products> products = new ArrayList<>();
        String sql = "SELECT * FROM products WHERE vendor_id = ?";

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, vendorId);
            
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Products product = new Products();
                    // Handle potential null values from database
                    product.setProductId(rs.getInt("product_id"));
                    product.setVendorId(rs.getInt("vendor_id"));
                    
                    // Safe handling for product name
                    String productName = rs.getString("product_name");
                    product.setProductName(productName != null ? productName : "Unknown Product");
                    
                    // Safe handling for other string fields
                    product.setDescription(rs.getString("description"));
                    product.setPrice(rs.getDouble("price"));
                    product.setStockQuantity(rs.getInt("stock_quantity"));
                    
                    String category = rs.getString("category");
                    product.setCategory(category != null ? category : "Uncategorized");
                    
                    product.setImageUrl(rs.getString("image_url"));
                    
                    String certifications = rs.getString("sustainability_certifications");
                    product.setSustainabilityCertifications(certifications != null ? certifications : "None");
                    
                    String metrics = rs.getString("sustainability_metrics");
                    product.setSustainabilityMetrics(metrics != null ? metrics : "Not specified");
                    
                    String status = rs.getString("approval_status");
                    product.setApprovalStatus(status != null ? status : "Pending");
                    
                    products.add(product);
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error retrieving products for vendor " + vendorId, e);
            throw e; // Re-throw after logging
        }
        return products;
    }

    // Example of addProduct method with error handling
    public boolean addProduct(Products product) {
        String sql = "INSERT INTO products (vendor_id, product_name, description, price, "
                   + "stock_quantity, category, image_url, sustainability_certifications, "
                   + "sustainability_metrics) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            stmt.setInt(1, product.getVendorId());
            stmt.setString(2, product.getProductName());
            stmt.setString(3, product.getDescription());
            stmt.setDouble(4, product.getPrice());
            stmt.setInt(5, product.getStockQuantity());
            stmt.setString(6, product.getCategory());
            stmt.setString(7, product.getImageUrl());
            stmt.setString(8, product.getSustainabilityCertifications());
            stmt.setString(9, product.getSustainabilityMetrics());
            
            int affectedRows = stmt.executeUpdate();
            
            if (affectedRows == 0) {
                throw new SQLException("Creating product failed, no rows affected.");
            }
            
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    product.setProductId(generatedKeys.getInt(1));
                    return true;
                } else {
                    throw new SQLException("Creating product failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error adding product: " + product.getProductName(), e);
            return false;
        }
    }
    
    // Similar error-handled methods for updateProduct and deleteProduct would go here
}