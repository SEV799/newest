package Service;

import java.sql.SQLException;
import java.util.List;

import Dao.PlantCommentDao;
import Dao.PlantDao;
import model.Plant;
import model.PlantCommentUser;



public class PlantService {
    private PlantDao plantDao = new PlantDao();
    private PlantCommentDao plantCommentDao = new PlantCommentDao();

    public void addPlant(Plant plant) {
        try {
            plantDao.insertPlant(plant);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Plant getPlantById(long id) {
        return plantDao.selectPlantById(id);
    }

    public List<Plant> getAllPlants() {
        return plantDao.selectAllPlants();
    }


    public void updatePlant(Plant plant) {
        try {
            plantDao.updatePlant(plant);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletePlant(long id) {
        try {
            plantDao.deletePlant(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

	public List<Plant> getPlantsByName(String name) {
		return plantDao.selectPlantsByName(name);
	}
	
	public List<PlantCommentUser> getPlantCommentUserViewByPlantId(long plantId) {
	    return plantCommentDao.selectPlantCommentUserViewByPlantId(plantId);
	}
	public List<PlantCommentUser> getPlantCommentUserViewByUserName(String userName) {
	    return plantCommentDao.selectPlantCommentUserViewByUserName(userName);
	}
	


}








