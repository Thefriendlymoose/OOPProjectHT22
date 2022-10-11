package model.observer;

//just an example

public class ConcreteSubscriber implements Listener{

    private String window;

    public ConcreteSubscriber(String window) {
        this.window = window;
    }

    @Override
    public void update(Event eventType) {
        // Actually send the push notification to username
        System.out.println("Notifying regarding" + eventType);
    }
}
