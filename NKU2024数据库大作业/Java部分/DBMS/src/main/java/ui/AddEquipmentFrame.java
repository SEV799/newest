package ui;



import javax.swing.*;

import controller.EquipmentController;
import model.Equipment;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddEquipmentFrame extends JFrame {
    private EquipmentController equipmentController;
    private JTextField nameField;
    private JTextField defenseField;
    private JTextField damageField;
    private JTextArea effectField;
    private JTextField imageUrlField;
    private JButton addButton;
    private JButton backButton;

    public AddEquipmentFrame(EquipmentController equipmentController) {
        this.equipmentController = equipmentController;
        setTitle("添加装备");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel nameLabel = new JLabel("名称:");
        nameLabel.setBounds(20, 20, 80, 25);
        add(nameLabel);

        nameField = new JTextField(20);
        nameField.setBounds(100, 20, 250, 25);
        add(nameField);

        JLabel defenseLabel = new JLabel("防御:");
        defenseLabel.setBounds(20, 60, 80, 25);
        add(defenseLabel);

        defenseField = new JTextField(20);
        defenseField.setBounds(100, 60, 250, 25);
        add(defenseField);

        JLabel damageLabel = new JLabel("伤害:");
        damageLabel.setBounds(20, 100, 80, 25);
        add(damageLabel);

        damageField = new JTextField(20);
        damageField.setBounds(100, 100, 250, 25);
        add(damageField);

        JLabel effectLabel = new JLabel("效果:");
        effectLabel.setBounds(20, 140, 80, 25);
        add(effectLabel);

        effectField = new JTextArea();
        effectField.setBounds(100, 140, 250, 75);
        add(effectField);

        JLabel imageUrlLabel = new JLabel("图片URL:");
        imageUrlLabel.setBounds(20, 230, 80, 25);
        add(imageUrlLabel);

        imageUrlField = new JTextField(20);
        imageUrlField.setBounds(100, 230, 250, 25);
        add(imageUrlField);

        addButton = new JButton("添加");
        addButton.setBounds(60, 280, 80, 25);
        add(addButton);

        backButton = new JButton("返回");
        backButton.setBounds(180, 280, 80, 25);
        add(backButton);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Equipment equipment = new Equipment();
                equipment.setName(nameField.getText());
                equipment.setDefense(Integer.parseInt(defenseField.getText()));
                equipment.setDamage(Integer.parseInt(damageField.getText()));
                equipment.setEffect(effectField.getText());
                equipment.setImageUrl(imageUrlField.getText());
                equipmentController.addEquipment(equipment);
                JOptionPane.showMessageDialog(AddEquipmentFrame.this, "装备添加成功！");
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

