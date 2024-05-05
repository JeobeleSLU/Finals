import java.util.ArrayList;

public class Student {
    private String name;
    private Course course;
    private Course fCourse;
    public Student() {
        this.name = "";
        this.course = null;
        this.fCourse = null;
    }
    public Student(String name, Course course, Course fCourse) {
        this.name = name;
        this.course = course;
        this.fCourse = fCourse;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public void setFCourse(Course fCourse) {
        this.fCourse = fCourse;
    }

    public String getName() {
        return name;
    }

    public Course getCourse() {
        return course;
    }

    public Course getfCourse() {
        return fCourse;
    }


    ArrayList< Course> failedCourses = new ArrayList<>();
    String status;
    void addFailedCourse(Course course){
        if (!course.hasPassed()) {
            failedCourses.add(course);
        }

    }

}
