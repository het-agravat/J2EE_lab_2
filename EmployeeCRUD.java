// Write a Program which demonstrates the use of CRUD operation on emp table.

import java.sql.*;
import java.util.Scanner;

public class EmployeeCRUD {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/emp";
    private static final String USERNAME = "Het Agravat";
    private static final String PASSWORD = "8339";

    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD)) {
            Scanner scanner = new Scanner(System.in);

            while (true) {
                System.out.println("\nCRUD Operations:");
                System.out.println("1. Create Employee");
                System.out.println("2. Read Employee");
                System.out.println("3. Update Employee");
                System.out.println("4. Delete Employee");
                System.out.println("5. Exit");
                System.out.print("Choose an option: ");
                int choice = scanner.nextInt();
                scanner.nextLine(); 

                switch (choice) {
                    case 1 -> createEmployee(conn, scanner);
                    case 2 -> readEmployee(conn, scanner);
                    case 3 -> updateEmployee(conn, scanner);
                    case 4 -> deleteEmployee(conn, scanner);
                    case 5 -> {
                        System.out.println("Exiting...");
                        return;
                    }
                    default -> System.out.println("Invalid choice. Please try again.");
                }
            }
        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
        }
    }

    private static void createEmployee(Connection conn, Scanner scanner) throws SQLException {
        System.out.print("Enter Employee Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Designation: ");
        String designation = scanner.nextLine();
        System.out.print("Enter Salary: ");
        double salary = scanner.nextDouble();

        String sql = "INSERT INTO emp (name, designation, salary) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setString(2, designation);
            pstmt.setDouble(3, salary);
            int rows = pstmt.executeUpdate();
            System.out.println(rows + " employee(s) created.");
        }
    }

    private static void readEmployee(Connection conn, Scanner scanner) throws SQLException {
        System.out.print("Enter Employee ID: ");
        int id = scanner.nextInt();

        String sql = "SELECT * FROM emp WHERE id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                System.out.println("ID: " + rs.getInt("id"));
                System.out.println("Name: " + rs.getString("name"));
                System.out.println("Designation: " + rs.getString("designation"));
                System.out.println("Salary: " + rs.getDouble("salary"));
            } else {
                System.out.println("Employee not found.");
            }
        }
    }

    private static void updateEmployee(Connection conn, Scanner scanner) throws SQLException {
        System.out.print("Enter Employee ID: ");
        int id = scanner.nextInt();
        scanner.nextLine(); 
        System.out.print("Enter New Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter New Designation: ");
        String designation = scanner.nextLine();
        System.out.print("Enter New Salary: ");
        double salary = scanner.nextDouble();

        String sql = "UPDATE emp SET name = ?, designation = ?, salary = ? WHERE id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setString(2, designation);
            pstmt.setDouble(3, salary);
            pstmt.setInt(4, id);
            int rows = pstmt.executeUpdate();
            System.out.println(rows + " employee(s) updated.");
        }
    }

    private static void deleteEmployee(Connection conn, Scanner scanner) throws SQLException {
        System.out.print("Enter Employee ID: ");
        int id = scanner.nextInt();

        String sql = "DELETE FROM emp WHERE id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            int rows = pstmt.executeUpdate();
            System.out.println(rows + " employee(s) deleted.");
        }
    }
}
