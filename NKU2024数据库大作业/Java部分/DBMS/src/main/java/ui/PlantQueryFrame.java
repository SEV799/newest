package ui;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import controller.PlantController;
import model.Plant;
import model.User;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;



public class PlantQueryFrame extends JFrame {
    private PlantController plantController;
    private User user;
    private JTextField searchField;
    private JTable plantTable;

    public PlantQueryFrame(PlantController plantController, User user) {
        this.plantController = plantController;
        this.user = user;
        setTitle("查询植物");
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
                    displayPlants(plantController.getPlantsByName(query));
                } else {
                    JOptionPane.showMessageDialog(null, "普通用户无法按名称查询植物", "权限不足", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        searchPanel.add(searchField);
        searchPanel.add(searchButton);
        add(searchPanel, BorderLayout.NORTH);

        plantTable = new JTable();
        add(new JScrollPane(plantTable), BorderLayout.CENTER);

        if (user.getRole().equals("VIP")) {
            searchField.setEnabled(true);
            searchButton.setEnabled(true);
        } else {
            searchField.setEnabled(false);
            searchButton.setEnabled(false);
            displayPlants(plantController.getAllPlants());
        }
    }

    private void displayPlants(List<Plant> plants) {
        String[] columnNames = {"ID", "名字", "韧性", "威力", "范围", "弹药", "特点", "花费", "冷却", "描述", "图片URL"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        for (Plant plant : plants) {
            Object[] rowData = {
                    plant.getId(),
                    plant.getName(),
                    plant.getToughness(),
                    plant.getPower(),
                    plant.getRange(),
                    plant.getAmmo(),
                    plant.getFeatures(),
                    plant.getCost(),
                    plant.getCooldown(),
                    plant.getDescription(),
                    plant.getImageUrl()
            };
            model.addRow(rowData);
        }
        plantTable.setModel(model);
    }
}

