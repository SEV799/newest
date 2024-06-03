package ui;



import javax.swing.*;

import controller.AmmoController;
import controller.EquipmentController;
import controller.PlantController;
import controller.UserController;
import controller.ZombieController;
import model.Admin;
import model.User;




public class AdminMainFrame extends JFrame {
    private UserController userController;
    private PlantController plantController;
    private ZombieController zombieController;
    private AmmoController ammoController;
    private EquipmentController equipmentController;
    private User adminUser;

    public AdminMainFrame(User adminUser, UserController userController, PlantController plantController, 
                          ZombieController zombieController, AmmoController ammoController, EquipmentController equipmentController) {
        this.adminUser = adminUser;
        this.userController = userController;
        this.plantController = plantController;
        this.zombieController = zombieController;
        this.ammoController = ammoController;
        this.equipmentController = equipmentController;

        setTitle("Admin Main Frame");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel welcomeLabel = new JLabel("欢迎管理员, " + adminUser.getName());
        welcomeLabel.setBounds(20, 20, 300, 25);
        add(welcomeLabel);

        JButton managePlantsButton = new JButton("管理植物");
        managePlantsButton.setBounds(20, 60, 150, 25);
        managePlantsButton.addActionListener(e -> new ManagePlantsFrame(plantController).setVisible(true));
        add(managePlantsButton);

        JButton manageZombiesButton = new JButton("管理僵尸");
        manageZombiesButton.setBounds(20, 100, 150, 25);
        manageZombiesButton.addActionListener(e -> new ManageZombiesFrame(zombieController).setVisible(true));
        add(manageZombiesButton);

        JButton manageAmmoButton = new JButton("管理弹药");
        manageAmmoButton.setBounds(20, 140, 150, 25);
        manageAmmoButton.addActionListener(e -> new ManageAmmoFrame(ammoController).setVisible(true));
        add(manageAmmoButton);

        JButton manageEquipmentButton = new JButton("管理装备");
        manageEquipmentButton.setBounds(20, 180, 150, 25);
        manageEquipmentButton.addActionListener(e -> new ManageEquipmentFrame(equipmentController).setVisible(true));
        add(manageEquipmentButton);

        JButton manageUsersButton = new JButton("管理用户");
        manageUsersButton.setBounds(20, 220, 150, 25);
        manageUsersButton.addActionListener(e -> new ManageUsersFrame(userController).setVisible(true));
        add(manageUsersButton);
        
        JButton commentButton = new JButton("评论管理");
        commentButton.setBounds(20, 260, 150, 25);
        commentButton.addActionListener(e -> new CommentFrame(plantController, zombieController, ammoController, equipmentController, adminUser).setVisible(true));
        add(commentButton);
        
        JButton logoutButton = new JButton("注销");
        logoutButton.setBounds(20, 300, 150, 25);
        logoutButton.addActionListener(e -> {
            new LoginFrame(plantController, zombieController, ammoController, equipmentController, userController).setVisible(true);
            dispose();
        });
        add(logoutButton);
    }
}



