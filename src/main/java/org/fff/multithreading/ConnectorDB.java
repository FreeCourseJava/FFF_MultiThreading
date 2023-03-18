package org.fff.multithreading;

import java.sql.*;


public class ConnectorDB {
    private Connection connection;

    private PreparedStatement prepStat;

    public ConnectorDB() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    public void put(String time) {
        String statSQL = "INSERT INTO public.timestamps (timestamp) VALUES (?)";

        try {
            getConnection();
            prepStat = connection.prepareStatement(statSQL);
            prepStat.setString(1, time);
            prepStat.executeUpdate();
            closeConnection();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void showDBContent() {
        String statSQL = "SELECT * FROM public.timestamps";

        try {
            getConnection();
            prepStat = connection.prepareStatement(statSQL);
            ResultSet result = prepStat.executeQuery();
            do {
                result.next();
                System.out.println(result.getString("timestamp"));
            } while (!result.isLast());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public void getConnection() {
        try {
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/timestamps",
                    "postgres", "666");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void closeConnection() {
        try {
            prepStat.close();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
