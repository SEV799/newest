package Service;

import java.sql.SQLException;
import java.util.List;

import Dao.EquipmentDao;
import model.Equipment;

public class EquipmentService {
    private EquipmentDao equipmentDao = new EquipmentDao();

    public void addEquipment(Equipment equipment) {
        try {
            equipmentDao.insertEquipment(equipment);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Equipment getEquipmentById(long id) {
        return equipmentDao.selectEquipmentById(id);
    }

    public List<Equipment> getAllEquipment() {
        return equipmentDao.selectAllEquipment();
    }

    public List<Equipment> getCommonEquipment() {
        // Assuming common equipment means equipment with defense less than a certain threshold
        return equipmentDao.selectEquipmentByDefenseThreshold(50);
    }

    public void updateEquipment(Equipment equipment) {
        try {
            equipmentDao.updateEquipment(equipment);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteEquipment(long id) {
        try {
            equipmentDao.deleteEquipment(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Equipment> getEquipmentByName(String name) {
        return equipmentDao.selectEquipmentByName(name);
    }
}

