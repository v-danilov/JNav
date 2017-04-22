package net.navigation.Services;


import net.navigation.Models.authorization.User;

import java.util.List;

public interface UserService {

    public void addUser(User user);

    public void updateUser(User auser);

    public void removeUser(int id);

    public boolean validateUser(String log, String pass);

    public List<User> listUsers();
}
