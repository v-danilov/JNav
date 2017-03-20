package net.navigation.DAO;

import net.navigation.Models.Node;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class NodeDaoImpl implements NodeDao {

    private static final Logger logger = LoggerFactory.getLogger(NodeDaoImpl.class);
    private SessionFactory sessionFactory;
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addNode(Node node){
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(node);
        logger.info("Node successfully saved. Node details: " + node);
    }

    @Override
    public void updateNode(Node node){
        Session session = this.sessionFactory.getCurrentSession();
        session.update(node);
        logger.info("Node successfully update. Node details: " + node);
    }

    @Override
    public void removeNode(int id){
        Session session = this.sessionFactory.getCurrentSession();
        Node node = session.load(Node.class, new Integer(id));

        if(node!=null){
            session.delete(node);
        }
        logger.info("Node successfully removed. Node details: " + node);
    }

    @Override
    public List<Node> listNodes(){
        Session session = this.sessionFactory.getCurrentSession();
        List<Node> nodeList = session.createQuery("from Node").list();

        for(Node node: nodeList){
            logger.info("Node list: " + node);
        }

        return nodeList;
    }

    @Override
    public Node findNodeById(int id){
        Session session =this.sessionFactory.getCurrentSession();
        Node node = session.load(Node.class, new Integer(id));
        logger.info("Node successfully loaded. Node details: " + node);
        return node;
    }

    @Override
    public Node findNodeByNodeNumber(int node_number, int floor_id){
        Session session =this.sessionFactory.getCurrentSession();
        List<Node> result = session.createQuery("from Node n where n.node_number= :node_number AND n.floor_id= :floor_id")
                .setParameter("node_number",node_number)
                .setParameter("floor_id",floor_id).list();
        Node node = result.get(0);
        if(result.size() > 1){
            logger.info("Warning! To many values: " + result.size());
        }
        logger.info("Node successfully loaded. Node details: " + node);

        return node;
    }

}
