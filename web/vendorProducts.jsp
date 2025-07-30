<%@ page import="java.util.List, models.Product, models.Vendor" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%
    Vendor vendor = (Vendor) session.getAttribute("user");
    if (vendor == null) {
        response.sendRedirect("../login.jsp");
        return;
    }
    List<Product> products = vendor.getMyProducts();
%>
<html>
<head>
    <title>My Products - GreenLoop</title>
</head>
<body>
    <h2>My Product Listings</h2>

    <table border="1">
        <tr><th>ID</th><th>Name</th><th>Price</th><th>Certification</th></tr>
        <% for (Product p : products) { %>
            <tr>
                <td><%= p.getProductId() %></td>
                <td><%= p.getName() %></td>
                <td><%= p.getPrice() %></td>
                <td><%= p.getCertification() %></td>
            </tr>
        <% } %>
    </table>

    <h3>Add New Product</h3>
    <form action="products" method="post">
        Name: <input type="text" name="name" required /><br />
        Price: <input type="number" name="price" step="0.01" required /><br />
        Certification: <input type="text" name="cert" required /><br />
        <input type="submit" value="Add Product" />
    </form>

    <br /><a href="../dashboard.jsp">Back to Dashboard</a>
</body>
</html>
