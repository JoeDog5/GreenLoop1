/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.greenloop.servlet;

import com.greenloop.dao.ProductDAO;
import com.greenloop.model.Products;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/vendor/products")
public class VendorProductServlet extends HttpServlet {
    private ProductDAO productDao;

    @Override
    public void init() {
        productDao = new ProductDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        int vendorId = Integer.parseInt(request.getParameter("vendorId"));
        
        try {
            List<Products> products = productDao.getProductsByVendor(vendorId);
            request.setAttribute("products", products);
            request.getRequestDispatcher("/vendorProducts.jsp").forward(request, response);
        } catch (SQLException e) {
            throw new ServletException("Database error", e);
        }
    }
}