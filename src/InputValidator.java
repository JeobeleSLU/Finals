import java.util.Scanner;

public interface InputValidator {
    Scanner scanner = new Scanner(System.in);

    default int validateInt() {

        int number = 0;
        while (true) {
            try {
                number = Integer.parseInt(scanner.next());
                return number;

            } catch (NumberFormatException e) {
                System.out.println("Enter valid input!");
            }
        }
    }
    default double validateDouble() {

        double number = 0;
        while (true) {
            try {
                number = Integer.parseInt(scanner.next());
                return number;

            } catch (NumberFormatException e) {
                System.out.println("Enter valid input!");
            }
        }
    }
    default float validateFloat() {

        float number = 0;
        while (true) {
            try {
                number = Integer.parseInt(scanner.next());
                return number;

            } catch (NumberFormatException e) {
                System.out.println("Enter valid input!");
            }
        }
    }
}
