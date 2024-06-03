package controller;

import java.sql.SQLException;
import java.util.List;

import Dao.PlantCommentDao;
import Service.PlantService;
import model.Plant;
import model.PlantComment;
import model.PlantCommentUser;


public class PlantController {
    private PlantService plantService;
    private PlantCommentDao plantCommentDao;

    
    public PlantController(PlantService plantService) {
        this.plantService = plantService;
        this.plantCommentDao = new PlantCommentDao();
    }

    public void addPlant(Plant plant) {
        plantService.addPlant(plant);
    }

    public Plant getPlantById(long id) {
        return plantService.getPlantById(id);
    }

    public List<Plant> getAllPlants() {
        return plantService.getAllPlants();
    }


    public void updatePlant(Plant plant) {
        plantService.updatePlant(plant);
    }

    public void deletePlant(long id) {
        plantService.deletePlant(id);
    }

	public List<Plant> getPlantsByName(String name) {
		return plantService.getPlantsByName(name);
	}
	public void addPlantComment(long userId, long plantId, String userAccount, String content) {
	    PlantComment comment = new PlantComment();
	    comment.setUserId(userId);
	    comment.setPlantId(plantId);
	    comment.setUserAccount(userAccount); 
	    comment.setContent(content);
	    try {
	        plantCommentDao.insertPlantComment(comment);
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}

    public List<PlantComment> getPlantComments(long plantId) {
        return plantCommentDao.selectCommentsByPlantId(plantId);
    }

    public boolean deletePlantComment(long commentId) {
        try {
            return plantCommentDao.deletePlantComment(commentId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public List<PlantCommentUser> getPlantCommentUserViewByPlantId(long plantId) {
        return plantService.getPlantCommentUserViewByPlantId(plantId);
    }

	public List<PlantCommentUser> getPlantCommentUserViewByUserName(String userName) {
		return plantService.getPlantCommentUserViewByUserName(userName);
	}



}
