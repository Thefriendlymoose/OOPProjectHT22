package model.user.strategySort.descending;

import model.user.User;
import model.user.strategySort.IStrategySort;

import java.util.List;

/**
 * A class used when sorting FirstName in descending order
 */
public class FirstNameSortDescending implements IStrategySort {
    /**
     * Sorts the users by the firstName in a descending order
     * @param users to be sorted
     */
    @Override
    public void sort(List<User> users) {
        users.sort((User u1, User u2)-> u1.getFirstName(u1.getName()).compareTo(u2.getFirstName(u2.getName())) );
    }
}
