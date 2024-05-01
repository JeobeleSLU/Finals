import java.io.*;
import java.util.ArrayList;

public class InfoReader {
    private final ArrayList<MajorCourses> major;
    private final ArrayList<MinorCourses> minor;

    public InfoReader() {
        major = readCourses("MajorCourses.ser");
        minor = readCourses("MinorCourses.ser");
    }

    public ArrayList<MajorCourses> getMajor() {
        return major;
    }

    public ArrayList<MinorCourses> getMinor() {
        return minor;
    }

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
