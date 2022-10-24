package model.user.strategySort.ascending;

import model.user.User;
import model.user.strategySort.IStrategySort;

import java.util.List;

public class FirstNameSortAscending implements IStrategySort {

    @Override
    public void sort(List<User> users) {
        users.sort((User u1, User u2)-> u2.getFirstName(u2.getName()).compareTo(u1.getFirstName(u1.getName())) );
    }
}
