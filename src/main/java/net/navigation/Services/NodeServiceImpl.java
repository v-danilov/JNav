package net.navigation.Services;
import net.navigation.DAO.NodeDao;
import net.navigation.Models.Node;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class NodeServiceImpl implements NodeService {

    private NodeDao nodeDao;

    public void setNodeDao(NodeDao nodeDao){this.nodeDao = nodeDao;}


    @Override
    @Transactional
    public void addNode(Node node){this.nodeDao.addNode(node);};

    @Override
    @Transactional
    public void updateNode(Node node){this.nodeDao.updateNode(node);};

    @Override
    @Transactional
    public void removeNode(int id){this.removeNode(id);};

    @Override
    @Transactional
    public List<Node> listNodes(){return this.nodeDao.listNodes();};

    @Override
    @Transactional
    public Node findNodeById(int id){return this.nodeDao.findNodeById(id);}

    @Override
    @Transactional
    public Node findNodeByNodeNumber(int node_number, int floor_id){return this.nodeDao.findNodeByNodeNumber(node_number,floor_id);}
}
