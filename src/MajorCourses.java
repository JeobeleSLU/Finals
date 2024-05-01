public class MajorCourses extends Course{
    private int id ;

    public MajorCourses(String name, float units, float grade, int id) {
        super(name, units, grade);
        this.id = id;
    }
    public MajorCourses(){
        super();
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public MajorCourses(int id) {
        this.id = id;
    }

    public void setYear() {
       super.setYear(this.id/100);
    }
    public void validateId(int id) {
        String idString = String.valueOf(id);
        if (idString.length() != 3) {
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
            this.id = id;
            return;
        } else
            System.out.println("invalid ID!");
        id = validateInt();
        validateId(id);
    }

    public void setAllValues(String name, float units, float grade,int id ) {
        super.setAllValues(name, units, grade);
        this.id = id;
    }
}
