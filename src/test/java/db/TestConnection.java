package db;

import jdk.nashorn.internal.ir.annotations.Ignore;
import org.junit.Test;

import java.sql.*;

/**
 * Created by pom on 08.03.2018.
 */
public class TestConnection {

//    @Ignore  // игнорированиен теста
//    @Test
//    public  void testLoginConnection(String[] argv) {
//
//            System.out.println("-------- MySQL JDBC Connection Testing ------------");
//
//            try {
//                Class.forName("com.mysql.jdbc.Driver");
//            } catch (ClassNotFoundException e) {
//                System.out.println("Where is your MySQL JDBC Driver?");
//                e.printStackTrace();
//                return;
//            }
//
//            System.out.println("MySQL JDBC Driver Registered!");
//            Connection connection = null;
//
//
//
//            try {
//                connection = DriverManager
//                        //      .getConnection("jdbc:mysql://localhost:3306/librarytytyty", "root", "root");
//                        //        .getConnection("jdbc:mysql://sql11.freemysqlhosting.net:3306/sql11219539", "sql11219539", "Prod2018Prod");
//                        .getConnection("jdbc:mysql://www.db4free.net:3306/productionmodel", "production", "production");
//
//
//                if (connection != null) {
//                    System.out.println("You made it, take control your database now!");
//                } else {
//                    System.out.println("Failed to make connection!");
//                }
//
//                Statement st = connection.createStatement();
//
//                //           st.executeUpdate("INSERT INTO productionmodel.machine VALUES ( 3,'KD 2323_03',   'Press_03')");
//                ResultSet rs = st.executeQuery("select * from productionmodel.machine ");
//                //         ResultSet rs = st.executeQuery("select * from sql11219539.mashine ");
//                //       st.executeUpdate("CREATE TABLE machine (id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,name VARCHAR(32),description VARCHAR(256))");
////CREATE TABLE employees (id INT NOT NULL PRIMARY KEY AUTO_INCREMENT, lastname VARCHAR(20), firstname VARCHAR(20), phone VARCHAR(20),  dateofbirth DATE)
//                while (rs.next()) {
//                    System.out.println("text: " + rs.getString(2));
//                }
//                rs.close();
//
//
//            } catch (SQLException e) {
//                System.out.println("Connection Failed! Check output console");
//                e.printStackTrace();
//                return;
//            } finally {
//                try {
//                    if (connection!=null) connection.close();
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }
//            }
//
//
//        }


    @Test
    public void testDataConnection() throws SQLException {
        Statement stmt = null;
        ResultSet rs = null;
        Connection conn = null;

        conn = Database.getConnection();

        stmt = conn.createStatement();
        rs = stmt.executeQuery("SELECT * FROM flow_production.login ORDER BY name");
        while (rs.next()) {

            System.out.println(rs.getString("email"));
            System.out.println(rs.getString("password"));


        }
    }


}

