import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VendorDAO {

	// Approve a vendor by setting 'approved' to true
	public static boolean approveVendorById(int vendorId) {
		String sql = "UPDATE vendors SET approved = TRUE WHERE vendor_id = ?";
		try (Connection conn = DBConnection.getConnection();
			PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setInt(1, vendorId);
			return stmt.executeUpdate() > 0;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	// Optional: Reject a vendor (could delete or mark as rejected)
	public static boolean rejectVendorById(int vendorId) {
		String sql = "DELETE FROM vendors WHERE vendor_id = ?";
		try (Connection conn = DBConnection.getConnection();
			PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setInt(1, vendorId);
			return stmt.executeUpdate() > 0;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}	
	
	// Retrieve all vendors who are not yet approved
	public static List<Vendor> getPendingVendors() {
	List<Vendor> vendorList = new ArrayList<>();
	String sql = "SELECT vendor_id, name, email FROM vendors WHERE approved = FALSE";

	try (Connection conn = DBConnection.getConnection();
		PreparedStatement stmt = conn.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery()) {
		while (rs.next()) {
			Vendor vendor = new Vendor();
			vendor.setId(rs.getInt("vendor_id"));
			vendor.setName(rs.getString("name"));
			vendor.setEmail(rs.getString("email"));
			vendorList.add(vendor);
		}
	} catch (Exception e) {
		e.printStackTrace();
		}
		return vendorList;
	}

	public static Vendor getVendorById(int vendorId) {
	    String sql = "SELECT vendor_id, name, email FROM vendors WHERE vendor_id = ?";
	    try (Connection conn = DBConnection.getConnection();
	         PreparedStatement stmt = conn.prepareStatement(sql)) {
	        stmt.setInt(1, vendorId);
	        try (ResultSet rs = stmt.executeQuery()) {
	            if (rs.next()) {
	                Vendor vendor = new Vendor();
	                vendor.setId(rs.getInt("vendor_id"));
	                vendor.setName(rs.getString("name"));
	                vendor.setEmail(rs.getString("email"));
	                return vendor;
	            }
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return null;
	}
}