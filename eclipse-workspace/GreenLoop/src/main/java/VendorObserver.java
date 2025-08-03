public class VendorObserver implements NotificationService.Observer {
    @Override
    public void update(String message) {
        System.out.println("Notification: " + message);
        // Optionally send email or log to file
    }
}