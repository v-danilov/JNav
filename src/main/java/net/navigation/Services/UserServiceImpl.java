package net.navigation.Services;

import net.navigation.DAO.UserDao;
import net.navigation.Models.authorization.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private UserDao userDao;


    public void setUserDao(UserDao userDao){this.userDao = userDao;}

    @Override
    @Transactional
    public void addUser(User user){this.userDao.addUser(user);}

    @Override
    @Transactional
    public void updateUser(User user){this.updateUser(user);}

    @Override
    @Transactional
    public void removeUser(int id){this.removeUser(id);}

    @Override
    @Transactional
    public boolean validateUser(String log, String pass){
        User user = this.userDao.getUserByLogin(log);
        if(pass.equals(user.getPass())){
            return  true;
        }
        return false;
    }

    @Override
    @Transactional
    public List<User> listUsers(){return this.userDao.listUsers();}
}
