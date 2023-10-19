package ra.service;

import ra.config.InpustMethods;
import ra.model.Product;
import ra.model.User;
import ra.util.DataBase;

import java.util.ArrayList;
import java.util.List;



public class ProductService {
    private List<Product> listProduct ;
    private DataBase<Product> productData =new DataBase<>();

    public ProductService(){
        List<Product> list= productData.readFormFile(DataBase.PRODUCT_PATH);
        if(list ==null){
            list=new ArrayList<>();
        }
        this.listProduct = list;// du lieu doc tu file
    }
    public int getNewId() {
        int max = 0;
        for (Product pt : listProduct) {
            if (pt.getProductId() > max) {
                max = pt.getProductId();
            }
        }
        return max + 1;
    }

    public void getAll() {
        for (Product product : listProduct) {
            product.displayProduct();
        }
    }

    public void add() {
        Product newProduct = new Product();
        newProduct.setProductId(getNewId());
        System.out.println("Id san pham : " + newProduct.getProductId());
        System.out.println("Nhap vao ten san pham ");
        newProduct.setProductName(InpustMethods.getString());
        System.out.println("Mo ta san pham ");
        newProduct.setDescribe(InpustMethods.getString());
        System.out.println("Gia nhap  : ");
        double priceImprot = InpustMethods.getDouble();
        newProduct.setImportPrice(priceImprot);
        double priceExprot = priceImprot * 1.5 ;
        newProduct.setExportPrice(priceExprot);
        newProduct.setProfit(priceExprot - priceImprot);
        System.out.println("So luong hang nhap vao : ");
        newProduct.setQuantity(InpustMethods.getInteger());
        listProduct.add(newProduct);
        productData.writeToFile(listProduct,DataBase.PRODUCT_PATH);
    }

    public void searchProductByName() {
        List<Product> list = productData.readFormFile(DataBase.PRODUCT_PATH);
        System.out.println("Nhap vao ten san pham can tim kiem ");
        String name = InpustMethods.getString();
        boolean isSearch = false ;
        for (Product product : list) {
            if( product.getProductName().toLowerCase().trim().contains(name.trim().toLowerCase())){
                 product.displayProduct();
                 isSearch = true ;
            }
        }
        if (!isSearch) {
                System.err.println("Khong tim thay san pham ten " + name +". Vui long nhap lai ❤");
                searchProductByName();
        }
    }

    public Product findById(int id) {
        for (Product pt : listProduct) {
            if (pt.getProductId() == id ) {
                return pt ;
            }
        }
        return null;
    }

    public void save(Product product) {
        if (findById(product.getProductId()) == null) {
            listProduct.add(product);
        } else {
            listProduct.set(listProduct.indexOf(findById(product.getProductId())), product);
        }
        productData.writeToFile(listProduct, DataBase.PRODUCT_PATH);
    }

}
