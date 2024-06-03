package ui;

import model.User;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import controller.EquipmentController;
import model.Equipment;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;



public class EquipmentQueryFrame extends JFrame {
    private EquipmentController equipmentController;
    private User user;
    private JTextField searchField;
    private JTable equipmentTable;

    public EquipmentQueryFrame(EquipmentController equipmentController, User user) {
        this.equipmentController = equipmentController;
        this.user = user;
        setTitle("查询装备");
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
                    displayEquipment(equipmentController.getEquipmentByName(query));
                } else {
                    JOptionPane.showMessageDialog(null, "普通用户无法按名称查询装备", "权限不足", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        searchPanel.add(searchField);
        searchPanel.add(searchButton);
        add(searchPanel, BorderLayout.NORTH);

        equipmentTable = new JTable();
        add(new JScrollPane(equipmentTable), BorderLayout.CENTER);

        if (user.getRole().equals("VIP")) {
            searchField.setEnabled(true);
            searchButton.setEnabled(true);
        } else {
            searchField.setEnabled(false);
            searchButton.setEnabled(false);
            displayEquipment(equipmentController.getAllEquipment());
        }
    }

    private void displayEquipment(List<Equipment> equipmentList) {
        String[] columnNames = {"ID", "名字", "防御", "伤害", "效果", "图片URL"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        for (Equipment equipment : equipmentList) {
            Object[] rowData = {
                    equipment.getId(),
                    equipment.getName(),
                    equipment.getDefense(),
                    equipment.getDamage(),
                    equipment.getEffect(),
                    equipment.getImageUrl()
            };
            model.addRow(rowData);
        }
        equipmentTable.setModel(model);
    }
}
