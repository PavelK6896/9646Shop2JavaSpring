package app.web.pavelk.july.market.repositories;


import app.web.pavelk.july.market.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriesRepository extends JpaRepository<Category, Long> {
}