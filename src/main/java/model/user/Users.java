package model.user;

import model.article.ArticleStatus;
import model.observer.Observable;
import model.observer.Observer;
import model.order.Order;
import model.order.OrderStatus;
import model.site.Site;

import java.util.*;

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
    private int getIndexByName(String name){
        int i = 0;
        for (User user : getAllUsers()){
            if (Objects.equals(user.getName(), name)){
                return i;
            }
            i++;
        }
        // Scuffed will change
        return -2;
    }


    // Extremly scuffed sort
    /*
    public void sortByName(){
        List<String> allNames = new ArrayList<>();
        System.out.println(getAllUsers().size());

        for (User user : getAllUsers()){
            allNames.add(user.getName());
        }
        System.out.println(allNames.size());
        List<User> allUsers = new ArrayList<>();
        Map<Long,User> newOrderedMap = new HashMap<>();

        allNames.sort(Comparator.naturalOrder());

        for (int i = 0; i <getAllUsers().size(); i++){
            int index =  getIndexByName(allNames.get(i));
            allUsers.add(getAllUsers().get(index));
            System.out.println(allUsers.get(i).getName());
            i++;
        }
        System.out.println(allUsers.size());
        for (int i = 0; i < getAllUsers().size(); i++){
            System.out.println(getAllUsers().get(i).getName());
        }

        System.out.println(String.valueOf(allUsers.size()));
        for (User user : allUsers){
            newOrderedMap.put(user.getUserId(),user);
        }

        System.out.println(allNames);
    }

     */


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
