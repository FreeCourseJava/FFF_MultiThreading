package src;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class ConnectorDB {
    private Connection connection;
    private Connection connection2;
    private PreparedStatement prepStat;

    public ConnectorDB() {
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/timestamps", "postgres", "666");
            connection2 = DriverManager.getConnection("jdbc:postgresql://localhost:5432/timestamps", "postgres", "666");
        } catch (Exception e) {
            System.out.println("Не удается подключится к базе данных");
            throw new RuntimeException(e);
        }

    }

    public void loseConnection() {
        try {
            connection.close();
            System.out.println("База отвалилась!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void getConnection() {
        connection = connection2;
        System.out.println("Подключение к базе восстановлено");
    }

    public void put(String time) {
        String statSQL = "INSERT INTO public.timestamps (timestamp) VALUES (?)";

        try {
            prepStat = connection.prepareStatement(statSQL);
            prepStat.setString(1, time);
            prepStat.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
