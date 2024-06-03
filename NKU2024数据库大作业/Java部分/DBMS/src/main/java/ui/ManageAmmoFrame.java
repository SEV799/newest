package ui;



import javax.swing.*;

import controller.AmmoController;
import model.Ammo;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;


public class ManageAmmoFrame extends JFrame {
    private AmmoController ammoController;
    private JTable ammoTable;
    private JTextField searchField;

    public ManageAmmoFrame(AmmoController ammoController) {
        this.ammoController = ammoController;
        setTitle("管理弹药");
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
        ammoTable = new JTable();
        JScrollPane scrollPane = new JScrollPane(ammoTable);
        add(scrollPane, BorderLayout.CENTER);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddAmmoFrame(ammoController).setVisible(true);
                refreshTable();
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = ammoTable.getSelectedRow();
                if (selectedRow != -1) {
                    Ammo ammo = ammoController.getAllAmmo().get(selectedRow);
                    new UpdateAmmoFrame(ammoController, ammo).setVisible(true);
                    refreshTable();
                }
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = ammoTable.getSelectedRow();
                if (selectedRow != -1) {
                    Ammo ammo = ammoController.getAllAmmo().get(selectedRow);
                    int confirmation = JOptionPane.showConfirmDialog(null, "确认删除弹药 " + ammo.getName() + " 吗？", "删除确认", JOptionPane.YES_NO_OPTION);
                    if (confirmation == JOptionPane.YES_OPTION) {
                        ammoController.deleteAmmo(ammo.getId());
                        refreshTable();
                    }
                }
            }
        });

        queryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = searchField.getText();
                List<Ammo> ammoList = ammoController.getAmmoByName(name);
                updateTable(ammoList);
            }
        });

        refreshTable();
    }

    private void refreshTable() {
        List<Ammo> ammoList = ammoController.getAllAmmo();
        updateTable(ammoList);
    }

    private void updateTable(List<Ammo> ammoList) {
        String[] columnNames = {"ID", "名字", "伤害", "效果", "图片URL"};
        String[][] data = new String[ammoList.size()][5];
        for (int i = 0; i < ammoList.size(); i++) {
            Ammo ammo = ammoList.get(i);
            data[i][0] = String.valueOf(ammo.getId());
            data[i][1] = ammo.getName();
            data[i][2] = String.valueOf(ammo.getDamage());
            data[i][3] = ammo.getEffect();
            data[i][4] = ammo.getImageUrl();
        }
        ammoTable.setModel(new javax.swing.table.DefaultTableModel(data, columnNames));
    }
}

