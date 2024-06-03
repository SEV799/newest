package Service;


import java.sql.SQLException;
import java.util.List;

import Dao.UserDao;
import model.User;

public class UserService {
    private UserDao userDao = new UserDao();



    public User getUserById(long id) {
        return userDao.selectUserById(id);
    }

    public User getUserByAccount(String account) {
        return userDao.selectUserByAccount(account);
    }

    public List<User> getAllUsers() {
        return userDao.selectAllUsers();
    }



    public void deleteUser(long id) {
        try {
            userDao.deleteUser(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public User login(String account, String password) {
        User user = userDao.selectUserByAccount(account);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }

/*
    public String registerUser(User user) {
        // 先检查账号是否存在
        if (userDao.accountExists(user.getAccount())) {
            return "账号已存在";
        }

        try {
            // 直接调用存储过程插入用户
            userDao.insertUser(user);
        } catch (SQLException e) {
            // 检查是否是触发器抛出的错误
            if (e.getMessage().contains("Account cannot be Admin")) {
                return "不能使用 'Admin' 作为账号名称";
            } else {
                e.printStackTrace();
                return "注册失败";
            }
        }
        return "注册成功";
    }
*/
    public String registerUser(User user) {
        try {
            // 直接调用存储过程插入用户
            userDao.insertUser(user);
        } catch (SQLException e) {
            // 检查是否是触发器抛出的错误
            if (e.getMessage().contains("Account cannot be Admin")) {
                return "不能使用 'Admin' 作为账号名称";
            } else if (e.getMessage().contains("Account already exists")) {
                return "账号已存在";
            } else {
                e.printStackTrace();
                return "注册失败";
            }
        }
        return "注册成功";
    }
    public String updateUser(User user) {
        try {
            userDao.updateUser(user);
            return "更新成功";
        } catch (SQLException e) {
            if (e.getMessage().contains("Cannot modify VIP user account")) {
                return "不能修改VIP用户的账号";
            } else {
                e.printStackTrace();
                return "更新失败";
            }
        }
    }
    
    public List<User> searchUsersByName(String name) {
        return userDao.selectUsersByName(name);
    }
}