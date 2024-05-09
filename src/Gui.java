import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.io.IOException;

public class Gui extends JPanel  implements KeyListener {

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

    public Gui() {
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

    @Override
    public void keyTyped(KeyEvent e) {
        e.getKeyCode();

    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println(e.getKeyCode());

    }

    @Override
    public void keyReleased(KeyEvent e) {
        System.out.println(e.getKeyCode());

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
                case "Add other courses taken":
                    addCourse();
                    break;
                case "Display courses with Grade point Average":
                    displayCourseWithAverage();
                    break;
                case "Display courses in alphabetical order":
                    displayAlphabetically();
                    break;
                case "Display courses with grades in Highest to Lowest":
                    displayHighToLow();
                    break;
                case "Quit":
                    System.exit(0);
                    break;
                default:
                    break;
            }
        }

        private void showSubjectsForTerm() {
            // Create a new window to display the tables
            JFrame mainFrame = new JFrame("Subjects for All Terms");
            mainFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            mainFrame.setSize(600, 400); // Increase the size to accommodate the tables
            mainFrame.setLayout(new BoxLayout(mainFrame.getContentPane(), BoxLayout.Y_AXIS)); // Set layout to vertical
            // Fetch subjects for the term from program.getFilteredYearSem(year, semester)
            for (int i = 1; i <= 4; i++) {
                for (int j = 1; j <= 3; j++) {
                    ArrayList<Course> subjects = program.getFilteredYearSem(i, j); // Assuming it returns ArrayList<Course>

                    // Create a table model with column names
                    DefaultTableModel tableModel = new DefaultTableModel(
                            new Object[]{"CourseNo","Name", "Year", "Sem", "Units"}, 0); // Set row count to 0

                    // Populate the table model with subjects' data
                    for (Course subject : subjects) {
                        tableModel.addRow(new Object[]{
                                subject.getCourseNo() ,
                                subject.getName(),
                                subject.getYear(),
                                subject.getSem(),
                                subject.getUnits()
                        });
                    }
                    // Create a JTable with the populated table model
                    JTable table = new JTable(tableModel);
                    table.setPreferredScrollableViewportSize(new Dimension(500, 300)); // Set preferred size of the table
                    table.setDefaultEditor(Object.class, null);

                    // Add the table to a scroll pane
                    JScrollPane scrollPane = new JScrollPane(table);

                    // Add the scroll pane to the main frame
                    mainFrame.add(scrollPane);

                    // Make the frame visible only after the user presses Enter
                    mainFrame.setVisible(false);
                    // Wait for user input
                    System.out.println("Press [Enter] to continue...");
                    try {
                        System.in.read(); // This line will wait for the user to press enter
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    // Show the frame after the user presses Enter
                    mainFrame.setVisible(true);
                }
            }
        }


        private void showSubjectsWithGrades() {
            // Fetch subjects for the term from program.getFilteredYearSem(year, semester)
            ArrayList<Course> subjects = program.getFilteredYearSem(1,1); // Assuming it returns ArrayList<Course>

            // Create a new window and populate it with appropriate content
            JFrame newFrame = new JFrame("Subjects With Grades For Term " + 1 + " Semester " + 1);
            newFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            newFrame.setSize(600, 400); // Increase the size to accommodate the table
            newFrame.setLocationRelativeTo(null);
            setFocusable(true);
            addKeyListener((KeyListener) newFrame);

            // Create a table model with column names
            DefaultTableModel tableModel = new DefaultTableModel(
                    new Object[]{"CourseNo","Name", "Year", "Sem", "Units", "Grade"}, 0); // Set row count to 0

            // Populate the table model with subjects' data
            for (Course subject : subjects) {
                tableModel.addRow(new Object[]{
                        subject.getCourseNo(),
                        subject.getName(),
                        subject.getYear(),
                        subject.getSem(),
                        subject.getUnits(),
                        subject.getGrade()
                });
            }

            // Create a JTable with the populated table model
            JTable table = new JTable(tableModel);
            table.setPreferredScrollableViewportSize(new Dimension(500, 300)); // Set preferred size of the table

            table.setDefaultEditor(Object.class, null);
            // Add the table to a scroll pane
            JScrollPane scrollPane = new JScrollPane(table);

            // Add the scroll pane to the frame
            newFrame.add(scrollPane);

            // Make the frame visible
            newFrame.setVisible(true);
        }

        private void enterGrades() {
            // Similarly, you can create a new window for entering grades
            JFrame newFrame = new JFrame("Enter Grades");
            newFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            newFrame.setSize(400, 300);
            newFrame.setLocationRelativeTo(null);
            // Add your content here
            JPanel panel = new JPanel();
            panel.add(new JLabel("Enter Grades"));
            newFrame.add(panel);
            newFrame.setVisible(true);
        }

        private void editCourse() {
            // Similarly, you can create a new window for entering grades
            JFrame newFrame = new JFrame("Edit Course");
            newFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            newFrame.setSize(400, 300);
            newFrame.setLocationRelativeTo(null);
            // Add your content here
            JPanel panel = new JPanel();
            panel.add(new JLabel("Edit Course"));
            newFrame.add(panel);
            newFrame.setVisible(true);
        }


        private void addCourse() {

            // Create a new window for entering course details
            JFrame newFrame = new JFrame("Add Course");
            newFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            newFrame.setSize(400, 200);
            newFrame.setLocationRelativeTo(null);

            // Create a panel to hold the form components
            JPanel panel = new JPanel(new GridLayout(9, 2)); // Increased rows for better spacing

            // Add labels and text fields for course details
            JLabel courseNoLabel = new JLabel("Course No:");
            JTextField courseNoField = new JTextField();
            JLabel nameLabel = new JLabel("Name:");
            JTextField nameField = new JTextField();
            JLabel yearLabel = new JLabel("Year:");
            JTextField yearField = new JTextField();
            JLabel semLabel = new JLabel("Semester:");
            JTextField semField = new JTextField();
            JLabel unitsLabel = new JLabel("Units:");
            JTextField unitsField = new JTextField();
            JLabel gradeLabel = new JLabel("Grade:");
            JTextField gradeField = new JTextField();

            // Add the components to the panel
            panel.add(courseNoLabel);
            panel.add(courseNoField);
            panel.add(nameLabel);
            panel.add(nameField);
            panel.add(yearLabel);
            panel.add(yearField);
            panel.add(semLabel);
            panel.add(semField);
            panel.add(unitsLabel);
            panel.add(unitsField);
            panel.add(gradeLabel);
            panel.add(gradeField);

            // Create a button to submit the new course
            JButton addButton = new JButton("Add Course");
            addButton.addActionListener(e -> {
                // Extract the values from the text fields
                String courseNo ="";
                String name = nameField.getText();
                int year = Integer.parseInt(yearField.getText());
                int sem = Integer.parseInt(semField.getText());
                float units = Float.parseFloat(unitsField.getText());
                float grade = Float.parseFloat(gradeField.getText());
                program.addCourses(courseNo,name, year, sem, units, grade);
                newFrame.dispose();

            });
            panel.add(addButton);

            // Add the panel to the frame
            newFrame.add(panel);

            // Make the frame visible
            newFrame.setVisible(true);
            newFrame.pack();
        }

        private void displayCourseWithAverage() {
            // Similarly, you can create a new window for entering grades
            JFrame newFrame = new JFrame("Display Course with Average");
            newFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            newFrame.setSize(400, 300);
            newFrame.setLocationRelativeTo(null);
            // Add your content here
            JPanel panel = new JPanel();
            panel.add(new JLabel("Display Course with Average"));
            newFrame.add(panel);
            newFrame.setVisible(true);
        }

        private void displayAlphabetically() {
            JFrame newFrame = new JFrame("Display Alphabetically");
            newFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            newFrame.setSize(600, 400); // Increase the size to accommodate the table
            newFrame.setLocationRelativeTo(null);

            // Fetch subjects for the term from program.getFilteredYearSem(1, 1)
            ArrayList<Course> subjects = program.getFilteredYearSem(1, 1); // Assuming it returns ArrayList<Course>
            subjects = program.sortName(subjects);

            // Create a table model with column names
            DefaultTableModel tableModel = new DefaultTableModel(
                    new Object[]{"CourseNo","Name", "Year", "Sem", "Units"}, 0); // Set row count to 0

            // Populate the table model with subjects' data
            for (Course subject : subjects) {
                tableModel.addRow(new Object[]{
                        subject.getCourseNo(),
                        subject.getName(),
                        subject.getYear(),
                        subject.getSem(),
                        subject.getUnits()
                });
            }

            // Create a JTable with the populated table model
            JTable table = new JTable(tableModel);
            table.setPreferredScrollableViewportSize(new Dimension(500, 300)); // Set preferred size of the table
            table.setDefaultEditor(Object.class, null);
            // Add the table to a scroll pane
            JScrollPane scrollPane = new JScrollPane(table);

            // Add the scroll pane to the frame
            newFrame.add(scrollPane);

            // Make the frame visible
            newFrame.setVisible(true);
        }

        private void displayHighToLow() {
            // This is where you can create the new window and populate it with appropriate content
            JFrame newFrame = new JFrame("Subjects With Grades");
            newFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            newFrame.setSize(600, 400); // Increase the size to accommodate the table
            newFrame.setLocationRelativeTo(null);

            // Fetch subjects for the term from program.getFilteredYearSem(1, 1)
            ArrayList<Course> subjects = program.getFilteredYearSem(1, 1); // Assuming it returns ArrayList<Course>
            subjects = program.sortGrade(subjects);

            // Create a table model with column names
            DefaultTableModel tableModel = new DefaultTableModel(
                    new Object[]{"CourseNo","Name", "Year", "Sem", "Units", "Grade"}, 0); // Set row count to 0

            // Populate the table model with subjects' data
            for (Course subject : subjects) {
                tableModel.addRow(new Object[]{
                        subject.getCourseNo(),
                        subject.getName(),
                        subject.getYear(),
                        subject.getSem(),
                        subject.getUnits(),
                        subject.getGrade()
                });
            }

            // Create a JTable with the populated table model
            JTable table = new JTable(tableModel);
            table.setPreferredScrollableViewportSize(new Dimension(500, 300)); // Set preferred size of the table
            table.setDefaultEditor(Object.class, null);
            // Add the table to a scroll pane
            JScrollPane scrollPane = new JScrollPane(table);

            // Add the scroll pane to the frame
            newFrame.add(scrollPane);

            // Make the frame visible
            newFrame.setVisible(true);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Main Window");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.getContentPane().add(new Gui());
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
            frame.pack();
        });
    }
}
