public class Admin extends User {

    public Admin(int userId, String name, String email, String passwordHash) {
        super(userId, name, email, passwordHash, Role.ADMIN);
    }

    public boolean approveVendorApplication(int vendorId) {
        boolean result = VendorDAO.approveVendorById(vendorId);
        if (result) {
            NotificationService.getInstance().notifyObservers("Vendor ID " + vendorId + " has been approved.");
        }
        return result;
    }

    public void updateUserRole(int userId, boolean makeAdmin) {
        Role newRole = makeAdmin ? Role.ADMIN : Role.USER;
        UserDAO.updateUserRole(userId, newRole);
        System.out.printf("User ID %d has been assigned role: %s%n", userId, newRole);
    }

    public void generateReports() {
    	generateUserActivityReport();
        generateSalesSummaryReport();
    }

    private void generateUserActivityReport() {
        System.out.println("\n--- User Activity ---");
        List<UserActivity> activities = UserDAO.getUserActivity();

        if (activities.isEmpty()) {
            System.out.println("No user activity found.");
            return;
        }

        for (UserActivity activity : activities) {
            String roleLabel = activity.getRole().toString();
            System.out.printf("[%s] %s (ID: %d) performed action: \"%s\" at %s%n",
                    roleLabel,
                    activity.getUserName(),
                    activity.getUserId(),
                    activity.getAction(),
                    activity.getTimestamp().toString());
        }
    }
    
    private void generateSalesSummaryReport() {
        System.out.println("\n--- Sales Summary ---");

        SalesSummary summary = SalesDAO.getSalesSummary();

        if (summary == null) {
            System.out.println("No sales data available.");
            return;
        }

        System.out.println("Total Revenue: $" + String.format("%.2f", summary.getTotalRevenue()));
        System.out.println("Total Orders: " + summary.getTotalOrders());

        System.out.println("Top-Selling Products:");
        for (ProductSale ps : summary.getTopSellingProducts()) {
            System.out.printf("- %s (Sold: %d)\n", ps.getProductName(), ps.getQuantitySold());
        }
    }

    
}