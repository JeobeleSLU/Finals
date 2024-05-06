import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;

public  class Course implements InputValidator,GradeCalculator, Serializable {
    private String courseNo;
    private String name;
    private float units;
    private float grade;
    private int year;
    private float weightedGrade;
    private boolean hasPassed;
    private int sem;
    @Serial
    private static final long serialVersionUID = 1L;

    public Course(String courseNo, String name,int year,int sem, float units, float grade) {
        this.courseNo = courseNo;
        this.name = name;
        this.year = year;
        this.sem = sem;
        this.units = units;
        this.grade = grade;
        this.weightedGrade = calculateGrade(this) ;
        getResult();
        setMaximumGrade();
    }

    public void setMaximumGrade() {
        if (this.grade > 99){
            this.grade=99;
        }else if (this.grade <65){
            this.grade = 65;
        }
    }


    public void setWeightedGrade(float weightedGrade) {
        this.weightedGrade = weightedGrade;
    }

    public Course(){
        courseNo = "";
        name ="";
        units = 0.0f;
        grade = 0.0f;
        weightedGrade = 0;
        getResult();
        setMaximumGrade();
    }

    public int getSem() {
        return sem;
    }


    public void getResult(){
        hasPassed = grade > 75;
    }
    public boolean hasPassed() {
        return !(getGrade() > 75);

    }

    public void setName(String name) {
        this.name = name;
    }


    public void setUnits(float units) {
        this.units = units;
    }

    public void setGrade(float grade) {
        this.grade = grade;
        this.weightedGrade = calculateGrade(this);
        getResult();
    }

    public float getWeightedGrade() {
        return weightedGrade;
    }

    public String getName() {
        return name;
    }


    public float getUnits() {
        return units;
    }

    public float getGrade() {
        return grade;
    }


    // Calculate the weighted grade
    public void calculateWeightedGrade() {
        this.weightedGrade = calculateGrade(this);
    }

    public void setCourseNo(String courseNo) {
        this.courseNo = courseNo;
    }

    public String getCourseNo() {
        return courseNo;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getYear() {
        return year;
    }

    public void setSem(int sem) {
        this.sem = sem;
    }

    public void setAllValues(String courseNo, String name, int year, int sem, float units, float grade){
        setCourseNo(courseNo);
        setName(name);
        setUnits(units);
        setGrade(grade);
        setYear(year);
        setSem(sem);
        setMaximumGrade();


    }
    void recheckAllValues (){
        setMaximumGrade();
        getResult();
    }
    public static void addCourse(ArrayList<Course> courses,String courseNo, String name, int year, int sem, float units, float grade) {
        courses.add(new Course(courseNo,name, year, sem, units, grade));

    }
}



