package ra.model;

import ra.config.InpustMethods;

import java.io.Serializable;

public class Address implements Serializable {
    private static int nextId = 1 ;
    private int addressId ;
    private String city ;
    private String district ;
    private String specifically ;
    public Address() {
        this.addressId = nextId++ ;
    }

    public Address(int addressId, String city, String district, String specifically) {
        this.addressId = addressId;
        this.city = city;
        this.district = district;
        this.specifically = specifically;
    }

    public static int getNextId() {
        return nextId;
    }

    public static void setNextId(int nextId) {
        Address.nextId = nextId;
    }

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getSpecifically() {
        return specifically;
    }

    public void setSpecifically(String specifically) {
        this.specifically = specifically;
    }


    public void displayAddress() {
        System.out.println("Dia chi : " + city + " - " + district + " - " + specifically);
    }
}
