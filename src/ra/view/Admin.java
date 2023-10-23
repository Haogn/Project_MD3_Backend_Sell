package ra.view;

import ra.config.InpustMethods;
import ra.controller.CatalogController;
import ra.controller.OrderController;
import ra.controller.ProductController;
import ra.controller.UserController;


public class Admin {
    private static UserController userController = new UserController();
    private static CatalogController catalogController = new CatalogController();
    private static ProductController productController = new ProductController();
    private static OrderController orderController = new OrderController();

    // TODO : ➖➖➖➖➖ Admin ➖➖➖➖➖➖➖
    public static void menuAdmin() {
        int selectAdmin ;
        do {
            System.out.println("╔════════════════════════════════════════╗");
            System.out.println("║              Quan tri vien             ║");
            System.out.println("╟────────┬───────────────────────────────╢");
            System.out.println("║   1    │ Quan ly tai khoan nguoi dung  ║");
            System.out.println("║   2    │ Quan ly danh muc              ║");
            System.out.println("║   3    │ Quan ly san pham              ║");
            System.out.println("║   4    │ Quan ly dat hang              ║");
            System.out.println("║   5    │ Dang xuat                     ║");
            System.out.println("║        │                               ║");
            System.out.println("╚════════╧═══════════════════════════════╝");
            System.out.println("Nhap lua chon cua ban : ");
            selectAdmin = InpustMethods.getInteger();
            switch (selectAdmin){
                case 1 : // TODO : Quan ly tai khoan nguoi dung
                    UserManagement();
                    break;
                case 2 : // TODO : Quan ly danh muc san pham
                    CatalongManagament();
                    break;
                case 3 : // TODO : Quan ly san pham
                    ProductManagement();
                    break;
                case 4 : // TODO : Quan ly cac don dat hang
                    OrderManagement();
                    break;
                case 5 :
                    userController.checkout();
                    break;
            }
        } while (selectAdmin != 5);
    }

    private static void OrderManagement() {
        System.out.println("╔══════════════════════════════════════╗");
        System.out.println("║            Quan ly don hang          ║");
        System.out.println("╟────────┬─────────────────────────────╢");
        System.out.println("║   1    │ Hien thi don hang nguoi dung║");
        System.out.println("║   2    │ Xac nhan don hang           ║");
        System.out.println("║   3    │ Thoat                       ║");
        System.out.println("╚════════╧═════════════════════════════╝");
        System.out.println("Enter your choice:                       ");
        int choice = InpustMethods.getInteger();
        switch (choice) {
            case 1 : // TODO : Hien thi don hang cua cua nguoi dung
                orderController.showOrderUsertoAdmin();
                break;
            case 2 : // TODO : Xac nhan don hang
                orderController.OrderConfirm();
                break;
            case 3:
                return;
            default:
                System.err.println("❌❌❌ Lua chon khong phu hop. Vui long chon lai ❤ ");
        }
    }

    public static void UserManagement(){
        while (true) {
            System.out.println("╔══════════════════════════════════════╗");
            System.out.println("║          Quan ly nguoi dung          ║");
            System.out.println("╟────────┬─────────────────────────────╢");
            System.out.println("║   1    │ Hien thi toan bo nguoi dung ║");
            System.out.println("║   2    │ Khoa / Mo tai khoan         ║");
            System.out.println("║   3    │ Thoat                       ║");
            System.out.println("╚════════╧═════════════════════════════╝");
            System.out.println(" Nhap lua chon cua ban :  ");
            int choice = InpustMethods.getInteger();
            switch (choice){
                case 1: // TODO : Hien thi toan bo nguoi dung
                    userController.showAllCout();
                    break;
                case 2: // TODO : Khoa hoac mo tai khoan
                    userController.toggleIsActiveUser();
                    break;
                case 3:
                    return;
                default:
                    System.err.println("❌❌❌ Lua chon khong phu hop. Vui long chon lai ❤ ");
            }
        }
    }

    public static void CatalongManagament(){
        while (true){
            System.out.println("╔══════════════════════════════════════╗");
            System.out.println("║      Quan ly danh muc san pham       ║");
            System.out.println("╟────────┬─────────────────────────────╢");
            System.out.println("║   1    |  Hien thi danh muc          ║");
            System.out.println("║   2    │  Them moi danh muc          ║");
            System.out.println("║   3    │  Thay doi danh muc          ║");
            System.out.println("║   4    │  Xoa danh muc               ║");
            System.out.println("║   5    │  Tim kiem danh muc theo ten ║");
            System.out.println("║   6    │  Thoat                      ║");
            System.out.println("╚════════╧═════════════════════════════╝");
            System.out.println("Enter your choice:                       ");
            int choice = InpustMethods.getInteger();
            switch (choice){
                case 1 : // Hien thi danh muc san pham
                    catalogController.getAll();
                    break;
                case 2 : // Them moi danh muc san pham
                    catalogController.createCatalog();
                    break;
                case 3 : // TODO : Update danh muc
                    catalogController.updateCatalog();
                    break;
                case 4 : // TODO : Xoa danh muc san pham
                    catalogController.deleteCatalog();
                    break;
                case 5 : // Tim kiem san pham theo ten
                    catalogController.searchCatalogByName();
                    break;
                case 6 :
                    return;
                default:
                    System.err.println("❌❌❌ Lua chon khong phu hop. Vui long chon lai ❤ ");
            }
        }
    }

    public static void ProductManagement(){
        while (true){
            System.out.println("╔══════════════════════════════════════╗");
            System.out.println("║          Quan ly san pham            ║");
            System.out.println("╟────────┬─────────────────────────────╢");
            System.out.println("║   1    │ Hien thi san pham           ║");
            System.out.println("║   2    │ Them moi san pham           ║");
            System.out.println("║   3    │ Sua doi san pham            ║");
            System.out.println("║   4    │ Xoa san pham                ║");
            System.out.println("║   5    │ Tim kiem san pham theo ten  ║");
            System.out.println("║   6    │ Thoat                       ║");
            System.out.println("╚════════╧═════════════════════════════╝");
            System.out.println("Nhap lua chon cua ban :    ");
            int choice = InpustMethods.getInteger();
            switch (choice){
                case 1: // TODO : Hien thi toan bo san pham
                    productController.getAll();
                    break;
                case 2: // TODO : Them moi san pham
                    productController.createProduct();
                    break;
                case 3: // TODO : Update san pham
                    productController.updateProduc();
                    break;
                case 4: // TODO : Xoa san pham
                    productController.deletePro();
                    break;
                case 5: // TODO : Tim kiem san pham theo ten
                    productController.searchProductByName();
                    break;
                case 6:
                    return;
                default:
                    System.err.println("❌❌❌ Lua chon khong phu hop. Vui long chon lai ❤ ");
            }
        }
    }

}
