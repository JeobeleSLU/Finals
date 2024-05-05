import javax.swing.*;
import java.util.Scanner;

public interface InputValidator {// input validator interface / contract
    Scanner scanner = new Scanner(System.in);

    default int validateInt() {

        int number ;
        while (true) {
            try {
                scanner.next();
                number = Integer.parseInt(scanner.next());
                return number;

            } catch (NumberFormatException e) {
                System.out.println("Enter valid input!");
            }
        }
    }
    default double validateDouble() {

        double number;
        while (true) {
            try {
                scanner.next();
                number = Double.parseDouble(scanner.next());
                return number;

            } catch (NumberFormatException e) {
                System.out.println("Enter valid input!");
            }
        }
    }
    default float validateFloat() {

        float number;
        while (true) {
            try {
                scanner.next();

                number = Float.parseFloat(scanner.next());
                return number;

            } catch (NumberFormatException e) {
                System.out.println("Enter valid input!");
            }
        }
    }
    default float validateFloat(float number) {

        try {
            scanner.next();

            number = Float.parseFloat(scanner.next());
            return number;

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null,
                    "Please input a valid input","Invalid input",JOptionPane.ERROR);
            return number = 0;
        }
    }
    default double validateDouble(double number) {

        try {
            scanner.next();

            number = Float.parseFloat(scanner.next());
            return number;

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null,
                    "Please input a valid input","Invalid input",JOptionPane.ERROR);
            return number = 0;
        }
    }
    default double validateInt(double number) {

        try {
            scanner.next();

            number = Integer.parseInt(scanner.next());
            return number;

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null,
                    "Please input a valid input","Invalid input",JOptionPane.ERROR);
            return number = 0;
        }
    }
}

