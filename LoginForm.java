import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Task 4: Login Form
 * Username JTextField, Password JPasswordField, Login JButton.
 * Displays "Login Successful" or "Invalid Username or Password".
 */
public class LoginForm extends JFrame {

    // Hard-coded valid credentials
    private static final String VALID_USERNAME = "admin";
    private static final String VALID_PASSWORD = "1234";

    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton clearButton;
    private JLabel messageLabel;

    public LoginForm() {
        setTitle("Login Form");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(420, 300);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Title panel
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(new Color(50, 90, 160));
        titlePanel.setBorder(BorderFactory.createEmptyBorder(8, 0, 8, 0));
        JLabel titleLabel = new JLabel("  User Login  ");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setForeground(Color.WHITE);
        titlePanel.add(titleLabel);

        // Form panel
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 10, 30));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 8, 10, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        Font labelFont = new Font("Arial", Font.BOLD, 13);
        Font fieldFont = new Font("Arial", Font.PLAIN, 14);

        // Username row
        gbc.gridx = 0; gbc.gridy = 0; gbc.weightx = 0;
        JLabel userLabel = new JLabel("Username:");
        userLabel.setFont(labelFont);
        formPanel.add(userLabel, gbc);

        gbc.gridx = 1; gbc.weightx = 1;
        usernameField = new JTextField(18);
        usernameField.setFont(fieldFont);
        formPanel.add(usernameField, gbc);

        // Password row
        gbc.gridx = 0; gbc.gridy = 1; gbc.weightx = 0;
        JLabel passLabel = new JLabel("Password:");
        passLabel.setFont(labelFont);
        formPanel.add(passLabel, gbc);

        gbc.gridx = 1; gbc.weightx = 1;
        passwordField = new JPasswordField(18);
        passwordField.setFont(fieldFont);
        formPanel.add(passwordField, gbc);

        // Hint label
        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 2;
        JLabel hintLabel = new JLabel("Hint: username=admin, password=1234", SwingConstants.CENTER);
        hintLabel.setFont(new Font("Arial", Font.ITALIC, 10));
        hintLabel.setForeground(Color.GRAY);
        formPanel.add(hintLabel, gbc);

        // Button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 5));

        loginButton = new JButton("Login");
        loginButton.setFont(new Font("Arial", Font.BOLD, 13));
        loginButton.setBackground(new Color(50, 90, 160));
        loginButton.setForeground(Color.WHITE);
        loginButton.setFocusPainted(false);
        loginButton.setPreferredSize(new Dimension(110, 35));

        clearButton = new JButton("Clear");
        clearButton.setFont(new Font("Arial", Font.BOLD, 13));
        clearButton.setBackground(new Color(160, 60, 50));
        clearButton.setForeground(Color.WHITE);
        clearButton.setFocusPainted(false);
        clearButton.setPreferredSize(new Dimension(110, 35));

        buttonPanel.add(loginButton);
        buttonPanel.add(clearButton);

        // Message label
        messageLabel = new JLabel(" ", SwingConstants.CENTER);
        messageLabel.setFont(new Font("Arial", Font.BOLD, 14));
        messageLabel.setBorder(BorderFactory.createEmptyBorder(5, 10, 10, 10));

        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.add(buttonPanel, BorderLayout.NORTH);
        bottomPanel.add(messageLabel, BorderLayout.SOUTH);

        add(titlePanel, BorderLayout.NORTH);
        add(formPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        // Login action
        ActionListener loginAction = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText().trim();
                String password = new String(passwordField.getPassword());

                if (username.equals(VALID_USERNAME) && password.equals(VALID_PASSWORD)) {
                    messageLabel.setText("✔ Login Successful!");
                    messageLabel.setForeground(new Color(0, 120, 0));
                } else {
                    messageLabel.setText("✘ Invalid Username or Password");
                    messageLabel.setForeground(Color.RED);
                    passwordField.setText("");
                    passwordField.requestFocus();
                }
            }
        };

        loginButton.addActionListener(loginAction);
        // Allow Enter key in password field
        passwordField.addActionListener(loginAction);

        // Clear action
        clearButton.addActionListener(e -> {
            usernameField.setText("");
            passwordField.setText("");
            messageLabel.setText(" ");
            usernameField.requestFocus();
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginForm().setVisible(true));
    }
}
