package model.customer;

public class Address implements IAddress{
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
    @Override
    public String getStreetName() {
        return streetName;
    }

    @Override
    public String getStreetNumber() {
        return streetNumber;
    }

    @Override
    public String getPostalCode() {
        return postalCode;
    }

    @Override
    public String getCityName() {
        return cityName;
    }

    @Override
    public String getCountry() {
        return country;
    }

    @Override
    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    @Override
    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
    }

    @Override
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    @Override
    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    @Override
    public void setCountry(String country) {
        this.country = country;
    }
}
