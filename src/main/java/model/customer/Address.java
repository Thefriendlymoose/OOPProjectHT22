package model.customer;

import model.pojos.AddressPojo;

public class Address implements IAddress{
    private AddressPojo pojo;

    public Address(AddressPojo addressPojo){
        pojo = addressPojo;
    }

    @Override
    public String getStreetName() {
        return pojo.getStreetName();
    }

    @Override
    public String getStreetNumber() {
        return pojo.getStreetNumber();
    }

    @Override
    public String getZipCode() {
        return pojo.getZipCode();
    }

    @Override
    public String getCityName() {
        return pojo.getCityName();
    }
}
