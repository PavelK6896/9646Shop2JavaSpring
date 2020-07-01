package app.web.pavelk.shop2.controllers;


import app.web.pavelk.shop2.domains.Product;
import app.web.pavelk.shop2.service.Service1Main;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("product")
public class Controller1Main {
    private final ApplicationContext context;
    private final Service1Main service1Main;

    @Autowired
    public Controller1Main(ApplicationContext context, Service1Main service1Main) {
        this.context = context;
        this.service1Main = service1Main;
    }

    @GetMapping("/json")
    @ResponseBody
    public List<Product> findAll() {
        System.out.println("get all");


        return service1Main.findAll();
    }

    @GetMapping("/json/{id}")
    @ResponseBody
    public Optional<Product> findById(@PathVariable("id") Long id) {
        System.out.println("get " + id);
        return service1Main.findById(id);
    }


    @PostMapping
    public String saveNewStudent(@ModelAttribute Product product) {
        System.out.println("post " + product);
        service1Main.addProduct(product);
        return "redirect:/product/";
    }

    @DeleteMapping("{id}")
    @ResponseBody
    public String delete(@PathVariable("id") Long id) {
        System.out.println("delete " + id);
        service1Main.delete(id);
        return "index";
    }


    @GetMapping
    public String main(Model model) {

        model.addAttribute("hostname", service1Main.getHostname());
        model.addAttribute("product", service1Main.findAll());

        return "index";
    }

    @GetMapping("{id}")
    public String main(@PathVariable("id") Long id, Model model) {

        model.addAttribute("hostname", service1Main.getHostname());
        model.addAttribute("product", service1Main.findById(id));
        return "index";
    }

    @PutMapping("{id}")
    @ResponseBody
    public void update(@PathVariable("id") Long id,
                       @RequestBody Product product) {
        System.out.println("put" + id + " " + product);
        Product productFromDb = service1Main.getOne(id);
        BeanUtils.copyProperties(product, productFromDb, "id");
        service1Main.addProduct(productFromDb);
    }

}
