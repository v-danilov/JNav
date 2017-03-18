package net.navigation.Controllers;

import net.navigation.Models.Arc;
import net.navigation.Services.ArcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ArcController{

    private ArcService arcService;
    //private Graph routeGraph;


    @Autowired(required = true)
    @Qualifier(value = "arcService")
    public void setArcService(ArcService arcService){this.arcService = arcService;}

    @RequestMapping(value = "main", method = RequestMethod.GET)
    public String createGraph(Model model){

        //Graph = this.arcService.createGraph();
        return "main";
    }


    public String listBooks(Model model){
        model.addAttribute("arc", new Arc());
        model.addAttribute("listBooks", this.arcService.listArcs());


        return "main";
    }

    @RequestMapping(value = "/main/find", method = RequestMethod.POST)
    public String findRoute(@ModelAttribute("arc") Arc arc){
        System.out.println("Trying to find route");

        return "redirect:/main";
    }

}
