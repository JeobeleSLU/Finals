import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class TrialGui extends JFrame {
    private Program program;
    private JTable coursesTable;
    private JTextField courseNoField;
    private JTextField nameField;
    private JTextField unitsField;
    private JTextField gradeField;
    private JTextField idField;
    private JButton updateButton;
    JScrollPane scrollPane;
    DefaultTableModel model;
    JPanel mainPanel;
    JButton addCourse;
    JButton addButton;


    public TrialGui(String programName) {
        this.program = new Program(programName);
        this.initializeComponents();
        createGUI();
        this.updateCoursesTable();
        this.addWindowListener(new WindowAdapter() {
                public void windowClosing(WindowEvent e) {
                TrialGui.this.program.close();
            }
        });
    }

    void initializeComponents(){
        this.coursesTable = new JTable();
        this.coursesTable.setFillsViewportHeight(true);
        this.courseNoField = new JTextField(20);
        this.nameField = new JTextField(20);
        this.unitsField = new JTextField(10);
        this.gradeField = new JTextField(10);
        this.idField = new JTextField(10);
        this.updateButton = new JButton("Update Values");
        this.updateButton.addActionListener(e-> {
            TrialGui.this.program.updateValues();
           mainPanel.repaint();
        });
    }

    private void createGUI() {
        this.setTitle("Course Management");
        this.setSize(400, 400);
        this.setDefaultCloseOperation(3);
        this.setExtendedState(6);
        this.scrollPane = new JScrollPane(this.coursesTable);
        this.coursesTable.setDefaultEditor(Object.class, (TableCellEditor)null);
        this.mainPanel = new JPanel(new BorderLayout());
        this.mainPanel.add(this.scrollPane, "Center");
        JPanel inputPanel = new JPanel(new GridLayout(2, 1));

        addButton = new JButton("Add Course");
        inputPanel.add(addButton);
        addButton.addActionListener(e -> {
            openAddCourseFrame();
        });
        JButton resetButton = new JButton("reset");
        inputPanel.add(resetButton);
        resetButton.addActionListener(
                (e) -> {
            program.reset();
            recreateTable();
        });
        inputPanel.add(this.updateButton);
        this.mainPanel.add(inputPanel,BorderLayout.SOUTH);
        this.add(this.mainPanel);
        this.setVisible(true);
    }

    private void updateCoursesTable() {
        JPanel termPanel = new JPanel();
        termPanel.setLayout(new BoxLayout(termPanel, BoxLayout.Y_AXIS));

        for (int i = 1; i <= 4; i++) {
            for (int j = 1; j <= 3; j++) {
                if (i == 4 && j >2){
                    break;
                }
                JPanel yearSemPanel = new JPanel(new BorderLayout());
                JLabel yearSemLabel = new JLabel("Year " + i + " Semester " + j);
                yearSemPanel.add(yearSemLabel, BorderLayout.NORTH);

                yearSemLabel.setPreferredSize(new Dimension(20,20));
                 model = new DefaultTableModel(
                        new Object[]{"Course No","Course", "Sem", "Units", "Grade", "Weighted Grade","Passed"}, 0);
                ArrayList<Course> courses = program.getFilteredYearSem(i, j);
                for (Course course : courses) {
                    model.addRow(new Object[]{
                            course.getCourseNo(),
                            course.getName(),
                            course.getSem(),
                            course.getUnits(),
                            course.getGrade(),
                            course.getWeightedGrade(),
                            course.hasPassed(), // Update to use isPassed() method
                    });

                }
                JTable table = new JTable(model);
                table.setDefaultEditor(Object.class, null);
                table.getColumnModel().getColumn(6).setCellRenderer(new RadioButtonRenderer());

                // Add editor for the "Grade" column
                table.getColumnModel().getColumn(4).setCellEditor(new DefaultCellEditor(new JTextField()));

                // Set row height to make cells bigger
                table.setRowHeight(35);

                JScrollPane scrollPane = new JScrollPane(table);

                // Set components to fill the available space vertically
                yearSemLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                scrollPane.setAlignmentX(Component.CENTER_ALIGNMENT);

                yearSemPanel.add(scrollPane, BorderLayout.CENTER);
                termPanel.add(yearSemPanel);

                // Add listener to capture changes in "Grade" cell
                table.getModel().addTableModelListener(e -> {
                    if (e.getType() == TableModelEvent.UPDATE) {
                        int row = e.getFirstRow();
                        int column = e.getColumn();
                        if (column == 4) {
                            float newGrade = Float.parseFloat(table.getValueAt(row, column).toString());
                            newGrade = program.validateFloat(newGrade);
                            Course updatedCourse = courses.get(row);
                            updatedCourse.setGrade(newGrade);
                            table.setValueAt(model,row,column);

                            // Call method in backend to update the course with the new grade
                            program.updateCourseGrade(updatedCourse);

                            updatedCourse.recheckAllValues();
                            // Add editor for the "Grade" column
                            table.getColumnModel().getColumn(4).setCellEditor(new DefaultCellEditor(new JTextField()));

                            table.getModel().setValueAt(updatedCourse.hasPassed(), row, 4);
                            // Update the "Weighted Grade" column
                            table.getModel().setValueAt(updatedCourse.getWeightedGrade(), row, 5);

                        }
                    }
                });
            }
        }
        mainPanel.remove(scrollPane); // Remove the previous scroll pane
        scrollPane = new JScrollPane(termPanel); // Add the new termPanel to scroll pane
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.revalidate(); // Refresh the layout
        mainPanel.repaint(); // Repaint the panel
    }

    private void openAddCourseFrame() {
        JFrame addCourseFrame = new JFrame("Add Course");
        addCourseFrame.setSize(400, 490);
        addCourseFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        addCourseFrame.setResizable(false);

        // Create components for adding course details (e.g., labels, text fields, buttons)
        JLabel courseNoLabel = new JLabel("Course No:");
        JTextField courseNoField = createStandardTextField();
        JLabel nameLabel = new JLabel("Course Name:");
        JTextField nameField = createStandardTextField();
        JLabel unitsLabel = new JLabel("Units:");
        JTextField unitsField = createStandardTextField();
        JLabel gradeLabel = new JLabel("Grade:");
        JTextField gradeField = createStandardTextField();
        JLabel yearLabel = new JLabel("Year:");
        JTextField yearField = createStandardTextField();
        JLabel semLabel = new JLabel("Term:");
        JTextField semField = createStandardTextField();
        JButton addButton = new JButton("Add");

        // Set layout for the panel
        JPanel addCoursePanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 5, 5, 5); // Padding

        // Add components to the panel
        addCoursePanel.add(courseNoLabel, gbc);
        gbc.gridy++;
        addCoursePanel.add(courseNoField, gbc);
        gbc.gridy++;
        addCoursePanel.add(nameLabel, gbc);
        gbc.gridy++;
        addCoursePanel.add(nameField, gbc);
        gbc.gridy++;
        addCoursePanel.add(unitsLabel, gbc);
        gbc.gridy++;
        addCoursePanel.add(unitsField, gbc);
        gbc.gridy++;
        addCoursePanel.add(gradeLabel, gbc);
        gbc.gridy++;
        addCoursePanel.add(gradeField, gbc);
        gbc.gridy++;
        addCoursePanel.add(yearLabel, gbc);
        gbc.gridy++;
        addCoursePanel.add(yearField, gbc);
        gbc.gridy++;
        addCoursePanel.add(semLabel, gbc);
        gbc.gridy++;
        addCoursePanel.add(semField, gbc);
        gbc.gridy++;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        addCoursePanel.add(addButton, gbc);


        // Add action listener for the add button (if needed)
        addButton.addActionListener(e -> {
            int grade = program.forceInt(gradeField.getText());
            int year = program.forceInt(yearField.getText());
            int unit = program.forceInt(unitsField.getText());
            int sem = program.forceInt(semField.getText());
            String name = nameField.getText();
            String courseNo = courseNoField.getText();
            program.addCourses(courseNo, name, year, sem, unit, grade);
            recreateTable();
            addCourseFrame.dispose();
        });


        addCourseFrame.add(addCoursePanel);
        addCourseFrame.setVisible(true);

    }

    private JTextField createStandardTextField() {
        JTextField textField = new JTextField(20);
        textField.setPreferredSize(new Dimension(250, 30)); // Set the preferred size
        return textField;
    }
    private void recreateTable() {
        mainPanel.remove(scrollPane); // Remove the previous scroll pane

        // Recreate the table
        JScrollPane newScrollPane = createScrollPane();

        mainPanel.add(newScrollPane, BorderLayout.CENTER); // Add the new scroll pane
        mainPanel.revalidate(); // Refresh the layout
        mainPanel.repaint(); // Repaint the panel
    }

    private JScrollPane createScrollPane() {
        JPanel termPanel = new JPanel();
        termPanel.setLayout(new BoxLayout(termPanel, BoxLayout.Y_AXIS));

        // Populate termPanel with data
        for (int i = 1; i <= 4; i++) {
            for (int j = 1; j <= 3; j++) {
                if (i == 4 && j > 2) {
                    break;
                }
                JPanel yearSemPanel = new JPanel(new BorderLayout());
                JLabel yearSemLabel = new JLabel("Year " + i + " Semester " + j);
                yearSemPanel.add(yearSemLabel, BorderLayout.NORTH);
                yearSemLabel.setPreferredSize(new Dimension(20, 20));
                DefaultTableModel model = new DefaultTableModel(new Object[]{"Course No", "Course", "Sem", "Units", "Grade", "Weighted Grade", "Passed"}, 0);
                ArrayList<Course> courses = program.getFilteredYearSem(i, j);
                for (Course course : courses) {
                    model.addRow(new Object[]{
                            course.getCourseNo(),
                            course.getName(),
                            course.getSem(),
                            course.getUnits(),
                            course.getGrade(),
                            course.getWeightedGrade(),
                            course.hasPassed(),
                    });
                }
                JTable table = new JTable(model);
                table.setDefaultEditor(Object.class, null);
                table.getColumnModel().getColumn(6).setCellRenderer(new RadioButtonRenderer());
                table.getColumnModel().getColumn(4).setCellEditor(new DefaultCellEditor(new JTextField()));
                table.setRowHeight(35);
                JScrollPane scrollPane = new JScrollPane(table);
                yearSemPanel.add(scrollPane, BorderLayout.CENTER);
                termPanel.add(yearSemPanel);
            }
        }

        JScrollPane newScrollPane = new JScrollPane(termPanel);
        return newScrollPane;
    }

//    private void addCourse() {
//        String name = this.nameField.getText();
//        float units = Float.parseFloat(this.unitsField.getText());
//        float grade = Float.parseFloat(this.gradeField.getText());
//        int year = Integer.parseInt(this.gradeField.getText());
//        int sem = Integer.parseInt(this.gradeField.getText());
//        this.program.addCourses(name, year, sem, units, grade);
//        this.updateCoursesTable();
//        this.nameField.setText("");
//        this.unitsField.setText("");
//        this.gradeField.setText("");
//    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new TrialGui("Information Technology");
        });
    }
}
