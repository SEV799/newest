package ui;

import controller.AmmoController;
import model.AmmoComment;
import model.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class AmmoCommentFrame extends JFrame {
    private AmmoController ammoController;
    private User currentUser;
    private long ammoId;
    private JTable commentTable;
    private JTextField commentField;

    public AmmoCommentFrame(AmmoController ammoController, User currentUser, long ammoId) {
        this.ammoController = ammoController;
        this.currentUser = currentUser;
        this.ammoId = ammoId;

        setTitle("弹药评论管理");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Comment input panel
        JPanel inputPanel = new JPanel();
        commentField = new JTextField(50);
        JButton addButton = new JButton("添加评论");
        JButton deleteButton = new JButton("删除评论");
        inputPanel.add(commentField);
        inputPanel.add(addButton);
        if ("VIP".equalsIgnoreCase(currentUser.getRole())) {
            inputPanel.add(deleteButton);
        }
        add(inputPanel, BorderLayout.NORTH);

        // Comment table
        commentTable = new JTable();
        JScrollPane scrollPane = new JScrollPane(commentTable);
        add(scrollPane, BorderLayout.CENTER);

        addButton.addActionListener(e -> {
            String content = commentField.getText();
            ammoController.addAmmoComment(currentUser.getId(), ammoId, currentUser.getAccount(), content); // 确保传递 userAccount
            refreshTable();
            commentField.setText("");
        });


        deleteButton.addActionListener(e -> {
            if (commentTable.getSelectedRow() != -1) {
                long commentId = Long.parseLong(commentTable.getValueAt(commentTable.getSelectedRow(), 0).toString());
                ammoController.deleteAmmoComment(commentId);
                refreshTable();
            }
        });

        refreshTable();
    }

    private void refreshTable() {
        List<AmmoComment> comments = ammoController.getAmmoComments(ammoId);
        updateTable(comments);
    }

    private void updateTable(List<AmmoComment> comments) {
        String[] columnNames = {"评论ID", "用户ID" ,"用户账号","评论内容", "评论时间"};
        String[][] data = new String[comments.size()][5];
        for (int i = 0; i < comments.size(); i++) {
            AmmoComment comment = comments.get(i);
            data[i][0] = String.valueOf(comment.getId());
            data[i][1] = String.valueOf(comment.getUserId());
            data[i][2] = String.valueOf(comment.getUserAccount());
            data[i][3] = comment.getContent();
            data[i][4] = comment.getCommentTime().toString();
        }
        commentTable.setModel(new DefaultTableModel(data, columnNames));
    }
}
