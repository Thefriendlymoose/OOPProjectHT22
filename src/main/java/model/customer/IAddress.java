package model.customer;

public interface IAddress {

    //getters
    String getStreetName();
    String getStreetNumber();
    String getPostalCode();
    String getCityName();
    String getCountry();

    //setters

    void setStreetName(String streetName);
    void setStreetNumber(String streetNumber);
    void setPostalCode(String postalCode);
    void setCityName(String cityName);
    void setCountry(String country);
}
