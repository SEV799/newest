package ui;

import controller.UserController;
import model.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ManageUsersFrame extends JFrame {
    private UserController userController;
    private JTable userTable;
    private JTextField searchField;

    public ManageUsersFrame(UserController userController) {
        this.userController = userController;
        setTitle("管理用户");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Button Panel
        JPanel buttonPanel = new JPanel();
        JButton addButton = new JButton("添加");
        JButton updateButton = new JButton("更新");
        JButton deleteButton = new JButton("删除");
        JButton queryButton = new JButton("查询");
        searchField = new JTextField(20);
        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(new JLabel("按名字查询:"));
        buttonPanel.add(searchField);
        buttonPanel.add(queryButton);
        add(buttonPanel, BorderLayout.NORTH);

        // Table
        userTable = new JTable();
        JScrollPane scrollPane = new JScrollPane(userTable);
        add(scrollPane, BorderLayout.CENTER);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddUserFrame(userController).setVisible(true);
                refreshTable();
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = userTable.getSelectedRow();
                if (selectedRow != -1) {
                    Long userId = Long.valueOf(userTable.getValueAt(selectedRow, 0).toString());
                    User user = userController.getUserById(userId);
                    new UpdateUserFrame(userController, user).setVisible(true);
                    refreshTable();
                } else {
                    JOptionPane.showMessageDialog(null, "请选择要更新的用户", "提示", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = userTable.getSelectedRow();
                if (selectedRow != -1) {
                    Long userId = Long.valueOf(userTable.getValueAt(selectedRow, 0).toString());
                    int confirmation = JOptionPane.showConfirmDialog(null, "确认删除用户 ID 为 " + userId + " 的用户吗？", "删除确认", JOptionPane.YES_NO_OPTION);
                    if (confirmation == JOptionPane.YES_OPTION) {
                        userController.deleteUser(userId);
                        refreshTable();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "请选择要删除的用户", "提示", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        queryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = searchField.getText();
                List<User> users = userController.searchUsersByName(name);
                updateTable(users);
            }
        });

        refreshTable();
    }

    private void refreshTable() {
        List<User> users = userController.getAllUsers();
        updateTable(users);
    }

    private void updateTable(List<User> users) {
        String[] columnNames = {"ID", "名字", "账号", "密码", "年龄", "性别", "个性签名", "角色"};
        String[][] data = new String[users.size()][8];
        for (int i = 0; i < users.size(); i++) {
            User user = users.get(i);
            data[i][0] = String.valueOf(user.getId());
            data[i][1] = user.getName();
            data[i][2] = user.getAccount();
            data[i][3] = user.getPassword();
            data[i][4] = String.valueOf(user.getAge());
            data[i][5] = user.getGender();
            data[i][6] = user.getBio();
            data[i][7] = user.getRole();
        }
        userTable.setModel(new DefaultTableModel(data, columnNames));
    }
}
