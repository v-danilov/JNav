package net.navigation.DAO;

import net.navigation.Models.navigation.Arc;

import org.hibernate.*;

import org.slf4j.Logger;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ArcDaoImpl implements ArcDao {

    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(ArcDaoImpl.class);

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    @Override
    public void addArc(Arc arc){
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(arc);
        logger.info("Arc successfully saved. Arc details: " + arc);
    }

    @Override
    public void updateArc(Arc arc){
        Session session = this.sessionFactory.getCurrentSession();
        session.update(arc);
        logger.info("Arc successfully update. Arc details: " + arc);
    }

    @Override
    public void removeArc(int id){
        Session session = this.sessionFactory.getCurrentSession();
        Arc arc = (Arc) session.load(Arc.class, new Integer(id));
        if(arc!=null){
            session.delete(arc);
        }
        logger.info("Arc successfully removed. Arc details: " + arc);
    }

    @Override
    public Arc findArcByID(int id){
        Session session =this.sessionFactory.getCurrentSession();
        Arc arc = (Arc) session.load(Arc.class, new Integer(id));
        logger.info("Arc successfully loaded. Arc details: " + arc);
        return arc;
    }

    @Override
    public Arc findArcByNodes(int start_node_id, int end_node_id){
        Session session =this.sessionFactory.getCurrentSession();
        List<Arc> result = session.createQuery("from Arc a where a.start_node= :start_node_id AND a.end_node= :end_node_id")
                .setParameter("start_node_id",start_node_id)
                .setParameter("end_node_id",end_node_id).list();
        Arc arc = result.get(0);
        if(result.size() > 1){
            logger.info("Warning! To many values: " + result.size());
        }
        logger.info("Node successfully loaded. Node details: " + arc);
        return arc;
    }

    @Override
    public List<Arc> listArcs(){

        Session session = this.sessionFactory.getCurrentSession();
        List<Arc> arcList = (List<Arc>) session.createQuery("from Arc").list();

        for(Arc arc: arcList){
            logger.info("Arc list: " + arc);
        }

        return arcList;
    }

}
