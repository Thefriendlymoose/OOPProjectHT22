package model.user.strategySort;

import model.user.User;

import java.util.List;

/**
 * An interface for sorting a list of users using the strategy pattern
 *
 */
public interface IStrategySort {

    public void sort(List<User> users);
}
