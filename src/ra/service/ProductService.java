package ra.service;

import ra.config.InpustMethods;
import ra.controller.CatalogController;
import ra.model.Catalog;
import ra.model.Product;
import ra.model.User;
import ra.util.DataBase;

import java.util.ArrayList;
import java.util.List;

import static ra.view.Admin.*;


public class ProductService {
    private List<Product> listProduct ;
    private DataBase<Product> productData =new DataBase<>();
    private CatalogController catalogController = new CatalogController();

    public ProductService(){
        List<Product> list= productData.readFormFile(DataBase.PRODUCT_PATH);
        if(list ==null){
            list=new ArrayList<>();
        }
        this.listProduct = list;// du lieu doc tu file
    }

    public int getNewId() { // TODO : Id tu tang
        int max = 0;
        for (Product pt : listProduct) {
            if (pt.getProductId() > max) {
                max = pt.getProductId();
            }
        }
        return max + 1;
    }

    public void getAll() { // TODO : show toan bo san pham
        for (Product product : listProduct) {
            product.displayProduct();
        }
    }

    public void add() { // TODO : Them moi san pham
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
        System.out.println("Danh muc san pham thuoc ve ");
        if (catalogController.findAll().isEmpty()) {
            System.out.println("Danh muc san pham dang rong . Can them danh muc san pham cho san pham" );
            Catalog newCatalog =  catalogController.createCatalog();
            List<Catalog> list = new ArrayList<>();
            list.add(newCatalog);
            DataBase<Catalog> catalogDataBase = new DataBase<>();
            catalogDataBase.writeToFile(list , DataBase.CATALOG_PATH);
            newProduct.setCatalog(newCatalog);
        } else {
           for (Catalog ca : catalogController.findAll()){
               ca.displayCatalog();
           }
            System.out.println("Nhap vao Id danh muc san pham thuoc ve ");
           int idCatalog = InpustMethods.getInteger();
           boolean isDulicate = false;
           for (Catalog ca : catalogController.findAll()){
               if(ca.getCatalogId() == idCatalog) {
                   newProduct.setCatalog(ca);
                   isDulicate= true;
                   break;
               }
           }
           if(!isDulicate) {
               System.err.println("San pham khong thuoc ve nhom san pham ");
           }
        }
        if (newProduct.getQuantity() > 0 ) {
            newProduct.setProductStatus(true);
        } else {
            newProduct.setProductStatus(false);
        }
        listProduct.add(newProduct);
        productData.writeToFile(listProduct,DataBase.PRODUCT_PATH);
    }
    public void createProduct(){ // TODO : Them 1 hoac nhieu san pham
        System.out.println("Nhap so luong san pham muon them vao danh sach");
        int n = InpustMethods.getInteger();
        for (int i = 0; i < n; i++) {
            add();
        }
    }

    public void searchProductByName() { // TODO : Tim kiem san pham theo ten
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

    public Product findById(int id) { // TODO : tra ve san pham theo Id
        for (Product pt : listProduct) {
            if (pt.getProductId() == id ) {
                return pt ;
            }
        }
        return null;
    }

    public void save(Product product) { // TODO : luu lai thong tin san pham thi co thay doi
        if (findById(product.getProductId()) == null) {
            listProduct.add(product);
        } else {
            listProduct.set(listProduct.indexOf(findById(product.getProductId())), product);
        }
        productData.writeToFile(listProduct, DataBase.PRODUCT_PATH);
    }
    public void updateProduc(){ // TODO : Update san pham
        System.out.println("Nhap vao Id san pham can chinh sua");
        int id = InpustMethods.getInteger();
        Product updateProduct = findById(id) ;
        if ( updateProduct == null ) {
            System.err.println("Khong tim thay san pham trong danh sach ");
            return;
        }

        System.out.println("Ten moi cua san pham ");
        updateProduct.setProductName(InpustMethods.getString());
        System.out.println("Mo ta moi cua san pham");
        updateProduct.setDescribe(InpustMethods.getString());
        System.out.println("Gia ban : " );
        double priceImport = InpustMethods.getDouble();
        updateProduct.setImportPrice(priceImport);
        double priceExport = priceImport * 1.5;
        updateProduct.setExportPrice(priceExport);
        updateProduct.setProfit(priceExport-priceImport);
        System.out.println("So luong hang nhap vao : ");
        updateProduct.setQuantity(InpustMethods.getInteger());
        System.out.println("Danh muc san pham thuoc ve ");
        if (catalogController.findAll().isEmpty()) {
            System.out.println("Danh muc san pham dang rong . Can them danh muc san pham cho san pham" );
            Catalog newCatalog =  catalogController.createCatalog();
            List<Catalog> list = new ArrayList<>();
            list.add(newCatalog);
            DataBase<Catalog> catalogDataBase = new DataBase<>();
            catalogDataBase.writeToFile(list , DataBase.CATALOG_PATH);
            updateProduct.setCatalog(newCatalog);
        } else {
            for (Catalog ca : catalogController.findAll()){
                ca.displayCatalog();
            }
            System.out.println("Nhap vao Id danh muc san pham thuoc ve ");
            int idCatalog = InpustMethods.getInteger();
            boolean isDulicate = false;
            for (Catalog ca : catalogController.findAll()){
                if(ca.getCatalogId() == idCatalog) {
                    updateProduct.setCatalog(ca);
                    isDulicate= true;
                    break;
                }
            }
            if(!isDulicate) {
                System.err.println("San pham khong thuoc ve nhom san pham ");
            }
        }
        if (updateProduct.getQuantity() > 0 ) {
            updateProduct.setProductStatus(true);
        } else {
            updateProduct.setProductStatus(false);
        }
        save(updateProduct);
    }

    public void deletePro() { // TODO : Xoa san pham khoi danh sach
        System.out.println("Nhap vao Id san pham can xoa khoi danh sach");
        int idDelete = InpustMethods.getInteger();
        Product deleteProduct = findById(idDelete) ;
        if ( deleteProduct == null) {
            System.err.println("Khong tim thay san pham can xoa trong danh sach ");
            return;
        }
        System.out.println("Ban muon chac chan muon xoa san pham danh sach chu");
        System.out.println("1. Co           2. khong  ");
        System.out.println("Lua chon cua ban : ");
        int select = InpustMethods.getInteger();
        if ( select == 1 ) {
            listProduct.remove(deleteProduct);
            System.out.println("Xoa san pham thanh cong !!! ");
            productData.writeToFile(listProduct, DataBase.PRODUCT_PATH);
        } else if (select == 2){
            ProductManagement();
        }
    }

}
