import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class GuiUlit extends JPanel {

    // Buttons
    JButton showButton;
    JButton showButtons;
    JButton enterButton;
    JButton editButton;
    JButton addButton;
    JButton displayButton;
    JButton displayButton2;
    JButton displayButton3;
    JButton quitButton;

    Program program = new Program("IT");
    JFrame frame;

    public GuiUlit() {
        setLayout(new BorderLayout());

        // Create the top panel with "My Checklist Manager" label
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel titleLabel = new JLabel("My Checklist Manager", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24)); // Set font and size
        topPanel.add(titleLabel);
        add(topPanel, BorderLayout.NORTH);

        // Create the center panel for buttons with BoxLayout
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));

        // Instantiate buttons and add them to the center panel
        showButton = new JButton("Show subjects for each school term");
        showButtons = new JButton("Show subjects with grades for each term");
        enterButton = new JButton("Enter Grades for subjects recently finished");
        editButton = new JButton("Edit a course");
        addButton = new JButton("Add other courses taken");
        displayButton = new JButton("Display courses with Grade point Average");
        displayButton2 = new JButton("Display courses in alphabetical order");
        displayButton3 = new JButton("Display courses with grades in Highest to Lowest");
        quitButton = new JButton("Quit");

        // Add ActionListener to each button
        showButton.addActionListener(new ButtonListener());
        showButtons.addActionListener(new ButtonListener());
        enterButton.addActionListener(new ButtonListener());
        editButton.addActionListener(new ButtonListener());
        addButton.addActionListener(new ButtonListener());
        displayButton.addActionListener(new ButtonListener());
        displayButton2.addActionListener(new ButtonListener());
        displayButton3.addActionListener(new ButtonListener());
        quitButton.addActionListener(new ButtonListener());

        // Add buttons with spaces between them
        int spacing = 30; // Adjust the spacing as needed
        centerPanel.add(Box.createVerticalStrut(spacing));
        centerPanel.add(showButton);
        showButton.setAlignmentX(Component.CENTER_ALIGNMENT); // Align button to center
        centerPanel.add(Box.createVerticalStrut(spacing));
        centerPanel.add(showButtons);
        showButtons.setAlignmentX(Component.CENTER_ALIGNMENT); // Align button to center
        centerPanel.add(Box.createVerticalStrut(spacing));
        centerPanel.add(enterButton);
        enterButton.setAlignmentX(Component.CENTER_ALIGNMENT); // Align button to center
        centerPanel.add(Box.createVerticalStrut(spacing));
        centerPanel.add(editButton);
        editButton.setAlignmentX(Component.CENTER_ALIGNMENT); // Align button to center
        centerPanel.add(Box.createVerticalStrut(spacing));
        centerPanel.add(addButton);
        addButton.setAlignmentX(Component.CENTER_ALIGNMENT); // Align button to center
        centerPanel.add(Box.createVerticalStrut(spacing));
        centerPanel.add(displayButton);
        displayButton.setAlignmentX(Component.CENTER_ALIGNMENT); // Align button to center
        centerPanel.add(Box.createVerticalStrut(spacing));
        centerPanel.add(displayButton2);
        displayButton2.setAlignmentX(Component.CENTER_ALIGNMENT); // Align button to center
        centerPanel.add(Box.createVerticalStrut(spacing));
        centerPanel.add(displayButton3);
        displayButton3.setAlignmentX(Component.CENTER_ALIGNMENT); // Align button to center
        centerPanel.add(Box.createVerticalStrut(spacing));
        centerPanel.add(quitButton);
        quitButton.setAlignmentX(Component.CENTER_ALIGNMENT); // Align button to center
        centerPanel.add(Box.createVerticalStrut(spacing));

        add(centerPanel, BorderLayout.CENTER);
    }

    // ActionListener implementation for button clicks
    private class ButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();

            switch (command) {
                case "Show subjects for each school term":
                    showSubjectsForTerm();
                    break;
                case "Show subjects with grades for each term":
                    showSubjectsWithGrades();
                    break;
                case "Enter Grades for subjects recently finished":
                    enterGrades();
                    break;
                case "Edit a course":
                    editCourse();
                    break;
                // Handle other cases similarly
                case "Add other courses taken":
                    addCourse();
                    break;
                // Handle other cases similarly
                case "Display courses with Grade point Average":
                    displayCourseWithAverage();
                    break;
                // Handle other cases similarly
                case "Display courses in alphabetical order":
                    displayAlphabetically();
                    break;
                case "Display courses with grades in Highest to Lowest":
                    displayHighToLow();
                    break;
                // Handle other cases similarly
                case "Quit":
                    System.exit(0);
                    break;
                default:
                    break;
            }
        }

        private void showSubjectsForTerm() {
            // Retrieve courses for the specified year and semester
            ArrayList<Course> filteredCourses = program.getFilteredYearSem(1, 1);

            // Create table model with data
            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("Course Name");
            model.addColumn("Year");

            // Populate table with course data
            for (Course course : filteredCourses) {
                model.addRow(new Object[]{course.getName(), course.getYear()});
            }

            // Create and display the table in a JFrame
            JFrame tableFrame = new JFrame("Subjects For Term");
            JTable table = new JTable(model);
            JScrollPane scrollPane = new JScrollPane(table);
            tableFrame.getContentPane().add(scrollPane, BorderLayout.CENTER);
            tableFrame.setSize(400, 300);
            tableFrame.setVisible(true);
        }

        // Implement other action methods similarly
        private void showSubjectsWithGrades() {
            // Implementation for showing subjects with grades
        }

        private void enterGrades() {
            // Implementation for entering grades
        }

        private void editCourse() {
            // Implementation for editing a course
        }

        private void addCourse() {
            // Implementation for adding a course
        }

        private void displayCourseWithAverage() {
            // Implementation for displaying course with average
        }

        private void displayAlphabetically() {
            // Implementation for displaying courses alphabetically
        }

        private void displayHighToLow() {
            // Implementation for displaying courses with grades in highest to lowest
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Main Window");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.getContentPane().add(new Gui());
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
