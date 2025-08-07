<%-- 
    Document   : vendorProducts
    Created on : Aug 4, 2025, 12:42:09 PM
    Author     : annen
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <title>Vendor Products</title>
    <style>
        table { width: 100%; border-collapse: collapse; }
        th, td { padding: 8px; text-align: left; border-bottom: 1px solid #ddd; }
        .btn { padding: 5px 10px; text-decoration: none; color: white; border-radius: 3px; }
        .edit-btn { background-color: #4CAF50; }
        .delete-btn { background-color: #f44336; }
    </style>
</head>
<body>
    <h1>My Products</h1>
    <table>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Price</th>
            <th>Stock</th>
            <th>Status</th>
            <th>Actions</th>
        </tr>
        <c:forEach var="product" items="${products}">
            <tr>
                <td>${product.productId}</td>
                <td>${product.productName}</td>
                <td>$${product.price}</td>
                <td>${product.stockQuantity}</td>
                <td>${product.approvalStatus}</td>
                <td>
                    <a href="editProduct?id=${product.productId}" class="btn edit-btn">Edit</a>
                    <a href="deleteProduct?id=${product.productId}" class="btn delete-btn" 
                       onclick="return confirm('Delete this product?')">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>
    <br>
    <a href="addProduct.jsp" class="btn edit-btn">Add New Product</a>
</body>
</html>