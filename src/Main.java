import javax.swing.*;
import java.util.Scanner;

public class Main  {
    static Program program;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        program = new Program("Information Technology");
        System.out.println("What do you want to add?");
        getUserInput();
        try {
            program.close();
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, System.err, "what the fuck are you doin?", JOptionPane.ERROR_MESSAGE);
        }

    }

    private static void getUserInput() {
        Scanner scanner = new Scanner(System.in);

        int uChoice = program.validateInt();

            if (uChoice == 1){
                System.out.println("how many minor courses u want to add?");
                int n = program.validateInt();
                for (int i = 0; i < n; i++){
                    program.addMinoCourse();
                }
            } else if (uChoice == 2 ) {
                System.out.println("how many major courses u want to add?");
                int n = program.validateInt();
                for (int i = 0; i < n; i++) {
                    program.addMinoCourse();
                }
            }else System.out.println("invalid input");
        }
}



