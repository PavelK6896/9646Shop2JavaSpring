package app.web.pavelk.shop2.domains;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
@ToString
@Data
@EqualsAndHashCode(of = {"id"})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;


    @ManyToMany
    @JoinTable(
            name = "user_product",
            joinColumns = {@JoinColumn(name = "user_id1")},
            inverseJoinColumns = {@JoinColumn(name = "product_id1")}
    )
    private List<Product> product;

    public List<Product> getProduct() {
        return product;
    }
}
