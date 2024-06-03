package ui;



import javax.swing.*;

import controller.EquipmentController;
import model.Equipment;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpdateEquipmentFrame extends JFrame {
    private EquipmentController equipmentController;
    private Equipment equipment;
    private JTextField nameField;
    private JTextField defenseField;
    private JTextField damageField;
    private JTextArea effectField;
    private JTextField imageUrlField;
    private JButton updateButton;
    private JButton backButton;

    public UpdateEquipmentFrame(EquipmentController equipmentController, Equipment equipment) {
        this.equipmentController = equipmentController;
        this.equipment = equipment;
        setTitle("更新装备");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel nameLabel = new JLabel("名称:");
        nameLabel.setBounds(20, 20, 80, 25);
        add(nameLabel);

        nameField = new JTextField(20);
        nameField.setBounds(100, 20, 250, 25);
        nameField.setText(equipment.getName());
        add(nameField);

        JLabel defenseLabel = new JLabel("防御:");
        defenseLabel.setBounds(20, 60, 80, 25);
        add(defenseLabel);

        defenseField = new JTextField(20);
        defenseField.setBounds(100, 60, 250, 25);
        defenseField.setText(String.valueOf(equipment.getDefense()));
        add(defenseField);

        JLabel damageLabel = new JLabel("伤害:");
        damageLabel.setBounds(20, 100, 80, 25);
        add(damageLabel);

        damageField = new JTextField(20);
        damageField.setBounds(100, 100, 250, 25);
        damageField.setText(String.valueOf(equipment.getDamage()));
        add(damageField);

        JLabel effectLabel = new JLabel("效果:");
        effectLabel.setBounds(20, 140, 80, 25);
        add(effectLabel);

        effectField = new JTextArea();
        effectField.setBounds(100, 140, 250, 75);
        effectField.setText(equipment.getEffect());
        add(effectField);

        JLabel imageUrlLabel = new JLabel("图片URL:");
        imageUrlLabel.setBounds(20, 230, 80, 25);
        add(imageUrlLabel);

        imageUrlField = new JTextField(20);
        imageUrlField.setBounds(100, 230, 250, 25);
        imageUrlField.setText(equipment.getImageUrl());
        add(imageUrlField);

        updateButton = new JButton("更新");
        updateButton.setBounds(60, 280, 80, 25);
        add(updateButton);

        backButton = new JButton("返回");
        backButton.setBounds(180, 280, 80, 25);
        add(backButton);

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                equipment.setName(nameField.getText());
                equipment.setDefense(Integer.parseInt(defenseField.getText()));
                equipment.setDamage(Integer.parseInt(damageField.getText()));
                equipment.setEffect(effectField.getText());
                equipment.setImageUrl(imageUrlField.getText());
                equipmentController.updateEquipment(equipment);
                JOptionPane.showMessageDialog(UpdateEquipmentFrame.this, "装备更新成功！");
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

