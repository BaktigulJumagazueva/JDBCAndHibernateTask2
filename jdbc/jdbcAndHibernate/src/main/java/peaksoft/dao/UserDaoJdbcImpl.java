package peaksoft.dao;
import peaksoft.model.User;
import peaksoft.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJdbcImpl implements UserDao {

    public UserDaoJdbcImpl() {

    }

    public void createUsersTable() {
        String query = "create table if not exists users(" +
                "id serial primary key," +
                "name varchar(50) not null," +
                "lastName varchar(50) not null," +
                "age smallint );";

        try (Connection connection = Util.getConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(query);
            System.out.println("Table created successfully!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void dropUsersTable() {
        String query = "drop table if exists users;";
        try (Connection connection = Util.getConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(query);
            System.out.println("Table dropped successfully!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String query = "insert into users(name, lastName, age) values(?, ?, ?);";
        try (Connection connection = Util.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
            System.out.println("User " + name + " saved successfully!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    public void removeUserById(long id) {
        String query1 = "delete from users where id = ?;";

        try (Connection connection = Util.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query1);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            System.out.println("remove id");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<User> getAllUsers() throws SQLException {
        User user = new User();
        String query = "select * from users";

        try (Connection connection = Util.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
            List<User> userList = new ArrayList<>();
            while (resultSet.next()) {
                userList.add(new User(resultSet.getString("name"),
                        resultSet.getString("lastName"),
                        resultSet.getByte("age")));
            }
            return userList;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new SQLException();
        }


    }

    public void cleanUsersTable() {
        String query = "truncate table users ;";

        try (Connection connection = Util.getConnection();
             Statement statement = connection.createStatement()) {
            statement.execute(query);
            System.out.println("Table cleared");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }
}








