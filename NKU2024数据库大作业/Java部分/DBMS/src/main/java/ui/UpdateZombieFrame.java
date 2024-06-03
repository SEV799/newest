package ui;

import javax.swing.*;

import controller.ZombieController;
import model.Zombie;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpdateZombieFrame extends JFrame {
    private ZombieController zombieController;
    private Zombie zombie;
    private JTextField nameField;
    private JTextField toughnessField;
    private JTextField equipmentField;
    private JTextField biteDamageField;
    private JTextField throwDamageField;
    private JTextField crushDamageField;
    private JTextField speedField;
    private JTextField featuresField;
    private JTextArea descriptionField;
    private JTextField imageUrlField;
    private JButton updateButton;
    private JButton backButton;

    public UpdateZombieFrame(ZombieController zombieController, Zombie zombie) {
        this.zombieController = zombieController;
        this.zombie = zombie;
        setTitle("更新僵尸");
        setSize(400, 550);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel nameLabel = new JLabel("名称:");
        nameLabel.setBounds(20, 20, 80, 25);
        add(nameLabel);

        nameField = new JTextField(20);
        nameField.setBounds(100, 20, 250, 25);
        nameField.setText(zombie.getName());
        add(nameField);

        JLabel toughnessLabel = new JLabel("韧性:");
        toughnessLabel.setBounds(20, 60, 80, 25);
        add(toughnessLabel);

        toughnessField = new JTextField(20);
        toughnessField.setBounds(100, 60, 250, 25);
        toughnessField.setText(String.valueOf(zombie.getToughness()));
        add(toughnessField);

        JLabel equipmentLabel = new JLabel("装备:");
        equipmentLabel.setBounds(20, 100, 80, 25);
        add(equipmentLabel);

        equipmentField = new JTextField(20);
        equipmentField.setBounds(100, 100, 250, 25);
        equipmentField.setText(zombie.getEquipment());
        add(equipmentField);

        JLabel biteDamageLabel = new JLabel("啃食伤害:");
        biteDamageLabel.setBounds(20, 140, 80, 25);
        add(biteDamageLabel);

        biteDamageField = new JTextField(20);
        biteDamageField.setBounds(100, 140, 250, 25);
        biteDamageField.setText(String.valueOf(zombie.getBiteDamage()));
        add(biteDamageField);

        JLabel throwDamageLabel = new JLabel("投掷物伤害:");
        throwDamageLabel.setBounds(20, 180, 80, 25);
        add(throwDamageLabel);

        throwDamageField = new JTextField(20);
        throwDamageField.setBounds(100, 180, 250, 25);
        throwDamageField.setText(String.valueOf(zombie.getThrowDamage()));
        add(throwDamageField);

        JLabel crushDamageLabel = new JLabel("碾压伤害:");
        crushDamageLabel.setBounds(20, 220, 80, 25);
        add(crushDamageLabel);

        crushDamageField = new JTextField(20);
        crushDamageField.setBounds(100, 220, 250, 25);
        crushDamageField.setText(String.valueOf(zombie.getCrushDamage()));
        add(crushDamageField);

        JLabel speedLabel = new JLabel("速度:");
        speedLabel.setBounds(20, 260, 80, 25);
        add(speedLabel);

        speedField = new JTextField(20);
        speedField.setBounds(100, 260, 250, 25);
        speedField.setText(zombie.getSpeed());
        add(speedField);

        JLabel featuresLabel = new JLabel("特点:");
        featuresLabel.setBounds(20, 300, 80, 25);
        add(featuresLabel);

        featuresField = new JTextField(20);
        featuresField.setBounds(100, 300, 250, 25);
        featuresField.setText(zombie.getFeatures());
        add(featuresField);

        JLabel descriptionLabel = new JLabel("描述:");
        descriptionLabel.setBounds(20, 340, 80, 25);
        add(descriptionLabel);

        descriptionField = new JTextArea();
        descriptionField.setBounds(100, 340, 250, 75);
        descriptionField.setText(zombie.getDescription());
        add(descriptionField);

        JLabel imageUrlLabel = new JLabel("图片URL:");
        imageUrlLabel.setBounds(20, 430, 80, 25);
        add(imageUrlLabel);

        imageUrlField = new JTextField(20);
        imageUrlField.setBounds(100, 430, 250, 25);
        imageUrlField.setText(zombie.getImageUrl());
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
                zombie.setName(nameField.getText());
                zombie.setToughness(Integer.parseInt(toughnessField.getText()));
                zombie.setEquipment(equipmentField.getText());
                zombie.setBiteDamage(Integer.parseInt(biteDamageField.getText()));
                zombie.setThrowDamage(Integer.parseInt(throwDamageField.getText()));
                zombie.setCrushDamage(Integer.parseInt(crushDamageField.getText()));
                zombie.setSpeed(speedField.getText());
                zombie.setFeatures(featuresField.getText());
                zombie.setDescription(descriptionField.getText());
                zombie.setImageUrl(imageUrlField.getText());
                zombieController.updateZombie(zombie);
                JOptionPane.showMessageDialog(UpdateZombieFrame.this, "僵尸更新成功！");
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
