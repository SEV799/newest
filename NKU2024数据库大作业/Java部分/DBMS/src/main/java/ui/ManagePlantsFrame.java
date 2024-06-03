package ui;

import controller.PlantController;
import model.Plant;



import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;


public class ManagePlantsFrame extends JFrame {
    private PlantController plantController;
    private JTable plantTable;
    private JTextField searchField;

    public ManagePlantsFrame(PlantController plantController) {
        this.plantController = plantController;
        setTitle("管理植物");
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
        plantTable = new JTable();
        JScrollPane scrollPane = new JScrollPane(plantTable);
        add(scrollPane, BorderLayout.CENTER);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddPlantFrame(plantController).setVisible(true);
                refreshTable();
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = plantTable.getSelectedRow();
                if (selectedRow != -1) {
                    Plant plant = plantController.getAllPlants().get(selectedRow);
                    new UpdatePlantFrame(plantController, plant).setVisible(true);
                    refreshTable();
                }
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = plantTable.getSelectedRow();
                if (selectedRow != -1) {
                    Plant plant = plantController.getAllPlants().get(selectedRow);
                    int confirmation = JOptionPane.showConfirmDialog(null, "确认删除植物 " + plant.getName() + " 吗？", "删除确认", JOptionPane.YES_NO_OPTION);
                    if (confirmation == JOptionPane.YES_OPTION) {
                        plantController.deletePlant(plant.getId());
                        refreshTable();
                    }
                }
            }
        });

        queryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = searchField.getText();
                List<Plant> plants = plantController.getPlantsByName(name);
                updateTable(plants);
            }
        });

        refreshTable();
    }

    private void refreshTable() {
        List<Plant> plants = plantController.getAllPlants();
        updateTable(plants);
    }

    private void updateTable(List<Plant> plants) {
        String[] columnNames = {"ID", "名字", "韧性", "威力", "范围", "弹药", "特点", "花费", "冷却速度", "简单描述", "图片URL"};
        String[][] data = new String[plants.size()][11];
        for (int i = 0; i < plants.size(); i++) {
            Plant plant = plants.get(i);
            data[i][0] = String.valueOf(plant.getId());
            data[i][1] = plant.getName();
            data[i][2] = String.valueOf(plant.getToughness());
            data[i][3] = String.valueOf(plant.getPower());
            data[i][4] = plant.getRange();
            data[i][5] = plant.getAmmo();
            data[i][6] = plant.getFeatures();
            data[i][7] = String.valueOf(plant.getCost());
            data[i][8] = String.valueOf(plant.getCooldown());
            data[i][9] = plant.getDescription();
            data[i][10] = plant.getImageUrl();
        }
        plantTable.setModel(new javax.swing.table.DefaultTableModel(data, columnNames));
    }
}
