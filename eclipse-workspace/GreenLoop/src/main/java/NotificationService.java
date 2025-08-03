import java.util.ArrayList;
import java.util.List;

public class NotificationService {
    private static NotificationService instance;
    private final List<Observer> observers = new ArrayList<>();

    private NotificationService() {}

    public static synchronized NotificationService getInstance() {
        if (instance == null) {
            instance = new NotificationService();
        }
        return instance;
    }

    public void registerObserver(Observer o) {
        observers.add(o);
    }

    public void notifyObservers(String message) {
        for (Observer o : observers) {
            o.update(message);
        }
    }

    public interface Observer {
        void update(String message);
    }
}