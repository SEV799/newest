package ui;



import model.Admin;
import model.Ammo;
import model.Equipment;
import model.Plant;
import model.User;
import model.Zombie;

import javax.swing.*;

import controller.AmmoController;
import controller.EquipmentController;
import controller.PlantController;
import controller.UserController;
import controller.ZombieController;



public class MainFrame extends JFrame {
    private User user;
    private PlantController plantController;
    private ZombieController zombieController;
    private AmmoController ammoController;
    private EquipmentController equipmentController;
    private UserController userController;

    public MainFrame(User user, PlantController plantController, ZombieController zombieController,
                     AmmoController ammoController, EquipmentController equipmentController, UserController userController) {
        this.user = user;
        this.plantController = plantController;
        this.zombieController = zombieController;
        this.ammoController = ammoController;
        this.equipmentController = equipmentController;
        this.userController = userController;

        setTitle("Main Frame");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel welcomeLabel = new JLabel("欢迎, " + user.getName() + " (" + user.getRole() + ")");
        welcomeLabel.setBounds(20, 20, 300, 25);
        add(welcomeLabel);

        JButton queryPlantsButton = new JButton("查询植物");
        queryPlantsButton.setBounds(20, 60, 150, 25);
        queryPlantsButton.addActionListener(e -> new PlantQueryFrame(plantController, user).setVisible(true));
        add(queryPlantsButton);

        JButton queryZombiesButton = new JButton("查询僵尸");
        queryZombiesButton.setBounds(20, 100, 150, 25);
        queryZombiesButton.addActionListener(e -> new ZombieQueryFrame(zombieController, user).setVisible(true));
        add(queryZombiesButton);

        JButton queryAmmoButton = new JButton("查询弹药");
        queryAmmoButton.setBounds(20, 140, 150, 25);
        queryAmmoButton.addActionListener(e -> new AmmoQueryFrame(ammoController, user).setVisible(true));
        add(queryAmmoButton);

        JButton queryEquipmentButton = new JButton("查询装备");
        queryEquipmentButton.setBounds(20, 180, 150, 25);
        queryEquipmentButton.addActionListener(e -> new EquipmentQueryFrame(equipmentController, user).setVisible(true));
        add(queryEquipmentButton);
       
        JButton commentButton = new JButton("评论管理");
        commentButton.setBounds(20, 220, 150, 25);
        commentButton.addActionListener(e -> new CommentFrame(plantController, zombieController, ammoController, equipmentController, user).setVisible(true));
        add(commentButton);

        JButton logoutButton = new JButton("注销");
        logoutButton.setBounds(20, 260, 150, 25);
        logoutButton.addActionListener(e -> {
            new LoginFrame(plantController, zombieController, ammoController, equipmentController, userController).setVisible(true);
            dispose();
        });
        add(logoutButton);
    }
}
