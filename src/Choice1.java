
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class Choice1 extends JFrame {
    private JPanel viewSubject;
    private JPanel headerPanel;
    private JLabel headerLabel;
    private JTable subject;
    private JPanel cardPanel;
    private CardLayout cardLayout;
    private DefaultTableModel tableModel;
    private JPanel nextPanel;
    private JPanel previewPanel;
    private JButton previewButton;
    private JButton backToMainMenuButton;
    private JButton nextButton;
    private int currentYear = 1; // Default year
    private int currentSemester = 1; // Default semester
    InfoReader infoReader = new InfoReader();


    public Choice1() {
        Program program  = new Program("BSIT");
        setTitle("Show subjects for each school term");
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
        headerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        headerLabel = new JLabel();
        headerPanel.add(headerLabel);
        viewSubject.add(headerPanel, BorderLayout.NORTH);


        headerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        headerLabel = new JLabel();
        headerPanel.add(headerLabel);

        viewSubject.add(headerPanel, BorderLayout.NORTH);

        // Create next panel
        nextPanel = new JPanel();
        nextPanel.add(new JLabel("Next Panel"));

        // Create preview panel
        previewPanel = new JPanel();
        previewPanel.add(new JLabel("Preview Panel"));

        // Add panels to card panel
        cardPanel.add(viewSubject, "tablePanel");
        cardPanel.add(nextPanel, "nextPanel");
        cardPanel.add(previewPanel, "previewPanel");

        // Create buttons
        previewButton = new JButton("Back");
        backToMainMenuButton = new JButton("Back to Main Menu");
        nextButton = new JButton("Next");


        // Add action listeners to buttons
        previewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentYear > 1 || currentSemester > 1) {
                    if (currentSemester > 1) {
                        currentSemester--;
                    } else {
                        currentSemester = 3;
                        currentYear--;
                    }
                    listOfCourses();
                    cardLayout.show(cardPanel, "tablePanel"); // Update to show the tablePanel

                    // Add the updated table to the viewSubject panel
                    viewSubject.removeAll(); // Remove previous components
                    viewSubject.add(new JScrollPane(subject), BorderLayout.CENTER);
                    viewSubject.add(headerPanel, BorderLayout.NORTH); // Re-add header panel
                    viewSubject.revalidate(); // Revalidate the panel to reflect changes
                }
                if (currentYear == 1 && currentSemester == 1) {
                    previewButton.setEnabled(false);
                }
                nextButton.setEnabled(true); // Enable the "Next" button if it was previously disabled

            }

        });

        backToMainMenuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                new WelcomePage();
                //choice1.dispose();
            }
        });

        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentYear < 5 || currentSemester < 3) {
                    if (currentSemester < 3) {
                        currentSemester++;
                    } else {
                        currentSemester = 1;
                        currentYear++;
                    }

                    listOfCourses(); // Update the course list
                    headerLabel.setText("Year: " + currentYear + " - Semester: " + currentSemester); // Update the header label
                }
                if (currentYear == 5 && currentSemester == 2) { // Update the loop termination condition
                    nextButton.setEnabled(false);
                    cardLayout.show(cardPanel, "nextPanel");
                    // Add the updated table to the next panel
                    nextPanel.removeAll(); // Remove previous components
                    nextPanel.add(new JScrollPane(subject));
                    nextPanel.revalidate(); // Revalidate the panel to reflect changes
                }
                if (currentYear == 4 && currentSemester == 2) {
                    nextButton.setEnabled(false);
                }
                previewButton.setEnabled(true); // Enable the "Preview" button if it was previously disabled

            }
        });

        // Create panel for buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        buttonPanel.add(Box.createHorizontalGlue());
        buttonPanel.add(previewButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(10, 0)));
        buttonPanel.add(backToMainMenuButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(10, 0)));
        buttonPanel.add(nextButton);
        buttonPanel.add(Box.createHorizontalGlue());

        // Add components to frame
        getContentPane().add(cardPanel, BorderLayout.CENTER);
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);

        listOfCourses();
        previewButton.setEnabled(false);
    }

    public void listOfCourses() {
        try {
            Program program = new Program("My Program");

            // Call the getFilteredYearSem method
            ArrayList<Course> filteredCourses = program.getFilteredYearSem(currentYear, currentSemester);

            // Create list to hold course data
            List<Object[]> data = new ArrayList<>();

            List<String> courseIds = new ArrayList<>(); // Create a list for course IDs

            // Populate data list with course information
            for (Course course : filteredCourses) {
                // Each row is an Object array with course code, name, and units
                Object[] rowData = {course.getCourseNo(), course.getName(), course.getUnits()};
                data.add(rowData);
                courseIds.add(course.getCourseNo()); // Add course ID to the list
            }

            // Column names
            String[] columnNames = {"Course Id", "Course", "Units"};
            // Convert list of course IDs to array
            String[] courseIdArray = courseIds.toArray(new String[0]);
            // Now you have the course IDs in the courseIdArray
            for (String courseId : courseIdArray) {
                System.out.println(courseId);
            }

            // Convert list of arrays to two-dimensional array
            Object[][] courses = new Object[data.size()][];
            for (int x = 0; x < data.size(); x++) {
                courses[x] = data.get(x);
            }

            System.out.println(courses);
            headerLabel.setText("Year: " + currentYear + " - Semester: " + currentSemester);

            // Set table model
            tableModel.setDataVector( courses, columnNames);
            // Convert list of course IDs to array
            courseIdArray = courseIds.toArray(new String[0]);
            // Now you have the course IDs in the courseIdArray
            for (String courseId : courseIdArray) {
                System.out.println(courseId);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Choice1();
    }
}