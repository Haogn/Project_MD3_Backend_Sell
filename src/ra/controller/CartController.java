package ra.controller;

import ra.model.Cart;
import ra.model.User;
import ra.service.CartService;

import java.util.List;

public class CartController {
    private CartService cartService;
    public CartController(User userLogin){
        this.cartService = new CartService(userLogin);
    }
    public int getNewId(){
        return cartService.getNewId();
    }

    public List<Cart> findAll() {
        return cartService.findAll();
    }

    public void save(Cart cart) {
        cartService.save(cart);
    }

    public Cart findByProductId(int id) {
        return cartService.findByProductId(id);
    }

    public Cart findById(int id) {
        return cartService.findById(id);
    }

    public void delete(int id) {
        cartService.delete(id);
    }

    public  void clearAll(){
        cartService.clearAll();
    }
    public void addToCart(){
        cartService.addToCart();
    }
    public void showCart(){
        cartService.showCart();
    }

    public void changQuantity(){
        cartService.changQuantity();
    }
    public void deleteItemProduct(){
        cartService.deleteItemProduct();
    }

}
