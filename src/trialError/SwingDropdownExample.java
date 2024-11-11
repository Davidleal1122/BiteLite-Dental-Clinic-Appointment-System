package trialError;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SwingDropdownExample {
    public static void main(String[] args) {
        // Create a JFrame
        JFrame frame = new JFrame("Swing Dropdown Example");
        frame.setSize(300, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create a String array with options
        String[] options = {"Option 1", "Option 2", "Option 3"};

        // Create a JComboBox (dropdown)
        JComboBox<String> dropdown = new JComboBox<>(options);

        // Create a label to display the selected option
        JLabel label = new JLabel("Selected Option: ");

        // Create a button to show the selected option
        JButton showButton = new JButton("Show Selected Option");
        showButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedOption = (String) dropdown.getSelectedItem();
                label.setText("Selected Option: " + selectedOption);
            }
        });

        // Create a panel and add components
        JPanel panel = new JPanel();
        panel.add(dropdown);
        panel.add(showButton);
        panel.add(label);

        // Add the panel to the frame
        frame.add(panel);

        // Set the frame visibility
        frame.setVisible(true);
    }
}
