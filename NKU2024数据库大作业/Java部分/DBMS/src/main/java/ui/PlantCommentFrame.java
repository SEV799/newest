package ui;

import controller.PlantController;
import model.PlantCommentUser;
import model.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class PlantCommentFrame extends JFrame {
    private PlantController plantController;
    private User currentUser;
    private long plantId;
    private JTable commentTable;
    private JTextField commentField;
    private JTextField searchField;

    public PlantCommentFrame(PlantController plantController, User currentUser, long plantId) {
        this.plantController = plantController;
        this.currentUser = currentUser;
        this.plantId = plantId;

        setTitle("植物评论管理");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Comment input panel
        JPanel inputPanel = new JPanel();
        commentField = new JTextField(50);
        searchField = new JTextField(20);
        JButton addButton = new JButton("添加评论");
        JButton deleteButton = new JButton("删除评论");
        JButton searchButton = new JButton("查询评论");
        inputPanel.add(commentField);
        inputPanel.add(addButton);
        inputPanel.add(searchField);
        inputPanel.add(searchButton);
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
            plantController.addPlantComment(currentUser.getId(), plantId, currentUser.getAccount(), content);
            refreshTable();
            commentField.setText("");
        });

        deleteButton.addActionListener(e -> {
            if (commentTable.getSelectedRow() != -1) {
                long commentId = Long.parseLong(commentTable.getValueAt(commentTable.getSelectedRow(), 0).toString());
                plantController.deletePlantComment(commentId);
                refreshTable();
            }
        });

        searchButton.addActionListener(e -> {
            String userName = searchField.getText();
            List<PlantCommentUser> comments = plantController.getPlantCommentUserViewByUserName(userName);
            updateTable(comments);
        });

        refreshTable();
    }

    private void refreshTable() {
        List<PlantCommentUser> comments = plantController.getPlantCommentUserViewByPlantId(plantId);
        updateTable(comments);
    }

    private void updateTable(List<PlantCommentUser> comments) {
        String[] columnNames = {"评论ID", "用户ID", "植物ID", "用户账号", "评论内容", "评论时间", "用户名", "用户年龄", "用户性别"};
        String[][] data = new String[comments.size()][columnNames.length];
        for (int i = 0; i < comments.size(); i++) {
            PlantCommentUser comment = comments.get(i);
            data[i][0] = String.valueOf(comment.getCommentId());
            data[i][1] = String.valueOf(comment.getUserId());
            data[i][2] = String.valueOf(comment.getPlantId());
            data[i][3] = comment.getUserAccount();
            data[i][4] = comment.getContent();
            data[i][5] = comment.getCommentTime().toString();
            data[i][6] = comment.getUserName();
            data[i][7] = String.valueOf(comment.getUserAge());
            data[i][8] = comment.getUserGender();
        }
        commentTable.setModel(new DefaultTableModel(data, columnNames));
    }
}

