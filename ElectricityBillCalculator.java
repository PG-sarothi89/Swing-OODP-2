import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Task 6: Electricity Bill Calculator
 * Takes the number of units consumed and calculates the electricity bill
 * according to different unit ranges. Displays the total bill when Calculate is clicked.
 *
 * Tariff Rates:
 *   0  -  100 units : Rs. 5.00 per unit
 *   101 - 300 units : Rs. 7.50 per unit  (first 100 at Rs.5, rest at Rs.7.50)
 *   301 - 500 units : Rs. 10.00 per unit (first 100 at Rs.5, next 200 at Rs.7.50, rest at Rs.10)
 *   Above 500 units : Rs. 15.00 per unit (first 100 at Rs.5, next 200 at Rs.7.50,
 *                                          next 200 at Rs.10, rest at Rs.15)
 *   Fixed Service Charge : Rs. 50.00
 */
public class ElectricityBillCalculator extends JFrame {

    private JTextField unitsField;
    private JTextArea breakdownArea;
    private JLabel totalLabel;
    private JButton calculateButton;
    private JButton clearButton;

    // Tariff constants
    private static final double SLAB1_LIMIT  = 100;
    private static final double SLAB2_LIMIT  = 300;
    private static final double SLAB3_LIMIT  = 500;
    private static final double RATE1        = 5.00;
    private static final double RATE2        = 7.50;
    private static final double RATE3        = 10.00;
    private static final double RATE4        = 15.00;
    private static final double SERVICE_CHARGE = 50.00;

    public ElectricityBillCalculator() {
        setTitle("Electricity Bill Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(480, 480);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(8, 8));

        // Title
        JLabel titleLabel = new JLabel("⚡ Electricity Bill Calculator", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setOpaque(true);
        titleLabel.setBackground(new Color(30, 100, 180));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(12, 0, 12, 0));

        // Input panel
        JPanel inputPanel = new JPanel(new GridBagLayout());
        inputPanel.setBorder(BorderFactory.createEmptyBorder(15, 20, 5, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        Font labelFont = new Font("Arial", Font.BOLD, 13);
        Font fieldFont = new Font("Arial", Font.PLAIN, 14);

        gbc.gridx = 0; gbc.gridy = 0; gbc.weightx = 0;
        JLabel unitsLabel = new JLabel("Units Consumed:");
        unitsLabel.setFont(labelFont);
        inputPanel.add(unitsLabel, gbc);

        gbc.gridx = 1; gbc.weightx = 1;
        unitsField = new JTextField(15);
        unitsField.setFont(fieldFont);
        inputPanel.add(unitsField, gbc);

        // Tariff table info
        JTextArea tariffInfo = new JTextArea(
            "Tariff Rates:\n" +
            "  0   -  100 units  @ Rs.  5.00/unit\n" +
            "  101 -  300 units  @ Rs.  7.50/unit\n" +
            "  301 -  500 units  @ Rs. 10.00/unit\n" +
            "  Above 500 units   @ Rs. 15.00/unit\n" +
            "  Service Charge    = Rs. 50.00"
        );
        tariffInfo.setFont(new Font("Monospaced", Font.PLAIN, 11));
        tariffInfo.setEditable(false);
        tariffInfo.setBackground(new Color(230, 240, 255));
        tariffInfo.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createTitledBorder("Rate Chart"),
            BorderFactory.createEmptyBorder(5, 8, 5, 8)
        ));

        gbc.gridx = 0; gbc.gridy = 1; gbc.gridwidth = 2; gbc.weightx = 1;
        inputPanel.add(tariffInfo, gbc);

        // Button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 8));

        calculateButton = new JButton("Calculate Bill");
        calculateButton.setFont(new Font("Arial", Font.BOLD, 13));
        calculateButton.setBackground(new Color(30, 100, 180));
        calculateButton.setForeground(Color.WHITE);
        calculateButton.setFocusPainted(false);
        calculateButton.setPreferredSize(new Dimension(150, 35));

        clearButton = new JButton("Clear");
        clearButton.setFont(new Font("Arial", Font.BOLD, 13));
        clearButton.setBackground(new Color(160, 60, 50));
        clearButton.setForeground(Color.WHITE);
        clearButton.setFocusPainted(false);
        clearButton.setPreferredSize(new Dimension(100, 35));

        buttonPanel.add(calculateButton);
        buttonPanel.add(clearButton);

        // Breakdown output area
        breakdownArea = new JTextArea(7, 40);
        breakdownArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        breakdownArea.setEditable(false);
        breakdownArea.setBackground(new Color(245, 245, 245));
        breakdownArea.setBorder(BorderFactory.createEmptyBorder(6, 8, 6, 8));

        JScrollPane scrollPane = new JScrollPane(breakdownArea);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Bill Breakdown"));

        // Total label
        totalLabel = new JLabel("Total Bill: Rs. 0.00", SwingConstants.CENTER);
        totalLabel.setFont(new Font("Arial", Font.BOLD, 16));
        totalLabel.setForeground(new Color(30, 100, 30));
        totalLabel.setOpaque(true);
        totalLabel.setBackground(new Color(220, 245, 220));
        totalLabel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(2, 0, 0, 0, Color.LIGHT_GRAY),
            BorderFactory.createEmptyBorder(10, 0, 10, 0)
        ));

        JPanel bottomPanel = new JPanel(new BorderLayout(5, 5));
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 5, 10));
        bottomPanel.add(scrollPane, BorderLayout.CENTER);
        bottomPanel.add(totalLabel, BorderLayout.SOUTH);

        add(titleLabel, BorderLayout.NORTH);
        add(inputPanel, BorderLayout.NORTH);  // overrides, so use CENTER below

        JPanel mainCenter = new JPanel(new BorderLayout(5, 5));
        mainCenter.add(inputPanel, BorderLayout.NORTH);
        mainCenter.add(buttonPanel, BorderLayout.CENTER);
        mainCenter.add(bottomPanel, BorderLayout.SOUTH);

        add(titleLabel, BorderLayout.NORTH);
        add(mainCenter, BorderLayout.CENTER);

        // Calculate action
        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    double units = Double.parseDouble(unitsField.getText().trim());
                    if (units < 0) {
                        JOptionPane.showMessageDialog(ElectricityBillCalculator.this,
                            "Units cannot be negative.", "Input Error",
                            JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    calculateBill(units);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(ElectricityBillCalculator.this,
                        "Please enter a valid number of units.", "Input Error",
                        JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Allow Enter key
        unitsField.addActionListener(calculateButton.getActionListeners()[0]);

        // Clear action
        clearButton.addActionListener(e -> {
            unitsField.setText("");
            breakdownArea.setText("");
            totalLabel.setText("Total Bill: Rs. 0.00");
            totalLabel.setForeground(new Color(30, 100, 30));
            unitsField.requestFocus();
        });
    }

    private void calculateBill(double units) {
        double slab1Cost = 0, slab2Cost = 0, slab3Cost = 0, slab4Cost = 0;
        double slab1Units = 0, slab2Units = 0, slab3Units = 0, slab4Units = 0;

        if (units <= SLAB1_LIMIT) {
            slab1Units = units;
        } else if (units <= SLAB2_LIMIT) {
            slab1Units = SLAB1_LIMIT;
            slab2Units = units - SLAB1_LIMIT;
        } else if (units <= SLAB3_LIMIT) {
            slab1Units = SLAB1_LIMIT;
            slab2Units = SLAB2_LIMIT - SLAB1_LIMIT;
            slab3Units = units - SLAB2_LIMIT;
        } else {
            slab1Units = SLAB1_LIMIT;
            slab2Units = SLAB2_LIMIT - SLAB1_LIMIT;
            slab3Units = SLAB3_LIMIT - SLAB2_LIMIT;
            slab4Units = units - SLAB3_LIMIT;
        }

        slab1Cost = slab1Units * RATE1;
        slab2Cost = slab2Units * RATE2;
        slab3Cost = slab3Units * RATE3;
        slab4Cost = slab4Units * RATE4;

        double energyCost = slab1Cost + slab2Cost + slab3Cost + slab4Cost;
        double totalBill  = energyCost + SERVICE_CHARGE;

        StringBuilder sb = new StringBuilder();
        sb.append(String.format("  Units Consumed     : %.0f units%n", units));
        sb.append("  ─────────────────────────────────────────%n".formatted());
        if (slab1Units > 0)
            sb.append(String.format("  Slab 1 (0-100)     : %.0f × Rs.%.2f = Rs.%.2f%n",
                    slab1Units, RATE1, slab1Cost));
        if (slab2Units > 0)
            sb.append(String.format("  Slab 2 (101-300)   : %.0f × Rs.%.2f = Rs.%.2f%n",
                    slab2Units, RATE2, slab2Cost));
        if (slab3Units > 0)
            sb.append(String.format("  Slab 3 (301-500)   : %.0f × Rs.%.2f = Rs.%.2f%n",
                    slab3Units, RATE3, slab3Cost));
        if (slab4Units > 0)
            sb.append(String.format("  Slab 4 (>500)      : %.0f × Rs.%.2f = Rs.%.2f%n",
                    slab4Units, RATE4, slab4Cost));
        sb.append("  ─────────────────────────────────────────%n".formatted());
        sb.append(String.format("  Energy Charges     : Rs.%.2f%n", energyCost));
        sb.append(String.format("  Service Charge     : Rs.%.2f%n", SERVICE_CHARGE));
        sb.append("  ─────────────────────────────────────────%n".formatted());
        sb.append(String.format("  TOTAL BILL         : Rs.%.2f", totalBill));

        breakdownArea.setText(sb.toString());
        totalLabel.setText(String.format("Total Bill: Rs. %.2f", totalBill));
        totalLabel.setForeground(new Color(30, 100, 30));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ElectricityBillCalculator().setVisible(true));
    }
}
