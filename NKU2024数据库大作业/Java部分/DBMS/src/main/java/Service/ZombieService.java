package Service;

import java.sql.SQLException;
import java.util.List;

import Dao.ZombieDao;
import model.Zombie;


public class ZombieService {
    private ZombieDao zombieDao = new ZombieDao();

    public void addZombie(Zombie zombie) {
        try {
            zombieDao.insertZombie(zombie);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Zombie> getZombiesByName(String id) {
        return zombieDao.selectZombiesByName(id);
    }

    public List<Zombie> getAllZombies() {
        return zombieDao.selectAllZombies();
    }

    public List<Zombie> getCommonZombies() {
        return zombieDao.selectZombiesByToughnessThreshold(100);
    }

    public void updateZombie(Zombie zombie) {
        try {
            zombieDao.updateZombie(zombie);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteZombie(long id) {
        try {
            zombieDao.deleteZombie(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

