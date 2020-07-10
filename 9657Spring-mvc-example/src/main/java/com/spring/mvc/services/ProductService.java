package com.spring.mvc.services;

import com.spring.mvc.model.Product;
import com.spring.mvc.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private ProductRepository productRepository;

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public List<Product> findAll(Specification<Product> specifications) {
        return productRepository.findAll(specifications);
    }

    public Page<Product> findAll(Specification<Product> specifications, Integer page) {
        if (page < 1L) {
            page = 1;
        }

        return productRepository.findAll(specifications, PageRequest.of(page - 1, 3));
    }


}
