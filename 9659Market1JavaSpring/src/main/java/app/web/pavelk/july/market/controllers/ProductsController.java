package app.web.pavelk.july.market.controllers;

import app.web.pavelk.july.market.entities.Category;
import app.web.pavelk.july.market.entities.Product;
import app.web.pavelk.july.market.services.CategoriesService;
import app.web.pavelk.july.market.services.ProductsService;
import app.web.pavelk.july.market.utils.ProductFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/products")
public class ProductsController {
    private ProductsService productsService;
    private CategoriesService categoriesService;

    @Autowired
    public ProductsController(ProductsService productsService, CategoriesService categoriesService) {
        this.productsService = productsService;
        this.categoriesService = categoriesService;
    }

    @GetMapping
    public String showAll(
            Model model,
            @RequestParam Map<String, String> requestParams,
            @RequestParam(name = "categories", required = false) List<Long> categoriesIds
    ) {
        Integer pageNumber = Integer.parseInt(requestParams.getOrDefault("p", "1"));

        List<Category> categoriesFilter = null;
        if (categoriesIds != null) { // ид категорий
            // получаем все категории из репо
            categoriesFilter = categoriesService.getCategoriesByIds(categoriesIds);
        }

        // собираем спецификацию
        ProductFilter productFilter = new ProductFilter(requestParams, categoriesFilter);
        // получаем продукты согласно спецкфекации
        Page<Product> products = productsService.findAll(productFilter.getSpec(), pageNumber);

        model.addAttribute("products", products);
        model.addAttribute("filterDef", productFilter.getFilterDefinition().toString());
        return "all_products";
    }

    @GetMapping("/add")
    public String showAddForm() {
        return "add_product_form";
    }

    @PostMapping("/add")
    public String saveNewProduct(@ModelAttribute Product product) {
        productsService.saveOrUpdate(product);
        return "redirect:/products/";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        model.addAttribute("product", productsService.findById(id));
        return "edit_product_form";
    }

    @PostMapping("/edit")
    public String modifyProduct(@ModelAttribute Product product) {
        productsService.saveOrUpdate(product);
        return "redirect:/products/";
    }
}