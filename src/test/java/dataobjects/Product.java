package dataobjects;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class Product {

    public enum CategoryEnum {
            PHONE, LAPTOP, MONITOR, UNKNOWN;
    }

    private String name;
    private BigDecimal price;
    private CategoryEnum category;

    public Product(String name) {
        this(name, CategoryEnum.UNKNOWN, null);
    }

    public Product(String name, CategoryEnum category) {
        this(name, category, null);
    }

    public Product(String name, CategoryEnum category, BigDecimal price) {
        this.name = name;
        this.category = category;
        this.price = price;
    }
}
