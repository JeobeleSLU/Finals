
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class Choice5 extends JFrame {
    private JPanel mainPanel;
    private JLabel titleLabel;
    private JLabel headerLabel;
    private JTable courseTable;
    private CustomTableModel tableModel;
    private JButton addRowButton;
    private JButton addCourseButton;
    private boolean tableEditable = true;
    private JButton backToMainMenuButton;
    private JLabel messageLabel;
    private JButton removeLastRowButton;
    private int currentYear = 1;
    private int currentSemester = 1;


//todo kulang ng course id at grade sa printing tas update ng info kapag nag add yung user

    public Choice5() {
        setTitle("Add a Course");
        setSize(700, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        titleLabel = new JLabel("Add a Course");
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        messageLabel = new JLabel();
        messageLabel.setHorizontalAlignment(JLabel.CENTER);

        String[] columnNames = {"Course ID", "Course Name", "Units", "Grades"};
        tableModel = new CustomTableModel(columnNames, 0);
        courseTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(courseTable);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        addRowButton = new JButton("Add Row");

        addRowButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                tableModel.addRow(new Object[]{"", "", "", ""});
                tableEditable = true;
                addCourseButton.setEnabled(true);
                messageLabel.setText("Row added! You can now add a course.");
                messageLabel.repaint();
                updateRemoveLastRowButtonState();
            }
        });

        addCourseButton = new JButton("Add Course");
        addCourseButton.setEnabled(false);
        addCourseButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                courseTable.clearSelection();
                tableEditable = false;
                messageLabel.setText("Course added!");
                messageLabel.repaint();
                addCourseButton.setEnabled(false);
            }
        });

        removeLastRowButton = new JButton("Remove last row");
        removeLastRowButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int rowCount = tableModel.getRowCount();
                if (rowCount > 0) {
                    tableModel.removeRow(rowCount - 1);
                    messageLabel.setText("Course removed!");
                    messageLabel.repaint();
                }
                updateRemoveLastRowButtonState();
            }
        });

        backToMainMenuButton = new JButton("Back to Main Menu");
        backToMainMenuButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                WelcomePage welcome = new WelcomePage();
                welcome.setVisible(true);
            }
        });

        JPanel buttonPanel = new JPanel(new GridLayout(3, 1));
        JPanel yearTermPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        yearTermPanel.add(new JLabel("Year:"));
        JTextField yearField = new JTextField(10);
        ((AbstractDocument) yearField.getDocument()).setDocumentFilter(new IntegerFilter());
        yearTermPanel.add(yearField);
        yearTermPanel.add(new JLabel("Term:"));
        JTextField termField = new JTextField(10);
        ((AbstractDocument) termField.getDocument()).setDocumentFilter(new IntegerFilter());
        yearTermPanel.add(termField);

        JButton enterButton = new JButton("Enter");
        enterButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                currentYear = Integer.parseInt(yearField.getText());
                currentSemester = Integer.parseInt(termField.getText());
                listOfCourses(); // Call the method to update the table
            }
        });
        yearTermPanel.add(enterButton);
        buttonPanel.add(yearTermPanel);
        JPanel messagePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        messagePanel.add(messageLabel);
        buttonPanel.add(messagePanel);
        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonsPanel.add(addRowButton);
        buttonsPanel.add(addCourseButton);
        buttonsPanel.add(removeLastRowButton);
        buttonsPanel.add(backToMainMenuButton);
        buttonPanel.add(buttonsPanel);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);
        setVisible(true);
    }

    class CustomTableModel extends DefaultTableModel {
        public CustomTableModel(Object[] columnNames, int rowCount) {
            super(columnNames, rowCount);
        }

        @Override
        public boolean isCellEditable(int row, int column) {
            return tableEditable && row == getRowCount() - 1;
        }
    }

    private void updateRemoveLastRowButtonState() {
        removeLastRowButton.setEnabled(tableModel.getRowCount() != 0);
    }

    class IntegerFilter extends DocumentFilter {
        @Override
        public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
            if (string.matches("\\d+")) {
                super.insertString(fb, offset, string, attr);
            }
        }

        @Override
        public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
            if (text.matches("\\d+")) {
                super.replace(fb, offset, length, text, attrs);
            }
        }
    }

    public void listOfCourses() {
        try {
            Program program = new Program("My Program");
            ArrayList<Course> filteredCourses = program.getFilteredYearSem(currentYear, currentSemester);
            List<Object[]> data = new ArrayList<>();
            List<String> courseIds = new ArrayList<>();

            for (Course course : filteredCourses) {
                Object[] rowData = {course.getCourseNo(), course.getName(), course.getUnits(), course.getGrade()};
                data.add(rowData);
                courseIds.add(course.getCourseNo());
            }

            String[] columnNames = {"Course Id", "Course", "Units", "Grades"};
            String[] courseIdArray = courseIds.toArray(new String[0]);
            for (String courseId : courseIdArray) {
                System.out.println(courseId);
            }

            Object[][] courses = new Object[data.size()][];
            for (int x = 0; x < data.size(); x++) {
                courses[x] = data.get(x);
            }

            headerLabel = new JLabel();

            headerLabel.setText("Year: " + currentYear + " - Semester: " + currentSemester);

            tableModel.setDataVector(courses, columnNames);
            courseIdArray = courseIds.toArray(new String[0]);
            for (String courseId : courseIdArray) {
                System.out.println(courseId);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Choice5();
            }
        });
    }
}
