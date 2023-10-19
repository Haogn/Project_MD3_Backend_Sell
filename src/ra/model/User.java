package ra.model;

import ra.config.InpustMethods;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class User implements Serializable {
    private int userId;
    private String userName;
    private String email;
    private String password;
    private Address address ;
    private String phone ;
    private boolean userStatus ;
    private List<Cart> cart = new ArrayList<>();
//    private Set<RoleName> roles = new HashSet<>();
    public User() {

    }

    public User(int userId, String userName, String email, String password, Address address, String phone, boolean userStatus, List<Cart> cart) {
        this.userId = userId;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.address = address;
        this.phone = phone;
        this.userStatus = userStatus;
        this.cart = cart;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isUserStatus() {
        return userStatus;
    }

    public void setUserStatus(boolean userStatus) {
        this.userStatus = userStatus;
    }

    public List<Cart> getCart() {
        return cart;
    }

    public void setCart(List<Cart> cart) {
        this.cart = cart;
    }

    public void displayUser(){
        System.out.println("_____ THONG TIN USER _____");
        System.out.println("Id : "+ userId+" | Ten tai khoan: " + userName + " | Email : " + email+ " | So dien thoai : " + phone);
//        System.out.println("Dia chi : " + address.getCity()+" - "+address.getDistrict()+" - "+address.getSpecifically());
        address.displayAddress();
    }
}
