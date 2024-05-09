
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class Choice3 extends JFrame {
    private JComboBox<String> yearComboBox;
    private JComboBox<String> semesterComboBox;
    private JComboBox<String> courseComboBox; // JComboBox to display courses
    private JButton continueButton;
    private JPanel cardPanel;
    private CardLayout cardLayout;
    Program program = new Program("BSIT");
    private JTextField inputField;

    public Choice3() {
        // Frame settings
        setTitle("View Subjects Each Term");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(400, 300); // Set the preferred size
        setMinimumSize(new Dimension(400, 300)); // Set the minimum size
        inputField = new JTextField(20);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                program.close();
                super.windowClosing(e);
            }
        });

        // Initialize JComboBoxes
        String[] yearOptions = {"1st Year", "2nd Year", "3rd Year"};
        yearComboBox = new JComboBox<>(yearOptions);

        String[] semesterOptions = {"1st Semester", "2nd Semester", "3rd Semester"};
        semesterComboBox = new JComboBox<>(semesterOptions);

        // Initialize Continue button
        continueButton = new JButton("Continue");

        // Initialize JComboBox for courses
        courseComboBox = new JComboBox<>();

        // Set up layout
        JPanel mainPanel = new JPanel(new BorderLayout());

        // Panel for selecting year and semester
        JPanel selectionPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        selectionPanel.add(yearComboBox);
        selectionPanel.add(semesterComboBox);
        selectionPanel.add(continueButton);

        // Panel for displaying course dropdown
        JPanel coursePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        coursePanel.add(courseComboBox);

        // Card layout for switching between screens
        cardPanel = new JPanel();
        cardLayout = new CardLayout();
        cardPanel.setLayout(cardLayout);
        cardPanel.add(selectionPanel, "SELECTION");
        cardPanel.add(coursePanel, "COURSE");

        // Add components to the main panel
        mainPanel.add(cardPanel, BorderLayout.CENTER);

        // Add main panel to the frame
        getContentPane().add(mainPanel);

        // Action listener for continue button
        continueButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get the selected options
                String selectedYear = (String) yearComboBox.getSelectedItem();
                String selectedSemester = (String) semesterComboBox.getSelectedItem();

                // Convert selected options to corresponding integers
                int year = Integer.parseInt(selectedYear.substring(0, 1)); // Extract the numeric part
                int semester = Integer.parseInt(selectedSemester.substring(0, 1)); // Extract the numeric part

                // Navigate to the course selection screen
                navigateToCourseScreen(year, semester);

            }
        });

        // Center frame on screen
        setLocationRelativeTo(null);
    }

    // Method to navigate to the course selection screen
    private void navigateToCourseScreen(int year, int semester) {

        // Call the getFilteredYearSem method
        ArrayList<Course> filteredCourses = program.getFilteredYearSem(year, semester);

        // Clear the existing items in the courseComboBox
        courseComboBox.removeAllItems();

        // Populate courseComboBox with course names
        for (Course course : filteredCourses) {
            courseComboBox.addItem(course.getName());
        }

        // Switch to the course selection screen
        cardLayout.show(cardPanel, "COURSE");

        // Adjust the frame size to fit the course selection screen
        pack();
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Choice3().setVisible(true));
    }
}
