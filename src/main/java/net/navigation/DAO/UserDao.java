package net.navigation.DAO;

import net.navigation.Models.authorization.User;
import net.navigation.Models.navigation.Arc;

import java.util.List;

public interface UserDao {

    public void addUser(User user);

    public void updateUser(User auser);

    public void removeUser(int id);

    public User getUserByLogin(String log);

    public List<User> listUsers();
}
