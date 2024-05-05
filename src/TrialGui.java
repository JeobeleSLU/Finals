import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class TrialGui extends JFrame {
    private Program program;
    private JTable coursesTable;
    private JTextField nameField;
    private JTextField unitsField;
    private JTextField gradeField;
    private JTextField idField;
    private JLabel firstYearFirstSem;


    public TrialGui(String programName) {
        program = new Program(programName);
        initializeComponents();
        createGUI();
        updateCoursesTable();
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                program.close();
            }
        });
    }

    private void initializeComponents() {
        // Initialize components
        coursesTable = new JTable();
        coursesTable.setFillsViewportHeight(true);
        firstYearFirstSem = new JLabel(" First Year First sem");
        nameField = new JTextField(20);
        unitsField = new JTextField(10);
        gradeField = new JTextField(10);
        idField = new JTextField(10);

    }

    private void createGUI() {
        setTitle("Course Management");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        firstYearFirstSem.setHorizontalTextPosition(JLabel.CENTER);
        JScrollPane scrollPane = new JScrollPane(coursesTable);
        coursesTable.setDefaultEditor(Object.class,null);
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        JPanel inputPanel = new JPanel(new GridLayout(6, 2)); // Adjust grid layout based on your preference

        inputPanel.add(new JLabel("Name:"));
        inputPanel.add(nameField);
        inputPanel.add(new JLabel("Units:"));
        inputPanel.add(unitsField);
        inputPanel.add(new JLabel("Grade:"));
        inputPanel.add(gradeField);
        inputPanel.add(new JLabel("ID:")); // Adding label for ID
        inputPanel.add(idField); // Adding text field for ID


        JButton addButton = new JButton("Add Course");
//        addButton.addActionListener(e -> addCourse());
        inputPanel.add(addButton);
        JButton resetButton = new JButton("reset");
        inputPanel.add(resetButton);
        resetButton.addActionListener(e -> {
            program.reset();
            updateCoursesTable();
        });
        mainPanel.add(inputPanel, BorderLayout.SOUTH);

        add(mainPanel);
        setVisible(true);
    }




    private void updateCoursesTable() {
        int i = 0;
        ArrayList <Course>  majorCourses= program.getFirstSemFirstYear();

        DefaultTableModel model = new DefaultTableModel(
                new Object[]{"Course","Sem", "Units", "Grade", "Finished"}, 0); // Corrected column order

        for (Course course : majorCourses) {
            course.setGrade(80+i);
            i++;
            model.addRow(new Object[]{course.getName(), course.getSem(), course.getUnits(),course.getGrade(), course.isFinished()});
        }

        coursesTable.setModel(model);
        coursesTable.setDefaultEditor(Object.class,null); // Make the JTable non-editable
        coursesTable.getColumnModel().getColumn(4).setCellRenderer(new RadioButtonRenderer());
    }




// private void addCourse() {
//        String name = nameField.getText();
//        float units = Float.parseFloat(unitsField.getText());
//        float grade = Float.parseFloat(gradeField.getText());
//        program.addMinorCourse(name, units, grade);
//        updateCoursesTable();
//        // Clear input fields after adding course
//        nameField.setText("");
//        unitsField.setText("");
//        gradeField.setText("");
//    }

//private void addMajorCourse() {
//        String name = nameField.getText();
//        float units = Float.parseFloat(unitsField.getText());
//        float grade = Float.parseFloat(gradeField.getText());
//        int id  = Integer.parseInt(idField.getText());
//        program.addMinorCourse(name, units, grade);
//        updateCoursesTable();
//        // Clear input fields after adding course
//        nameField.setText("");
//        unitsField.setText("");
//        gradeField.setText("");
//    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TrialGui("Information Technology"));
    }
}
