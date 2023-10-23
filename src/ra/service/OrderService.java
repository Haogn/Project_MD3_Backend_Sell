package ra.service;

import com.sun.org.apache.xpath.internal.operations.Or;
import ra.config.InpustMethods;
import ra.controller.CartController;
import ra.controller.OrderController;
import ra.controller.ProductController;
import ra.model.Cart;
import ra.model.Order;
import ra.model.Product;
import ra.model.User;
import ra.util.DataBase;
import ra.view.UI;

import javax.jws.soap.SOAPBinding;
import java.util.ArrayList;
import java.util.List;

public class OrderService {
    private List<Order> orders;
    private DataBase<Order> orderData;
    private UserService userService = new UserService();
    private ProductController productController = new ProductController();
    private CartController cartController = new CartController(userService.userLogin());

    public OrderService() {
        orderData = new DataBase<>();
        orders = orderData.readFormFile(DataBase.ORDER_PATH);
    }

    public int getNewId() {
        int max = 0;
        for (Order o : orders) {
            if (o.getId() > max) {
                max = o.getId();
            }
        }
        return max + 1;
    }

    public List<Order> findAll() {
        return orders;
    }

    public Order findALlById(int id) {
        for (Order o : orders) {
            if (o.getId() == id) {
                return o;
            }
        }
        return null;
    }

    public void save(Order od) {
        int index = -1;
        for (int i = 0; i < orders.size(); i++) {
            if (orders.get(i).getId() == od.getId()) {
                index = i;
                break;
            }
        }
        if (index == -1) {
            orders.add(od);
        } else {
            orders.set(index, od);
        }
        orderData.writeToFile(orders, DataBase.ORDER_PATH);
    }

    public List<Order> findOderByUserId() {
        List<Order> newOder = new ArrayList<>();
        for (Order o : orders) {
            if (o.getIdUser() == userService.userLogin().getUserId()) {
                newOder.add(o);
            }
        }
        return newOder;
    }

    public Order findById(int id) {
        for (Order o : findOderByUserId()) {
            if (o.getId() == id) {
                return o;
            }
        }
        return null;
    }
    public double total(){
        User user = userService.userLogin();
        double total = 0;
        for (Cart ca : user.getCart()) {
            total += ca.getProduct().getExportPrice() * ca.getQuantity();
        }
        return total ;
    }

    public void endPay() {
        User user = userService.userLogin();
        Order newOrder = new Order();
        showAllOrder();
        System.out.println("Ban muon thanh toan : 1- Toan bo san pham      2- Mot san pham trong gio hang");
        System.out.println("Nhap vao lua chon cua ban");
        int choice = InpustMethods.getInteger();
        switch (choice) {
            case 1 : // TODO : Thanh toan toan bo
                newOrder.setId(getNewId());
                // cap nhap tong tien
                double total = 0;
                for (Cart ca : user.getCart()) {
                    total += ca.getProduct().getExportPrice() * ca.getQuantity();
                }
                newOrder.setTotal(total);
                newOrder.setIdUser(user.getUserId());
                System.out.println("Nhap ten nguoi nhan hang");
                newOrder.setReceiver(InpustMethods.getUseName());
                newOrder.setNumberPhone(user.getPhone());
                newOrder.setAddress(user.getAddress());

                // tien hanh tru di so luong trong kho hang
                for (Cart ca : user.getCart()) {
                    Product pt = productController.findById(ca.getProduct().getProductId());
                    pt.setQuantity(ca.getProduct().getQuantity() - ca.getQuantity());
                    productController.save(pt);
                }
                newOrder.setOrderDetail(user.getCart());
                save(newOrder);
                cartController.clearAll();
                break;
            case 2: // TODO :  thanh toan 1 san pham
                System.out.println("Nhap vao Id san pham can thanh toan ");
                int id = InpustMethods.getInteger();
                Product newProduct = productController.findById(id) ;
                List<Cart> cart = user.getCart();
                if (cart.contains(newProduct)) {
                    newOrder.setId(getNewId());
                    newOrder.setTotal(total());
                    newOrder.setIdUser(user.getUserId());
                    System.out.println("Nhap ten nguoi nhan hang");
                    newOrder.setReceiver(InpustMethods.getUseName());
                    newOrder.setNumberPhone(user.getPhone());
                    newOrder.setAddress(user.getAddress());

                    // tien hanh tru di so luong trong kho hang
                    for (Cart ca : user.getCart()) {
                        Product pt = productController.findById(ca.getProduct().getProductId());
                        pt.setQuantity(ca.getProduct().getQuantity() - ca.getQuantity());
                        productController.save(pt);
                    }
                    newOrder.setOrderDetail(cart);
                    save(newOrder);
                    cartController.delete(newProduct.getProductId());
                }
                break;
            default:
                System.err.println("❌❌❌ Lua chon khong phu hop. Vui long chon lai ❤ ");
        }
        UI.menuUser();
    }

    public void showAllOrder() {
        List<Order> list = findOderByUserId();
        if (list.isEmpty()) {
            System.err.println("Chua co don hang nao thanh toan ");
            return;
        }
        for (Order o : list) {
            o.display();
        }
    }

    public void showOrderByCode(byte code) {
        List<Order> list = findOderByUserId();
        List<Order> filter = new ArrayList<>();
        for (Order o : list) {
            if(o.getStatus() == code) {
                filter.add(o) ;
            }
        }
        if (filter.isEmpty()) {
            System.out.println("Don hang rong");
            return;
        }
        for (Order o : filter) {
            System.out.println(o);
        }
    }

    public void showOrderDetail(){
        System.out.println("Nhap vao Id gio hang");
        int idOrder = InpustMethods.getInteger();
        Order order = findById(idOrder);
        if ( order == null) {
            System.err.println("Gio hang rong");
            return;
        }
        // in ra chi tiet hoa don
        System.out.println("______________________ CHI TIET DON HANG ______________________");
        System.out.println("");
        System.out.println("Nguoi nhan : " + order.getReceiver());
        System.out.println("So dien thoai : " + order.getNumberPhone());
        System.out.println("Dia chi : " + order.getAddress().getSpecifically() + " - " +  order.getAddress().getDistrict() + " _ " +  order.getAddress().getCity());
        System.out.println("___________________________ Don hang ___________________________");
        for (Cart ca : order.getOrderDetail()){
            System.out.println(ca);
        }
        System.out.println("Tong tien : " + order.getTotal());
        System.out.println("❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤");

        // TODO neu don hang dang o trang thai cho co the huy don hang
        if (order.getStatus() == 0 ){
            System.out.println("Bạn có muốn hủy đơn hàng này không?");
            System.out.println("1. Co ");
            System.out.println("2. Khong");
            System.out.println("Nhap lua chon cua ban : ");
            int choice = InpustMethods.getInteger() ;
            if ( choice == 1 ) {
                for (Cart ca : order.getOrderDetail()){
                    Product pt = productController.findById(ca.getProduct().getProductId());
                    pt.setQuantity(ca.getProduct().getQuantity() + ca.getQuantity());
                    productController.save(pt);
                }
                order.setStatus((byte) 2);
                save(order);
            }
        }
        UI.menuUser();
    }

    public void showOrderUsertoAdmin(){
        for (Order o : orders) {
            System.out.println("----------------------------------------------------------------");
            o.display();
            System.out.println("----------------------------------------------------------------");
        }
    }

    public void OrderConfirm(){
        System.out.println("Nhap ma don hang");
        int idOrder = InpustMethods.getInteger();
        Order orderConfirm = findById(idOrder) ;
        if (orderConfirm == null) {
            System.err.println("KHong tim thay don hang nay trong danh sach");
        } else if (orderConfirm.getStatus() == 0 ) {
            orderConfirm.display();
            System.out.println("Ban muon xac nhan don hang nay chu");
            System.out.println("1. Co ");
            System.out.println("2. Khong");
            System.out.println("Nhap lua chon cua ban");
            int choice = InpustMethods.getInteger();
            switch (choice) {
                case 1 :
                    orderConfirm.setStatus((byte) 1);
                    save(orderConfirm);
                    break;
                case 2 :
                    break;
                default:
                    System.err.println("❌❌❌ Lua chon khong phu hop. Vui long chon lai ❤ ");
            }
        } else if (orderConfirm.getStatus() == 1) {
            System.out.println("Don hang nay khach hang da mua");
        }
    }

}
