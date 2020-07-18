package app.web.pavelk.july.market.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;


@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
@Scope("prototype")
public class Basket {
    private List<Product> listProduct = new LinkedList<>();
    private String name;
}
