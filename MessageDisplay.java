import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Task 1: Message Display
 * A Swing window with a text field and a button.
 * When the button is clicked, the entered text is displayed in a JLabel.
 */
public class MessageDisplay extends JFrame {

    private JTextField inputField;
    private JLabel displayLabel;
    private JButton showButton;

    public MessageDisplay() {
        setTitle("Message Display");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 200);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // Input panel (top)
        JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 10));

        inputField = new JTextField(20);
        inputField.setFont(new Font("Arial", Font.PLAIN, 14));

        showButton = new JButton("Show Message");
        showButton.setFont(new Font("Arial", Font.BOLD, 13));
        showButton.setBackground(new Color(70, 130, 180));
        showButton.setForeground(Color.WHITE);
        showButton.setFocusPainted(false);

        inputPanel.add(new JLabel("Enter Text:"));
        inputPanel.add(inputField);
        inputPanel.add(showButton);

        // Display panel (center)
        JPanel displayPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 20));
        displayPanel.setBorder(BorderFactory.createTitledBorder("Output"));

        displayLabel = new JLabel("Your message will appear here.");
        displayLabel.setFont(new Font("Arial", Font.ITALIC, 14));
        displayLabel.setForeground(Color.DARK_GRAY);

        displayPanel.add(displayLabel);

        add(inputPanel, BorderLayout.NORTH);
        add(displayPanel, BorderLayout.CENTER);

        // Button action
        showButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = inputField.getText().trim();
                if (text.isEmpty()) {
                    displayLabel.setText("(No text entered)");
                    displayLabel.setForeground(Color.RED);
                } else {
                    displayLabel.setText(text);
                    displayLabel.setForeground(new Color(0, 100, 0));
                }
            }
        });

        // Allow pressing Enter to trigger the button
        inputField.addActionListener(showButton.getActionListeners()[0]);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MessageDisplay().setVisible(true));
    }
}
