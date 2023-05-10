package org.example.Database;

import org.example.OOP.*;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class DatabaseControl {
    public static class Database {
        private static String connectionString = "jdbc:sqlserver://localhost;encrypt=false;integratedSecurity=false;user=auctionApp;password=1234";

        public static boolean InitilizeDatabase() {
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
                    statementExecutable.execute("CREATE TABLE BIDS (BID_ID INT IDENTITY, PRODUCT_ID INT NOT NULL, USER_ID INT NOT NULL, AMOUNT NUMERIC NULL CONSTRAINT BIDS_PK PRIMARY KEY (BID_ID), CONSTRAINT BIDS_FK FOREIGN KEY (PRODUCT_ID) REFERENCES PRODUCTS (PRODUCT_ID))");
                    System.out.println("created Bids table...");
                    statementExecutable.execute("CREATE TABLE SALES_COMMISSIONS (SALES_ID INT IDENTITY, PRODUCT_ID INT, DATE DATE, AMOUNT NUMERIC, BUYER_ID INT, SELLER_ID INT, CONSTRAINT SALES_PK PRIMARY KEY (SALES_ID), CONSTRAINT SALES_FK FOREIGN KEY (PRODUCT_ID) REFERENCES PRODUCTS (PRODUCT_ID))");
                    System.out.println("created sales_commissions table...");
                } catch (Exception exception) {
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

        public static Product[] getProducts()
        {
            List<Product> finalResult = new LinkedList<>();
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
                connection.close();
            } catch (SQLException e) {
                System.out.println("Error while parsing product out of many.");
            }
            Product[] returnResult = finalResult.toArray(new Product[finalResult.size()]);
            return returnResult;
        }

        public static void addProduct(String productName, int OwnerId)
        {
            try
            {
                Connection connection = DriverManager.getConnection(connectionString);
                PreparedStatement statement = connection.prepareStatement("INSERT INTO PRODUCTS (NAME, OWNER,STATUS) VALUES (?, ?, 'ON_GOING'");
                statement.setString(1, productName);
                statement.setInt(2, OwnerId);
                statement.execute();
                connection.close();
            }
            catch (SQLException e)
            {
                System.out.println("Error while parsing product out of many.");
            }
        }

        public static void addSalesCommission(int product_ID, Date date, double Amount, int Buyer_ID, int Seller_ID)
        {
            try
            {
                Connection connection = DriverManager.getConnection(connectionString);
                PreparedStatement statement = connection.prepareStatement("INSERT INTO SALES_COMMISSIONS (PRODUCT_ID, DATE, AMOUNT, BUYER_ID, SELLER_ID) VALUES (?,?,?,?,?)");
                statement.setInt(1, product_ID);
                statement.setDate(2, date);
                statement.setDouble(3, Amount);
                statement.setInt(4, Buyer_ID);
                statement.setInt(5, Seller_ID);
                statement.execute();
                connection.close();
            }
            catch (SQLException e)
            {
                System.out.println("Error while adding sales commission.");
            }
        }

        public static SalesCommission[] getUserSalesCommission(int User_ID)
        {
            List<SalesCommission> finalResult = new LinkedList<>();
            try
            {
                Connection connection = DriverManager.getConnection(connectionString);
                PreparedStatement theStatement = connection.prepareStatement("SELECT * FROM SALES_COMMISSIONS WHERE BUYER_ID = ? OR SELLER_ID = ?");
                theStatement.setInt(1, User_ID);
                theStatement.setInt(2, User_ID);
                ResultSet result = theStatement.executeQuery();

                while(result.next()) {
                    int Sales_ID = result.getInt(1);
                    Date date = result.getDate(2);
                    int AmountNumeric = result.getInt(3);
                    int Buyer_ID = result.getInt(4);
                    int Seller_ID = result.getInt(5);
                    finalResult.add(new SalesCommission(Sales_ID, date, AmountNumeric, Buyer_ID, Seller_ID));
                }
                connection.close();
                SalesCommission[] returnResult = finalResult.toArray(new SalesCommission[finalResult.size()]);
                return returnResult;
            }
            catch (SQLException e)
            {
                System.out.println("Error while getting sales commission");
            }
            return null;
        }

        public static void updateProductStatus(int Product_ID, PRODUCT_STATUS productStatus){

            try {
                Connection connection = DriverManager.getConnection(connectionString);
                PreparedStatement statement = connection.prepareStatement("UPDATE PRODUCTS SET STATUS = ? WHERE PRODUCT_ID = ?");
                statement.setString(1, productStatus.toString());
                statement.setInt(2, Product_ID);
                statement.execute();
                connection.close();
            } catch (SQLException e) {
                System.out.println("Error while updating product status.");
            }
        }

        public static Product getProductById(int id)
        {
            try {
                Connection connection = DriverManager.getConnection(connectionString);
                PreparedStatement theStatement = connection.prepareStatement("SELECT * FROM PRODUCTS WHERE PRODUCT_ID = ?");
                theStatement.setInt(1, id);
                ResultSet result = theStatement.executeQuery();

                while(result.next()) {
                    int product_id = result.getInt(1);
                    String name = result.getString(2);
                    int owner_id = result.getInt(3);
                    String status = result.getString(4);
                    return new Product(product_id, owner_id, name, PRODUCT_STATUS.valueOf(status));
                }
            } catch (SQLException e)
            {
                System.out.println("Error while getting product by ID");
            }
            return null;
        }

        public static void addUser (String userName , String phoneNo , String password , String email)
        {
            try{
                Connection connection = DriverManager.getConnection(connectionString);
                PreparedStatement theStatement = connection.prepareStatement("INSERT INTO USERS (USER_NAME, EMAIL, PHONE_NUMBER, PASSWORD) VALUES (?, ?, ?, ?)");
                theStatement.setString(1, userName);
                theStatement.setString(2, email);
                theStatement.setString(3, phoneNo);
                theStatement.setString(4, password);
                theStatement.execute();
            } catch (SQLException e) {
                System.out.println("Error while getting product by ID");
            }
        }
        public static void addBids (String productId , int userId , Double amount )
        {
            try
            {
                Connection connection = DriverManager.getConnection(connectionString);
                PreparedStatement theStatement = connection.prepareStatement("INSERT INTO BIDS (PRODUCT_ID, USER_ID, AMOUNT) VALUES (?, ?, ?)");
                theStatement.setString(1, productId);
                theStatement.setInt(2, userId);
                theStatement.setDouble(3, amount);
                theStatement.execute();
            }
            catch (SQLException e) {
                System.out.println("Error while adding bids");
            }
        }
        public static Bid[] getBidsForProduct(int ProductId)
        {
            List<Bid> resultContainer = new LinkedList<>();
            try {
                Connection connection = DriverManager.getConnection(connectionString);
                PreparedStatement theStatement = connection.prepareStatement("SELECT * From BIDS WHERE PRODUCT_ID = ?");
                theStatement.setInt(1, ProductId);

                ResultSet result = theStatement.executeQuery();
                while (result.next()) {
                    int BidId = result.getInt(1);
                    int found_product_id = result.getInt(2);
                    int UserId = result.getInt(3);
                    double Amount = result.getDouble(4);
                    resultContainer.add(new Bid(BidId, found_product_id, UserId, Amount));
                }
            } catch (SQLException e) {
                System.out.println("Error while getting bids for product by ID");
            }
            return resultContainer.toArray(new Bid[resultContainer.size()]);
        }

        public static void DeleteBid (String productId , int userId )
        {
            try {
                Connection connection = DriverManager.getConnection(connectionString);
                PreparedStatement theStatement = connection.prepareStatement("DELETE FROM BIDS WHERE PRODUCT_ID = ? AND USER_ID = ?");
                theStatement.setString(1, productId);
                theStatement.setInt(2, userId);
                theStatement.execute();
            } catch (SQLException e) {
                System.out.println("Error while Deleting bid");
            }
        }
        public static void UpdateBid (String productId , int userId , Double amount ) {
            try {
                Connection connection = DriverManager.getConnection(connectionString);
                PreparedStatement theStatement = connection.prepareStatement("UPDATE BIDS SET AMOUNT = ? WHERE USER_ID = ? AND PRODUCT_ID = ? ");
                theStatement.setString(3, productId);
                theStatement.setInt(2, userId);
                theStatement.setDouble(1, amount);
                theStatement.execute();
            } catch (SQLException e) {
                System.out.println("Error while updating bid");
            }
        }

        public static User getUser(String username, String password) {
            try {
                Connection connection = DriverManager.getConnection(connectionString);
                PreparedStatement statement = connection.prepareStatement("SELECT * FROM USERS WHERE USER_NAME = ? AND PASSWORD = ?");
                statement.setString(1, username);
                statement.setString(2, password);
                ResultSet result = statement.executeQuery();
                while (result.next()) {
                    int user_id = result.getInt(1);
                    String user_name = result.getString(2);
                    String email = result.getString(3);
                    String phone_number = result.getString(4);
                    return new User(user_id, user_name, email, phone_number);
                }
                connection.close();
            } catch (SQLException e) {
                System.out.println("error while authenticating user");
            }
            return null;
        }
    }
}
