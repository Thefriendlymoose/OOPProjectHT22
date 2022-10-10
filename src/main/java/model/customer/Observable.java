package model.customer;

public interface Observable {

    void registerObserver(Observer o);
    void unregisterObserver(Observer o);
    void unregisterAll();
    void notifyObservers();
}
