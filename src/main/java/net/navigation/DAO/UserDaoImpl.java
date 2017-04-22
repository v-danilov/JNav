package net.navigation.DAO;

import net.navigation.Models.authorization.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    private static final Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);
    private SessionFactory sessionFactory;
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addUser(User user){
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(user);
        logger.info("User successfully saved. User details: " + user);
    }

    @Override
    public void updateUser(User user){
        Session session = this.sessionFactory.getCurrentSession();
        session.update(user);
        logger.info("User successfully update. User details: " + user);
    }

    @Override
    public void removeUser(int id){
        Session session = this.sessionFactory.getCurrentSession();
        User user = (User) session.load(User.class, new Integer(id));

        if(user!=null){
            session.delete(user);
        }
        logger.info("User successfully removed. User details: " + user);
    }

    @Override
    public User getUserByLogin(String log){
        Session session = this.sessionFactory.getCurrentSession();
        User user = (User) session.createQuery("from User where login =:log")
                .setParameter("log", log).uniqueResult();

        return user;
    }

    @Override
    public List<User> listUsers(){
        Session session = this.sessionFactory.getCurrentSession();
        List<User> userList =(List<User>) session.createQuery("from User ").list();

        for(User user: userList){
            logger.info("User list: " + user);
        }

        return userList;
    }
}
