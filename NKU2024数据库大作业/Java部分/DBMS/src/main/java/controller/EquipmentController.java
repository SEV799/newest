package controller;

import java.sql.SQLException;
import java.util.List;

import Dao.EquipmentCommentDao;
import Service.EquipmentService;
import model.Equipment;
import model.EquipmentComment;



public class EquipmentController {
    private EquipmentService equipmentService;
    private EquipmentCommentDao equipmentCommentDao;

    public EquipmentController(EquipmentService equipmentService) {
        this.equipmentService = equipmentService;
        this.equipmentCommentDao = new EquipmentCommentDao();
    }

    public void addEquipment(Equipment equipment) {
        equipmentService.addEquipment(equipment);
    }

    public Equipment getEquipmentById(long id) {
        return equipmentService.getEquipmentById(id);
    }

    public List<Equipment> getAllEquipment() {
        return equipmentService.getAllEquipment();
    }

    public List<Equipment> getCommonEquipment() {
        return equipmentService.getCommonEquipment();
    }

    public void updateEquipment(Equipment equipment) {
        equipmentService.updateEquipment(equipment);
    }

    public void deleteEquipment(long id) {
        equipmentService.deleteEquipment(id);
    }

	public List<Equipment> getEquipmentByName(String name) {
		return equipmentService.getEquipmentByName(name);
	}
	public void addEquipmentComment(long userId, long equipmentId, String userAccount, String content) {
	    EquipmentComment comment = new EquipmentComment();
	    comment.setUserId(userId);
	    comment.setEquipmentId(equipmentId);
	    comment.setUserAccount(userAccount); 
	    comment.setContent(content);
	    try {
	        equipmentCommentDao.insertEquipmentComment(comment);
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}


	    public List<EquipmentComment> getEquipmentComments(long equipmentId) {
	        return equipmentCommentDao.selectCommentsByEquipmentId(equipmentId);
	    }

	    public boolean deleteEquipmentComment(long commentId) {
	        try {
	            return equipmentCommentDao.deleteEquipmentComment(commentId);
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return false;
	    }
}
