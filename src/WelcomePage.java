
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.table.*;

public class WelcomePage extends JFrame {
    private JPanel wholePanel;
    private JButton choice1;
    private JButton choice4;
    private JButton choice3;
    private JButton choice2;
    private JButton choice5;
    private JButton choice8;
    private JButton choice6;
    private JButton choice7;
    private JButton choice9;
    private JPanel introPanel;
    private JPanel infoPanel;
    private JLabel introMessage;
    private JLabel nameTitle;
    private JLabel programTitle;
    private JLabel yearTitle;
    private JLabel termTitle;
    private JLabel nameAns;
    private JLabel programAns;
    private JLabel yearAns;
    private JLabel termAns;
    private JPanel buttonPanel;
    private JPanel upperButtonsPanel;
    private JPanel topRowPanel;
    private JPanel bottomRowPanel;
    private JPanel lowerButtonPanel;
    private JTable infoTable;
    private DefaultTableModel tableModel;

    public WelcomePage () {


        wholePanel = new JPanel(new BorderLayout());
        introPanel = new JPanel();
        infoPanel = new JPanel(new BorderLayout());
        infoPanel.setLayout(new BorderLayout());
        buttonPanel = new JPanel(new BorderLayout());
        upperButtonsPanel = new JPanel(new BorderLayout());
        lowerButtonPanel = new JPanel();
        topRowPanel = new JPanel();
        bottomRowPanel = new JPanel();
        lowerButtonPanel = new JPanel();

        infoPanel.setPreferredSize(new Dimension(200, infoPanel.getHeight()));

        setContentPane(wholePanel);
        setTitle("My Checklist Manager");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(700, 500);
        setLocationRelativeTo(null);
        setResizable(false); // Make the JFrame not resizable
        setVisible(true);

        choice1 = new JButton("<html><center>View subjects<br>for each school<br>term</center></html>");
        choice2 = new JButton("<html><center>View subjects<br>with grades for<br>each term</center></html>");
        choice3 = new JButton("<html><center>Enter grades for<br>subjects recently<br>finished</center></html>");
        choice4 = new JButton("<html><center>Edit a course taken</center></html>");
        choice5 = new JButton("<html><center>Add other course<br>taken</center></html>");
        choice6 = new JButton("<html><center>View courses with<br>Grade Point Average</center></html>");
        choice7 = new JButton("<html><center>View courses in<br>alphabetical order</center></html>");
        choice8 = new JButton("<html><center>View courses with<br>grades in Highest<br>to Lowest</center></html>");
        choice9 = new JButton("Log out");

        wholePanel.add(introPanel, BorderLayout.NORTH);
        wholePanel.add(infoPanel, BorderLayout.CENTER);
        wholePanel.add(buttonPanel, BorderLayout.SOUTH);

        buttonPanel.add(upperButtonsPanel, BorderLayout.NORTH);
        buttonPanel.add(lowerButtonPanel, BorderLayout.SOUTH);

        upperButtonsPanel.add(topRowPanel, BorderLayout.NORTH);
        upperButtonsPanel.add(bottomRowPanel, BorderLayout.SOUTH);

        introMessage = new JLabel("Welcome to My Checklist Manager!");
        introMessage.setHorizontalAlignment(SwingConstants.CENTER);
        introMessage.setVerticalAlignment(SwingConstants.CENTER); // di kita
        introPanel.add(introMessage);

        tableModel = new DefaultTableModel(
                new Object[][]{
                        {"Name", "Juan Dela Cruz"},
                        {"Program", "BSIT"},
                        {"Year", "1"},
                        {"Term", "1"}
                },
                new String[]{"Category", "Value"}
        );



        // Creating JTable with the created model
        infoTable = new JTable(tableModel);
        infoTable.setEnabled(false); // Disabling editing

        // Setting up table appearance
        infoTable.setRowHeight(30);
        infoTable.setFont(new Font("Arial", Font.PLAIN, 14));

        // Adding table to scroll pane to handle large content
        JScrollPane scrollPane = new JScrollPane(infoTable);

        infoTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        TableColumnModel columnModel = infoTable.getColumnModel();

        columnModel.getColumn(0).setPreferredWidth(340);
        columnModel.getColumn(1).setPreferredWidth(340);

        // Adding scroll pane to the whole panel
        wholePanel.add(scrollPane, BorderLayout.CENTER);

        upperButtonsPanel.setLayout(new GridLayout(2, 1)); // 2 rows, 1 column
        lowerButtonPanel.setLayout(new FlowLayout(FlowLayout.CENTER)); // Center-aligned flow layout
        upperButtonsPanel.setPreferredSize(new Dimension(wholePanel.getWidth(), 200));
        introPanel.setPreferredSize(new Dimension(wholePanel.getWidth(), 50));

        // Adding buttons to upperButtonsPanel
        upperButtonsPanel.add(topRowPanel);
        upperButtonsPanel.add(bottomRowPanel);

        Dimension buttonSize = new Dimension(150, 53);
        choice1.setPreferredSize(buttonSize);
        choice2.setPreferredSize(buttonSize);
        choice3.setPreferredSize(buttonSize);
        choice4.setPreferredSize(buttonSize);
        choice5.setPreferredSize(buttonSize);
        choice6.setPreferredSize(buttonSize);
        choice7.setPreferredSize(buttonSize);
        choice8.setPreferredSize(buttonSize);

        // Adding buttons to lowerButtonPanel
        lowerButtonPanel.add(choice9);

        topRowPanel.add(choice1);
        topRowPanel.add(choice2);
        topRowPanel.add(choice3);
        topRowPanel.add(choice4);
        bottomRowPanel.add(choice5);
        bottomRowPanel.add(choice6);
        bottomRowPanel.add(choice7);
        bottomRowPanel.add(choice8);

        lowerButtonPanel.add(choice9);

        choice1.addActionListener(new ActionListener() {
            @ Override
            public void actionPerformed(ActionEvent e) {
                // Instantiate Choice1 class
                Choice1 choice1Frame = new Choice1();
                choice1Frame.setVisible(true);
            }
        });

        choice2.addActionListener(new ActionListener() {
            @ Override
            public void actionPerformed(ActionEvent e) {
                // Instantiate Choice2 class
                Choice2 choice2Frame = new Choice2();
                choice2Frame.setVisible(true);
            }
        });

        choice3.addActionListener(new ActionListener() {
            @ Override
            public void actionPerformed(ActionEvent e) {
                // Instantiate Choice2 class
                Choice3 choice3Frame = new Choice3();
                choice3Frame.setVisible(true);
            }
        });

        choice4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //Create an instance of Choice4
                Choice4 choice4Panel = new Choice4();
                //Set the visibility of Choice4 panel to true
                choice4Panel.setVisible(true);
            }
        });

        choice5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Create an instance of Choice5
                Choice5 choice5Panel = new Choice5();
                // Set the visibility of Choice5 panel to true
                choice5Panel.setVisible(true);
            }
        });

        choice6.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Create an instance of Choice6
                Choice6 choice6Panel = new Choice6();
                // Set the visibility of Choice6
                choice6Panel.setVisible(true);
            }
        });

        choice7.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Create an instance of Choice6
                Choice7 choice7Panel = new Choice7();
                // Set the visibility of Choice6
                choice7Panel.setVisible(true);
            }
        });


        choice8.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Create an instance of Choice6
                Choice8 choice6Panel = new Choice8();
                // Set the visibility of Choice6
                choice6Panel.setVisible(true);
            }
        });

        choice9.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Dispose of the current WelcomePage frame
                dispose();
                LoginPage login = new LoginPage();
                // Set the visibility of Choice9/login
                login.setVisible(true);
            }
        });





    }

    public static void main(String[] args) {
        new WelcomePage();
    }

}