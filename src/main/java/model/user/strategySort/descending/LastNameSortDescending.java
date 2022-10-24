package model.user.strategySort.descending;

import model.user.User;
import model.user.strategySort.IStrategySort;

import java.util.List;

public class LastNameSortDescending implements IStrategySort {
    @Override
    public void sort(List<User> users) {
        users.sort((User u1, User u2)-> (u1.getLastName(u1.getName())).compareTo(u2.getLastName(u2.getName())) );
    }
}
