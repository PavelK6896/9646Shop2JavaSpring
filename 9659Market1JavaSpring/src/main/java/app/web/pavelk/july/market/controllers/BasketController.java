package app.web.pavelk.july.market.controllers;

import app.web.pavelk.july.market.models.Basket;
import app.web.pavelk.july.market.services.ProductsService;
import com.sun.xml.bind.v2.model.core.AttributePropertyInfo;
import org.apache.tomcat.util.bcel.classfile.AnnotationElementValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ModelAndViewDefiningException;
import org.springframework.web.servlet.mvc.annotation.ModelAndViewResolver;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import javax.management.AttributeList;
import javax.persistence.metamodel.Attribute;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
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
    public String basket1(Model model) {
        model.addAttribute("mapProduct", basket.getMapProduct());
        return "basket";
    }

    @GetMapping("/1")
    public String basket(RedirectAttributes redirectAttributes) {
        redirectAttributes.addAttribute("sizeProduct", basket.getSize());
        redirectAttributes.addAttribute("sumProduct", basket.getSumPrice());
        return "redirect:";
    }

    @PostMapping("/add")
    public String saveNewProduct(
            Model model,
            @RequestParam Long id,
            RedirectAttributes redirectAttributes,//параметры
            @RequestHeader(required = false) String referer) {

        basket.add(productsService.findById(id));

        //нужно чтобы все вернулось как было
        UriComponents components = UriComponentsBuilder.fromHttpUrl(referer).build();
        components.getQueryParams()
                .entrySet()
                .forEach(pair -> {
                    redirectAttributes.addAttribute(pair.getKey(), pair.getValue());
                });

        redirectAttributes.addAttribute("sizeProduct", basket.getSize());
        redirectAttributes.addAttribute("sumProduct", basket.getSumPrice());


        return "redirect:" + components.getPath();
    }


}
