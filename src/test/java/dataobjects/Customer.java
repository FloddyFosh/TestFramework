package dataobjects;

public class Customer {

    private String name;
    private String country;
    private String city;
    private String creditcardNumber;
    private String creditcardMonth;
    private String creditcardYear;

    public Customer(String name, String country, String city, String creditcardNumber, String creditcardMonth, String creditcardYear) {
        this.name = name;
        this.country = country;
        this.city = city;
        this.creditcardNumber = creditcardNumber;
        this.creditcardMonth = creditcardMonth;
        this.creditcardYear = creditcardYear;
    }

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public String getCreditcardNumber() {
        return creditcardNumber;
    }

    public String getCreditcardMonth() {
        return creditcardMonth;
    }

    public String getCreditcardYear() {
        return creditcardYear;
    }
}
