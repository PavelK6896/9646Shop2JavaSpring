package app.web.pavelk.july.market.utils;

import app.web.pavelk.july.market.entities.Category;
import app.web.pavelk.july.market.entities.Product;
import app.web.pavelk.july.market.repositories.specifications.ProductSpecifications;
import lombok.Getter;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Map;

@Getter
public class ProductFilter {
    private Specification<Product> spec;
    private StringBuilder filterDefinition;

    public ProductFilter(Map<String, String> map, List<Category> categories) {
        this.spec = Specification.where(null);
        this.filterDefinition = new StringBuilder();
        if (map.containsKey("min_price") && !map.get("min_price").isEmpty()) {
            int minPrice = Integer.parseInt(map.get("min_price"));
            spec = spec.and(ProductSpecifications.priceGreaterOrEqualsThan(minPrice));
            filterDefinition.append("&min_price=").append(minPrice);
        }
        if (map.containsKey("max_price") && !map.get("max_price").isEmpty()) {
            int maxPrice = Integer.parseInt(map.get("max_price"));
            spec = spec.and(ProductSpecifications.priceLesserOrEqualsThan(maxPrice));
            filterDefinition.append("&max_price=").append(maxPrice);
        }
        if (map.containsKey("name1") && !map.get("name1").isEmpty()) {
            spec = spec.and(ProductSpecifications.titleEqualsThan(map.get("name1")));
            filterDefinition.append("&name1=").append(map.get("name1"));
        }
        if (categories != null && !categories.isEmpty()) { // ели получили лист категорий в параметрах
            Specification specCategories = null;
            int t = 0;
            for (Category c : categories) { // переберает все категории
                if (specCategories == null) {
                    specCategories = ProductSpecifications.categoryIs(c);
                } else {
                    specCategories = specCategories.or(ProductSpecifications.categoryIs(c));
                }
            }
            spec = spec.and(specCategories);
        }


    }
}
