package peaksoft;

import peaksoft.dao.UserDaoJdbcImpl;
import peaksoft.service.UserService;
import peaksoft.service.UserServiceImpl;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        // реализуйте алгоритм здесь
       UserService userService= new UserServiceImpl();
       /* userService.createUsersTable();
        userService.dropUsersTable();*/


userService.saveUser("Aduna","Nurlanova", (byte) 22);
userService.saveUser("Kerim","Muratov", (byte) 21);
userService.saveUser("Munara","Jumatova", (byte) 18);
userService.saveUser("Kanat","Urmatov", (byte) 19);

/*
userService.removeUserById(1);
userService.getAllUsers();
userService.cleanUsersTable();
*/



    }
}
