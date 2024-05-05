import java.io.*;
import java.util.ArrayList;

public class InfoReader {
    private final ArrayList<Course> courses;

    public InfoReader() {
        courses = readCourses("Courses.ser");
    }

    public ArrayList<Course> getCourses() {
        return courses;
    }



    //uses the course reader
    private ArrayList readCourses(String fileName) {
        ArrayList courses = new ArrayList<>();
        try (FileInputStream fileIn = new FileInputStream(fileName);
             ObjectInputStream objectIn = new ObjectInputStream(fileIn)) {
            courses = (ArrayList) objectIn.readObject();
    } catch (FileNotFoundException e) {
            System.err.println("File not found: " + fileName);
        } catch (IOException | ClassNotFoundException | ClassCastException e) {
            System.err.println("Error reading file: " + fileName);
            e.printStackTrace();
        }
        return courses;
    }
}
