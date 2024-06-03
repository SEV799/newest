package ui;



import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import controller.AmmoController;
import model.Ammo;
import model.User;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;


public class AmmoQueryFrame extends JFrame {
    private AmmoController ammoController;
    private User user;
    private JTextField searchField;
    private JTable ammoTable;

    public AmmoQueryFrame(AmmoController ammoController, User user) {
        this.ammoController = ammoController;
        this.user = user;
        setTitle("查询弹药");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel searchPanel = new JPanel();
        searchField = new JTextField(20);
        JButton searchButton = new JButton("搜索");

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String query = searchField.getText();
                if (user.getRole().equals("VIP")) {
                    displayAmmo(ammoController.getAmmoByName(query));
                } else {
                    JOptionPane.showMessageDialog(null, "普通用户无法按名称查询弹药", "权限不足", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        searchPanel.add(searchField);
        searchPanel.add(searchButton);
        add(searchPanel, BorderLayout.NORTH);

        ammoTable = new JTable();
        add(new JScrollPane(ammoTable), BorderLayout.CENTER);

        if (user.getRole().equals("VIP")) {
            searchField.setEnabled(true);
            searchButton.setEnabled(true);
        } else {
            searchField.setEnabled(false);
            searchButton.setEnabled(false);
            displayAmmo(ammoController.getAllAmmo());
        }
    }

    private void displayAmmo(List<Ammo> ammoList) {
        String[] columnNames = {"ID", "名字", "伤害", "效果", "图片URL"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        for (Ammo ammo : ammoList) {
            Object[] rowData = {
                    ammo.getId(),
                    ammo.getName(),
                    ammo.getDamage(),
                    ammo.getEffect(),
                    ammo.getImageUrl()
            };
            model.addRow(rowData);
        }
        ammoTable.setModel(model);
    }
}
