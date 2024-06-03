package controller;

import java.sql.SQLException;
import java.util.List;

import Dao.ZombieCommentDao;
import Service.ZombieService;
import model.Zombie;
import model.ZombieComment;


public class ZombieController {
    private ZombieService zombieService;
    private ZombieCommentDao zombieCommentDao;
    public ZombieController(ZombieService zombieService) {
        this.zombieService = zombieService;
        this.zombieCommentDao = new ZombieCommentDao();
    }

    public void addZombie(Zombie zombie) {
        zombieService.addZombie(zombie);
    }

    public List<Zombie> getZombiesByName(String name) {
        return zombieService.getZombiesByName(name);
    }

    public List<Zombie> getAllZombies() {
        return zombieService.getAllZombies();
    }

    public List<Zombie> getCommonZombies() {
        return zombieService.getCommonZombies();
    }

    public void updateZombie(Zombie zombie) {
        zombieService.updateZombie(zombie);
    }

    public void deleteZombie(long id) {
        zombieService.deleteZombie(id);
    }
    public void addZombieComment(long userId, long zombieId, String userAccount, String content) {
        ZombieComment comment = new ZombieComment();
        comment.setUserId(userId);
        comment.setZombieId(zombieId);
        comment.setUserAccount(userAccount); 
        comment.setContent(content);
        try {
            zombieCommentDao.insertZombieComment(comment);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public List<ZombieComment> getZombieComments(long zombieId) {
        return zombieCommentDao.selectCommentsByZombieId(zombieId);
    }

    public boolean deleteZombieComment(long commentId) {
        try {
            return zombieCommentDao.deleteZombieComment(commentId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }




}
