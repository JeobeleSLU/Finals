public abstract class Course implements InputValidator,GradeCalculator {
    private String name;

    private float units;
    private float grade;
    private int year;
    private float weightedGrade ;
    private boolean isFinished;

    public Course(String name, float units, float grade) {
        this.name = name;
        this.units = units;
        this.grade = grade;
        this.weightedGrade = calculateGrade(this) ;
        getResult();
    }

    public void setWeightedGrade(float weightedGrade) {
        this.weightedGrade = weightedGrade;
    }

    public Course(){
        name ="";
        units = 0.0f;
        grade = 0.0f;
        weightedGrade = 0;
        getResult();
    }
    public void getResult(){
        isFinished = grade > 75;
    }

    public void setName(String name) {
        this.name = name;
    }


    public void setUnits(float units) {
        this.units = units;
    }

    public void setGrade(float grade) {
        this.grade = grade;
        calculateGrade(this);
        getResult();
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

    public void setYear(int year) {
        this.year = year;
    }

    public int getYear() {
        return year;
    }
    public void setAllValues(String name, float units, float grade){
        setName(name);
        setUnits(units);
        setGrade(grade);

    }
}



