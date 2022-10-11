//package model.observer;
//
//public class TempMain {
//
//
//    public static void main(String[] args) {
//
//        TopLevel topLevel = new TopLevel();
//        topLevel.getService().subscribe(Event.NEW_ITEM, new ConcreteSubscriber("sites"));
//        topLevel.getService().subscribe(Event.EDITED_ITEM, new ConcreteSubscriber("articles"));
//        topLevel.getService().subscribe(Event.EDITED_USER, new ConcreteSubscriber("users"));
//        topLevel.getService().subscribe(Event.EDITED_ITEM, new ConcreteSubscriber("orders"));
//
//        topLevel.newItemPromotion();
//
//        System.out.println("==========================================");
//
//        topLevel.salePromotion();
//
//        System.out.println("==========================================");
//
//        topLevel.getService().unsubscribe(Event.EDITED_USER, new ConcreteSubscriber("users"));
//        topLevel.salePromotion();
//
//    }
//
//}
