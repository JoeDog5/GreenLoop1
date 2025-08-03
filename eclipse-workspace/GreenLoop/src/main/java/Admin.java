public class Admin extends User {

    public Admin(int userId, String name, String email, String passwordHash) {
        super(userId, name, email, passwordHash, Role.ADMIN);
    }

    public boolean approveVendorApplication(int vendorId) {
        // Assume DAO call here to approve in DB
        boolean result = VendorDAO.approveVendorById(vendorId);
        if (result) {
            NotificationService.getInstance().notifyObservers("Vendor ID " + vendorId + " has been approved.");
        }
        return result;
    }

    public void manageUser(int userId, String action) {
        // e.g., "suspend", "activate"
        UserDAO.performActionOnUser(userId, action);
    }

    public void generateReports() {
        // Placeholder for report logic
    }
}