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
    public void validateId(int id) {//validate id checks if the digits of the course is ok
        String idString = String.valueOf(id);
        //checks the length of the string to avoid string index out of bounds when converting back to int
        if (idString.length() != 3) {
            System.out.println("invalid length, should be only 3 digits");
            id = validateInt();
            validateId(id);
            return;
        }
        int firstDigit = Integer.parseInt(String.valueOf(idString.charAt(0)));
        int secondDigit = Integer.parseInt(String.valueOf(idString.charAt(1)));
        int thirdDigit = Integer.parseInt(String.valueOf(idString.charAt(2)));

        //checks if the first digit only range from 1-4 and 2nd and 3rd digit range 1-3
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
        validateId(id); //calls the method to recheck
    }

    public void setAllValues(String name, float units, float grade,int id ) {
        super.setAllValues(name, units, grade);
        this.id = id;
    }
}
