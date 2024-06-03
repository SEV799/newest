package controller;

import java.sql.SQLException;
import java.util.List;

import Dao.AmmoCommentDao;
import Service.AmmoService;
import model.Ammo;
import model.AmmoComment;



public class AmmoController {
    private AmmoService ammoService;
    private AmmoCommentDao ammoCommentDao;

    public AmmoController(AmmoService ammoService) {
        this.ammoService = ammoService;
        this.ammoCommentDao = new AmmoCommentDao();
    }

    public void addAmmo(Ammo ammo) {
        ammoService.addAmmo(ammo);
    }

    public Ammo getAmmoById(long id) {
        return ammoService.getAmmoById(id);
    }

    public List<Ammo> getAllAmmo() {
        return ammoService.getAllAmmo();
    }

    public List<Ammo> getCommonAmmo() {
        return ammoService.getCommonAmmo();
    }

    public void updateAmmo(Ammo ammo) {
        ammoService.updateAmmo(ammo);
    }

    public void deleteAmmo(long id) {
        ammoService.deleteAmmo(id);
    }

	public List<Ammo> getAmmoByName(String name) {
		return ammoService.getAmmoByName(name);
	}
	public void addAmmoComment(long userId, long ammoId, String userAccount, String content) {
	    AmmoComment comment = new AmmoComment();
	    comment.setUserId(userId);
	    comment.setAmmoId(ammoId);
	    comment.setUserAccount(userAccount); 
	    comment.setContent(content);
	    try {
	        ammoCommentDao.insertAmmoComment(comment);
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}


    public List<AmmoComment> getAmmoComments(long ammoId) {
        return ammoCommentDao.selectCommentsByAmmoId(ammoId);
    }

    public boolean deleteAmmoComment(long commentId) {
        try {
            return ammoCommentDao.deleteAmmoComment(commentId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
