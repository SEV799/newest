
import Service.AdminService;
import Service.AmmoService;
import Service.EquipmentService;
import Service.PlantService;
import Service.UserService;
import Service.ZombieService;
import controller.AmmoController;
import controller.EquipmentController;
import controller.PlantController;
import controller.UserController;
import controller.ZombieController;
import ui.LoginFrame;


import javax.swing.*;


public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            UserService userService = new UserService();
            AdminService adminService = new AdminService();
            UserController userController = new UserController(userService, adminService);

            PlantController plantController = new PlantController(new PlantService());
            ZombieController zombieController = new ZombieController(new ZombieService());
            AmmoController ammoController = new AmmoController(new AmmoService());
            EquipmentController equipmentController = new EquipmentController(new EquipmentService());

            LoginFrame loginFrame = new LoginFrame(plantController, zombieController, ammoController, equipmentController, userController);
            loginFrame.setVisible(true);
        });
    }
}
