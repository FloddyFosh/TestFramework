package dataentities;

import lombok.Data;

@Data
public class Customer {

    String name = "Pietje Pietersen";
    String country = "Nederland";
    String city = "Amsterdam";
    String creditcard = "4444 3333 2222 1111";
    String creditcardMonth = "01";
    String creditcardYear = "2022";
}
