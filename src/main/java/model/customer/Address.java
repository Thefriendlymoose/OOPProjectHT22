package model.customer;

/**
 * A class that represents an address
 */
public class Address{
    private String streetName;
    private String streetNumber;
    private String postalCode;
    private String cityName;
    private String country;

    public Address(String streetName, String streetNumber, String postalCode, String cityName, String country){
        this.streetName = streetName;
        this.streetNumber = streetNumber;
        this.postalCode = postalCode;
        this.cityName = cityName;
        this.country = country;

    }

    public Address(){};

    // getters
    public String getStreetName() {
        return streetName;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getCityName() {
        return cityName;
    }

    public String getCountry() {
        return country;
    }

    // setters

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString(){
        return "Street Name: " + streetName +
                "\nStreet Number: " + streetNumber +
                "\nPostal Code: " + postalCode +
                "\nCity: " + cityName +
                "\nCountry: " + country;
    }
}
