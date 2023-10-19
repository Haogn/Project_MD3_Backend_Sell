package ra.controller;

import ra.model.Order;
import ra.service.OrderService;

import java.util.List;

public class OrderController {
    private OrderService orderService;
    public OrderController(){
        orderService = new OrderService();
    }

    public int getNewId() {
        return orderService.getNewId();
    }

    public List<Order> findAll() {
        return orderService.findAll();
    }

    public Order findALlById(int id) {
        return orderService.findALlById(id) ;
    }

    public void save(Order od) {
        orderService.save(od);
    }

    public List<Order> findOderByUserId(){
        return orderService.findOderByUserId();
    }

    public Order findById(int id) {
        return orderService.findById(id) ;
    }

    public void endPay() {
        orderService.endPay();
    }
    public void showAllOrder() {
        orderService.showAllOrder();
    }
    public void showOrderByCode(byte code) {
        orderService.showOrderByCode(code);
    }
    public void showOrderDetail(){
        orderService.showOrderDetail();
    }
}
