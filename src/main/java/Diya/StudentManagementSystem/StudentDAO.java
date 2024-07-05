package Diya.StudentManagementSystem;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The StudentDAO class provides methods to perform CRUD operations on the student database.
 * <p>
 *     This class uses the DatabaseConnector class to establish a connection to the database.
 *     It also uses the StudentModel class to represent student data.
 *     It includes methods to insert, retrieve, and delete student records.
 * <p>
 */
public class StudentDAO {

    /**
     * Inserts a student record into the database using data from a JSON file.
     *
     * @param student the StudentModel object containing student data
     * @throws SQLException if a database access error occurs
     */
    public static void insertStudentFromJson(StudentModel student) throws SQLException {
        String query = "INSERT INTO students (sr_no, division_wise_student_count, enrollment_no, student, division, email_id, remark_based_on_mis) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            // Setting parameters for the prepared statement
            preparedStatement.setInt(1, student.getSrNo());
            preparedStatement.setInt(2, student.getDivisionWiseStudentCount());
            preparedStatement.setLong(3, student.getEnrollmentNo());
            preparedStatement.setString(4, student.getStudent());
            preparedStatement.setString(5, student.getDivision());
            preparedStatement.setString(6, student.getEmailId());
            preparedStatement.setString(7, student.getRemarkBasedOnMIS());

            // Executing the update
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            // Handle SQL exception
            System.err.println("SQL Exception: " + e.getMessage());
            throw e;
        }
    }

    /**
     * Inserts a student record into the database.
     *
     * @param student the StudentModel object containing student data
     * @throws SQLException if a database access error occurs
     */
    public void insertStudent(StudentModel student) throws SQLException {
        String query = "INSERT INTO students (sr_no, division_wise_student_count, enrollment_no, student, division, email_id, remark_based_on_mis) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            // Set parameters for the prepared statement
            preparedStatement.setInt(1, student.getSrNo());
            preparedStatement.setInt(2, student.getDivisionWiseStudentCount());
            preparedStatement.setLong(3, student.getEnrollmentNo());
            preparedStatement.setString(4, student.getStudent());
            preparedStatement.setString(5, student.getDivision());
            preparedStatement.setString(6, student.getEmailId());
            preparedStatement.setString(7, student.getRemarkBasedOnMIS());

            // Execute the update
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            // Handle SQL exception
            System.err.println("SQL Exception: " + e.getMessage());
            throw e;
        }
    }

    /**
     * Retrieves all student records from the database.
     *
     * @return a 2D array of strings containing student data
     * @throws SQLException if a database access error occurs
     */
    public String[][] getAllStudents() throws SQLException {
        String query = "SELECT sr_no, division_wise_student_count, enrollment_no, student, division, email_id, remark_based_on_mis FROM students";
        List<String[]> dataList = new ArrayList<>();

        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();

            while (resultSet.next()) {
                String[] rowData = new String[columnCount];
                for (int i = 1; i <= columnCount; i++) {
                    rowData[i - 1] = resultSet.getString(i);
                }
                dataList.add(rowData);
            }
        } catch (SQLException e) {
            // Handle SQL exception
            System.err.println("SQL Exception: " + e.getMessage());
            throw e;
        }

        // Convert the list to a 2D array
        String[][] data = new String[dataList.size()][];
        return dataList.toArray(data);
    }

    /**
     * Retrieves student records from the database based on the division.
     *
     * @param division the division to filter students by
     * @return a 2D array of strings containing student data for the specified division
     * @throws SQLException if a database access error occurs
     */
    public String[][] getStudentsByDivision(String division) throws SQLException {
        String query = "SELECT sr_no, division_wise_student_count, enrollment_no, student, division, email_id, remark_based_on_mis FROM students WHERE division = ?";
        List<String[]> dataList = new ArrayList<>();

        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, division);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                ResultSetMetaData metaData = resultSet.getMetaData();
                int columnCount = metaData.getColumnCount();

                while (resultSet.next()) {
                    String[] rowData = new String[columnCount];
                    for (int i = 1; i <= columnCount; i++) {
                        rowData[i - 1] = resultSet.getString(i);
                    }
                    dataList.add(rowData);
                }
            }
        } catch (SQLException e) {
            // Handle SQL exception
            System.err.println("SQL Exception: " + e.getMessage());
            throw e;
        }

        // Convert the list to a 2D array
        String[][] data = new String[dataList.size()][];
        return dataList.toArray(data);
    }

    /**
     * Retrieves a student record from the database based on the enrollment number.
     *
     * @param enrollmentNo the enrollment number to filter students by
     * @return a StudentModel object containing student data, or null if no student is found
     * @throws SQLException if a database access error occurs
     */
    public StudentModel getStudentByEnrollmentNo(long enrollmentNo) throws SQLException {
        String query = "SELECT sr_no, division_wise_student_count, student, division, email_id, remark_based_on_mis FROM students WHERE enrollment_no = ?";
        StudentModel student = null;

        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setLong(1, enrollmentNo);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    student = new StudentModel();
                    student.setSrNo(resultSet.getInt("sr_no"));
                    student.setDivisionWiseStudentCount(resultSet.getInt("division_wise_student_count"));
                    student.setStudent(resultSet.getString("student"));
                    student.setDivision(resultSet.getString("division"));
                    student.setEmailId(resultSet.getString("email_id"));
                    student.setRemarkBasedOnMIS(resultSet.getString("remark_based_on_mis"));
                }
            }
        } catch (SQLException e) {
            // Handle SQL exception
            System.err.println("SQL Exception: " + e.getMessage());
            throw e;
        }

        return student;
    }

    /**
     * Deletes a student record from the database based on the enrollment number.
     *
     * @param enrollmentNo the enrollment number of the student to be deleted
     * @throws SQLException if a database access error occurs
     */
    public void deleteStudentByEnrollmentNo(long enrollmentNo) throws SQLException {
        String query = "DELETE FROM students WHERE enrollment_no = ?";

        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setLong(1, enrollmentNo);
            int rowsDeleted = preparedStatement.executeUpdate();

            if (rowsDeleted > 0) {
                System.out.println("Student with Enrollment No " + enrollmentNo + " deleted successfully.");
            } else {
                System.out.println("No student found with Enrollment No " + enrollmentNo + ".");
            }
        } catch (SQLException e) {
            // Handle SQL exception
            System.err.println("SQL Exception: " + e.getMessage());
            throw e;
        }
    }

}
