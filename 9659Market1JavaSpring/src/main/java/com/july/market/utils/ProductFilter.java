package com.july.market.utils;

import com.july.market.entities.Product;
import com.july.market.repositories.specifications.ProductSpecifications;
import lombok.Getter;
import org.springframework.data.jpa.domain.Specification;

import java.util.Map;

@Getter
public class ProductFilter {
    private Specification<Product> spec;
    private Specification<Product> spec2;
    private StringBuilder filterDefinition;

    public ProductFilter(Map<String, String> map) {
        this.spec = Specification.where(null);
        this.spec2 = Specification.where(null);
        this.filterDefinition = new StringBuilder();

        System.out.println(map);

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

        if (map.containsKey("dairy") && !map.get("dairy").isEmpty()) {
            spec2 = spec2.or(ProductSpecifications.categoryThan("dairy"));
            filterDefinition.append("&dairy=").append(map.get("dairy"));
        }

        if (map.containsKey("meat") && !map.get("meat").isEmpty()) {
            spec2 = spec2.or(ProductSpecifications.categoryThan("meat"));
            filterDefinition.append("&meat=").append(map.get("meat"));
        }

        if (map.containsKey("vegetables") && !map.get("vegetables").isEmpty()) {
            spec2 = spec2.or(ProductSpecifications.categoryThan("vegetables"));
            filterDefinition.append("&vegetables=").append(map.get("vegetables"));
        }
        spec = spec.and(spec2);

    }
}
