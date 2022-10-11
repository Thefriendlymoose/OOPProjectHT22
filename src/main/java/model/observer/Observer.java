package model.observer;

public interface Observer {
    public void subscribe(Event eventType, Observable obs);

    public void unsubscribe(Event eventType, Observable obs);

    public void notifyAll(Event eventType);
}
