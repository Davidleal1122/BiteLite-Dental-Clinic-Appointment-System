package trialError;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class CustomComboBox extends JFrame {

    public CustomComboBox() {
        setTitle("Custom ComboBox");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        String[] options = {"Option 1", "Option 2", "Option 3"};
        JComboBox<String> comboBox = new JComboBox<>(options);
        setComboBoxFont(comboBox, "src/fonts/splashScreenFonts/Poppins Regular 400.ttf");

        JPanel panel = new JPanel();
        panel.add(comboBox);

        getContentPane().add(panel);
    }

    private void setComboBoxFont(JComboBox<?> comboBox, String fontPath) {
        try {
            Font font = Font.createFont(Font.TRUETYPE_FONT, new File(fontPath)).deriveFont(Font.PLAIN, 12);
            comboBox.setFont(font);
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CustomComboBox customComboBox = new CustomComboBox();
            customComboBox.setVisible(true);
        });
    }
}
