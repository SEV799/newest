package ui;



import javax.swing.*;

import controller.ZombieController;
import model.Zombie;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;


public class ManageZombiesFrame extends JFrame {
    private ZombieController zombieController;
    private JTable zombieTable;
    private JTextField searchField;

    public ManageZombiesFrame(ZombieController zombieController) {
        this.zombieController = zombieController;
        setTitle("管理僵尸");
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
        zombieTable = new JTable();
        JScrollPane scrollPane = new JScrollPane(zombieTable);
        add(scrollPane, BorderLayout.CENTER);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddZombieFrame(zombieController).setVisible(true);
                refreshTable();
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = zombieTable.getSelectedRow();
                if (selectedRow != -1) {
                    Zombie zombie = zombieController.getAllZombies().get(selectedRow);
                    new UpdateZombieFrame(zombieController, zombie).setVisible(true);
                    refreshTable();
                }
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = zombieTable.getSelectedRow();
                if (selectedRow != -1) {
                    Zombie zombie = zombieController.getAllZombies().get(selectedRow);
                    int confirmation = JOptionPane.showConfirmDialog(null, "确认删除僵尸 " + zombie.getName() + " 吗？", "删除确认", JOptionPane.YES_NO_OPTION);
                    if (confirmation == JOptionPane.YES_OPTION) {
                        zombieController.deleteZombie(zombie.getId());
                        refreshTable();
                    }
                }
            }
        });

        queryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = searchField.getText();
                List<Zombie> zombies = zombieController.getZombiesByName(name);
                updateTable(zombies);
            }
        });

        refreshTable();
    }

    private void refreshTable() {
        List<Zombie> zombies = zombieController.getAllZombies();
        updateTable(zombies);
    }

    private void updateTable(List<Zombie> zombies) {
        String[] columnNames = {"ID", "名字", "韧性", "装备", "啃食伤害", "投掷伤害", "碾压伤害", "速度", "特点", "简单描述", "图片URL"};
        String[][] data = new String[zombies.size()][11];
        for (int i = 0; i < zombies.size(); i++) {
            Zombie zombie = zombies.get(i);
            data[i][0] = String.valueOf(zombie.getId());
            data[i][1] = zombie.getName();
            data[i][2] = String.valueOf(zombie.getToughness());
            data[i][3] = zombie.getEquipment();
            data[i][4] = String.valueOf(zombie.getBiteDamage());
            data[i][5] = String.valueOf(zombie.getThrowDamage());
            data[i][6] = String.valueOf(zombie.getCrushDamage());
            data[i][7] = zombie.getSpeed();
            data[i][8] = zombie.getFeatures();
            data[i][9] = zombie.getDescription();
            data[i][10] = zombie.getImageUrl();
        }
        zombieTable.setModel(new javax.swing.table.DefaultTableModel(data, columnNames));
    }
}
