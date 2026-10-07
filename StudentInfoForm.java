import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Task 5: Student Information Form
 * Fields: Name, ID, Department, Gender (JRadioButton), Semester (JComboBox)
 * Displays all information in a JTextArea when Submit is clicked.
 */
public class StudentInfoForm extends JFrame {

    private JTextField nameField;
    private JTextField idField;
    private JTextField deptField;
    private JRadioButton maleRadio;
    private JRadioButton femaleRadio;
    private JRadioButton otherRadio;
    private JComboBox<String> semesterCombo;
    private JTextArea outputArea;
    private JButton submitButton;
    private JButton clearButton;

    public StudentInfoForm() {
        setTitle("Student Information Form");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 550);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // Title
        JLabel titleLabel = new JLabel("Student Information Form", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setOpaque(true);
        titleLabel.setBackground(new Color(50, 90, 160));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        // Form panel
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createEmptyBorder(15, 20, 10, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(7, 8, 7, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;

        Font labelFont = new Font("Arial", Font.BOLD, 12);
        Font fieldFont = new Font("Arial", Font.PLAIN, 13);

        // Name
        gbc.gridx = 0; gbc.gridy = 0; gbc.weightx = 0;
        formPanel.add(makeLabel("Name:", labelFont), gbc);
        gbc.gridx = 1; gbc.weightx = 1;
        nameField = new JTextField(20);
        nameField.setFont(fieldFont);
        formPanel.add(nameField, gbc);

        // Student ID
        gbc.gridx = 0; gbc.gridy = 1; gbc.weightx = 0;
        formPanel.add(makeLabel("Student ID:", labelFont), gbc);
        gbc.gridx = 1; gbc.weightx = 1;
        idField = new JTextField(20);
        idField.setFont(fieldFont);
        formPanel.add(idField, gbc);

        // Department
        gbc.gridx = 0; gbc.gridy = 2; gbc.weightx = 0;
        formPanel.add(makeLabel("Department:", labelFont), gbc);
        gbc.gridx = 1; gbc.weightx = 1;
        deptField = new JTextField(20);
        deptField.setFont(fieldFont);
        formPanel.add(deptField, gbc);

        // Gender
        gbc.gridx = 0; gbc.gridy = 3; gbc.weightx = 0;
        formPanel.add(makeLabel("Gender:", labelFont), gbc);
        gbc.gridx = 1; gbc.weightx = 1;

        JPanel genderPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        genderPanel.setOpaque(false);
        maleRadio   = new JRadioButton("Male");
        femaleRadio = new JRadioButton("Female");
        otherRadio  = new JRadioButton("Other");
        maleRadio.setFont(fieldFont);
        femaleRadio.setFont(fieldFont);
        otherRadio.setFont(fieldFont);
        maleRadio.setSelected(true);

        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(maleRadio);
        genderGroup.add(femaleRadio);
        genderGroup.add(otherRadio);

        genderPanel.add(maleRadio);
        genderPanel.add(femaleRadio);
        genderPanel.add(otherRadio);
        formPanel.add(genderPanel, gbc);

        // Semester
        gbc.gridx = 0; gbc.gridy = 4; gbc.weightx = 0;
        formPanel.add(makeLabel("Semester:", labelFont), gbc);
        gbc.gridx = 1; gbc.weightx = 1;

        String[] semesters = {"1st Semester", "2nd Semester", "3rd Semester",
                              "4th Semester", "5th Semester", "6th Semester",
                              "7th Semester", "8th Semester"};
        semesterCombo = new JComboBox<>(semesters);
        semesterCombo.setFont(fieldFont);
        formPanel.add(semesterCombo, gbc);

        // Buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 5));

        submitButton = new JButton("Submit");
        submitButton.setFont(new Font("Arial", Font.BOLD, 13));
        submitButton.setBackground(new Color(50, 90, 160));
        submitButton.setForeground(Color.WHITE);
        submitButton.setFocusPainted(false);
        submitButton.setPreferredSize(new Dimension(120, 35));

        clearButton = new JButton("Clear");
        clearButton.setFont(new Font("Arial", Font.BOLD, 13));
        clearButton.setBackground(new Color(160, 60, 50));
        clearButton.setForeground(Color.WHITE);
        clearButton.setFocusPainted(false);
        clearButton.setPreferredSize(new Dimension(120, 35));

        buttonPanel.add(submitButton);
        buttonPanel.add(clearButton);

        // Output area
        outputArea = new JTextArea(8, 40);
        outputArea.setFont(new Font("Monospaced", Font.PLAIN, 13));
        outputArea.setEditable(false);
        outputArea.setBackground(new Color(245, 245, 245));
        outputArea.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));

        JScrollPane scrollPane = new JScrollPane(outputArea);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Student Details"));
        scrollPane.setPreferredSize(new Dimension(560, 160));

        JPanel centerPanel = new JPanel(new BorderLayout(5, 5));
        centerPanel.add(formPanel, BorderLayout.NORTH);
        centerPanel.add(buttonPanel, BorderLayout.CENTER);
        centerPanel.add(scrollPane, BorderLayout.SOUTH);

        add(titleLabel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);

        // Submit action
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name     = nameField.getText().trim();
                String id       = idField.getText().trim();
                String dept     = deptField.getText().trim();
                String semester = (String) semesterCombo.getSelectedItem();

                // Validate required fields
                if (name.isEmpty() || id.isEmpty() || dept.isEmpty()) {
                    JOptionPane.showMessageDialog(StudentInfoForm.this,
                        "Please fill in all fields.", "Validation Error",
                        JOptionPane.WARNING_MESSAGE);
                    return;
                }

                String gender = maleRadio.isSelected() ? "Male"
                              : femaleRadio.isSelected() ? "Female" : "Other";

                String info = "========================================\n"
                            + "         STUDENT INFORMATION\n"
                            + "========================================\n"
                            + "  Name       : " + name + "\n"
                            + "  Student ID : " + id + "\n"
                            + "  Department : " + dept + "\n"
                            + "  Gender     : " + gender + "\n"
                            + "  Semester   : " + semester + "\n"
                            + "========================================";

                outputArea.setText(info);
            }
        });

        // Clear action
        clearButton.addActionListener(e -> {
            nameField.setText("");
            idField.setText("");
            deptField.setText("");
            maleRadio.setSelected(true);
            semesterCombo.setSelectedIndex(0);
            outputArea.setText("");
            nameField.requestFocus();
        });
    }

    private JLabel makeLabel(String text, Font font) {
        JLabel lbl = new JLabel(text);
        lbl.setFont(font);
        return lbl;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new StudentInfoForm().setVisible(true));
    }
}
