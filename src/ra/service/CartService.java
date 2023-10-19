package ra.service;

import ra.config.InpustMethods;
import ra.controller.CartController;
import ra.controller.ProductController;
import ra.model.Cart;
import ra.model.Product;
import ra.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.PrimitiveIterator;

public class CartService {
    private User userLogin ;
    private UserService userService;
    private CartController cartController ;
    private Cart cart ;
    private ProductController productController = new ProductController();
    public CartService(User userLogin) {
        this.userLogin = userLogin;
        userService= new UserService();
    }

    // TODO : Id tu tang
    public int getNewId(){
        int max = 0;
        for (Cart ci: userLogin.getCart()) {
            if(ci.getCartId() > max){
                max= ci.getCartId();
            }
        }
        return max+1;
    }

    // TODO : lay ra tat ca
    public List<Cart> findAll() {
        return userLogin.getCart();
    }

    // // TODO : lua hoac tahy doi
    public void save(Cart cart) {

        List<Cart> carts = userLogin.getCart();
        if (findById(cart.getCartId()) == null) {
            // TODO : them moi
           this.cart = findByProductId(cart.getProduct().getProductId());
           if (this.cart != null) {
               if (carts.contains(this.cart)) {
                   // TODO : da co san pham trong gio hang
                   this.cart.setQuantity(this.cart.getQuantity() + cart.getQuantity());
               } else {
                   carts.add(cart);
               }
           } else {
               // TODO : chua co san pham trong gio hang
               carts.add(cart) ;
           }
        }
        // lua du lieu vao file
        userService.save(userLogin);
    }

    // TODO : loc ra ra Cart theo Id product
    public Cart findByProductId(int id) {
        for (Cart ci : userLogin.getCart()) {
            if (ci.getProduct().getProductId() == id) {
                return ci ;
            }
        }
        return null;
    }

    // TODO : loc ra cart theo id cart
    public Cart findById(int id) {
        for (Cart ci : userLogin.getCart()){
            if (ci!=null && ci.getCartId() == id) {
                return ci ;
            }
        }
        return null ;
    }

    // TODO : xoa
    public void delete(int id) {
        userLogin.getCart().remove(findById(id)) ;
        userService.save(userLogin);
    }

    public  void clearAll(){
        userLogin.setCart(new ArrayList<>());
        userService.save(userLogin);
    }
    public void addToCart(){
        cartController = new CartController(userService.userLogin());
        showCart();
        System.out.println("Nhap vao id san pham them vao gio hang ");
        int idPro = InpustMethods.getInteger();
        Product pro = productController.findById(idPro) ;
        if (pro == null ) {
            System.err.println("Khong tim thay san pham trong danh sach");
        } else {
            Cart cart = new Cart();
            cart.setCartId(getNewId());
            cart.setProduct(pro);
            while (true){
                System.out.println("Nhap vao so luong muon them vao gio hang");
                int count = InpustMethods.getInteger();
                if ( count > cart.getProduct().getQuantity()) {
                    System.err.println("So luong nay lon hon hang chung toi dang co , Xin thong cam, vui long giam so luong xuong  ");
                } else {
                    cart.setQuantity(count);
                    cartController.save(cart);
                    break;
                }
        }


//            } else if (cout == cart.getProduct().getQuantity()) {
//                cart.getProduct().setQuantity(0);
//                cart.getProduct().setProductStatus(false);
//                System.out.println("Kho hang da het");
//                break;
//            } else {
//                int salary = cart.getProduct().getQuantity() - cout;
//                cart.setQuantity(cout);
//                cart.getProduct().setQuantity(salary);
//                if (cart.getProduct().getQuantity() == 0 ) {
//                    cart.getProduct().setProductStatus(false);
//                    System.out.println("Kho hang da het ");
//                }
//                break;
//            }
        }

    }

    public void showCart(){
        User user = userService.userLogin();
        if (user.getCart().isEmpty()){
            System.out.println("Gio hang rong ");
        } else {
            for (Cart ci : user.getCart()) {
                ci.setProduct(productController.findById(ci.getProduct().getProductId()));
                ci.display();
            }
        }
    }

    public void changQuantity(){
        System.out.println("Nhap vao Id ");
        int idCart = InpustMethods.getInteger();
        this.cartController=new CartController(userLogin);
        this.cart = cartController.findById(idCart);
        if (this.cart == null ) {
            System.err.println("San pham khong ton tai trong gio hang ");
           return;
        }
        System.out.println("Nhap vao so luong");
        int updateQuantity = InpustMethods.getInteger();
        if (updateQuantity > cart.getProduct().getQuantity() ){
            System.err.println("So luong nay lon hon so luong hang chung toi co. Xin thong cam , cam on ban");
        } else {
            cart.setQuantity(updateQuantity);
            cartController.save(cart);
        }
    }

    public void deleteItemProduct(){
        System.out.println("Nhap vao Id");
        int idCart = InpustMethods.getInteger();
        Cart cart = cartController.findById(idCart);
        if (cart == null) {
            System.err.println("San pham khong ton tai trong gio hang ");
            return;
        }
        cartController.delete(idCart);
    }

}
