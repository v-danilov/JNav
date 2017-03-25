package net.navigation.DAO;

import net.navigation.Models.Floor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;

import java.util.List;

public class FloorDaoImpl implements FloorDao {

    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(FloorDaoImpl.class);

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addFloor(Floor floor){
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(floor);
        logger.info("Floor successfully saved. Floor details: " + floor);
    }

    @Override
    public void updateFloor(Floor floor){
        Session session = this.sessionFactory.getCurrentSession();
        session.update(floor);
        logger.info("Floor successfully update. Floor details: " + floor);
    }

    @Override
    public void removeFloor(int id){
        Session session = this.sessionFactory.getCurrentSession();
        Floor floor = (Floor) session.load(Floor.class, new Integer(id));
        if(floor!=null){
            session.delete(floor);
        }
        logger.info("Floor successfully removed. Floor details: " + floor);
    }

    @Override
    public List<Floor> listFloors(){

        Session session = this.sessionFactory.getCurrentSession();
        List<Floor> floorList = session.createQuery("from Floor").list();

        for(Floor floor: floorList){
            logger.info("Floor list: " + floor);
        }

        return floorList;
    }
}
