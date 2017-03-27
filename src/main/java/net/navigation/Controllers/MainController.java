package net.navigation.Controllers;

import net.navigation.ExtClasses.*;
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

    @Autowired(required = true)
    @Qualifier(value = "nodeService")
    public void setNodeService(NodeService nodeService) {
        this.nodeService = nodeService;
    }

    @RequestMapping(value = "main", method = RequestMethod.GET)
    public String enter(Model model){
        model.addAttribute("formData", new FormData());
        //model.addAttribute("arc", new Arc());
        return "main";
    }

    @PostConstruct
    public void create_graph() {
        System.out.println("Build action started.");
        try {
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
                int size = nodes.size();
                for (int i = 0; i < size; i++) {
                    if (Integer.parseInt(nodes.get(i).getId())==(a.getStart_node())) {
                        source_index = i;
                    }
                    if (Integer.parseInt(nodes.get(i).getId())==(a.getEnd_node())) {
                        destination_index = i;
                    }

                }

                Edge newEdge = new Edge(Integer.toString(a.getId_arc()),nodes.get(destination_index), nodes.get(source_index), a.getWeight());
                arcs.add(newEdge);
                newEdge = new Edge(Integer.toString(a.getId_arc()),nodes.get(source_index),nodes.get(destination_index) , a.getWeight());
                arcs.add(newEdge);
            }

           /* for(Edge e : arcs){
                System.out.println(e.getId() + " | " + e.getDestination() + " | " + e.getSource() + " | " + e.getWeight());
            }*/

            graph = new Graph(nodes, arcs);
            dijkstraAlgorithm = new DijkstraAlgorithm(graph);
            System.out.println("Graph created");
        } catch (Exception e) {
            System.err.println("Error. Build graph faild.\n" + e.getStackTrace() + "\nMessage: " + e.getMessage() + "\n");
        }

    }

    @RequestMapping(value = "/main/find", method = RequestMethod.POST)
    public String findRoute(@ModelAttribute("formData") FormData formData) {

        List<Integer> svgHighlight = this.nodeService.buildRoute(formData.getFromNode(), formData.getToNode(), nodes, dijkstraAlgorithm);

        return "main";
    }

}
