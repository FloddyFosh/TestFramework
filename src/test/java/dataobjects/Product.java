package dataobjects;

import java.math.BigDecimal;

public class Product {

    public enum categoryEnum {
            PHONE, LAPTOP, MONITOR, UNKNOWN;
    }

    private String name;
    private BigDecimal price;
    private categoryEnum category;

    public Product(String name) {
        this(name, categoryEnum.UNKNOWN, null);
    }

    public Product(String name, BigDecimal price) {
        this(name, categoryEnum.UNKNOWN, price);
    }
    public Product(String name, categoryEnum category) {
        this(name, category, null);
    }

    public Product(String name, categoryEnum category, BigDecimal price) {
        this.name = name;
        this.category = category;
        this.price = price;
    }

    public String getName() {
        return this.name;
    }

    public categoryEnum getCategory() {
        return this.category;
    }

    public BigDecimal getPrice() { return this.price; }
}
