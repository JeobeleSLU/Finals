import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Program implements GradeCalculator,InputValidator {
    private String name;
    private ArrayList<Course> courses = new ArrayList<>();
    private ArrayList<Course> firstSemFirstYear = new ArrayList<>();
    private Student student = new Student();
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
        firstSemFirstYear = sortName(firstSemFirstYear);
    }

    private void startRead() {
        InfoReader resources = new InfoReader();
        courses.addAll(resources.getCourse());
    }

    //    for testing purposes
    void addCourses() {
        Course courseToAdd = new Course();
        System.out.println("Enter the course name");
        String name = scanner.next();
        System.out.println("Enter the course grade");
        float grade = validateFloat();
        if (courseToAdd.hasPassed()){
            student.addFailedCourse(courseToAdd);
        }
        System.out.println("Enter the units");
        float units = validateFloat();
        System.out.println("Enter the id");
        System.out.println("enter Year");
        int year = validateInt();
        System.out.println("enter Sem");
        int sem = validateInt();
        courseToAdd.setAllValues(name, year, sem, units, grade);
        courses.add(courseToAdd);
    }

    public ArrayList<Course> getFilteredYearSem(int yearToGet, int semToGet) {// for debugging
        ArrayList<Course> filteredArray = (ArrayList<Course>) courses.stream()
                .filter(c -> c.getYear() == yearToGet)
                .filter(c -> c.getSem() == semToGet)
                .collect(Collectors.toList());

        filteredArray.forEach(course -> System.out.println(course.getName()));
        return filteredArray;
    }
    public void updateValues() {
        for (Course course : courses) {
            // Update the values of each course
            // For example, if you need to recalculate the weighted grade:
            course.getResult(); // Update the result based on the new grade
        }
    }



    //todo: Create functionality based on the JTextField input


    public void addCourses(String name, int year, int sem, float units, float grade) {
        Course course = new Course();
        course.setAllValues(name, year, sem, units, grade);
        courses.add(course);
    }
    public void updateCourseGrade(Course updatedCourse) {
        // Find the corresponding course in the list and update its grade
        for (Course course : courses) {
            if (course.equals(updatedCourse)) {
                course.setGrade(updatedCourse.getGrade());
                // Recalculate the weighted grade and pass status
                course.calculateWeightedGrade();
                course.getResult();
                break;
            }
        }
    }

    //for debugging
    public ArrayList<Course> getCourses() {
        return courses;
    }

    public ArrayList<Course> getFirstSemFirstYear() {
        return firstSemFirstYear;
    }


    public void reset() {
        courses.removeAll(courses);
    }

    //todo: do sorting by grade, lexico

    //Sorting Alphabetically
    public ArrayList<Course> sortName(ArrayList<Course> sortedCourse) {
        ArrayList<Course> sortFirst = (ArrayList<Course>) sortedCourse.stream()
                .sorted(Comparator.comparing(Course::getName))
                .collect(Collectors.toList());

        sortFirst.forEach(course -> System.out.println(course.getName()));
        return sortFirst;
    }
    //sorting grade from highest to lowest
        public ArrayList<Course> sortGrade (ArrayList<Course> sortedGrade) {
            ArrayList<Course> sortFirst = (ArrayList<Course>) sortedGrade.stream()
                    .sorted(Comparator.comparing(Course::getGrade).reversed())
                    .collect(Collectors.toList());

            sortFirst.forEach(course -> System.out.println(course.getGrade()));
            return sortFirst;
        }
    public void close() {
        try {
            FileOutputStream fileOutMajor = new FileOutputStream("UserCourses.ser");
            ObjectOutputStream objectOutCourses = new ObjectOutputStream(fileOutMajor);
            objectOutCourses.writeObject(courses);

            System.out.println("info saved!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
