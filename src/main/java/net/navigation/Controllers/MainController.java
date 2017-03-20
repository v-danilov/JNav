package net.navigation.Controllers;

import net.navigation.ExtClasses.DijkstraAlgorithm;
import net.navigation.ExtClasses.Edge;
import net.navigation.ExtClasses.Graph;
import net.navigation.ExtClasses.Vertex;
import net.navigation.Models.*;
import net.navigation.Services.ArcService;
import net.navigation.Services.NodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Controller
public class MainController {

    private ArcService arcService;
    private NodeService nodeService;
    private Graph graph;
    private List<Vertex> nodes; //Del
    private List<Edge> arcs; //Del
    DijkstraAlgorithm dijkstraAlgorithm;

    @Autowired(required = true)
    @Qualifier(value = "arcService")
    public void setArcService(ArcService arcService) {
        this.arcService = arcService;
    }

    public void setNodeService(NodeService nodeService) {
        this.nodeService = nodeService;
    }

    @RequestMapping(value = "main", method = RequestMethod.GET)
    public String enter(Model model){
        model.addAttribute("arc", new Arc());
        return "main";
    }

   /* @PostConstruct
    public void createGraph(Model model) {
        System.out.println("Build action started.");
        nodes = new ArrayList<Vertex>();
        arcs = new ArrayList<Edge>();
        List<Node> nodeList = this.nodeService.listNodes();
        for (Node n : nodeList) {
            Vertex newVertex = new Vertex(Integer.toString(n.getId_node()), Integer.toString(n.getNode_number()));
            nodes.add(newVertex);
        }

        List<Arc> arcList = this.arcService.listArcs();
        int source_index = 0;
        int destination_index = 0;

        for (Arc a : arcList) {

            //Ищем положение Node в листе в соответсвии с данными дуги
            for (int i = 0; i < nodes.size(); i++) {
                if (nodes.get(i).getId().equals(a.getStart_node())) {
                    source_index = i;
                }
                if (nodes.get(i).getId().equals(a.getEnd_node())) {
                    destination_index = i;
                }

            }

            Edge newEdge = new Edge(Integer.toString(a.getId_arc()), nodes.get(source_index), nodes.get(destination_index), a.getWeight());
            arcs.add(newEdge);
        }

        graph = new Graph(nodes,arcs);
        dijkstraAlgorithm = new DijkstraAlgorithm(graph);


        //Graph = this.arcService.createGraph();
        System.out.println("Graph created");
    }*/

    @RequestMapping(value = "/main/find", method = RequestMethod.POST)
    public String findRoute(@ModelAttribute("arc") Arc arc) {
        System.out.println("Trying to find route");
        dijkstraAlgorithm.execute(nodes.get(0));
        LinkedList<Vertex> path = dijkstraAlgorithm.getPath(nodes.get(10));
        return "redirect:/main";
    }

}
