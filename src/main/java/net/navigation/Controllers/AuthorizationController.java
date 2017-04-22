package net.navigation.Controllers;

import net.navigation.ExtClasses.Circle;
import net.navigation.ExtClasses.FormData;
import net.navigation.Models.authorization.User;
import net.navigation.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.awt.*;
import java.awt.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

@Controller
public class AuthorizationController {

    private UserService userService;

    @Autowired(required = true)
    @Qualifier(value = "userService")
    public void setArcService(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String loin(ModelMap modelMap) {
        modelMap.put("user", new User());
        return "login";
    }

    @RequestMapping(value = "login/auth", method = RequestMethod.POST)
    public String authorization(@ModelAttribute("user") User user) {
        boolean b = userService.validateUser(user.getLogin(), user.getPass());
        System.out.println(b);

        FileDialog fd = new FileDialog(new Frame(), "Choose a file", FileDialog.LOAD);
        fd.setDirectory("C:\\");
        fd.setFile("*.svg");
        fd.setVisible(true);
        String filename = fd.getDirectory() + fd.getFile();
        System.out.println(filename);

        java.util.List<String> rooms = new ArrayList<String>();
        java.util.List<Circle> circles = new ArrayList<Circle>();

        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            /*System.out.println("Введите № этажа");
            String floor = br.readLine();*/

            br = new BufferedReader(new FileReader(filename));
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
            //Collections.sort(rooms);
            //System.out.println(rooms);


            for (Circle cir : circles) {
                System.out.println(cir);
                /*List<String> roomsList = cir.getRooms();
                int size = roomsList.size();
                for(String r : roomsList){
                    System.out.println(r + "|" + "floorID" + "|" + "1" + "|" + cir.getId());
                }*/

            }
            // System.out.println(circles);
        } catch (IOException e) {
            System.err.println(e.getMessage() + e.getStackTrace());
        }

        return "login";
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

}
