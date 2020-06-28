package app.web.pavelk.shop2.controller;

import app.web.pavelk.shop2.config.Box;
import app.web.pavelk.shop2.service.Service1Main;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Controller1Main {

    private final ApplicationContext context;
    private final Service1Main service1Main;

    public Controller1Main(ApplicationContext context, Service1Main service1Main) {
        this.context = context;
        this.service1Main = service1Main;
    }

    @GetMapping("/")
    public String main(Model model){

        Box box1 = context.getBean("box", Box.class);
        Box box2 = context.getBean("box", Box.class);

        System.out.println(box1.color);
        System.out.println(box2.color);

        box1.color = "Green";
        box2.color = "White";

        System.out.println(box1.color);
        System.out.println(box2.color);

        model.addAttribute("box1", box1);
        model.addAttribute("box2", box2);
        model.addAttribute("hostname", service1Main.getHostname());

        return "index";
    }


}
