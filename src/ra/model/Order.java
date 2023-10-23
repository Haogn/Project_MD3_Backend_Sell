package ra.model;


import ra.config.Message;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Order implements Serializable {
    private int id ;
    private int idUser ;
    private double total ;
    private Date buyDate = new Date();
    private String receiver;
    private String numberPhone ;
    private Address address ;
    private  byte status = 0 ;
    private List<Cart> orderDetail = new ArrayList<>();

    public Order() {

    }

    public Order(int id, int idUser, double total, Date buyDate, String receiver, String numberPhone, Address address, byte status, List<Cart> orderDetail) {
        this.id = id;
        this.idUser = idUser;
        this.total = total;
        this.buyDate = buyDate;
        this.receiver = receiver;
        this.numberPhone = numberPhone;
        this.address = address;
        this.status = status;
        this.orderDetail = orderDetail;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Date getBuyDate() {
        return buyDate;
    }

    public void setBuyDate(Date buyDate) {
        this.buyDate = buyDate;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getNumberPhone() {
        return numberPhone;
    }

    public void setNumberPhone(String numberPhone) {
        this.numberPhone = numberPhone;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public byte getStatus() {
        return status;
    }

    public void setStatus(byte status) {
        this.status = status;
    }

    public List<Cart> getOrderDetail() {
        return orderDetail;
    }

    public void setOrderDetail(List<Cart> orderDetail) {
        this.orderDetail = orderDetail;
    }

    public void display(){
        System.out.println("Don hang : " +id + " ➖ Ten nguoi nhan : "+receiver+ " ➖ So dien thoai : "+ numberPhone);
        System.out.println("Dia chi : " + address.getCity() + " ➖ " + address.getDistrict() + " ➖ " + address.getSpecifically());
        System.out.println("Trang thai : " + Message.getStatusByCode(status));
    }
}
