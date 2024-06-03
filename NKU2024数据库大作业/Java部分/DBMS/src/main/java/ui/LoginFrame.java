package ui;

import javax.swing.*;

import Service.AdminService;
import Service.UserService;
import controller.AmmoController;
import controller.EquipmentController;
import controller.PlantController;
import controller.UserController;
import controller.ZombieController;
import model.Admin;
import model.User;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;




public class LoginFrame extends JFrame {
    private JTextField accountField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton registerButton;
    private AdminService adminService;
    private UserService userService;
    private User user;

    public LoginFrame(PlantController plantController, ZombieController zombieController, AmmoController ammoController,
                      EquipmentController equipmentController, UserController userController) {
        this.adminService = new AdminService();
        this.userService = new UserService();

        setTitle("登录");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel accountLabel = new JLabel("账号:");
        accountLabel.setBounds(30, 30, 60, 25);
        add(accountLabel);

        accountField = new JTextField();
        accountField.setBounds(100, 30, 150, 25);
        add(accountField);

        JLabel passwordLabel = new JLabel("密码:");
        passwordLabel.setBounds(30, 70, 60, 25);
        add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(100, 70, 150, 25);
        add(passwordField);

        loginButton = new JButton("登录");
        loginButton.setBounds(30, 110, 100, 25);
        add(loginButton);
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String account = accountField.getText();
                String password = new String(passwordField.getPassword());
                User user = userController.login(account, password);
                if (user != null) {
                    if ("Admin".equalsIgnoreCase(account)) {
                        new AdminMainFrame(user, userController, plantController, zombieController, ammoController, equipmentController).setVisible(true);
                    } else {
                        new MainFrame(user, plantController, zombieController, ammoController, equipmentController, userController).setVisible(true);
                    }
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(LoginFrame.this, "登录失败，账号或密码错误", "错误", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        registerButton = new JButton("注册");
        registerButton.setBounds(150, 110, 100, 25);
        add(registerButton);
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new RegisterFrame(userController).setVisible(true);
            }
        });
    }
    
}

