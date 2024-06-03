package Service;

import java.sql.SQLException;
import java.util.List;

import Dao.AmmoDao;
import model.Ammo;

public class AmmoService {
    private AmmoDao ammoDao = new AmmoDao();

    public void addAmmo(Ammo ammo) {
        try {
            ammoDao.insertAmmo(ammo);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Ammo getAmmoById(long id) {
        return ammoDao.selectAmmoById(id);
    }

    public List<Ammo> getAllAmmo() {
        return ammoDao.selectAllAmmo();
    }

    public List<Ammo> getCommonAmmo() {
        // Assuming common ammo means ammo with damage less than a certain threshold
        return ammoDao.selectAmmoByDamageThreshold(50);
    }

    public void updateAmmo(Ammo ammo) {
        try {
            ammoDao.updateAmmo(ammo);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteAmmo(long id) {
        try {
            ammoDao.deleteAmmo(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

	public List<Ammo> getAmmoByName(String name) {
		return ammoDao.selectAmmoByName(name);
	}
}