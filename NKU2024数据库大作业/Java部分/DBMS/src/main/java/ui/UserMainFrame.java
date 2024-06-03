package ui;



import javax.swing.*;

import controller.AmmoController;
import controller.EquipmentController;
import controller.PlantController;
import controller.UserController;
import controller.ZombieController;
import model.User;


public class UserMainFrame extends JFrame {
    private User user;
    private PlantController plantController;
    private ZombieController zombieController;
    private AmmoController ammoController;
    private EquipmentController equipmentController;
    private UserController userController;

    public UserMainFrame(User user, PlantController plantController, ZombieController zombieController,
                         AmmoController ammoController, EquipmentController equipmentController, UserController userController) {
        this.user = user;
        this.plantController = plantController;
        this.zombieController = zombieController;
        this.ammoController = ammoController;
        this.equipmentController = equipmentController;
        this.userController = userController;

        setTitle("用户主界面");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        // 添加用户查询功能按钮
        JButton queryPlantsButton = new JButton("查询植物");
        queryPlantsButton.setBounds(20, 20, 150, 25);
        add(queryPlantsButton);
        queryPlantsButton.addActionListener(e -> new PlantQueryFrame(plantController, user).setVisible(true));

        JButton queryZombiesButton = new JButton("查询僵尸");
        queryZombiesButton.setBounds(20, 60, 150, 25);
        add(queryZombiesButton);
        queryZombiesButton.addActionListener(e -> new ZombieQueryFrame(zombieController, user).setVisible(true));

        JButton queryAmmoButton = new JButton("查询弹药");
        queryAmmoButton.setBounds(20, 100, 150, 25);
        add(queryAmmoButton);
        queryAmmoButton.addActionListener(e -> new AmmoQueryFrame(ammoController, user).setVisible(true));

        JButton queryEquipmentButton = new JButton("查询装备");
        queryEquipmentButton.setBounds(20, 140, 150, 25);
        add(queryEquipmentButton);
        queryEquipmentButton.addActionListener(e -> new EquipmentQueryFrame(equipmentController, user).setVisible(true));

        JButton logoutButton = new JButton("注销");
        logoutButton.setBounds(20, 180, 150, 25);
        add(logoutButton);
        logoutButton.addActionListener(e -> {
            new LoginFrame(plantController, zombieController, ammoController, equipmentController, userController).setVisible(true);
            dispose();
        });
    }
}


