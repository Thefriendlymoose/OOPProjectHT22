package model.observer;

import static model.observer.Event.EDITED_ITEM;
import static model.observer.Event.NEW_ITEM;

public class TopLevel {

    private final Publisher publisher;

    public TopLevel() {
        publisher = new Publisher();
    }

    public void newItemPromotion() {
        publisher.notifyCustomers(NEW_ITEM);
    }

    public void salePromotion() {
        publisher.notifyCustomers(EDITED_ITEM);
    }

    public Publisher getService() {
        return publisher;
    }

}
