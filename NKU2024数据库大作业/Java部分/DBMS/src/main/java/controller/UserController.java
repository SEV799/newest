package controller;

import java.sql.SQLException;
import java.util.List;

import Service.AdminService;
import Service.UserService;
import model.Admin;
import model.User;

public class UserController {
    private UserService userService;
    private AdminService adminService;

    public UserController(UserService userService, AdminService adminService) {
        this.userService = userService;
        this.adminService = adminService;
    }

    public String registerUser(User user) {
        return userService.registerUser(user);
    }

    public User login(String account, String password) {
        User user = userService.login(account, password);
        if (user != null) {
            return user;
        }
        User admin = adminService.login(account, password);
        if (admin != null) {
            return admin;
        }
        return null;
    }
  
    public String updateUser(User user)  {
        return userService.updateUser(user);
    }


    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    public User getUserById(long id) {
        return userService.getUserById(id);
    }



    public void deleteUser(long id) {
        userService.deleteUser(id);
    }

    public List<User> searchUsersByName(String name) {
        return userService.searchUsersByName(name);
    }
}