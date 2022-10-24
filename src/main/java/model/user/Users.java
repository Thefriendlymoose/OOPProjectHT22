package model.user;


import model.observer.Observable;
import model.observer.Observer;

import java.util.*;

public class Users implements Observable {


    private Map<Long, User> users;
    private Long nextUserID;
    private List<Observer> observers;

    public Users(Map<Long, User> users){
        this.users = users;
        if (users.isEmpty()){
            this.nextUserID = 1L;
        } else {
            nextUserID = Collections.max(users.keySet()) + 1;
        }
        observers = new ArrayList<>();
    }


    public List<User> getAllUsers(){
        return new ArrayList<>(users.values());
    }


    public void addUser(User user){
        if (isUserOK(user))
            return;
        users.put(user.getUserId(), user);
        nextUserID++;
        notifyObservers();
    }
    public Boolean isUserOK(User user){
        for(User u : getAllUsers()){
            if (u.getUserName() == user.getUserName())
                return true;
        }


        return false;
    }




    public void removeUser(User user){
        users.remove(user.getUserId());
        notifyObservers(); }

    public Long getNextUserID(){
        return nextUserID;
    }

    public Permission[] getPermissions(){
        return Permission.values();
    }

    public List<User> getInList() {
        return new ArrayList<>(users.values());
    }

    public void updateUser(){
        notifyObservers();
    }

    public User returnUserByUsername(String userName){
        for (User user : users.values()){
            if (user.getUserName().equals(userName)){
                return user;
            }
        }
        return null;
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
