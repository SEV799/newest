package controller;

import java.util.List;

import Service.AdminService;
import model.Admin;

public class AdminController {
    private AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    public void addAdmin(Admin admin) {
        adminService.addAdmin(admin);
    }

    public Admin getAdminById(long id) {
        return adminService.getAdminById(id);
    }

    public List<Admin> getAllAdmins() {
        return adminService.getAllAdmins();
    }

    public void updateAdmin(Admin admin) {
        adminService.updateAdmin(admin);
    }

    public void deleteAdmin(long id) {
        adminService.deleteAdmin(id);
    }
}