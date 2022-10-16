package model.user;

import model.observer.Observable;
import model.observer.Observer;
import model.order.Order;
import model.order.OrderStatus;
import model.site.Site;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class Users implements Observable {


    private Map<Long, User> users;
    private Long nextUserID;
    private List<Observer> observers;

    public Users(Map<Long, User> users){
        this.users = users;
        nextUserID = Collections.max(users.keySet()) + 1;
        observers = new ArrayList<>();
    }


    public List<User> getAllUsers(){
        return new ArrayList<>(users.values());
    }


    public void addUser(User user){
        users.put(user.getUserId(), user);
        nextUserID++;
        notifyObservers();
    }

    public void removeUser(User user){
        users.remove(user.getUserId());
        notifyObservers(); }

    public Long getNextUserID(){
        return nextUserID;
    }

    public void updateUser(){
        notifyObservers();
    }


    @Override
    public void registerObserver(Observer o) {observers.add(o);

    }

    @Override
    public void unregisterObserver(Observer o) { observers.remove(o);

    }

    @Override
    public void unregisterAll() { observers = new ArrayList<>();

    }

    @Override
    public void notifyObservers() {
        for (Observer o : observers){
            o.update();
        }
    }

}
