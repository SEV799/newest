package ui;

import javax.swing.*;

import controller.PlantController;
import model.Plant;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpdatePlantFrame extends JFrame {
    private PlantController plantController;
    private Plant plant;
    private JTextField nameField;
    private JTextField toughnessField;
    private JTextField powerField;
    private JTextField rangeField;
    private JTextField ammoField;
    private JTextField featuresField;
    private JTextField costField;
    private JTextField cooldownField;
    private JTextArea descriptionField;
    private JTextField imageUrlField;
    private JButton updateButton;
    private JButton backButton;

    public UpdatePlantFrame(PlantController plantController, Plant plant) {
        this.plantController = plantController;
        this.plant = plant;
        setTitle("更新植物");
        setSize(400, 550);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel nameLabel = new JLabel("名称:");
        nameLabel.setBounds(20, 20, 80, 25);
        add(nameLabel);

        nameField = new JTextField(20);
        nameField.setBounds(100, 20, 250, 25);
        nameField.setText(plant.getName());
        add(nameField);

        JLabel toughnessLabel = new JLabel("韧性:");
        toughnessLabel.setBounds(20, 60, 80, 25);
        add(toughnessLabel);

        toughnessField = new JTextField(20);
        toughnessField.setBounds(100, 60, 250, 25);
        toughnessField.setText(String.valueOf(plant.getToughness()));
        add(toughnessField);

        JLabel powerLabel = new JLabel("威力:");
        powerLabel.setBounds(20, 100, 80, 25);
        add(powerLabel);

        powerField = new JTextField(20);
        powerField.setBounds(100, 100, 250, 25);
        powerField.setText(String.valueOf(plant.getPower()));
        add(powerField);

        JLabel rangeLabel = new JLabel("范围:");
        rangeLabel.setBounds(20, 140, 80, 25);
        add(rangeLabel);

        rangeField = new JTextField(20);
        rangeField.setBounds(100, 140, 250, 25);
        rangeField.setText(plant.getRange());
        add(rangeField);

        JLabel ammoLabel = new JLabel("弹药:");
        ammoLabel.setBounds(20, 180, 80, 25);
        add(ammoLabel);

        ammoField = new JTextField(20);
        ammoField.setBounds(100, 180, 250, 25);
        ammoField.setText(plant.getAmmo());
        add(ammoField);

        JLabel featuresLabel = new JLabel("特点:");
        featuresLabel.setBounds(20, 220, 80, 25);
        add(featuresLabel);

        featuresField = new JTextField(20);
        featuresField.setBounds(100, 220, 250, 25);
        featuresField.setText(plant.getFeatures());
        add(featuresField);

        JLabel costLabel = new JLabel("花费:");
        costLabel.setBounds(20, 260, 80, 25);
        add(costLabel);

        costField = new JTextField(20);
        costField.setBounds(100, 260, 250, 25);
        costField.setText(String.valueOf(plant.getCost()));
        add(costField);

        JLabel cooldownLabel = new JLabel("冷却时间:");
        cooldownLabel.setBounds(20, 300, 80, 25);
        add(cooldownLabel);

        cooldownField = new JTextField(20);
        cooldownField.setBounds(100, 300, 250, 25);
        cooldownField.setText(String.valueOf(plant.getCooldown()));
        add(cooldownField);

        JLabel descriptionLabel = new JLabel("描述:");
        descriptionLabel.setBounds(20, 340, 80, 25);
        add(descriptionLabel);

        descriptionField = new JTextArea();
        descriptionField.setBounds(100, 340, 250, 75);
        descriptionField.setText(plant.getDescription());
        add(descriptionField);

        JLabel imageUrlLabel = new JLabel("图片URL:");
        imageUrlLabel.setBounds(20, 430, 80, 25);
        add(imageUrlLabel);

        imageUrlField = new JTextField(20);
        imageUrlField.setBounds(100, 430, 250, 25);
        imageUrlField.setText(plant.getImageUrl());
        add(imageUrlField);

        updateButton = new JButton("更新");
        updateButton.setBounds(60, 480, 80, 25);
        add(updateButton);

        backButton = new JButton("返回");
        backButton.setBounds(180, 480, 80, 25);
        add(backButton);

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                plant.setName(nameField.getText());
                plant.setToughness(Integer.parseInt(toughnessField.getText()));
                plant.setPower(Integer.parseInt(powerField.getText()));
                plant.setRange(rangeField.getText());
                plant.setAmmo(ammoField.getText());
                plant.setFeatures(featuresField.getText());
                plant.setCost(Integer.parseInt(costField.getText()));
                plant.setCooldown(Integer.parseInt(cooldownField.getText()));
                plant.setDescription(descriptionField.getText());
                plant.setImageUrl(imageUrlField.getText());
                plantController.updatePlant(plant);
                JOptionPane.showMessageDialog(UpdatePlantFrame.this, "植物更新成功！");
                dispose();
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }
}