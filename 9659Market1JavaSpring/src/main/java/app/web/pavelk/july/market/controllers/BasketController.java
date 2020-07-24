package app.web.pavelk.july.market.controllers;


import app.web.pavelk.july.market.entities.Basket;
import app.web.pavelk.july.market.services.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/basket")
public class BasketController {

    private ProductsService productsService;
    private Basket basket;

    @Autowired
    public BasketController(ProductsService productsService, Basket basket) {
        this.productsService = productsService;
        this.basket = basket;
    }

    @GetMapping
    public String basket(Model model) {
        Map<String, Integer> map = new HashMap<>();
        basket.getListProduct().forEach(p -> {
                    if (map.containsKey(p.getTitle())) {
                        map.put(p.getTitle(), map.get(p.getTitle()) + 1);
                    } else {
                        map.put(p.getTitle(), 1);
                    }
                }
        );
        model.addAttribute("basket", map);

        return "basket";
    }

    @PostMapping("/add")
    public String saveNewProduct(
            Model model,
            @RequestParam Long id,
            RedirectAttributes redirectAttributes,//параметры
            @RequestHeader(required = false) String referer) {

        basket.getListProduct().add(productsService.findById(id));
        model.addAttribute("basket2", basket.getListProduct());

        //нужно чтобы все вернулось как было
        UriComponents components = UriComponentsBuilder.fromHttpUrl(referer).build();
        components.getQueryParams()
                .entrySet()
                .forEach(pair -> {
                    redirectAttributes.addAttribute(pair.getKey(), pair.getValue());
                });

        redirectAttributes.addAttribute("basket", basket.getListProduct().size());
        return "redirect:" + components.getPath();
    }
}
