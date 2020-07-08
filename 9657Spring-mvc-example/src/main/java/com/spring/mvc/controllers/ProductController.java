package com.spring.mvc.controllers;

import com.spring.mvc.model.Product;
import com.spring.mvc.repositories.specifications.ProductSpecifications;
import com.spring.mvc.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ProductController {

    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    @GetMapping("/product")
    public String homePage(Model model) {

        model.addAttribute("product", productService.findAll());
        return "all_product";
    }


    @GetMapping("/product/{page}")
    public String showAllStudents(Model model,
                                  @PathVariable("page") Integer page,
                                  @RequestParam(name = "min_price", required = false) Integer minPrice,
                                  @RequestParam(name = "max_price", required = false) Integer maxPrice) {

        Specification<Product> spec = Specification.where(null);
        if (minPrice != null && minPrice != 0) {
            spec = spec.and(ProductSpecifications.priceGEThan(minPrice));
        }
        if (maxPrice != null && maxPrice != 0) {
            spec = spec.and(ProductSpecifications.priceLEThan(maxPrice));
        }

        Page<Product> productPage = productService.findAll(spec, page);
        List<Product> product = productPage.getContent();
        model.addAttribute("product", product);
        model.addAttribute("page", productPage);

        return "filter_product";
    }


}
