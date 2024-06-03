package ui;

import javax.swing.*;

import controller.AmmoController;
import controller.EquipmentController;
import controller.PlantController;
import controller.ZombieController;
import model.Ammo;
import model.Equipment;
import model.Plant;
import model.User;
import model.Zombie;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;


public class UserQueryFrame extends JFrame {
    private User user;
    private PlantController plantController;
    private ZombieController zombieController;
    private AmmoController ammoController;
    private EquipmentController equipmentController;
    private JButton viewPlantsButton;
    private JButton viewZombiesButton;
    private JButton viewAmmoButton;
    private JButton viewEquipmentButton;
    private JTextArea displayArea;

    public UserQueryFrame(User user, PlantController plantController, ZombieController zombieController, AmmoController ammoController, EquipmentController equipmentController) {
        this.user = user;
        this.plantController = plantController;
        this.zombieController = zombieController;
        this.ammoController = ammoController;
        this.equipmentController = equipmentController;
        setTitle("用户查询");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        viewPlantsButton = new JButton("查看植物");
        viewPlantsButton.setBounds(20, 20, 120, 25);
        add(viewPlantsButton);

        viewZombiesButton = new JButton("查看僵尸");
        viewZombiesButton.setBounds(160, 20, 120, 25);
        add(viewZombiesButton);

        viewAmmoButton = new JButton("查看弹药");
        viewAmmoButton.setBounds(300, 20, 120, 25);
        add(viewAmmoButton);

        viewEquipmentButton = new JButton("查看装备");
        viewEquipmentButton.setBounds(440, 20, 120, 25);
        add(viewEquipmentButton);

        displayArea = new JTextArea();
        displayArea.setBounds(20, 60, 540, 280);
        add(displayArea);

        viewPlantsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayArea.setText("");
                List<Plant> plants = user.getRole().equals("VIP") ? plantController.getAllPlants() : plantController.getAllPlants();
                for (Plant plant : plants) {
                    displayArea.append(plant.toString() + "\n");
                }
            }
        });

        viewZombiesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayArea.setText("");
                List<Zombie> zombies = user.getRole().equals("VIP") ? zombieController.getAllZombies() : zombieController.getCommonZombies();
                for (Zombie zombie : zombies) {
                    displayArea.append(zombie.toString() + "\n");
                }
            }
        });

        viewAmmoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayArea.setText("");
                List<Ammo> ammoList = user.getRole().equals("VIP") ? ammoController.getAllAmmo() : ammoController.getCommonAmmo();
                for (Ammo ammo : ammoList) {
                    displayArea.append(ammo.toString() + "\n");
                }
            }
        });

        viewEquipmentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayArea.setText("");
                List<Equipment> equipmentList = user.getRole().equals("VIP") ? equipmentController.getAllEquipment() : equipmentController.getCommonEquipment();
                for (Equipment equipment : equipmentList) {
                    displayArea.append(equipment.toString() + "\n");
                }
            }
        });
    }
}
