package ra.service;

import ra.config.InpustMethods;
import ra.config.Role;
import ra.model.Address;
import ra.model.User;
import ra.util.DataBase;
import ra.view.Admin;
import ra.view.UI;

import java.util.ArrayList;
import java.util.List;


import static ra.view.UI.*;

public class UserService {
    private UserService userService;
    private static List<User> listUser;
    private DataBase<User> userData = new DataBase<>();

    public UserService() {
        List<User> list = userData.readFormFile(DataBase.USER_PATH);
        if (list == null) {
            list = new ArrayList<>();
        }
        this.listUser = list;// du lieu doc tu file
    }

    // TODO : id tu tang
    public int getNewId() {
        int max = 0;
        for (User u : listUser) {
            if (u.getUserId() > max) {
                max = u.getUserId();
            }
        }
        return max + 1;
    }

    // TODO : dang ky
    public void register() {
        System.out.println("➖➖➖➖➖➖➖➖➖➖➖➖ Dang Ky ➖➖➖➖➖➖➖➖➖➖➖➖➖➖");
        User newUser = new User();
        newUser.setUserId(getNewId());
        System.out.println("Id nguoi dung :" + newUser.getUserId());
        while (true) {
            System.out.println("Nhap vao ten nguoi dung - ( it nhat 5 ky tu ) :");
            String name = InpustMethods.getUseName();
            boolean isDuplicate = false;
            for (User user : listUser) {
                if (user.getUserName().equals(name.trim())) {
                    System.err.println("Ten nguoi dung da ton tai trong danh sach . Vui long nhap lai");
                    isDuplicate = true;
                    break;
                }
            }
            if (!isDuplicate) {
                newUser.setUserName(name);
                break;
            }
        }
        System.out.println("Nhap vao email :");
        newUser.setEmail(InpustMethods.getEmail());
        System.out.println("Nhap vao password : - ( Bao gom it nhat 8 ky tu , co 1 chu cai in hoa , 1 ky tu dac biet va khong chua khoang trang ) ");
        newUser.setPassword(InpustMethods.getPassword());
        System.out.println("Nhap vao so dien thoai: ");
        newUser.setPhone(InpustMethods.getPhoneNumber());
        Address newAddress = new Address();
        System.out.println("Nhap vao thanh pho :");
        newAddress.setCity(InpustMethods.getString());
        System.out.println("Nhap vao quan / huyen :");
        newAddress.setDistrict(InpustMethods.getString());
        System.out.println("Nhap vao dia chi cu the ");
        newAddress.setSpecifically(InpustMethods.getString());
        newUser.setAddress(newAddress);
        newUser.setUserStatus(false);
        newUser.setActive(true);
        newUser.setRoles(Role.USER);
        listUser.add(newUser);
        DataBase<User> userDataBase = new DataBase<>();
        userDataBase.writeToFile(listUser, DataBase.USER_PATH);
        // dang ky thanh cong dieu huong lai den menu store
        System.out.println("Dang ki thanh cong");
        login();
    }

    // TODO : dang nhap
    public void login() {
        System.out.println("➖➖➖➖➖➖➖➖➖➖➖➖ Dang Nhap ➖➖➖➖➖➖➖➖➖➖➖➖➖➖");
        DataBase<User> userDataBase = new DataBase<>();
        List<User> userLogin = userDataBase.readFormFile(DataBase.USER_PATH);

        while (true) {
            System.out.println("Nhap vao email");
            String emailLogin = InpustMethods.getEmail();
            boolean userFound = false;

            for (User user : userLogin) {
                if (user.getEmail().equals(emailLogin)) {
                    userFound = true;
                    if (user.isActive()) {
                        System.out.println("Nhap vao password:");
                        String pasLogin = InpustMethods.getPassword();

                        if (user.getPassword().equals(pasLogin)) {
                            user.setUserStatus(true);

                            if (user.getRoles() == Role.USER) {
                                System.out.println("Dang nhap thanh cong");
                                // Redirect to the user menu
                                menuUser();
                            } else if (user.getRoles() == Role.ADMIN) {
                                System.out.println("Dang nhap thanh cong");
                                // Redirect to the admin menu
                                Admin.menuAdmin();
                            }
                        } else {
                            System.err.println("Password khong trung khop. Vui long nhap lai ❤");
                        }
                    } else {
                        System.err.println("Tai khoan chua kich hoat. Vui long lien he voi quan tri vien.");
                    }
                    break;
                }
            }

            if (!userFound) {
                System.err.println("Email khong trung khop. Vui long nhap lai ❤");
            }
        }
    }



    // TODO : user dang dang nhap
    public User userLogin() {
        DataBase<User> userDataBase = new DataBase<>();
        List<User> list = userDataBase.readFormFile(DataBase.USER_PATH);
        for (User u : list) {
            if (u.isUserStatus()) {
                return u;
            }
        }
        return null;
    }

    // TODO : dang xuat
    public void checkout() {
        DataBase<User> userDataBase = new DataBase<>();
        List<User> list = userDataBase.readFormFile(DataBase.USER_PATH);
        for (User u : list) {
            if (u.isUserStatus()) {
                u.setUserStatus(false);
                break;
            }
        }
        menuStore();
    }

    // TODO : the moi hoac update
    public void save(User user) {
        if (finById(user.getUserId()) == null) {
            // TODO : add
            listUser.add(user);
        } else {
            // TODO : update
            listUser.set(listUser.indexOf(finById(user.getUserId())), user);
        }

        userData.writeToFile(listUser, DataBase.USER_PATH);
    }

    // TODO : tim kiem theo Id
    public User finById(int id) {
        for (User u : listUser) {
            if (u.getUserId() == id) {
                return u;
            }
        }
        return null;
    }

    public void profileChange(int id) {
        User user = finById(id);
        System.out.println("__________________________ THONG TIN CA NHAN __________________________");
        System.out.println("Ten :" + user.getUserName());
        System.out.println("Email : " + user.getEmail());
        System.out.println("So dien thoai : " + user.getPhone());
        System.out.println("Dia chi : " + user.getAddress().getSpecifically() + " - " + user.getAddress().getDistrict() + " - " + user.getAddress().getCity());
        System.out.println("❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤");
        System.out.println("");
        System.out.println("Ban muon thay doi thong tin khong ?");
        System.out.println("1. Co");
        System.out.println("2. Khong");
        System.out.println("Nhap lua chon cua ban");
        int choice = InpustMethods.getInteger();

        if (choice == 1) {
            User newUser = new User();
            // sao chep toan bo thong tin cua user trc khi thay doi
            newUser.setUserId(user.getUserId());
            newUser.setUserName(user.getUserName());
            newUser.setEmail(user.getEmail());
            newUser.setPassword(user.getPassword());
            newUser.setCart(user.getCart());
            newUser.setPhone(user.getPhone());
            newUser.setAddress(user.getAddress());
            newUser.setUserStatus(user.isUserStatus());

            // thay doi thong tin
            System.out.println("Nhap vao ten nguoi dung ");
            newUser.setUserName(InpustMethods.getUseName());
            System.out.println("Nhap vao so dien thoai : ");
            newUser.setPhone(InpustMethods.getPhoneNumber());
            System.out.println("Nhap vao dia chi : ");
            Address newAddress = new Address();
            System.out.println("Thanh pho : ");
            newAddress.setCity(InpustMethods.getString());
            System.out.println("Quan / Huyen : ");
            newAddress.setDistrict(InpustMethods.getString());
            System.out.println("Dia chi cu the : ");
            newAddress.setSpecifically(InpustMethods.getString());
            newUser.setAddress(newAddress);

            System.out.println("-->>> Ban chac chan muon thay doi thong tin chu ? ( Co / Khong ) ");
            String check = InpustMethods.getString();

            if (check.trim().equalsIgnoreCase("co")) {
                save(newUser);
            } else {
                System.out.println("Huy bo thay doi thong tin ");
            }
        }
        UI.menuUser();
    }
    public void changePassword(int id){
        User user = finById(id);
        System.out.println("Mật khẩu cũ : ");
        String oldPas = InpustMethods.getPassword();
        if (user.getPassword().trim().equals(oldPas.trim())){
            System.out.println("Mat khau moi : ");
            String newPas = InpustMethods.getPassword();
            user.setPassword(newPas);
            save(user);
            UI.menuUser();
        } else {
            System.err.println("Mat khau khong trung khop ");
            changePassword(id);
        }
    }

    // TODO ➖➖➖➖➖➖ADMIN➖➖➖➖➖➖➖
    public void showAllCout(){
        for (User u : listUser) {
            if ( u.getRoles() == Role.USER) {
                u.displayUser();
            }
        }
    }
    public void toggleIsActiveUser(){
        System.out.println("Nhap vao Id tai khoan nguoi dung");
        int id = InpustMethods.getInteger();
        User toggeStatus = finById(id) ;
        if (toggeStatus == null) {
            System.err.println("Khong tim thay nguoi dung trong danh sach");
            return;
        }
        if (toggeStatus.getUserId() == userLogin().getUserId()){
            System.out.println("Khong the chan hay bo chan tai khoan quan tri vien");
            return;
        }

        toggeStatus.setActive(!toggeStatus.isActive());

    }
}
