package app.web.pavelk.july.market.repositories;

import app.web.pavelk.july.market.entities.Product;
import app.web.pavelk.july.market.entities.dtos.ProductDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductsRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {
    List<ProductDto> findAllBy(); // оптимизируем выдачю
}