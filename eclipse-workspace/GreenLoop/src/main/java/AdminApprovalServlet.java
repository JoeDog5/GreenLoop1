import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

	/**
 	* Servlet implementation class AdminApprovalServlet
 	*/
	@WebServlet("/AdminApprovalServlet")

	public class AdminApprovalServlet extends HttpServlet {


		private static final long serialVersionUID = 1L;
		
	    @Override
	    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        int vendorId = Integer.parseInt(request.getParameter("vendorId"));

	        Admin admin = (Admin) request.getSession().getAttribute("loggedInUser");

	        if (admin != null && admin.approveVendorApplication(vendorId)) {
	            request.setAttribute("message", "Vendor approved successfully.");
	        } else {
	            request.setAttribute("error", "Approval failed.");
	        }

	        request.getRequestDispatcher("dashboard.jsp").forward(request, response);
	    }
	}