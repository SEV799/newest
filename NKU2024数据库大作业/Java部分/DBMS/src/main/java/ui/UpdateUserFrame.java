package ui;


import javax.swing.*;

import controller.UserController;
import model.User;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class UpdateUserFrame extends JFrame {
    private JTextField nameField;
    private JTextField accountField;
    private JPasswordField passwordField;
    private JTextField ageField;
    private JTextField genderField;
    private JTextArea bioField;
    private JComboBox<String> roleComboBox;
    private JButton updateButton;
    private UserController userController;
    private User user;

    public UpdateUserFrame(UserController userController, User user) {
        this.userController = userController;
        this.user = user;
        setTitle("更新用户");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel nameLabel = new JLabel("名字:");
        nameLabel.setBounds(30, 30, 60, 25);
        add(nameLabel);

        nameField = new JTextField(user.getName());
        nameField.setBounds(100, 30, 150, 25);
        add(nameField);

        JLabel accountLabel = new JLabel("账号:");
        accountLabel.setBounds(30, 70, 60, 25);
        add(accountLabel);

        accountField = new JTextField(user.getAccount());
        accountField.setBounds(100, 70, 150, 25);
        add(accountField);

        JLabel passwordLabel = new JLabel("密码:");
        passwordLabel.setBounds(30, 110, 60, 25);
        add(passwordLabel);

        passwordField = new JPasswordField(user.getPassword());
        passwordField.setBounds(100, 110, 150, 25);
        add(passwordField);

        JLabel ageLabel = new JLabel("年龄:");
        ageLabel.setBounds(30, 150, 60, 25);
        add(ageLabel);

        ageField = new JTextField(String.valueOf(user.getAge()));
        ageField.setBounds(100, 150, 150, 25);
        add(ageField);

        JLabel genderLabel = new JLabel("性别:");
        genderLabel.setBounds(30, 190, 60, 25);
        add(genderLabel);

        genderField = new JTextField(user.getGender());
        genderField.setBounds(100, 190, 150, 25);
        add(genderField);

        JLabel bioLabel = new JLabel("简介:");
        bioLabel.setBounds(30, 230, 60, 25);
        add(bioLabel);

        bioField = new JTextArea(user.getBio());
        bioField.setBounds(100, 230, 150, 60);
        add(bioField);

        JLabel roleLabel = new JLabel("角色:");
        roleLabel.setBounds(30, 300, 60, 25);
        add(roleLabel);

        String[] roles = { "common", "VIP" };
        roleComboBox = new JComboBox<>(roles);
        roleComboBox.setSelectedItem(user.getRole());
        roleComboBox.setBounds(100, 300, 150, 25);
        add(roleComboBox);

        updateButton = new JButton("更新");
        updateButton.setBounds(150, 340, 100, 25);
        add(updateButton);
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                user.setName(nameField.getText());
                user.setAccount(accountField.getText());
                user.setPassword(new String(passwordField.getPassword()));
                user.setAge(Integer.parseInt(ageField.getText()));
                user.setGender(genderField.getText());
                user.setBio(bioField.getText());
                user.setRole((String) roleComboBox.getSelectedItem());

                String result = userController.updateUser(user);
                if ("更新成功".equals(result)) {
                    JOptionPane.showMessageDialog(null, "更新成功", "成功", JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, result, "错误", JOptionPane.ERROR_MESSAGE);
                }
            }
            
        });


        
    }
}
