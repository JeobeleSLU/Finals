import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

public abstract class Program implements InputValidator, Serializable,GradeCalculator {
    ArrayList <Course> courses = new ArrayList<>();
    public Program() {
//        courses.add();
    }
    void addCourse(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the course name");
        String name = scanner.next();
        float grade = validateFloat();


//        courses.add();
    }




}
//na mind block di makagawa haha