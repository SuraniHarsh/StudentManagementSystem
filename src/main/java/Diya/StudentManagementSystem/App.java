package Diya.StudentManagementSystem;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * This class represents a simple Student Management System application.
 * It provides functionality to interact with student data stored in a database.
 */
public class App {

    /**
     * Main method to run the Student Management System application.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        // Path to the JSON file containing student data
        String filePath = "C:\\Users\\harsh\\eclipse-workspace\\StudentManagementSystem\\src\\main\\java\\Diya\\StudentManagementSystem\\data.json";

        // Initialize StudentDAO to interact with the database
        StudentDAO studentDAO = new StudentDAO();

        // Scanner object to read user input from the console
        Scanner scanner = new Scanner(System.in);

        // Headers for displaying table columns
        String[] headers = {"Sr. No", "Division Count", "Enrollment No", "Student", "Division", "Email ID", "Remark"};

        int option = 0;
        do {
            // Display menu options
            System.out.println("\nStudent Management System");
            System.out.println("1. View All Students");
            System.out.println("2. View Students By Division");
            System.out.println("3. View Students By Enrollment Number");
            System.out.println("4. Insert New Student");
            System.out.println("5. Delete Student");
            System.out.println("100. Import Data From JSON File");
            System.out.println("6. Exit");
            System.out.print("Enter Your Choice: ");

            // Read user input for menu option
            option = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (option) {
                case 1:
                    try {
                        // Retrieve and display all students from the database
                        String[][] data = studentDAO.getAllStudents();
                        System.out.print("\n");
                        printTableRow(headers);
                        for (String[] row : data) {
                            printTableRow(row);
                        }
                        System.out.println("\nTotal students: " + data.length + "\n");
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    break;

                case 2:
                    try {
                        // Prompt user to enter division and display students in that division
                        System.out.print("\nEnter division : ");
                        String divisionFilter = scanner.nextLine();
                        String[][] data = studentDAO.getStudentsByDivision(divisionFilter.toUpperCase());
                        printTableRow(headers);
                        for (String[] row : data) {
                            printTableRow(row);
                        }
                        System.out.println("\nTotal students: " + data.length + "\n");

                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    break;

                case 3:
                    try {
                        // Prompt user to enter enrollment number and display student details
                        System.out.print("\nEnter Enrollment Number: ");
                        long enrollmentNumber = scanner.nextLong();
                        StudentModel student = studentDAO.getStudentByEnrollmentNo(enrollmentNumber);
                        if (student != null) {
                            System.out.println("\nStudent Found With Enrollment Number " + enrollmentNumber + ":");
                            System.out.println("Sr. No: " + student.getSrNo());
                            System.out.println("Division Wise Student Count: " + student.getDivisionWiseStudentCount());
                            System.out.println("Student Name: " + student.getStudent());
                            System.out.println("Division: " + student.getDivision());
                            System.out.println("Email ID: " + student.getEmailId());
                            System.out.println("Remark based on MIS: " + student.getRemarkBasedOnMIS() + "\n");
                        } else {
                            System.out.println("\nStudent Found With Enrollment Number  " + enrollmentNumber + " not found.\n");
                        }

                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    break;

                case 4:
                    // Prompt user to enter details for a new student and insert into database
                    System.out.println("\nEnter Student Details:");
                    System.out.print("Sr. No: ");
                    int srNo = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Division Wise Student Count: ");
                    int divisionWiseStudentCount = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Enrollment No: ");
                    long enrollmentNo = scanner.nextLong();
                    scanner.nextLine();

                    System.out.print("Student Name: ");
                    String studentName = scanner.nextLine();

                    System.out.print("Division: ");
                    String division = scanner.nextLine();

                    System.out.print("Email ID: ");
                    String emailId = scanner.nextLine();

                    System.out.print("Remark based on MIS: ");
                    String remarkBasedOnMIS = scanner.nextLine();

                    StudentModel newStudent = new StudentModel(
                            srNo,
                            divisionWiseStudentCount,
                            enrollmentNo,
                            studentName,
                            division,
                            emailId,
                            remarkBasedOnMIS
                    );

                    try {
                        studentDAO.insertStudent(newStudent);
                        System.out.println("Student Inserted Successfully.\n");
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    break;

                case 5:
                    // Prompt user to enter enrollment number and delete student from database
                    System.out.print("\nEnter Enrollment No to delete student: ");
                    enrollmentNo = scanner.nextLong();
                    try {
                        studentDAO.deleteStudentByEnrollmentNo(enrollmentNo);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    break;

                case 6:
                    // Exit the application
                    scanner.close();
                    System.out.println("Exit");
                    break;

                case 100:
                    // Import student data from JSON file into database
                    try {
                        StudentModel[] students = JsonToJavaObj.readJsonFile(filePath);
                        for (StudentModel student : students) {
                            System.out.println(student.getSrNo());
                            StudentDAO.insertStudentFromJson(student);
                        }
                        System.out.println("\nData inserted successfully.\n");
                    } catch (IOException | SQLException e) {
                        e.printStackTrace();
                    }
                    break;

                default:
                    System.out.println("Invalid choice");
                    break;
            }
        } while (option != 6);
    }

    /**
     * Utility method to print a formatted table row with student data.
     *
     * @param row Array of strings representing student data for one row.
     */
    private static void printTableRow(String[] row) {
        System.out.format("| %-10s | %-20s | %-15s | %-30s | %-10s | %-30s | %-20s |\n",
                row[0], row[1], row[2], row[3], row[4], row[5], row[6]);
    }
}
