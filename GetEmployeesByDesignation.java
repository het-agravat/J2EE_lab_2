// Write a program which displays all the records of employees whose designation is provided by the user using a callable statement.

import java.sql.*;
import java.util.Scanner;

public class GetEmployeesByDesignation {
    public static void main(String[] args) {
        String dbUrl = "jdbc:mysql://localhost:3306/emp";
        String username = "Het Agravat";
        String password = "8339";

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter designation: ");
        String designation = scanner.nextLine();

        try (Connection conn = DriverManager.getConnection(dbUrl, username, password);
             CallableStatement cs = conn.prepareCall("{call get_employees_by_designation(?)}")) {

            cs.setString(1, designation);

            ResultSet rs = cs.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                double salary = rs.getDouble("salary");
                System.out.println("ID: " + id + ", Name: " + name + ", Salary: " + salary);
            }

        } catch (SQLException e) {
            System.err.println("Error retrieving employee records: " + e.getMessage());
        }
    }
}

/*

DELIMITER //

CREATE PROCEDURE get_employees_by_designation(IN empDesignation VARCHAR(50))
BEGIN
    SELECT id, name, salary FROM employees WHERE designation = empDesignation;
END //

DELIMITER ;
 
*/

