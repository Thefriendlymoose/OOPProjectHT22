package model.user.strategySort.descending;

import model.user.User;
import model.user.strategySort.IStrategySort;

import java.util.List;

public class FirstNameSortDescending implements IStrategySort {
    @Override
    public void sort(List<User> users) {
        users.sort((User u1, User u2)-> u1.getFirstName(u1.getName()).compareTo(u2.getFirstName(u2.getName())) );
    }
}
