package ra.view;

import ra.config.InpustMethods;
import ra.controller.CartController;
import ra.controller.OrderController;
import ra.controller.ProductController;
import ra.controller.UserController;
import ra.service.ProductService;
import ra.service.UserService;


public class UI {
    private static ProductController productController = new ProductController();

    private static UserController userController  = new UserController();
    private static CartController cartController = new CartController(userController.userLogin());
    private static OrderController orderController = new OrderController();

    public static void menuStore(){
        int choice ;
        do {
            // TODO :
            System.out.println("╔══════════════════════════════════════╗");
            System.out.println("║        WELCOME TO HAOGN'S STORE      ║");
            System.out.println("║--------------------------------------║");
            System.out.println("║   1    Dang nhap                     ║");
            System.out.println("║   2    Dang ky                       ║");
            System.out.println("║   3    Thoat khoi chuong trinh       ║");
            System.out.println("║                                      ║");
            System.out.println("╚══════╧═══════════════════════════════╝");
            System.out.println("Nhap lua chon cua ban : ");

            choice = InpustMethods.getInteger();

            switch (choice) {
                case 1 : // TODO : Dang nhap
                    userController.login();
                    break;
                case 2 : // TODO : Dang ky
                    userController.register();
                    break;
                case 3 :
                    System.err.println("🖐🏻 Thoat khoi chuong trinh . Hen gap lai ❤");
                    break;
                default:
                    System.err.println("❌❌❌ Lua chon khong phu hop. Vui long chon lai ❤ ");
            }
        } while (choice != 3) ;
    }

    public static void menuUser() {
        int choice ;
        do {
            System.out.println("╔══════════════════════════════════════╗");
            System.out.println("║             Nguoi Dung               ║");
            System.out.println("║--------------------------------------║");
            System.out.println("║   1    Hien thi danh sach san pham   ║");
            System.out.println("║   2    Tim kiem san pham theo ten    ║");
            System.out.println("║   3    Mua hang                      ║");
            System.out.println("║   4    Quan ly gio hang              ║");
            System.out.println("║   5    Lich su mua hang              ║");
            System.out.println("║   6    Thay doi thong tin ca nhan    ║");
            System.out.println("║   7    Thay doi mat khau             ║");
            System.out.println("║   0    Dang xuat                     ║");
            System.out.println("╚══════╧═══════════════════════════════╝");
            System.out.println("Nhap lua chon cua ban :   ");
            choice = InpustMethods.getInteger();
            switch (choice){
                case 1: // TODO : Hien thi danh sach san pham
                    productController.getAll();
                    break;
                case 2: // TODO : Tim kiem san pham theo ten
                    productController.searchProductByName();
                    break;
                case 3: // TODO : Them san pham vao gio hang
                    cartController.addToCart();
                    break;
                case 4: // TODO : Quan ly gio hang
                    menuCart();
                    break;
                case 5: // TODO : Lich sua mua hang
                    menuOderHitStory();
                    break;
                case 6: // TODO : Thay doi thong tin ca nhan
                    userController.profileChange(userController.userLogin().getUserId());
                    break;
                case 7: // TODO : Thay doi mat khau
                    userController.changePassword(userController.userLogin().getUserId());
                    break;
                case 0 :
                    userController.checkout();
                    return;
                default:
                    System.err.println("❌❌❌ Lua chon khong phu hop. Vui long chon lai ❤ ");
            }

        } while (true) ;
    }

    public static void menuCart(){
        int selectCart ;
        while (true){
            System.out.println("╔══════════════════════════════════════╗");
            System.out.println("║              Gio hang                ║");
            System.out.println("╟────────┬─────────────────────────────╢");
            System.out.println("║   1    │   Xem danh sach gio hang    ║");
            System.out.println("║   2    │   Chinh sua so luong        ║");
            System.out.println("║   3    │   Xoa 1 san pham            ║");
            System.out.println("║   4    │   Xoa toan bo san pham      ║");
            System.out.println("║   5    │   Thanh toan                ║");
            System.out.println("║   6    │   Thoat                     ║");
            System.out.println("╚════════╧═════════════════════════════╝");
            System.out.println("Nhap lua chon cua ban : ");
            selectCart = InpustMethods.getInteger();
            switch (selectCart){
                case 1: // TODO : Hien thi gio hang
                    cartController.showCart();
                    break;
                case 2: // TODO : Thay doi so luong san pham trong gio hang
                    cartController.changQuantity();
                    break;
                case 3: // TODO : Xoa 1 san pham khoi gio hang
                    cartController.deleteItemProduct();
                    break;
                case 4: // TODO : Xoa toan bo san pham khoi gio hang
                    cartController.clearAll();
                    break;
                case 5: // TODO : Thanh toan
                    orderController.endPay();
                    break;
                case 6 :
                    menuUser();
                default:
                    System.err.println("❌❌❌ Lua chon khong phu hop. Vui long chon lai ❤ ");
            }
        }
    }

    public static void menuOderHitStory(){
        while (true) {
            System.out.println("╔══════════════════════════════════════╗");
            System.out.println("║          Don hang-Lich su mua han    ║");
            System.out.println("╟────────┬─────────────────────────────╢");
            System.out.println("║   1    │  Hien thi tat ca don hang   ║");
            System.out.println("║   2    │  Dơn hang dang cho          ║");
            System.out.println("║   3    │  Don hang da xac nhan       ║");
            System.out.println("║   4    │  Don hang da huy            ║");
            System.out.println("║   5    │  Chi tiet don hang          ║");
            System.out.println("║   6    │  Thoat                      ║");
            System.out.println("╚════════╧═════════════════════════════╝");
            int choice = InpustMethods.getInteger();
            switch (choice) {
                case 1: // TODO : Hien thi tat ca don hang
                    orderController.showAllOrder();
                    break;
                case 2: // TODO : Don hang dang cho xac nhan
                    orderController.showOrderByCode((byte) 0);
                    break;
                case 3: // TODO : Don hang da da xac nhan
                    orderController.showOrderByCode((byte) 1);
                    break;
                case 4: // TODO : HUy don hang
                    orderController.showOrderByCode((byte) 2);
                    break;
                case 5: // TODO : Chi tiet don hang
                    orderController.showOrderDetail();
                    break;
                case 6:
                    return;
                default:
                    System.err.println("❌❌❌ Lua chon khong phu hop. Vui long chon lai ❤ ");
            }
        }

    }


}
