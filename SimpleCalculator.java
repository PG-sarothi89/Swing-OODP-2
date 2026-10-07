import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Task 3: Simple Calculator
 * Two input fields and four operation buttons: +, -, ×, /
 * Performs the selected operation and displays the result.
 */
public class SimpleCalculator extends JFrame {

    private JTextField num1Field;
    private JTextField num2Field;
    private JLabel resultLabel;
    private JLabel operationLabel;

    public SimpleCalculator() {
        setTitle("Simple Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(450, 300);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // Input panel
        JPanel inputPanel = new JPanel(new GridBagLayout());
        inputPanel.setBorder(BorderFactory.createEmptyBorder(15, 20, 5, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        Font fieldFont = new Font("Arial", Font.PLAIN, 14);
        Font labelFont = new Font("Arial", Font.BOLD, 13);

        gbc.gridx = 0; gbc.gridy = 0;
        JLabel lbl1 = new JLabel("Number 1:");
        lbl1.setFont(labelFont);
        inputPanel.add(lbl1, gbc);
        gbc.gridx = 1;
        num1Field = new JTextField(15);
        num1Field.setFont(fieldFont);
        inputPanel.add(num1Field, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        JLabel lbl2 = new JLabel("Number 2:");
        lbl2.setFont(labelFont);
        inputPanel.add(lbl2, gbc);
        gbc.gridx = 1;
        num2Field = new JTextField(15);
        num2Field.setFont(fieldFont);
        inputPanel.add(num2Field, gbc);

        // Button panel with operation buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

        String[] ops = {"+", "-", "×", "/"};
        Color[] colors = {
            new Color(34, 139, 34),
            new Color(70, 130, 180),
            new Color(184, 134, 11),
            new Color(178, 34, 34)
        };

        for (int i = 0; i < ops.length; i++) {
            final String op = ops[i];
            JButton btn = new JButton(op);
            btn.setFont(new Font("Arial", Font.BOLD, 18));
            btn.setBackground(colors[i]);
            btn.setForeground(Color.WHITE);
            btn.setFocusPainted(false);
            btn.setPreferredSize(new Dimension(70, 45));
            btn.addActionListener(e -> calculate(op));
            buttonPanel.add(btn);
        }

        // Clear button
        JButton clearBtn = new JButton("C");
        clearBtn.setFont(new Font("Arial", Font.BOLD, 16));
        clearBtn.setBackground(Color.DARK_GRAY);
        clearBtn.setForeground(Color.WHITE);
        clearBtn.setFocusPainted(false);
        clearBtn.setPreferredSize(new Dimension(70, 45));
        clearBtn.addActionListener(e -> {
            num1Field.setText("");
            num2Field.setText("");
            resultLabel.setText("Result = ?");
            operationLabel.setText("Select an operation");
            resultLabel.setForeground(new Color(0, 70, 140));
        });
        buttonPanel.add(clearBtn);

        // Result panel
        JPanel resultPanel = new JPanel(new GridLayout(2, 1));
        resultPanel.setBorder(BorderFactory.createTitledBorder("Result"));

        operationLabel = new JLabel("Select an operation above", SwingConstants.CENTER);
        operationLabel.setFont(new Font("Arial", Font.ITALIC, 12));
        operationLabel.setForeground(Color.GRAY);

        resultLabel = new JLabel("Result = ?", SwingConstants.CENTER);
        resultLabel.setFont(new Font("Arial", Font.BOLD, 16));
        resultLabel.setForeground(new Color(0, 70, 140));

        resultPanel.add(operationLabel);
        resultPanel.add(resultLabel);

        add(inputPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
        add(resultPanel, BorderLayout.SOUTH);
    }

    private void calculate(String operator) {
        try {
            double n1 = Double.parseDouble(num1Field.getText().trim());
            double n2 = Double.parseDouble(num2Field.getText().trim());
            double result;
            String opSymbol = operator;

            switch (operator) {
                case "+": result = n1 + n2; break;
                case "-": result = n1 - n2; break;
                case "×": result = n1 * n2; break;
                case "/":
                    if (n2 == 0) {
                        operationLabel.setText(n1 + " ÷ " + n2);
                        resultLabel.setText("Cannot divide by zero!");
                        resultLabel.setForeground(Color.RED);
                        return;
                    }
                    result = n1 / n2;
                    opSymbol = "÷";
                    break;
                default: return;
            }

            operationLabel.setText(formatNum(n1) + " " + opSymbol + " " + formatNum(n2));
            if (result == Math.floor(result) && !Double.isInfinite(result)) {
                resultLabel.setText("Result = " + (long) result);
            } else {
                resultLabel.setText(String.format("Result = %.4f", result));
            }
            resultLabel.setForeground(new Color(0, 100, 0));

        } catch (NumberFormatException ex) {
            operationLabel.setText("Error");
            resultLabel.setText("Please enter valid numbers!");
            resultLabel.setForeground(Color.RED);
        }
    }

    private String formatNum(double n) {
        if (n == Math.floor(n) && !Double.isInfinite(n)) return String.valueOf((long) n);
        return String.valueOf(n);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new SimpleCalculator().setVisible(true));
    }
}
