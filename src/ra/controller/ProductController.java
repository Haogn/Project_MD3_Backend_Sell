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
    } // TODO : Id tu tang
    public void getAll(){ // TODO : Lay toan bo san pham
        productService.getAll();
    }

    public void add() {
        productService.add();
    } // TODO : them moi san pham
    public void createProduct(){ // TODO : Them moi 1 hoac nhieu san pham
        productService.createProduct();
    }

    public void searchProductByName(){ // TODO : Tim kiem san pham theo ten
         productService.searchProductByName();
    }
    public Product findById(int id) { // TODO : Tra ve san pham theo Id
        return productService.findById(id) ;
    }

    public void save(Product product) { // TODO : Lua lai thong tin san pham khi co su thay doi
        productService.save(product);
    }
    public void updateProduc(){ // TODO : thay doi thon tin san pham
        productService.updateProduc();
    }
    public void deletePro() { // TODO :  xoa san pham khoi gio hang
        productService.deletePro();
    }
}
