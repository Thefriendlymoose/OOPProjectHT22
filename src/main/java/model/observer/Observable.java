package model.observer;

/**
 * Interface to the facades which follows the observer pattern.
 */
public interface Observable {

    /**
     * Adds an observer (Observer pattern).
     * @param o is the observer which is added.
     */
    void registerObserver(Observer o);

    /**
     * Removes all observers (Observer pattern).
     *
     */
    void unregisterObserver(Observer o);

    /**
     * Removes all observers (Observer pattern).
     *
     */
    void unregisterAll();

    /**
     * Notifies all observers.
     *
     */
    void notifyObservers();
}
