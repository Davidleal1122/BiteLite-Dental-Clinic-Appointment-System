package screens;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.Timer;

import swingComponents.panels.CustomizedPanel;

public class SplashScreen extends JFrame {

    private static final long serialVersionUID = 1L;
    
    private int width = 900;
    private int height = 600;

    private JProgressBar progressBar;
    private Timer timer;
    private int progressValue;
    private JPanel panel;
    private Font poppinsBold63;
    private Font poppinsBold35;
    private Font poppinsThin15;

    public SplashScreen() {
        // Create Frame
        CustomizedPanel loadingFrame = new CustomizedPanel(width, height);

        setSize(width, height);
        setLocationRelativeTo(null);
        setResizable(false);
        setUndecorated(true);
        setBackground(new Color(0, 0, 0, 0));

        // Create Progress Bar
        progressBar = new JProgressBar(0, 100) {

            protected void paintComponent(Graphics g) {
                int width1 = getWidth();
                Graphics2D bar = (Graphics2D) g.create();

                bar.setColor(Color.WHITE);
                bar.fillRect(0, 0, getWidth(), getHeight());

                bar.setColor(new Color(194, 216, 235));
                int width = (int) (getPercentComplete() * getWidth());
                bar.fillRect(0, 0, width, getHeight());

                bar.setColor(Color.BLACK);
                bar.setFont(getFont());
                FontMetrics fm = bar.getFontMetrics();
                String text = String.format("%.0f%%", getPercentComplete() * 100);
                int textWidth = fm.stringWidth(text);
                int textHeight = fm.getHeight();
                int x = (getWidth() - textWidth) / 2;
                int y = (getHeight() + textHeight) / 2;
                bar.drawString(text, x, y);

                bar.dispose();
            }
        };

        // Panel for background, text, images, and shapes
        panel = new JPanel() {
            private static final long serialVersionUID = 1L;

            public void paintComponent(Graphics h) {
                Graphics2D graphics = (Graphics2D) h.create();
                graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                graphics.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

                // Splash Screen Icons
                ImageIcon icon1 = new ImageIcon("src/image/splashScreensIcons/Splash Screen.png");
                ImageIcon icon2 = new ImageIcon("src/image/splashScreensIcons/Splash Screen 2.png");
                ImageIcon icon3 = new ImageIcon("src/image/splashScreensIcons/Splash Screen 3.png");

                // Splash Screen Background Icon
                icon1.paintIcon(this, h, 0, 0);

                // Splash Screen Background
                graphics.setColor(new Color(139, 164, 160, 220));
                graphics.fillRoundRect(0, 0, width, height, 20, 20);

                // Splash Screen Icons
                icon2.paintIcon(this, h, 0, 0);
                icon3.paintIcon(this, h, 23, 310);

                // Splash Screen Strings

                // Fonts
                try {
                    File fontFile1 = new File("src/fonts/splashScreenFonts/Poppins ExtraBold 800.ttf");
                    poppinsBold63 = Font.createFont(Font.TRUETYPE_FONT, fontFile1).deriveFont(Font.BOLD, 63);

                    File fontFile2 = new File("src/fonts/splashScreenFonts/Poppins ExtraBold 800.ttf");
                    poppinsBold35 = Font.createFont(Font.TRUETYPE_FONT, fontFile2).deriveFont(Font.BOLD, 35);

                    File fontFile3 = new File("src/fonts/splashScreenFonts/Poppins Regular 400.ttf");
                    poppinsThin15 = Font.createFont(Font.TRUETYPE_FONT, fontFile3).deriveFont(Font.BOLD, 15);

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
                graphics.drawString("ACHIEVE YOUR BEST SMILE", 80, 350);
                graphics.drawString("WITH BITELITE", 80, 370);

                super.paintComponent(h);
            }
        };

        panel.setOpaque(false);
        panel.setBounds(0, 0, width, height);

        progressBar.setValue(0);
        progressBar.setStringPainted(true);
        progressBar.setBounds(0, 560, 900, 30);
        add(progressBar);
        add(panel);
        add(loadingFrame);

        // Create a timer to simulate progress updates
        timer = new Timer(40, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                progressValue += 1;
                progressBar.setValue(progressValue);

                if (progressValue >= 100) {
                    timer.stop();
                    dispose();
                    LoginScreen loginFrame = new LoginScreen();
                    loginFrame.setVisible(true);
                }
            }
        });

        // Start the timer
        timer.start();
        setLocationRelativeTo(null);

        Timer textTimer = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Update x position and repaint to animate text
                progressBar.repaint();
            }
        });
        textTimer.start();
    }
}
