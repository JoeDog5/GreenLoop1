<%@ page import="models.Customer, models.CartItem, models.Product, java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%
    Customer customer = (Customer) session.getAttribute("user");
    if (customer == null) {
        response.sendRedirect("login.jsp");
        return;
    }
    List<CartItem> cartItems = customer.getShoppingCart().getItems();
%>
<html>
<head>
    <title>Shopping Cart - GreenLoop</title>
</head>
<body>
    <h2>Your Shopping Cart</h2>

    <% if (cartItems.isEmpty()) { %>
        <p>Your cart is empty.</p>
    <% } else { %>
        <table border="1">
            <tr><th>Product</th><th>Quantity</th><th>Price</th><th>Total</th></tr>
            <% double total = 0;
               for (CartItem item : cartItems) {
                   Product p = item.getProduct();
                   double subtotal = p.getPrice() * item.getQuantity();
                   total += subtotal;
            %>
                <tr>
                    <td><%= p.getName() %></td>
                    <td><%= item.getQuantity() %></td>
                    <td>$<%= p.getPrice() %></td>
                    <td>$<%= subtotal %></td>
                </tr>
            <% } %>
        </table>
        <h3>Total: $<%= total %></h3>
    <% } %>

    <br /><a href="dashboard.jsp">Back to Dashboard</a>
</body>
</html>
