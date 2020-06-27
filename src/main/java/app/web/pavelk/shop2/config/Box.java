package app.web.pavelk.shop2.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(value = "prototype")
public class Box {
    @Value("Red")
    public String color;

    @Override
    public String toString() {
        return "Box: " + color;
    }
}
