package model.user;


import model.observer.Observable;
import model.observer.Observer;

import java.util.*;

/***
 * A collection of Users
 */

public class Users implements Observable {

    private Map<Long, User> users;
    private Long nextUserID;
    private List<Observer> observers;

    /**
     * Constructor of users
     * @param users Takes a Map of userIds and users
     */

    public Users(Map<Long, User> users){
        this.users = users;
        if (users.isEmpty()){
            this.nextUserID = 1L;
        } else {
            nextUserID = Collections.max(users.keySet()) + 1;
        }
        observers = new ArrayList<>();
    }


    /**
     *
     * @return all the users from the users map
     */
    public List<User> getAllUsers(){
        return new ArrayList<>(users.values());
    }


    /**
     * Adds a user to users and increments userID by one
     * @param user to be added to users
     */
    public void addUser(User user){
        users.put(user.getUserId(), user);
        nextUserID++;
        notifyObservers();
    }

    /**
     * removes a user from users
     * @param user the user to be removed
     */
    public void removeUser(User user){
        users.remove(user.getUserId());
        notifyObservers(); }

    public Long getNextUserID(){
        return nextUserID;
    }

    public User getUserById(Long id) {
        return users.get(id);
    }

    public List<User> getInList() {
        return new ArrayList<>(users.values());
    }

    public void updateUser(){
        notifyObservers();
    }

    /**
     * Gets a user based on the given usersName
     * @param userName the user Name to be searched with
     * @return the user found
     */
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
