// Write a program which inserts a default record of an employee using a callable statement. (Procedure without parameter)

import java.sql.*;

public class InsertDefaultEmployee {
    public static void main(String[] args) {
        String dbUrl = "jdbc:mysql://localhost:3306/emp";
        String username = "Het Agravat";
        String password = "8339";

        try (Connection conn = DriverManager.getConnection(dbUrl, username, password);
             CallableStatement cs = conn.prepareCall("{call insert_default_employee()}")) {
            cs.execute();
            System.out.println("Default employee record inserted successfully!");
        } catch (SQLException e) {
            System.err.println("Error inserting default employee record: " + e.getMessage());
        }
    }
}
