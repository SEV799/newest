package ui;

import javax.swing.*;

import controller.AmmoController;
import model.Ammo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddAmmoFrame extends JFrame {
    private AmmoController ammoController;
    private JTextField nameField;
    private JTextField damageField;
    private JTextArea effectField;
    private JTextField imageUrlField;
    private JButton addButton;
    private JButton backButton;

    public AddAmmoFrame(AmmoController ammoController) {
        this.ammoController = ammoController;
        setTitle("添加弹药");
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

        JLabel damageLabel = new JLabel("伤害:");
        damageLabel.setBounds(20, 60, 80, 25);
        add(damageLabel);

        damageField = new JTextField(20);
        damageField.setBounds(100, 60, 250, 25);
        add(damageField);

        JLabel effectLabel = new JLabel("效果:");
        effectLabel.setBounds(20, 100, 80, 25);
        add(effectLabel);

        effectField = new JTextArea();
        effectField.setBounds(100, 100, 250, 75);
        add(effectField);

        JLabel imageUrlLabel = new JLabel("图片URL:");
        imageUrlLabel.setBounds(20, 190, 80, 25);
        add(imageUrlLabel);

        imageUrlField = new JTextField(20);
        imageUrlField.setBounds(100, 190, 250, 25);
        add(imageUrlField);

        addButton = new JButton("添加");
        addButton.setBounds(60, 240, 80, 25);
        add(addButton);

        backButton = new JButton("返回");
        backButton.setBounds(180, 240, 80, 25);
        add(backButton);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Ammo ammo = new Ammo();
                ammo.setName(nameField.getText());
                ammo.setDamage(Integer.parseInt(damageField.getText()));
                ammo.setEffect(effectField.getText());
                ammo.setImageUrl(imageUrlField.getText());
                ammoController.addAmmo(ammo);
                JOptionPane.showMessageDialog(AddAmmoFrame.this, "弹药添加成功！");
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