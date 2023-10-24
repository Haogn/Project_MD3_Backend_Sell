package ra.service;

import ra.config.InpustMethods;
import ra.controller.CartController;
import ra.controller.ProductController;
import ra.model.Cart;
import ra.model.Product;
import ra.model.User;
import ra.util.DataBase;

import java.util.ArrayList;
import java.util.List;
import java.util.PrimitiveIterator;

public class CartService {
    private User userLogin ;
    private  UserService userService = new UserService() ;
    ;
    private CartController cartController;
    private Cart cart;
    private final ProductController productController = new ProductController();

    public CartService(User userLogin) {
        this.userLogin = userService.userCart();
    }

    // TODO : Id tu tang
    public int getNewId() {

        int max = 0;
        for (Cart ci : userLogin.getCart()) {
            if (ci.getCartId() > max) {
                max = ci.getCartId();
            }
        }
        return max + 1;
    }

    // TODO : lay ra tat ca
    public List<Cart> findAll() {
        return userLogin.getCart();
    }

    // // TODO : lua hoac tahy doi
    public void save(Cart cart) {
        List<Cart> listCart = userLogin.getCart();
        // TODO : them moi
        boolean isExits= false;
        for (Cart o : listCart) {
            if (o.equals(cart)) {
                // TODO : da co san pham trong gio hang
                o.setQuantity(o.getQuantity() + cart.getQuantity());
                isExits=true;
                break;
            }

        }
        if (!isExits) {
            listCart.add(cart);
        }
        // lua du lieu vao file
        userLogin.setCart(listCart);
        userService.save(userLogin);
    }

    // TODO : loc ra ra Cart theo Id product
    public Cart findByProductId(int id) {
        for (Cart ci : userLogin.getCart()) {
            if (ci.getProduct().getProductId() == id) {
                return ci;
            }
        }
        return null;
    }

    // TODO : loc ra cart theo id cart
    public Cart findById(int id) {
        for (Cart ci : userLogin.getCart()) {
            if (ci != null && ci.getCartId() == id) {
                return ci;
            }
        }
        return null;
    }

    // TODO : xoa
    public void delete(int id) {
        userLogin.getCart().remove(findById(id));
        userService.save(userLogin);
    }

    public void clearAll() {
        userLogin.setCart(new ArrayList<>());
        userService.save(userLogin);
    }

    public void addToCart() {
        DataBase<User> userDataBase = new DataBase<>();
        List<User> list = userDataBase.readFormFile(DataBase.USER_PATH);
        User userLogin = userService.userCart();
        boolean isExit = false ;
        for (User user: list) {
            if (user.getUserId() == userLogin.getUserId()) {
                isExit = true ;
                break;
            }
        }
        if (!isExit) {
            cartController = new CartController(userService.userLogin());
            showCart();
            System.out.println("Nhap vao id san pham them vao gio hang ");
            int idPro = InpustMethods.getInteger();
            Product pro = productController.findById(idPro);
            if (pro == null) {
                System.err.println("Khong tim thay san pham trong danh sach");
            } else {
                Cart cart = new Cart();
                cart.setCartId(getNewId());
                cart.setProduct(pro);
                while (true) {
                    System.out.println("Nhap vao so luong muon them vao gio hang");
                    int count = InpustMethods.getInteger();
                    if (count > cart.getProduct().getQuantity()) {
                        System.err.println("So luong nay lon hon hang chung toi dang co , Xin thong cam, vui long giam so luong xuong  ");
                    } else {
                        cart.setQuantity(count);
                        save(cart);
                        break;
                    }
                }
            }
        }
//

    }

    public void showCart() {
        if (userLogin != null && userLogin.getCart() != null) {
            User userCart = userService.userCart();
            List<Cart> carts = userCart.getCart();
            if (carts.isEmpty()) {
                System.out.println("Giỏ hàng rỗng");
            } else {
                for (Cart ci : carts) {
                    ci.setProduct(productController.findById(ci.getProduct().getProductId()));
                    ci.display();
                }
            }
        } else {
            System.err.println("Rỗng");
        }
    }

    public void changQuantity() {
        System.out.println("Nhap vao Id ");
        int idCart = InpustMethods.getInteger();
        Cart cartItem = findById(idCart) ;
        if (cartItem == null) {
            System.err.println("San pham khong ton tai trong gio hang ");
            return;
        }
        System.out.println("Nhap vao so luong");
        int updateQuantity = InpustMethods.getInteger();
        if (updateQuantity > cartItem.getProduct().getQuantity()) {
            System.err.println("So luong nay lon hon so luong hang chung toi co. Xin thong cam , cam on ban");
        } else {
            cartItem.setQuantity(updateQuantity);
            userService.save(userLogin);
        }
    }

    public void deleteItemProduct() {
        cartController=new CartController(userLogin);
        System.out.println("Nhap vao Id");
        int idCart = InpustMethods.getInteger();
        Cart cart = findById(idCart);
        if (cart == null) {
            System.err.println("San pham khong ton tai trong gio hang ");
            return;
        }
        cartController.delete(idCart);
    }
}
