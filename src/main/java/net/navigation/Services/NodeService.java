package net.navigation.Services;

import net.navigation.Models.Node;

import java.util.List;

public interface NodeService {

    public void addNode(Node node);

    public void updateNode(Node node);

    public void removeNode(int id);

    public List<Node> listNodes();

    public Node findNodeById(int id);

    public Node findNodeByNodeNumber(int node_number, int floor_id);
}
