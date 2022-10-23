package model.user.strategySort;

import model.user.User;

import java.util.List;

public class UserIDSortDown implements IStrategySort{
    @Override
    public void sort(List<User> users) {
        users.sort((User u1, User u2)-> (int) (u1.getUserId() - u2.getUserId()));
    }
}
