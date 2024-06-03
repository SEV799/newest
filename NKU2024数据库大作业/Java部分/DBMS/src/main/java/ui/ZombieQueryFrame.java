package ui;



import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import controller.ZombieController;
import model.User;
import model.Zombie;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;



public class ZombieQueryFrame extends JFrame {
    private ZombieController zombieController;
    private User user;
    private JTextField searchField;
    private JTable zombieTable;

    public ZombieQueryFrame(ZombieController zombieController, User user) {
        this.zombieController = zombieController;
        this.user = user;
        setTitle("查询僵尸");
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
                    displayZombies(zombieController.getZombiesByName(query));
                } else {
                    JOptionPane.showMessageDialog(null, "普通用户无法按名称查询僵尸", "权限不足", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        searchPanel.add(searchField);
        searchPanel.add(searchButton);
        add(searchPanel, BorderLayout.NORTH);

        zombieTable = new JTable();
        add(new JScrollPane(zombieTable), BorderLayout.CENTER);

        if (user.getRole().equals("VIP")) {
            searchField.setEnabled(true);
            searchButton.setEnabled(true);
        } else {
            searchField.setEnabled(false);
            searchButton.setEnabled(false);
            displayZombies(zombieController.getAllZombies());
        }
    }

    private void displayZombies(List<Zombie> zombies) {
        String[] columnNames = {"ID", "名字", "韧性", "装备", "啃咬伤害", "投掷伤害", "碾压伤害", "速度", "特点", "描述", "图片URL"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        for (Zombie zombie : zombies) {
            Object[] rowData = {
                    zombie.getId(),
                    zombie.getName(),
                    zombie.getToughness(),
                    zombie.getEquipment(),
                    zombie.getBiteDamage(),
                    zombie.getThrowDamage(),
                    zombie.getCrushDamage(),
                    zombie.getSpeed(),
                    zombie.getFeatures(),
                    zombie.getDescription(),
                    zombie.getImageUrl()
            };
            model.addRow(rowData);
        }
        zombieTable.setModel(model);
    }
}

