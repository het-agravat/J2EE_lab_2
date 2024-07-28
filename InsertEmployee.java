// Write a program which inserts a record of an employee using a callable statement.(Procedure with Parameter)

import java.sql.*;

public class InsertEmployee {
    public static void main(String[] args) {
        String dbUrl = "jdbc:mysql://localhost:3306/emp";
        String username = "Het Agravat";
        String password = "8339";
    
        int empId = 101;
        String empName = "Het Agravat";
        double empSalary = 100000.00;

        try (Connection conn = DriverManager.getConnection(dbUrl, username, password);
             CallableStatement cs = conn.prepareCall("{call insert_employee(?, ?, ?)}")) {

            cs.setInt(1, empId);
            cs.setString(2, empName);
            cs.setDouble(3, empSalary);
            cs.execute();
            System.out.println("Employee record inserted successfully!");

        } catch (SQLException e) {
            System.err.println("Error inserting employee record: " + e.getMessage());
        }
    }
}


/*
DELIMITER //

CREATE PROCEDURE insert_employee(IN empId INT, IN empName VARCHAR(50), IN empSalary DOUBLE)
BEGIN
    INSERT INTO employees (id, name, salary) VALUES (empId, empName, empSalary);
END //

DELIMITER ;
*/
