package app.web.pavelk.shop2.repositories;

import app.web.pavelk.shop2.domains.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepo extends JpaRepository<Product, Long> {
}
