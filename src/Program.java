import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Program implements Serializable, GradeCalculator {
    private static final long serialVersionUID = 123L;
    private String name;
    private ArrayList<MajorCourses> majorCourses = new ArrayList<>();
    private ArrayList<MinorCourses> minorCourses = new ArrayList<>();
    private transient Scanner scanner = new Scanner(System.in); // transient because Scanner is not serializable

    public Program(String name) {
        this.name = name;
        startRead();
    }

    private void startRead() {
        try {
            InfoReader resources = new InfoReader();
            minorCourses.addAll(resources.getMinor());
            majorCourses.addAll(resources.getMajor());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void addMajorCourse() {
        MajorCourses courseToAdd = new MajorCourses();
        System.out.println("Enter the course name");
        String name = scanner.next();
        System.out.println("Enter the course grade");
        float grade = validateFloat();
        System.out.println("Enter the units");
        float units = validateFloat();
        System.out.println("Enter the id");
        int id = validateInt();
        courseToAdd.setAllValues(name, units, grade, id);
        majorCourses.add(courseToAdd);
    }

    void addMinorCourse() {
        MinorCourses courseToAdd = new MinorCourses();
        System.out.println("Enter the course name");
        String name = scanner.next();
        System.out.println("Enter the course grade");
        float grade = validateFloat();
        System.out.println("Enter the units");
        float units = validateFloat();
        courseToAdd.setAllValues(name, units, grade);
        minorCourses.add(courseToAdd);
    }

    private float validateFloat() {
        while (!scanner.hasNextFloat()) {
            System.out.println("Invalid input. Please enter a valid float value:");
            scanner.next();
        }
        return scanner.nextFloat();
    }

    private int validateInt() {
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input. Please enter a valid integer value:");
            scanner.next();
        }
        return scanner.nextInt();
    }

    public void close() {
        try (FileOutputStream fileOutMajor = new FileOutputStream("MajorCourses.ser");
             ObjectOutputStream objectOutMajor = new ObjectOutputStream(fileOutMajor);
             FileOutputStream fileOutMinor = new FileOutputStream("MinorCourses.ser");
             ObjectOutputStream objectOutMinor = new ObjectOutputStream(fileOutMinor)) {

            objectOutMajor.writeObject(majorCourses);
            objectOutMinor.writeObject(minorCourses);

            System.out.println("info saved!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
