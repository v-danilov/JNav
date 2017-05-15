package net.navigation.Controllers;

import com.sun.javafx.sg.prism.NGShape;
import com.sun.org.apache.xpath.internal.operations.Mod;
import net.navigation.Models.navigation.Arc;
import net.navigation.Models.navigation.Node;
import net.navigation.Services.ArcService;
import net.navigation.Services.FloorService;
import net.navigation.Services.NodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import net.navigation.ExtClasses.Circle;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.awt.*;
import java.awt.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

@Controller
public class DataParsingController {

    private NodeService nodeService;
    private FloorService floorService;
    private ArcService arcService;
    private java.util.List<Circle> circles = new ArrayList<Circle>();;

    @Autowired(required = true)
    @Qualifier(value = "nodeService")
    public void setNodeService(NodeService nodeService) {
        this.nodeService = nodeService;
    }

    @Autowired(required = true)
    @Qualifier(value = "floorService")
    public void setFloorService(FloorService floorService) {
        this.floorService = floorService;
    }

    @Autowired(required = true)
    @Qualifier(value = "arcService")
    public void setNodeService(ArcService arcService) {
        this.arcService = arcService;
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add() {
        return "addingpage";
    }

    @RequestMapping(value = "/addnodes", method = RequestMethod.GET)
    public ModelAndView addNodes() {
        FileDialog fd = new FileDialog(new Frame(), "Choose a file", FileDialog.LOAD);
        fd.setDirectory("C:\\");
        fd.setFile("*.svg");
        fd.toFront();
        fd.setVisible(true);
        String filename = fd.getDirectory() + fd.getFile();
        System.out.println(filename);

        fd.dispose();

        circles.addAll(getCirclesList(filename));

        for (Circle cir : circles) {

            java.util.List<String> auditories = cir.getRooms();

            String c_id = cir.getId();
            int housing = Integer.parseInt(c_id.charAt(0) + "");
            int floor = Integer.parseInt(c_id.charAt(1) + "");
            int f_id = floorService.findFloorId(floor, housing);

            for (String aud : auditories) {

                Node addNode = new Node();
                addNode.setFloor_id(f_id);
                addNode.setSvg_id(Integer.parseInt(cir.getId()));

                if (Character.isDigit(aud.charAt(0))) {

                    addNode.setNode_number(aud);
                    addNode.setType(1);


                } else {
                    if (aud.startsWith("t")) {

                        addNode.setType(3);
                        addNode.setNode_number(aud);
                        addNode.setDesc("Toilet");
                        System.out.println(addNode.toString());
                    }
                    if (aud.startsWith("s") || aud.startsWith("l")) {
                        addNode.setNode_number(aud);
                        addNode.setType(2);
                        addNode.setDesc("Transition");

                    }
                }

                //УБРАТЬ КОММЕНТАРИЙ
                nodeService.addNode(addNode);


            }
        }

        return new ModelAndView(new RedirectView("/add", true));
    }

    @RequestMapping(value = "/addarcs", method = RequestMethod.GET)
    public ModelAndView addArcs() {

            for(Circle c : circles){
                int housing = Integer.parseInt(c.getId().charAt(0) + "");
                int floor = Integer.parseInt(c.getId().charAt(1) + "");
                LinkedList<String> rooms_list = new LinkedList<String>();
                rooms_list.addAll(c.getRooms());
                Collections.sort(rooms_list);
                int f_id = floorService.findFloorId(floor, housing);

                //Обрабатываем смежные узлы
                String first_room = rooms_list.get(0);
                rooms_list.remove(0);
                int start_node_id = nodeService.findNodeByNodeNumber(first_room,f_id,housing).getId_node();
                java.util.List<Integer> node_id_list = new ArrayList<Integer>();
                node_id_list.add(start_node_id);

                if(rooms_list.size() >= 1){
                    for(String s : rooms_list){
                        if(s.startsWith("l") || s.startsWith("s") ){
                            //Конечные идентификаторы этажа и корпуса, если не совпадают
                            housing = Integer.parseInt(s.charAt(1) + "");
                            floor = Integer.parseInt(s.charAt(2) + "");
                            f_id = floorService.findFloorId(floor, housing);
                        }
                        int end_node_id = nodeService.findNodeByNodeNumber(s,f_id,housing).getId_node();
                        node_id_list.add(end_node_id);
                        arcService.addArc(new Arc(start_node_id,end_node_id,0,0,0));
                    }
                }

                //Заполняем связи
                String[] next = c.getNext_circle().split(",");
                rooms_list.clear();
                if(next.length == 1){
                    int n = circles.indexOf(new Circle(next[0], null,null,null));

                    //УБРАТЬ, ПОСЛЕ ТОГО КАК ИЗМЕНИТСЯ УЗЕЛ с ID=0
                    if(n != -1){
                        Circle next_circle = circles.get(n);
                        int end_housing = Integer.parseInt(next_circle.getId().charAt(0) + "");
                        int end_floor = Integer.parseInt(next_circle.getId().charAt(1) + "");
                        int end_floor_id = floorService.findFloorId(end_floor, end_housing);
                        rooms_list.addAll(next_circle.getRooms());
                        for(String s : rooms_list){
                            int end_node_id = nodeService.findNodeByNodeNumber(s,end_floor_id,end_housing).getId_node();
                            for(int id : node_id_list){
                                arcService.addArc(new Arc(id,end_node_id,1,0,0));
                            }

                        }
                    }


                }else {
                    for(int i = 0; i < next.length; i++) {
                        int n = circles.indexOf(new Circle(next[i], null, null, null));
                        if (n != -1) {
                            Circle next_circle = circles.get(n);
                            int end_housing = Integer.parseInt(next_circle.getId().charAt(0) + "");
                            int end_floor = Integer.parseInt(next_circle.getId().charAt(1) + "");
                            int end_floor_id = floorService.findFloorId(end_floor, end_housing);
                            rooms_list.addAll(next_circle.getRooms());
                            for (String s : rooms_list) {
                                int end_node_id = nodeService.findNodeByNodeNumber(s, end_floor_id, end_housing).getId_node();
                                for (int id : node_id_list) {
                                    arcService.addArc(new Arc(id, end_node_id, 1, 0, 0));
                                }
                            }
                            rooms_list.clear();
                        }
                    }

                }



            }
        return new ModelAndView(new RedirectView("/add", false));
    }

    private static void parseCircle(String str, Circle c) {
        str = str.trim();
        if (str.startsWith("id") && !str.contains("desc")) {
            String id = str.substring(str.indexOf('"') + 1, str.lastIndexOf('"'));
            c.setId(id);
        }
        /*if(str.trim().startsWith("cx")){
            String cx = str.substring(str.indexOf('"') + 1, str.lastIndexOf('"'));
            c.setCx(cx);
        }
        if(str.trim().startsWith("cy")){
            String cy = str.substring(str.indexOf('"') + 1, str.lastIndexOf('"'));
            c.setCy(cy);
        }*/
        if (str.contains("id=\"desc")) {
            int start_index = str.indexOf(">");
            int end_index = str.indexOf("<");
            String data = str.substring(start_index + 1, end_index);
            String[] params = data.split(";");
            String[] node = params[0].split("-");
            c.setPrev_circle(node[0]);
            c.setNext_circle(node[1]);
            c.setRooms(Arrays.asList(params[1].split("-")));
        }
    }

    private static java.util.List<Circle> getCirclesList(String filename){

        java.util.List<Circle> circles = new ArrayList<Circle>();

        try {
            BufferedReader br = new BufferedReader(new FileReader(filename));
            String tmp;
            while ((tmp = br.readLine()) != null) {
                /*if (tmp.contains("id=\"cabinets\"")) {
                    while(!(tmp = br.readLine()).contains("navigation")){
                        if(tmp.contains("id=\"_f")){
                            String string_with_id= tmp;

                            //Этаж
                            String floor = string_with_id.substring(string_with_id.indexOf('_') + 2,
                                    string_with_id.lastIndexOf('_'));
                            int start_index = string_with_id.lastIndexOf('_') + 1;
                            int end_index = string_with_id.lastIndexOf('"');

                            //Номер
                            String parse_number = string_with_id.substring(start_index, end_index);

                            //Корпус
                            int houdsing = Integer.parseInt(parse_number.charAt(parse_number.length() - 1) + "");

                            String node_number;
                            if (parse_number.contains("a")) {
                                node_number = floor
                                        + parse_number.substring(0, 2) + "/"
                                        + parse_number.charAt(3);
                            } else {
                                node_number = floor + parse_number.substring(0, 2);
                            }
                            String room = node_number + "/" + houdsing;
                            System.out.println(room + " | " + room.matches("[0-9]{3}/[0-9]{1}"));
                            rooms.add(room);

                        }
                    }

                }*/
                if (tmp.contains("id=\"dots\"")) {
                    while (!(tmp = br.readLine()).contains("</g>")) {
                        if (tmp.trim().equals("<circle")) {
                            Circle c = new Circle();
                            while (!(tmp = br.readLine()).contains("</circle")) {
                                parseCircle(tmp, c);
                            }
                            parseCircle(tmp, c);
                            circles.add(c);
                        }
                    }

                }



            }
            return circles;
        }
        catch (Exception e){
            System.out.println(e.getStackTrace() + e.getMessage());
            return null;
        }

    }
}
