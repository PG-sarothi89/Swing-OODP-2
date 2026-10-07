import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Task 2: Addition of Two Numbers
 * Two JTextFields for entering numbers and an Add button.
 * Displays the sum when the button is clicked.
 */
public class AdditionOfTwoNumbers extends JFrame {

    private JTextField num1Field;
    private JTextField num2Field;
    private JLabel resultLabel;
    private JButton addButton;
    private JButton clearButton;

    public AdditionOfTwoNumbers() {
        setTitle("Addition of Two Numbers");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(420, 250);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // Input panel
        JPanel inputPanel = new JPanel(new GridBagLayout());
        inputPanel.setBorder(BorderFactory.createEmptyBorder(15, 20, 10, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        Font labelFont = new Font("Arial", Font.BOLD, 13);
        Font fieldFont = new Font("Arial", Font.PLAIN, 14);

        gbc.gridx = 0; gbc.gridy = 0;
        inputPanel.add(new JLabel("First Number:"), gbc);
        gbc.gridx = 1;
        num1Field = new JTextField(15);
        num1Field.setFont(fieldFont);
        inputPanel.add(num1Field, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        inputPanel.add(new JLabel("Second Number:"), gbc);
        gbc.gridx = 1;
        num2Field = new JTextField(15);
        num2Field.setFont(fieldFont);
        inputPanel.add(num2Field, gbc);

        // Button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 5));

        addButton = new JButton("Add");
        addButton.setFont(new Font("Arial", Font.BOLD, 13));
        addButton.setBackground(new Color(34, 139, 34));
        addButton.setForeground(Color.WHITE);
        addButton.setFocusPainted(false);
        addButton.setPreferredSize(new Dimension(100, 32));

        clearButton = new JButton("Clear");
        clearButton.setFont(new Font("Arial", Font.BOLD, 13));
        clearButton.setBackground(new Color(178, 34, 34));
        clearButton.setForeground(Color.WHITE);
        clearButton.setFocusPainted(false);
        clearButton.setPreferredSize(new Dimension(100, 32));

        buttonPanel.add(addButton);
        buttonPanel.add(clearButton);

        // Result panel
        JPanel resultPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        resultPanel.setBorder(BorderFactory.createTitledBorder("Result"));

        resultLabel = new JLabel("Sum = ?");
        resultLabel.setFont(new Font("Arial", Font.BOLD, 16));
        resultLabel.setForeground(new Color(0, 70, 140));
        resultPanel.add(resultLabel);

        add(inputPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
        add(resultPanel, BorderLayout.SOUTH);

        // Add button action
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    double n1 = Double.parseDouble(num1Field.getText().trim());
                    double n2 = Double.parseDouble(num2Field.getText().trim());
                    double sum = n1 + n2;
                    // Display as integer if no decimal part
                    if (sum == Math.floor(sum) && !Double.isInfinite(sum)) {
                        resultLabel.setText("Sum = " + (long) sum);
                    } else {
                        resultLabel.setText(String.format("Sum = %.4f", sum));
                    }
                    resultLabel.setForeground(new Color(0, 100, 0));
                } catch (NumberFormatException ex) {
                    resultLabel.setText("Please enter valid numbers!");
                    resultLabel.setForeground(Color.RED);
                }
            }
        });

        // Clear button action
        clearButton.addActionListener(e -> {
            num1Field.setText("");
            num2Field.setText("");
            resultLabel.setText("Sum = ?");
            resultLabel.setForeground(new Color(0, 70, 140));
            num1Field.requestFocus();
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new AdditionOfTwoNumbers().setVisible(true));
    }
}
