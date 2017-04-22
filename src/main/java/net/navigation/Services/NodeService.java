package net.navigation.Services;

import net.navigation.ExtClasses.DijkstraAlgorithm;
import net.navigation.ExtClasses.Vertex;
import net.navigation.Models.navigation.Node;

import java.util.List;

public interface NodeService {

    public void addNode(Node node);

    public void updateNode(Node node);

    public void removeNode(int id);

    public List<Node> listNodes();

    public List<Integer> listSvgId(List<Vertex> vertexList);

    public Node findNodeById(int id);

    public Node findNodeByNodeNumber(int node_number, int floor_number, int housing_id);

    public List<Integer> buildRoute(String from, String to, List<Vertex> nodes, DijkstraAlgorithm dijkstraAlgorithm);
}
