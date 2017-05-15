package net.navigation.Services;
import net.navigation.DAO.NodeDao;
import net.navigation.ExtClasses.DijkstraAlgorithm;
import net.navigation.ExtClasses.Vertex;
import net.navigation.Models.navigation.Node;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;


@Service
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
    public Node findNodeByNodeNumber(String node_number, int floor_number, int housing_id) {
        //System.out.println(node_number + " | " + floor_number + " | " + housing_id);
        return this.nodeDao.findNodeByNodeNumber(node_number, floor_number, housing_id);
    }

    @Override
    @Transactional
    public List<Integer> listSvgId(List<Vertex> vertexList) {
        return this.nodeDao.listSvgId(vertexList);
    }

    @Override
    @Transactional
    public List<Integer> buildRoute(String from, String to, List<Vertex> nodes, DijkstraAlgorithm dijkstraAlgorithm) {
        System.out.println("Trying to find route");
        System.out.println("From: " + from + " | To: " + to);

        int split_index = from.lastIndexOf("/");
        int fromHousingId = Integer.parseInt(from.substring(split_index + 1));
        String fromNodeNumber = from.substring(0,split_index);
        int fromFloorId = Integer.parseInt(fromNodeNumber.substring(0, 1));


        split_index = to.lastIndexOf("/");
        int toHousingId = Integer.parseInt(to.substring(split_index + 1));
        String toNodeNumber = to.substring(0,split_index);
        int toFloorId = Integer.parseInt(toNodeNumber.substring(0, 1));

        /*String[] toParse = to.split("/");
        String toNodeNumber = toParse[0];
        int toFloorId = Integer.parseInt(toParse[0].substring(0, 1));
        int toHousingId = Integer.parseInt(toParse[1]);*/

        Node fromNode = this.nodeDao.findNodeByNodeNumber(fromNodeNumber, fromFloorId, fromHousingId);
        Node toNode = this.nodeDao.findNodeByNodeNumber(toNodeNumber, toFloorId, toHousingId);


        int size = nodes.size();
        int start_index = 0;
        int end_index = 0;
        for (int i = 0; i < size; i++) {
            if (Integer.parseInt(nodes.get(i).getId()) == fromNode.getId_node()) {
                start_index = i;
            }
            if (Integer.parseInt(nodes.get(i).getId()) == toNode.getId_node()) {
                end_index = i;
            }
        }
        System.out.println("From: " + start_index + " | To: " + end_index);
        dijkstraAlgorithm.execute(nodes.get(start_index));
        LinkedList<Vertex> path = dijkstraAlgorithm.getPath(nodes.get(end_index));

        for (Vertex v : path) {
            System.out.println(v);
        }

        return this.nodeDao.listSvgId(path);
    }

}
