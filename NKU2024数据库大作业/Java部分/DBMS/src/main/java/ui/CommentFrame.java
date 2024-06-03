package ui;

import controller.*;
import model.User;

import javax.swing.*;
import java.awt.*;

public class CommentFrame extends JFrame {
    private PlantController plantController;
    private ZombieController zombieController;
    private AmmoController ammoController;
    private EquipmentController equipmentController;
    private User currentUser;

    public CommentFrame(PlantController plantController, ZombieController zombieController, AmmoController ammoController, EquipmentController equipmentController, User currentUser) {
        this.plantController = plantController;
        this.zombieController = zombieController;
        this.ammoController = ammoController;
        this.equipmentController = equipmentController;
        this.currentUser = currentUser;

        setTitle("评论管理");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Comment input panel
        JPanel inputPanel = new JPanel();
        JComboBox<String> entityTypeComboBox = new JComboBox<>(new String[]{"植物", "僵尸", "弹药", "装备"});
        JTextField entityIdField = new JTextField(10);
        JButton manageButton = new JButton("管理评论");
        inputPanel.add(new JLabel("类型:"));
        inputPanel.add(entityTypeComboBox);
        inputPanel.add(new JLabel("ID:"));
        inputPanel.add(entityIdField);
        inputPanel.add(manageButton);
        add(inputPanel, BorderLayout.NORTH);

        manageButton.addActionListener(e -> {
            String entityType = (String) entityTypeComboBox.getSelectedItem();
            long entityId = Long.parseLong(entityIdField.getText());
            openCommentFrame(entityType, entityId);
        });
    }

    private void openCommentFrame(String entityType, long entityId) {
        switch (entityType) {
            case "植物":
                new PlantCommentFrame(plantController, currentUser, entityId).setVisible(true);
                break;
            case "僵尸":
                new ZombieCommentFrame(zombieController, currentUser, entityId).setVisible(true);
                break;
            case "弹药":
                new AmmoCommentFrame(ammoController, currentUser, entityId).setVisible(true);
                break;
            case "装备":
                new EquipmentCommentFrame(equipmentController, currentUser, entityId).setVisible(true);
                break;
        }
    }
}

