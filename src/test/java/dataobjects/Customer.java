package dataobjects;

import lombok.Data;

@Data
public class Customer {
    private final String name;
    private final String country;
    private final String city;
    private final String creditcardNumber;
    private final String creditcardMonth;
    private final String creditcardYear;
}
