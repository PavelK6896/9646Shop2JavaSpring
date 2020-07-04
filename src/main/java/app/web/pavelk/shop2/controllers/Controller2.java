package app.web.pavelk.shop2.controllers;

import app.web.pavelk.shop2.domains.Product;
import app.web.pavelk.shop2.domains.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class Controller2 {

    @GetMapping("/product/{product}/add")
    public void add(@PathVariable Product product) {
        User user = new User(); //@AuthenticationPrincipal User user
        List<Product> products = user.getProduct();
        products.add(product);
    }
}
