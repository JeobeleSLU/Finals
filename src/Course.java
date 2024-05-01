public class Course implements InputValidator {
    private String name;
    private int id;
    private float units;
    private float grade;
    private int year;

    public Course(String name, int id, float units, float grade) {
        this.name = name;
        this.id = id;
        this.units = units;
        this.grade = grade;
        setYear();
    }
    public Course(){
        name ="";
        id = 0;
        units = 0.0f;
        grade = 0.0f;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUnits(float units) {
        this.units = units;
    }

    public void setGrade(float grade) {
        this.grade = grade;
    }


    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public float getUnits() {
        return units;
    }

    public float getGrade() {
        return grade;
    }

    public void setYear() {
        this.year =this.id/100;
    }

    public int getYear() {
        return year;
    }

    public void validateId(int id) {
        String idString = String.valueOf(id);
        if (idString.length() != 3 ){
            System.out.println("invalid length, should be only 3 digits");
            id = validateInt();
            validateId(id);
            return;
        }
        int firstDigit = Integer.parseInt(String.valueOf(idString.charAt(0)));
        int secondDigit = Integer.parseInt(String.valueOf(idString.charAt(1)));
        int thirdDigit = Integer.parseInt(String.valueOf(idString.charAt(2)));

        //checks if the first and second digit have the range of 1-3
        boolean isCorrectRange = (firstDigit > 0 && firstDigit < 5) &&
                (secondDigit > 0 && secondDigit < 4) &&
                (thirdDigit > 0 && thirdDigit < 4);

        if (isCorrectRange) {
            System.out.println("sekai desu!");
            return;
        }else
            System.out.println("invalid ID!");
        id = validateInt();
        validateId(id);

    }


}
