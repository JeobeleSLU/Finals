import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Scanner;
import java.util.stream.Collectors;

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
    void getYearCourse(){
        ArrayList<MajorCourses> yearCourses = (ArrayList<MajorCourses>) majorCourses.stream()
                .sorted(Comparator.comparing(MajorCourses::getYear))
                .collect(Collectors.toList());
    }
    private void startRead() {
        InfoReader resources = new InfoReader();
        minorCourses.addAll(resources.getMinor());
        majorCourses.addAll(resources.getMajor());
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

    protected int validateInt() {
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
    void print(){
        minorCourses.forEach(mi -> System.out.println(mi.getName()));
        majorCourses.forEach(ma -> System.out.println(ma.getName()));

    }

    public void addMajorCourse(String name, float units, float grade,int id) {
        MajorCourses course = new MajorCourses();
        course.setAllValues(name,units,grade,id);
        majorCourses.add(course);
    }
    public void addMinorCourse(String name, float units, float grade) {
        MinorCourses course = new MinorCourses();
        course.setAllValues(name,units,grade);
        minorCourses.add(course);
    }
    public ArrayList<Course> getAllCourses() {
        ArrayList<Course> allCourses = new ArrayList<>();
        allCourses.addAll(majorCourses);
        allCourses.addAll(minorCourses);
        return allCourses;
    }

    public ArrayList<MajorCourses> getMajorCourses() {
        return majorCourses;
    }

    public ArrayList<MinorCourses> getMinorCourses() {
        return minorCourses;
    }

    public void reset(){
        majorCourses.removeAll(majorCourses);
        minorCourses.removeAll(minorCourses);

    }
}
