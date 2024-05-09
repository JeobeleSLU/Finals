
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class LoginPage extends JFrame {
    // Variables
    private JTextField idField;
    private JPasswordField passwordField;
    protected JPanel loginPanel;
    private JButton loginButton;
    private JLabel messageLabel;
    private HashMap<String, String> account;

    // Constructor
    public LoginPage() {
        // Initialize account HashMap
        account = new HashMap<>();
        account.put("2241276", "poong");
        account.put("2241198", "ligaya");

        // Initialize fields
        idField = new JTextField(15);
        passwordField = new JPasswordField(15);
        loginButton = new JButton("Login");
        messageLabel = new JLabel();
        messageLabel.setForeground(Color.RED);
        messageLabel.setVisible(false);

        //Initialize loginPanel with a BorderLayout manager
        loginPanel = new JPanel(new BorderLayout());

        //Printing and Positioning of Welcome message
        JLabel welcomeLabel = new JLabel("Welcome to My Checklist Manager!");
        welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        loginPanel.add(welcomeLabel, BorderLayout.NORTH);

        //Initializes fieldsPanel
        JPanel fieldsPanel = new JPanel(new GridLayout(2, 1));

        //Creating and Positioning of usernamePanel
        JPanel usernamePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        //Setting of the usernameLabel as "ID Number"
        JLabel usernameLabel = new JLabel("ID Number:");
        usernamePanel.add(usernameLabel);
        usernamePanel.add(idField);

        //Creating and Positioning of passwordPanel
        JPanel passwordPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        //Setting of the passswordLabel as "Password"
        JLabel passwordLabel = new JLabel("Password:");
        passwordPanel.add(passwordLabel);
        passwordPanel.add(passwordField);

        //Adding the usernamePanel and passwordPanel to the fieldsPanel
        fieldsPanel.add(usernamePanel);
        fieldsPanel.add(passwordPanel);

        //Adding the fieldsPanel to the loginPanel and centering
        loginPanel.add(fieldsPanel, BorderLayout.CENTER);

        //Action to be done when the login button is pressed
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = idField.getText();
                String password = new String(passwordField.getPassword());
                //checker if the account exists
                if (account.containsKey(id) && password.equals(account.get(id))) {
                    dispose(); // Dispose the login frame
                    new WelcomePage(); // Open welcome page
                } else {
                    if (!account.containsKey(id)) {
                        messageLabel.setText("ID number Not Found");
                    } else {
                        messageLabel.setText("Invalid Password");
                    }
                    messageLabel.setVisible(true);
                }
            }
        });

        //
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(loginButton);
        loginPanel.add(buttonPanel, BorderLayout.SOUTH);

        //Initialization for the message
        JPanel messagePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        messagePanel.add(messageLabel);

        //Positioning
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.add(buttonPanel, BorderLayout.CENTER);
        bottomPanel.add(messagePanel, BorderLayout.SOUTH);

        loginPanel.add(bottomPanel, BorderLayout.SOUTH);

        setContentPane(loginPanel);
        setTitle("My Checklist Manager");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(280, 180);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true); // Make the JFrame visible
    }
}
