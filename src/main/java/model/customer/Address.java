package model.customer;

public class Address implements IAddress{
    private String streetName;
    private String streetNumber;
    private String zipCode;
    private String cityName;

    public Address(String streetName, String streetNumber, String zipCode, String cityName){
        this.streetName = streetName;
        this.streetNumber = streetNumber;
        this.zipCode = zipCode;
        this.cityName = cityName;
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
    public String getZipCode() {
        return zipCode;
    }

    @Override
    public String getCityName() {
        return cityName;
    }
}
