package ra.view;


import ra.model.Product;
import ra.service.ProductService;
import ra.service.UserService;
import ra.util.DataBase;

import java.util.ArrayList;
import java.util.List;

public class StoreHome {


    public static void main(String[] args) {

        // TODO fix cung du lieu san pham
        List<Product> list = new ArrayList<>() ;
        list.add(new Product(1, "Iphone 15","Promax Titanium 1TB", 30000 ,30000*1.5, (30000*1.5)-30000 ,true , 100));
        list.add(new Product(2, "Iphone 14","Promax Slim 512G", 25000 , 25000*1.5, (25000*1.5)-25000,true , 100));
        list.add(new Product(3, "Iphone 13","Promax Gray 128G", 20000 ,20000*1.5, (20000*1.5)-20000, true , 100));
        list.add(new Product(4, "Samsunng Galaxy S23 Ultra","Gray 512G", 21000 ,21000*1.5,(21000*1.5)-21000, true , 100));
        list.add(new Product(5, "Samsung Galaxy Z Fold5","Black 12GB 256GB", 30000 ,30000*1.5, (30000*1.5)-30000 ,true , 100));
        list.add(new Product(6, "Samsung Galaxy Z Flip5","Purple 256G", 22000 ,22000*1.5, (22000*1.5)-22000, true , 100));
        list.add(new Product(7, "Xiaomi 13T Pro","Black 512GB", 15000 ,22000*1.5, (22000*1.5)-22000, true , 100));
        list.add(new Product(8, "Xiaomi Redmi Note 12","Titanium 4GB 128GB", 8900 ,8900*1.5, (8900*1.5)-8900,  true , 100));
        list.add(new Product(9, "Xiaomi Redmi 12C","Green 4GB 64GB", 5000 ,5000*1.5 ,(5000*1.5)-5000,true , 100));
        list.add(new Product(10, "OPPO Reno8 T 5G","Gray (8GB - 128GB)", 3000 ,3000*1.5 , (3000*1.5)-3000,true , 100));
        list.add(new Product(11, "OPPO Reno8","Gold 4GB 64GB", 7000 ,7000*1.5, (7000*1.5)-7000, true , 100));
        list.add(new Product(12, "OPPO Reno8 5G","Black 8GB 256GB", 6500 ,6500*1.5, (6500*1.5)-6500, true , 100));
       DataBase<Product> productDataBase = new DataBase<>();
       productDataBase.writeToFile(list, DataBase.PRODUCT_PATH);
//        UserService userService = new UserService();
        UI.menuStore();




    }
}
