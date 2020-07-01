package app.web.pavelk.shop2.service;

import app.web.pavelk.shop2.domains.Product;
import app.web.pavelk.shop2.repositories.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class Service1Main {

    private ProductRepo productRepo;

    @Autowired
    public void setProductRepo(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    @Value("${hostname}")
    private String hostname;

    public String getHostname() {
        return hostname;
    }


    public List<Product> findAll() {
        return productRepo.findAll();
    }


    public void addProduct(Product product) {
        productRepo.save(product);
    }

    public void delete(Long id) {
        productRepo.deleteById(id);
    }

    public Optional<Product> findById(Long id) {
        return productRepo.findById(id);
    }


    public Product getOne(Long id) {
        return productRepo.getOne(id);
    }
}
