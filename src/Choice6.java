
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class Choice6 extends JFrame {
    private JPanel viewSubject;
    private JTable subject;
    private JPanel cardPanel;
    private CardLayout cardLayout;
    private DefaultTableModel tableModel;
    private int currentYear = 1; // Default year
    private int currentSemester = 1; // Default semester
    InfoReader infoReader = new InfoReader();
    private JButton backToMainMenuButton;

    public Choice6() {
//        infoReader.delUserCourses();
        setTitle("Display courses with Grade point Average");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);

        // Create card layout panel
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        // Create table panel
        viewSubject = new JPanel(new BorderLayout());
        tableModel = new DefaultTableModel();
        subject = new JTable(tableModel);
        viewSubject.add(new JScrollPane(subject), BorderLayout.CENTER);

        // Add the table panel to the card panel
        cardPanel.add(viewSubject, "tablePanel");
        // Create back to main menu button
        backToMainMenuButton = new JButton("Back to Main Menu");
        backToMainMenuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });

// Create panel for buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(backToMainMenuButton);

// Add button panel to frame
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);


        // Add the card panel to the frame
        getContentPane().add(cardPanel, BorderLayout.CENTER);

        setVisible(true);

        listOfCourses();
    }
    public void listOfCourses() {
        try {
            Program program = new Program("My Program");
            ArrayList<Course> sortedCourses = program.sortName(program.getCourses());

            // Call the getFilteredYearSem method
            ArrayList<Course> filteredCourses = program.getFilteredYearSem(currentYear, currentSemester);

            // Create list to hold course data
            List<Object[]> data = new ArrayList<>();

            // Populate data list with filtered course information in reverse order
            for (int i = filteredCourses.size() - 1; i >= 0; i--) {
                Course course = filteredCourses.get(i);
                // Each row is an Object array with course name, units, grade, and weighted grade
                Object[] rowData = {course.getName(), course.getUnits(), course.getGrade(), course.getWeightedGrade()};
                data.add(rowData);
            }

            // Populate data list with sorted course information
            for (Course course : sortedCourses) {
                Object[] rowData = {course.getName(), course.getUnits(), "", ""}; // Empty strings for grade and weighted grade
                data.add(rowData);
            }

            // Column names
            String[] columnNames = {"Course", "Units", "Grade", "Weighted Grade"};

            // Convert list of arrays to two-dimensional array
            Object[][] courses = new Object[data.size()][];
            for (int x = 0; x < data.size(); x++) {
                courses[x] = data.get(x);
            }

            // Set table model
            tableModel.setDataVector(courses, columnNames);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Choice6();
    }
}
