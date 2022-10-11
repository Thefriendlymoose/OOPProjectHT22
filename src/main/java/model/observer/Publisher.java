package model.observer;
import java.util.*;

public class Publisher {

    private final Map<Event, List<Listener>> windows;

    public Publisher() {
        windows = new HashMap<>();
        Arrays.stream(Event.values()).forEach(event -> windows.put(event, new ArrayList<>()));
    }

    public void subscribe(Event eventType, Listener listener) {
        windows.get(eventType).add(listener);
    }

    public void unsubscribe(Event eventType, Listener listener) {
        windows.get(eventType).remove(listener);
    }

    public void notifyCustomers(Event eventType) {
        windows.get(eventType).forEach(listener -> listener.update(eventType));
    }

}
