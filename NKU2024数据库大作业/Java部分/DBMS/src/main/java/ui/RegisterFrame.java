package ui;

import javax.swing.*;

import controller.UserController;
import model.User;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class RegisterFrame extends JFrame {
    private JTextField nameField;
    private JTextField accountField;
    private JPasswordField passwordField;
    private JTextField ageField;
    private JTextField genderField;
    private JTextArea bioField;
    private JComboBox<String> roleComboBox;
    private JButton registerButton;
    private UserController userController;

    public RegisterFrame(UserController userController) {
        this.userController = userController;
        setTitle("注册");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel nameLabel = new JLabel("名字:");
        nameLabel.setBounds(30, 30, 60, 25);
        add(nameLabel);

        nameField = new JTextField();
        nameField.setBounds(100, 30, 150, 25);
        add(nameField);

        JLabel accountLabel = new JLabel("账号:");
        accountLabel.setBounds(30, 70, 60, 25);
        add(accountLabel);

        accountField = new JTextField();
        accountField.setBounds(100, 70, 150, 25);
        add(accountField);

        JLabel passwordLabel = new JLabel("密码:");
        passwordLabel.setBounds(30, 110, 60, 25);
        add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(100, 110, 150, 25);
        add(passwordField);

        JLabel ageLabel = new JLabel("年龄:");
        ageLabel.setBounds(30, 150, 60, 25);
        add(ageLabel);

        ageField = new JTextField();
        ageField.setBounds(100, 150, 150, 25);
        add(ageField);

        JLabel genderLabel = new JLabel("性别:");
        genderLabel.setBounds(30, 190, 60, 25);
        add(genderLabel);

        genderField = new JTextField();
        genderField.setBounds(100, 190, 150, 25);
        add(genderField);

        JLabel bioLabel = new JLabel("简介:");
        bioLabel.setBounds(30, 230, 60, 25);
        add(bioLabel);

        bioField = new JTextArea();
        bioField.setBounds(100, 230, 150, 60);
        add(bioField);

        JLabel roleLabel = new JLabel("角色:");
        roleLabel.setBounds(30, 300, 60, 25);
        add(roleLabel);

        String[] roles = { "common", "VIP" };
        roleComboBox = new JComboBox<>(roles);
        roleComboBox.setBounds(100, 300, 150, 25);
        add(roleComboBox);

        registerButton = new JButton("注册");
        registerButton.setBounds(150, 340, 100, 25);
        add(registerButton);
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String account = accountField.getText();
                String password = new String(passwordField.getPassword());
                int age = Integer.parseInt(ageField.getText());
                String gender = genderField.getText();
                String bio = bioField.getText();
                String role = (String) roleComboBox.getSelectedItem();

                User user = new User();
                user.setName(name);
                user.setAccount(account);
                user.setPassword(password);
                user.setAge(age);
                user.setGender(gender);
                user.setBio(bio);
                user.setRole(role);

                String result = userController.registerUser(user);
                if ("注册成功".equals(result)) {
                    JOptionPane.showMessageDialog(null, "注册成功", "成功", JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, result, "错误", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}

