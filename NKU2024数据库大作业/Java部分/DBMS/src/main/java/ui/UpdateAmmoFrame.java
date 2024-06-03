package ui;

import javax.swing.*;

import controller.AmmoController;
import model.Ammo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpdateAmmoFrame extends JFrame {
    private AmmoController ammoController;
    private Ammo ammo;
    private JTextField nameField;
    private JTextField damageField;
    private JTextArea effectField;
    private JTextField imageUrlField;
    private JButton updateButton;
    private JButton backButton;

    public UpdateAmmoFrame(AmmoController ammoController, Ammo ammo) {
        this.ammoController = ammoController;
        this.ammo = ammo;
        setTitle("更新弹药");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel nameLabel = new JLabel("名称:");
        nameLabel.setBounds(20, 20, 80, 25);
        add(nameLabel);

        nameField = new JTextField(20);
        nameField.setBounds(100, 20, 250, 25);
        nameField.setText(ammo.getName());
        add(nameField);

        JLabel damageLabel = new JLabel("伤害:");
        damageLabel.setBounds(20, 60, 80, 25);
        add(damageLabel);

        damageField = new JTextField(20);
        damageField.setBounds(100, 60, 250, 25);
        damageField.setText(String.valueOf(ammo.getDamage()));
        add(damageField);

        JLabel effectLabel = new JLabel("效果:");
        effectLabel.setBounds(20, 100, 80, 25);
        add(effectLabel);

        effectField = new JTextArea();
        effectField.setBounds(100, 100, 250, 75);
        effectField.setText(ammo.getEffect());
        add(effectField);

        JLabel imageUrlLabel = new JLabel("图片URL:");
        imageUrlLabel.setBounds(20, 190, 80, 25);
        add(imageUrlLabel);

        imageUrlField = new JTextField(20);
        imageUrlField.setBounds(100, 190, 250, 25);
        imageUrlField.setText(ammo.getImageUrl());
        add(imageUrlField);

        updateButton = new JButton("更新");
        updateButton.setBounds(60, 240, 80, 25);
        add(updateButton);

        backButton = new JButton("返回");
        backButton.setBounds(180, 240, 80, 25);
        add(backButton);

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ammo.setName(nameField.getText());
                ammo.setDamage(Integer.parseInt(damageField.getText()));
                ammo.setEffect(effectField.getText());
                ammo.setImageUrl(imageUrlField.getText());
                ammoController.updateAmmo(ammo);
                JOptionPane.showMessageDialog(UpdateAmmoFrame.this, "弹药更新成功！");
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