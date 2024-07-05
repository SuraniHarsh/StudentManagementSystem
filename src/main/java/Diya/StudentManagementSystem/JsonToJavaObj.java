package Diya.StudentManagementSystem;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

/**
 * The JsonToJavaObj class provides a method to read a JSON file and convert it into an array of StudentModel objects.
 * <p>
 * This class uses the Jackson library to map JSON data to Java objects.
 * </p>
 *
 */
public class JsonToJavaObj {

    /**
     * Reads a JSON file from the specified file path and converts it into an array of StudentModel objects.
     *
     * @param filePath the path to the JSON file to be read
     * @return an array of StudentModel objects parsed from the JSON file
     * @throws IOException if an I/O error occurs while reading the file
     */
    public static StudentModel[] readJsonFile(String filePath) throws IOException {
        File file = new File(filePath);
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(file, StudentModel[].class);
    }
}
