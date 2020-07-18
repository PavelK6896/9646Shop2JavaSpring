package app.web.pavelk.july.market.services;

import app.web.pavelk.july.market.exceptions.ProductNotFoundException;
import app.web.pavelk.july.market.entities.Product;
import app.web.pavelk.july.market.entities.dtos.ProductDto;
import app.web.pavelk.july.market.repositories.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductsService {
    private ProductsRepository productsRepository;

    @Autowired
    public void setProductsRepository(ProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
    }

    public List<Product> findAll() {
        return productsRepository.findAll();
    }

    public Page<Product> findAll(Specification<Product> spec, Integer page) {
        if (page < 1L) {
            page = 1;
        }
        return productsRepository.findAll(spec, PageRequest.of(page - 1, 3));
    }

    public Product findById(Long id) {
        return productsRepository.findById(id).orElseThrow(() ->
                new ProductNotFoundException("Can't found product with id = " + id));
    }

    public boolean existsById(Long id) { // существует
        return productsRepository.existsById(id);
    }

    public List<ProductDto> getDtoData() {
        return productsRepository.findAllBy();
    }

    public Product saveOrUpdate(Product product) {
        return productsRepository.save(product);
    }

    public void deleteAll() {
        productsRepository.deleteAll();
    }

    public void deleteById(Long id) {
        productsRepository.deleteById(id);
    }

}
