package app.web.pavelk.july.market.controllers;


import app.web.pavelk.july.market.exceptions.ProductNotFoundException;
import app.web.pavelk.july.market.services.ProductsService;
import app.web.pavelk.july.market.entities.Product;
import app.web.pavelk.july.market.entities.dtos.ProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class RestProductsController {
    private ProductsService productsService;

    @Autowired
    public RestProductsController(ProductsService productsService) {
        this.productsService = productsService;
    }

    @GetMapping("/dto")
    public List<ProductDto> getAllProductsDto() {
        return productsService.getDtoData();
    }

    @GetMapping
    public List<Product> getAllProducts() {
        return productsService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOneProducts(@PathVariable Long id) {
        if (!productsService.existsById(id)) {
            throw new ProductNotFoundException("Product not found, id: " + id);
        }
        return new ResponseEntity<>(productsService.findById(id), HttpStatus.OK);
    }

    @DeleteMapping
    public String deleteAllProducts(@PathVariable Long id) {
        productsService.deleteAll();
        return "OK";
    }

    @DeleteMapping("/{id}")
    public String deleteOneProducts(@PathVariable Long id) {
        productsService.deleteById(id);
        return "OK";
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product saveNewProduct(@RequestBody Product product) {
        if (product.getId() != null) {
            product.setId(null);
        }
        return productsService.saveOrUpdate(product);
    }

    @PutMapping
    public ResponseEntity<?> modifyProduct(@RequestBody Product product) {
        if (product.getId() == null || !productsService.existsById(product.getId())) {
            throw new ProductNotFoundException("Product not found, id: " + product.getId());
        }
        if (product.getPrice() < 0) {
            return new ResponseEntity<>("Product's price can not be negative", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(productsService.saveOrUpdate(product), HttpStatus.OK);
    }

    @ExceptionHandler
    public ResponseEntity<?> handleException(ProductNotFoundException exc) {
        return new ResponseEntity<>(exc.getMessage(), HttpStatus.NOT_FOUND);
    }
}