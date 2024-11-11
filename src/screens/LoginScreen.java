package screens;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import swingComponents.customizedButton.Button;
import swingComponents.customizedTextFields.CustomizedPasswordField;
import swingComponents.customizedTextFields.CustomizedTextField;
import swingComponents.panels.CustomizedPanel;

public class LoginScreen extends JFrame {

    private static final long serialVersionUID = 1L;

    private JPanel panel;
    private Font poppinsBold63;
    private Font poppinsBold35;
    private Font poppinsThin15;
    private Font poppinsThinPlain;
    private Font poppinsThinPlain10;
    private CustomizedTextField usernameField;
    private CustomizedPasswordField passwordField;
    private Color buttonColor = new Color(192, 218, 214);

    private Button signinButton = new Button(new Color(139, 164, 160));
    private String text1 = "Enter your credentials";
    private String username = "Admin";
    private String password = "Admin";
    private Color changeColorTo = Color.BLACK;

    public LoginScreen() {
        int width = 900;
        int height = 600;

        CustomizedPanel loginFrame = new CustomizedPanel(width, height);

        setSize(width, height);
        setLocationRelativeTo(null);
        setResizable(false);
        setUndecorated(true);
        setBackground(new Color(0, 0, 0, 0));

        panel = new JPanel() {
            private static final long serialVersionUID = 1L;

            public void paintComponent(Graphics h) {
                Graphics2D graphics = (Graphics2D) h.create();
                graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                graphics.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

                ImageIcon icon1 = new ImageIcon("src/image/loginScreenIcons/Login Screen 1.png");
                ImageIcon icon2 = new ImageIcon("src/image/loginScreenIcons/Login Screen 2.png");
                ImageIcon icon3 = new ImageIcon("src/image/loginScreenIcons/Login Screen 3.png");

                // Background
                icon1.paintIcon(this, h, 0, 0);

                // Background Panel
                graphics.setColor(new Color(139, 164, 160, 220));
                graphics.fillRoundRect(0, 0, 352, height, 20, 20);

                // Logo Backgrounds
                graphics.setColor(new Color(192, 218, 214));
                graphics.fillOval(273, 225, 150, 150);
                graphics.setColor(new Color(139, 164, 160));
                graphics.fillOval(283, 235, 130, 130);

                // BiteLite Icons
                icon2.paintIcon(this, h, 0, 0);
                icon3.paintIcon(this, h, 15, 310);

                
                // Fonts
                try {
                    File fontFile1 = new File("src/fonts/splashScreenFonts/Poppins ExtraBold 800.ttf");
                    poppinsBold63 = Font.createFont(Font.TRUETYPE_FONT, fontFile1).deriveFont(Font.BOLD, 63);

                    File fontFile2 = new File("src/fonts/splashScreenFonts/Poppins ExtraBold 800.ttf");
                    poppinsBold35 = Font.createFont(Font.TRUETYPE_FONT, fontFile2).deriveFont(Font.BOLD, 35);

                    File fontFile3 = new File("src/fonts/splashScreenFonts/Poppins Regular 400.ttf");
                    poppinsThin15 = Font.createFont(Font.TRUETYPE_FONT, fontFile3).deriveFont(Font.BOLD, 15);

                    File fontFile4 = new File("src/fonts/splashScreenFonts/Poppins Regular 400.ttf");
                    poppinsThinPlain = Font.createFont(Font.TRUETYPE_FONT, fontFile4).deriveFont(Font.PLAIN, 15);

                    File fontFile5 = new File("src/fonts/splashScreenFonts/Poppins Regular 400.ttf");
                    poppinsThinPlain10 = Font.createFont(Font.TRUETYPE_FONT, fontFile5).deriveFont(Font.PLAIN, 10);

                } catch (IOException | FontFormatException e) {
                    e.printStackTrace();
                }

                // BiteLite strings
                graphics.setColor(Color.WHITE);
                graphics.setFont(poppinsBold63);
                graphics.drawString("BITELITE", 30, 180);

                graphics.setFont(poppinsBold35);
                graphics.setColor(new Color(192, 218, 214));
                graphics.drawString("DENTAL", 30, 220);

                graphics.setColor(new Color(84, 84, 84));
                graphics.drawString("CLINIC", 180, 220);

                graphics.setColor(Color.WHITE);
                graphics.setFont(poppinsThin15);
                graphics.drawString("APPOINTMENT SYSTEM", 30, 250);
                graphics.drawString("ACHIEVE YOUR BEST SMILE", 65, 350);
                graphics.drawString("WITH BITELITE", 65, 370);

                // text for login functions
                graphics.setColor(changeColorTo);
                graphics.setFont(poppinsThinPlain10);
                graphics.drawString(text1, 470, 320);

                graphics.setColor(Color.BLACK);
                graphics.setFont(new Font("Arial", Font.BOLD, 40));
                graphics.drawString("SIGN IN", 555, 150);

                super.paintComponent(h);
            }
        };

        panel.setOpaque(false);
        panel.setBounds(0, 0, width, height);

        // SignIn Button
        signinButton.setText("SIGN IN");
        signinButton.setBounds(560, 400, 150, 40);

        // SignIn Fields
        usernameField = new CustomizedTextField();
        passwordField = new CustomizedPasswordField();

        usernameField.setBounds(470, 200, 300, 40);
        passwordField.setBounds(470, 250, 300, 40);

        usernameField.setHint("Username");
        passwordField.setHint("Password");

        usernameField.setColor(new Color(139, 164, 160));

        add(signinButton);
        add(passwordField);
        add(usernameField);
        add(panel);
        add(loginFrame);

        functions();
    }

    public void functions() {
        signinButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                String userName = usernameField.getText();
                String passWord = passwordField.getText();

                FramePanelHolder dashboardFrame = new FramePanelHolder();

                if (!userName.isBlank() || !passWord.isBlank()) {
                    if (!userName.equals(username) || !passWord.equals(password)) {
                        text1 = "Wrong Credentials";
                        changeColorTo = Color.RED;
                        repaint();
                    }
                    if (userName.isBlank() || passWord.isBlank()) {
                        text1 = "Please complete the information needed";
                        changeColorTo = Color.RED;
                        repaint();
                    }
                    if (userName.equals(username) && passWord.equals(password)) {
                        text1 = "Done";
                        repaint();
                        dashboardFrame.setVisible(true);
                        dispose();
                    }
                } else {
                    text1 = "Please complete the information needed";
                    changeColorTo = Color.RED;
                    repaint();
                }
            }
        });
    }
}
