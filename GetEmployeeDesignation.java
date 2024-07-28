// Write a program which displays employee’s designation by providing empno using callable statement.

import java.sql.*;

public class GetEmployeeDesignation {
    public static void main(String[] args) {
        
        String dbUrl = "jdbc:mysql://localhost:3306/emp";
        String username = "Het Agravat";
        String password = "8339";

        int empNo = 101;

        try (Connection conn = DriverManager.getConnection(dbUrl, username, password);
             CallableStatement cs = conn.prepareCall("{call get_employee_designation(?, ?)}")) {

            cs.setInt(1, empNo);

            cs.registerOutParameter(2, Types.VARCHAR);

            cs.execute();

            String designation = cs.getString(2);
            System.out.println("Employee Designation: " + designation);

        } catch (SQLException e) {
            System.err.println("Error retrieving employee designation: " + e.getMessage());
        }
    }
}
