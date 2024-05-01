import java.util.Scanner;

public class Main  {
    public static void main(String[] args) {
        validateId(999);
    }
    public static void validateId(int id) {
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

    private static int validateInt() {
        int number = 0;
        while (true) {
            Scanner scanner = new Scanner(System.in);
            try {
                number = Integer.parseInt(scanner.next());
                return number;

            } catch (NumberFormatException e) {
                System.out.println("Enter valid input!");
            }
        }
    }


}