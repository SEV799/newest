package ui;



import javax.swing.*;

import controller.EquipmentController;
import model.Equipment;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;



public class ManageEquipmentFrame extends JFrame {
    private EquipmentController equipmentController;
    private JTable equipmentTable;
    private JTextField searchField;

    public ManageEquipmentFrame(EquipmentController equipmentController) {
        this.equipmentController = equipmentController;
        setTitle("管理装备");
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
        equipmentTable = new JTable();
        JScrollPane scrollPane = new JScrollPane(equipmentTable);
        add(scrollPane, BorderLayout.CENTER);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddEquipmentFrame(equipmentController).setVisible(true);
                refreshTable();
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = equipmentTable.getSelectedRow();
                if (selectedRow != -1) {
                    Equipment equipment = equipmentController.getAllEquipment().get(selectedRow);
                    new UpdateEquipmentFrame(equipmentController, equipment).setVisible(true);
                    refreshTable();
                }
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = equipmentTable.getSelectedRow();
                if (selectedRow != -1) {
                    Equipment equipment = equipmentController.getAllEquipment().get(selectedRow);
                    int confirmation = JOptionPane.showConfirmDialog(null, "确认删除装备 " + equipment.getName() + " 吗？", "删除确认", JOptionPane.YES_NO_OPTION);
                    if (confirmation == JOptionPane.YES_OPTION) {
                        equipmentController.deleteEquipment(equipment.getId());
                        refreshTable();
                    }
                }
            }
        });

        queryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = searchField.getText();
                List<Equipment> equipmentList = equipmentController.getEquipmentByName(name);
                updateTable(equipmentList);
            }
        });

        refreshTable();
    }

    private void refreshTable() {
        List<Equipment> equipmentList = equipmentController.getAllEquipment();
        updateTable(equipmentList);
    }

    private void updateTable(List<Equipment> equipmentList) {
        String[] columnNames = {"ID", "名字", "防御", "伤害", "效果", "图片URL"};
        String[][] data = new String[equipmentList.size()][6];
        for (int i = 0; i < equipmentList.size(); i++) {
            Equipment equipment = equipmentList.get(i);
            data[i][0] = String.valueOf(equipment.getId());
            data[i][1] = equipment.getName();
            data[i][2] = String.valueOf(equipment.getDefense());
            data[i][3] = String.valueOf(equipment.getDamage());
            data[i][4] = equipment.getEffect();
            data[i][5] = equipment.getImageUrl();
        }
        equipmentTable.setModel(new javax.swing.table.DefaultTableModel(data, columnNames));
    }
}

