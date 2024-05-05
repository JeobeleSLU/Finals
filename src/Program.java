import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Program implements GradeCalculator {
    private String name;
    private ArrayList<Course> courses = new ArrayList<>();

    Scanner scanner = new Scanner(System.in);

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Program(String name) {
        this.name = name;
        startRead();
    }

    private void startRead() {
        InfoReader resources = new InfoReader();
        courses.addAll(resources.getCourses());
    }
//    for testing purposes
    void addCourses() {
        Course courseToAdd = new Course();
        System.out.println("Enter the course name");
        String name = scanner.next();
        System.out.println("Enter the course grade");
        float grade = validateFloat();
        System.out.println("Enter the units");
        float units = validateFloat();
        System.out.println("Enter the id");
        int year = 0;
        int sem = 0;
        courseToAdd.setAllValues(name,year,sem, units, grade);
        courses.add(courseToAdd);
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
        try{
            FileOutputStream fileOutMajor = new FileOutputStream("Courses.ser");
             ObjectOutputStream objectOutCourses = new ObjectOutputStream(fileOutMajor);
            objectOutCourses.writeObject(courses);

            System.out.println("info saved!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    void print(){// for debugging
        courses.forEach(mi -> System.out.println(mi.getName()));

    }

    //todo: Create functionality based on the JTextField input

    public void addCourses(String name, int year, int sem, float units, float grade) {
        Course course = new Course();
        course.setAllValues(name,year,sem,units,grade);
        courses.add(course);
    }

    //for debugging
    public ArrayList<Course> getCourses() {
        return courses;
    }



    public void reset(){
        courses.removeAll(courses);
    }
    //todo: do sorting by grade, lexico

}
