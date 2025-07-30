<%@ page import="models.User" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%
    User user = (User) session.getAttribute("user");
    if (user == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>
<html>
<head>
    <title>Dashboard</title>
</head>
<body>
    <h2>Welcome, <%= user.getName() %>!</h2>

    <c:choose>
        <c:when test="${user.role == 'Customer'}">
            <a href="cart">View Cart</a>
        </c:when>
        <c:when test="${user.role == 'Vendor'}">
            <a href="vendor/products">Manage My Products</a>
        </c:when>
        <c:when test="${user.role == 'Admin'}">
            <a href="admin/approve">Approve Products</a>
        </c:when>
    </c:choose>

    <br /><br />
    <a href="logout">Logout</a>
</body>
</html>
