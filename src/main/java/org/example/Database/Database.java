package org.example.Database;

import org.example.OOP.PRODUCT_STATUS;
import org.example.OOP.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Database {
    public static class DatabaseControl {
        private static String connectionString = "jdbc:sqlserver://localhost;encrypt=false;integratedSecurity=false;user=auctionApp;password=1234";
        public static boolean InitilizeDatabase()
        {
            try {
                System.out.println("Attempting to connect to the database....");

                Connection connection = DriverManager.getConnection(connectionString);
                System.out.println("Attempting to create tables...");

                try {
                    Statement statementExecutable = connection.createStatement();
                    statementExecutable.execute("CREATE TABLE PRODUCTS(PRODUCT_ID INT IDENTITY, NAME VARCHAR(50) NOT NULL, OWNER INT NOT NULL, STATUS VARCHAR(15) NOT NULL, CONSTRAINT PRODUCTS_PK PRIMARY KEY (PRODUCT_ID), CONSTRAINT PRODUCTS_FL FOREIGN KEY (PRODUCT_ID) REFERENCES PRODUCTS (PRODUCT_ID))");
                    System.out.println("created Products table...");
                    statementExecutable.execute("CREATE TABLE USERS(USER_ID INT IDENTITY NOT NULL, USER_NAME VARCHAR(25) NOT NULL, EMAIL VARCHAR(50) NOT NULL, PHONE_NUMBER VARCHAR (15) NOT NULL, PASSWORD VARCHAR(50) NOT NULL, CONSTRAINT USERS_PK PRIMARY KEY (USER_ID))");
                    System.out.println("created Users table...");
                    statementExecutable.execute("CREATE TABLE BIDS (BID_ID INT IDENTITY, PRODUCT_ID INT NOT NULL, USER_ID INT NOT NULL, AMOUNT INT NULL CONSTRAINT BIDS_PK PRIMARY KEY (BID_ID), CONSTRAINT BIDS_FK FOREIGN KEY (PRODUCT_ID) REFERENCES PRODUCTS (PRODUCT_ID))");
                    System.out.println("created Bids table...");
                    statementExecutable.execute("CREATE TABLE SALES_COMMISSIONS (SALES_ID INT IDENTITY, PRODUCT_ID INT, DATE DATE, AMOUNT NUMERIC, BUYER_ID INT, SELLER_ID INT, CONSTRAINT SALES_PK PRIMARY KEY (SALES_ID), CONSTRAINT SALES_FK FOREIGN KEY (PRODUCT_ID) REFERENCES PRODUCTS (PRODUCT_ID))");
                    System.out.println("created sales_commissions table...");
                }
                catch (Exception exception)
                {
                    System.out.println("Tables already exist.");
                }

                connection.close();

                System.out.println("SUCCESSFULLY CONNECTED TO THE DATABASE.");
            } catch (SQLException ex) {
                System.out.println("FAILED TO CONNECT TO THE DATABASE.");
                throw new RuntimeException(ex);
            }
            return false;
        }

        public static Product getProductFromId(int id)
        {
            try {
                Connection connection = DriverManager.getConnection(connectionString);
                PreparedStatement statement = connection.prepareStatement("SELECT * FROM PRODUCTS WHERE PRODUCT_ID = ?");
                statement.setInt(1, id);
                ResultSet result = statement.executeQuery();
                while (result.next()) {
                    int product_id = result.getInt(1);
                    String name = result.getString(2);
                    int owner_id = result.getInt(3);
                    String status = result.getString(4);
                    return new Product(product_id, owner_id, name, PRODUCT_STATUS.CANCELLED);
                }
            } catch (SQLException e) {
                System.out.println("Error with getting product.");
            }
            return null;
        }

        public static Product[] getProducts()
        {
            List<Product> finalResult = new LinkedList<Product>();
            try {
                Connection connection = DriverManager.getConnection(connectionString);
                PreparedStatement statement = connection.prepareStatement("SELECT * FROM PRODUCTS");
                ResultSet result = statement.executeQuery();
                while (result.next()) {
                    int product_id = result.getInt(1);
                    String name = result.getString(2);
                    int owner_id = result.getInt(3);
                    String status = result.getString(4);

                    finalResult.add(new Product(product_id, owner_id, name, PRODUCT_STATUS.valueOf(status)));
                }
            } catch (SQLException e) {
                System.out.println("Error while parsing product out of many.");
            }
            Product[] returnResult = finalResult.toArray(new Product[finalResult.size()]);
            return returnResult;
        }
    }
}
