package ra.controller;

import ra.model.Product;
import ra.service.ProductService;

public class ProductController {
    private ProductService productService;
    public ProductController(){
        productService = new ProductService();
    }
    public int getNewId(){
        return productService.getNewId();
    }
    public void getAll(){
        productService.getAll();
    }

    public void add() {
        productService.add();
    }

    public void searchProductByName(){
         productService.searchProductByName();
    }
    public Product findById(int id) {
        return productService.findById(id) ;
    }

    public void save(Product product) {
        productService.save(product);
    }
}
