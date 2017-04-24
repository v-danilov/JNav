package net.navigation.DAO;

import net.navigation.ExtClasses.Vertex;
import net.navigation.Models.navigation.Node;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
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
        Node node = (Node) session.load(Node.class, new Integer(id));

        if(node!=null){
            session.delete(node);
        }
        logger.info("Node successfully removed. Node details: " + node);
    }

    @Override
    public List<Node> listNodes(){
        Session session = this.sessionFactory.getCurrentSession();
        List<Node> nodeList =(List<Node>) session.createQuery("from Node ").list();

        for(Node node: nodeList){
            logger.info("Node list: " + node);
        }

        return nodeList;
    }

    @Override
    public Node findNodeById(int id){
        Session session =this.sessionFactory.getCurrentSession();
        Node node = (Node) session.load(Node.class, new Integer(id));
        logger.info("Node successfully loaded. Node details: " + node);
        return node;
    }

    @Override
    public Node findNodeByNodeNumber(String node_number, int floor_number, int housing_id){
        Session session =this.sessionFactory.getCurrentSession();

        List<Node> result = session.createQuery("from Node n where n.node_number=:node_number and n.floor_id = (select id_floor from Floor where floor_number = :floor_number and housing_id = :housing_id)")
                .setParameter("node_number",node_number)
                .setParameter("floor_number",floor_number)
                .setParameter("housing_id", housing_id).list();
        Node node = result.get(0);
        if(result.size() > 1){
            logger.info("Warning! To many values: " + result.size());
        }
        logger.info("Node successfully loaded. Node details: " + node);

        return node;
    }

    @Override
    public List<Integer> listSvgId(List<Vertex> vertexList) {
        List<Integer> idList = new ArrayList<Integer>();
        for (Vertex v : vertexList) {
            idList.add(Integer.parseInt(v.getId()));
        }
        Session session = this.sessionFactory.getCurrentSession();
        List<Integer> svgIdList = session.createQuery("select DISTINCT n.svg_id from Node n where n.id_node in (:id)")
                .setParameterList("id", idList).list();

        for (Integer i : svgIdList) {
            System.out.println(i);
            logger.info("svgIdlist: " + i);
        }
        return svgIdList;
    }

}


