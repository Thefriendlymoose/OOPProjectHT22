package model.user.strategySort.descending;

import model.user.User;
import model.user.strategySort.IStrategySort;

import java.util.List;

/**
 * A class used when sorting UserID in descending order
 */
public class UserIDSortDescending implements IStrategySort {
    @Override
    public void sort(List<User> users) {
        users.sort((User u1, User u2)-> (int) (u1.getUserId() - u2.getUserId()));
    }
}
