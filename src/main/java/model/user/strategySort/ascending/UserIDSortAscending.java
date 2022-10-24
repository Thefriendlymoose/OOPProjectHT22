package model.user.strategySort.ascending;

import model.user.User;
import model.user.strategySort.IStrategySort;

import java.util.List;

/**
 * A class used when sorting UserID in ascending order
 */

public class UserIDSortAscending implements IStrategySort {
    @Override
    public void sort(List<User> users) {
        users.sort((User u1, User u2)-> (int) (u2.getUserId() - u1.getUserId()));
    }
}
