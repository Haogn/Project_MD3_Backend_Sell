package ra.controller;

import ra.model.User;
import ra.service.UserService;

public class UserController {
    private UserService userService;
    public UserController(){
        userService =new UserService();
    }

    // TODO : Id tu tang
    public int getNewId(){
        return userService.getNewId();
    }

    // TODO : dang ky
    public void register() {
        userService.register();
    }

    // TODO : dang nhap
    public void login() {
        userService.login();
    }

    public User userLogin() {
        return userService.userLogin();
    }
    public void checkout() {
        userService.checkout();
    }
    public void save(User user) {
        userService.save(user);
    }

    public User finById(int id) {
        return userService.finById(id) ;
    }
    public void profileChange(int id) {
        userService.profileChange(id);
    }
    public void changePassword(int id){
        userService.changePassword(id);
    }
}
