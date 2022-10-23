package model.user.strategySort.ascending;

import model.user.User;
import model.user.strategySort.IStrategySort;

import java.util.List;

public class LastNameSortAscending implements IStrategySort {
    @Override
    public void sort(List<User> users) {
        users.sort((User u1, User u2)-> u2.getLastName(u2.getName()).compareTo(u1.getLastName(u1.getName())) );
    }
}
