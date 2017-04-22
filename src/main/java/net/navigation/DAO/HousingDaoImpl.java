package net.navigation.DAO;


import net.navigation.Models.navigation.Housing;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;

import java.util.List;

public class HousingDaoImpl implements HousingDao {

    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(ArcDaoImpl.class);

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addHousing(Housing housing){
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(housing);
        logger.info("Housing successfully saved. Housing details: " + housing);
    }

    @Override
    public void updateHousing(Housing housing){
        Session session = this.sessionFactory.getCurrentSession();
        session.update(housing);
        logger.info("Housing successfully update. Housing details: " + housing);
    }

    @Override
    public void removeHousing(int id){
        Session session = this.sessionFactory.getCurrentSession();
        Housing housing = (Housing) session.load(Housing.class, new Integer(id));
        if(housing!=null){
            session.delete(housing);
        }
        logger.info("Housing successfully removed. Housing details: " + housing);
    }

    @Override
    public List<Housing> listHousings(){

        Session session = this.sessionFactory.getCurrentSession();
        List<Housing> housingList = session.createQuery("from Housing").list();

        for(Housing housing: housingList){
            logger.info("Housing list: " + housing);
        }

        return housingList;
    }
}
