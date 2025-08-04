package servlets;

import model.Customer;
import model.Product;
import model.ProductDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/cart")
public class CustomerCartServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Customer customer = (Customer) req.getSession().getAttribute("user");
        req.setAttribute("cart", customer.getShoppingCart());
        req.getRequestDispatcher("/cart.jsp").forward(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Customer customer = (Customer) req.getSession().getAttribute("user");

        int productId = Integer.parseInt(req.getParameter("productId"));
        int qty = Integer.parseInt(req.getParameter("quantity"));

        Product product = ProductDAO.findById(productId);
        customer.getShoppingCart().addItem(product, qty);

        resp.sendRedirect("cart");
    }
}
