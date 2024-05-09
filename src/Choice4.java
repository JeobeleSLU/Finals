
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;


//todo kulang ng column, walang course id at grades then update ng edits
public class Choice4 extends JFrame {
    private JLabel headingLabel;
    private JTable coursesTable;
    private JButton backButton;
    private JButton applyChangesButton;
    private DefaultTableModel tableModel;
    private int currentYear = 1;
    private int currentSemester = 1;

    public Choice4() {
        setTitle("Edit Courses");
        setSize(700, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Heading
        headingLabel = new JLabel("Edit Courses");
        headingLabel.setFont(new Font("Arial", Font.BOLD, 18));
        headingLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(headingLabel, BorderLayout.NORTH);

        // Table Model
        String[] columnNames = {"Course", "Units"};
        tableModel = new DefaultTableModel(null, columnNames);
        coursesTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(coursesTable);
        add(scrollPane, BorderLayout.CENTER);

        // Button Panel
        JPanel buttonPanel = new JPanel(new BorderLayout());

        // Apply Changes Button
        applyChangesButton = new JButton("Apply Changes");
        applyChangesButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Disable editing in the table
                coursesTable.setEnabled(false);
                applyChangesButton.setEnabled(false); // Disable the button after applying changes

                // Update the file with the changes
                try {
                    Program program = new Program("My Program");
                    ArrayList<Course> sortedCourses = program.sortName(program.getCourses());

                    // Iterate through the rows of the JTable
                    for (int i = 0; i < tableModel.getRowCount(); i++) {
                        // Retrieve the course name and units from the table
                        String courseName = (String) tableModel.getValueAt(i, 0);
                        int units = Integer.parseInt(tableModel.getValueAt(i, 1).toString());

                        // Update the corresponding course in the program
                        for (Course course : sortedCourses) {
                            if (course.getName().equals(courseName)) {
                                course.setUnits(units);
                                break; // No need to continue searching once the course is found
                            }
                        }
                    }
                    //     program.updateCourse(course);

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        buttonPanel.add(applyChangesButton, BorderLayout.WEST);

        // Back to Main Menu Button functionalities
        backButton = new JButton("Back to Main Menu");
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        buttonPanel.add(backButton, BorderLayout.EAST);

        add(buttonPanel, BorderLayout.SOUTH); // Add the button panel to the south region
    }

    // Method to update the table with courses
    public void updateCoursesTable() {
        try {
            Program program = new Program("My Program");
            ArrayList<Course> sortedCourses = program.sortName(program.getCourses());

            // Call the getFilteredYearSem method
            ArrayList<Course> filteredCourses = program.getFilteredYearSem(currentYear, currentSemester);

            // Create list to hold course data
            List<Object[]> data = new ArrayList<>();
            for (Course course : sortedCourses) {
                Object[] rowData = {course.getName(), course.getUnits()};
                data.add(rowData);
            }

            // Populate data list with course information
            for (Course course : filteredCourses) {
                // Each row is an Object array with course code, name, and units
                Object[] rowData = {course.getName(), course.getUnits()};
                data.add(rowData);
            }

            // Convert list of arrays to two-dimensional array
            Object[][] courses = new Object[data.size()][];
            for (int x = 0; x < data.size(); x++) {
                courses[x] = data.get(x);
            }

            // Set table model
            tableModel.setDataVector(courses, new String[]{"Course", "Units"});

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // A method to set the visibility of this panel
    public void showChoice4Panel(boolean visible) {
        setVisible(visible);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                Choice4 choice4 = new Choice4();
                choice4.updateCoursesTable(); // Update the table with courses
                choice4.setVisible(true); // Make the frame visible
            }
        });
    }
}