<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin Dashboard</title>
</head>
<body>
    <h2>Welcome, Admin</h2>

    <c:if test="${not empty message}">
        <div style="color: green;">${message}</div>
    </c:if>

    <c:if test="${not empty error}">
        <div style="color: red;">${error}</div>
    </c:if>

    <h3>Pending Vendor Applications</h3>
    <table border="1">
        <tr>
            <th>Vendor ID</th>
            <th>Name</th>
            <th>Action</th>
        </tr>
        <c:forEach var="vendor" items="${pendingVendors}">
            <tr>
                <td>${vendor.id}</td>
                <td>${vendor.name}</td>
                <td>
                    <form action="AdminApprovalServlet" method="post">
                        <input type="hidden" name="vendorId" value="${vendor.id}" />
                        <input type="submit" value="Approve" />
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
