package org.example.Database;

import java.sql.*;

public class Database {
    public static class DatabaseControl {
        private static String connectionString = "jdbc:sqlserver://localhost;encrypt=false;integratedSecurity=false;user=auctionApp;password=1234";
        public static boolean InitilizeDatabase()
        {
            try {
                System.out.println("Attempting to connect to the database....");
                Connection connection = DriverManager.getConnection(connectionString);
                PreparedStatement statementExecutable = connection.prepareStatement("SELECT @@VERSION AS 'SQL Server Version Details'");

                ResultSet queryResult = statementExecutable.executeQuery();
                connection.close();

                System.out.println("SUCCESSFULLY CONNECTED TO THE DATABASE.");
            } catch (SQLException ex) {
                System.out.println("FAILED TO CONNECT TO THE DATABASE.");
                throw new RuntimeException(ex);
            }
            return false;
        }
    }
}
