package Service;

import java.sql.SQLException;
import java.util.List;

import Dao.AdminDao;
import model.Admin;
import model.User;

public class AdminService {
    private AdminDao adminDao = new AdminDao();

    public void addAdmin(Admin admin) {
        try {
            adminDao.insertAdmin(admin);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Admin getAdminById(long id) {
        return adminDao.selectAdminById(id);
    }

    public List<Admin> getAllAdmins() {
        return adminDao.selectAllAdmins();
    }

    public void updateAdmin(Admin admin) {
        try {
            adminDao.updateAdmin(admin);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteAdmin(long id) {
        try {
            adminDao.deleteAdmin(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public User login(String account, String password) {
        User admin = adminDao.selectAdminByAccount(account);
        if (admin != null && admin.getPassword().equals(password)) {
            return admin;
        }
        return null;
    }
}
