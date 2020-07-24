package app.web.pavelk.july.market.models;

import app.web.pavelk.july.market.entities.Product;
import lombok.Data;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import java.util.HashMap;

@Component
@Data
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class Basket {
    private HashMap<Product, Integer> mapProduct = new HashMap<>();
    private Integer sumPrice = 0;
    private Integer size = 0;

    public void add(Product product) {
        if (mapProduct.containsKey(product)) {
            mapProduct.put(product, mapProduct.get(product) + 1);
        } else {
            mapProduct.put(product, 1);
        }
        sumAndSizeAdd(product.getPrice());
    }

    public void delete(Product product) {
        if (mapProduct.get(product) > 1) {
            mapProduct.put(product, mapProduct.get(product) - 1);
        } else {
            mapProduct.remove(product);
        }
        sumAndSizeDelete(product.getPrice());
    }

    public void clear() {
        mapProduct.clear();
    }


    private void sumAndSizeDelete(int price) {
        sumPrice -= price;
        size--;
    }

    private void sumAndSizeAdd(int price) {
        sumPrice += price;
        size++;
    }

    private void sumAndSize() {
        sumPrice = 0;
        size = 0;
        mapProduct.forEach((p, i) -> {
            sumPrice = p.getPrice() * i;
            size += i;
        });
    }

}
