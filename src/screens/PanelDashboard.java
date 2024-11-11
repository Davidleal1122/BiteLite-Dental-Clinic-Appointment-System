package screens;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import connectDatabase.DatabaseConnection;

public class PanelDashboard extends JPanel {

    private static final int WIDTH = 765;
    private static final int HEIGHT = 650;

    public PanelDashboard() {
        setBounds(0, 0, WIDTH, HEIGHT);
        setLayout(null);
    }
DatabaseConnection dbConnect = new DatabaseConnection();
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics = (Graphics2D) g;
        setupGraphics(graphics);

        // Initialize ImageIcons
        ImageIcon background = new ImageIcon("src/image/dashboardIcons/BackgroundPanel.png");
        ImageIcon dentist = new ImageIcon("src/image/dashboardIcons/Dentist.png");
        ImageIcon patients = new ImageIcon("src/image/dashboardIcons/Patients.png");
        ImageIcon appointment = new ImageIcon("src/image/dashboardIcons/Appointment.png");

        // Draw components
        drawWhiteBackground(graphics);
        background.paintIcon(this, g, 0, 0);
        drawOvalPlaceholders(graphics);
        drawRoundRectangles(graphics);
        drawIcons(dentist, patients, appointment, g);
        drawText(graphics);

        setVisible(true);
    }

    // Set up graphics rendering hints
    private void setupGraphics(Graphics2D graphics) {
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
    }

    // Draw white background
    private void drawWhiteBackground(Graphics2D graphics) {
        graphics.setColor(new Color(255, 255, 255));
        graphics.fillRoundRect(0, 0, WIDTH, HEIGHT, 20, 20);
    }

    // Draw oval placeholders
    private void drawOvalPlaceholders(Graphics2D graphics) {
        graphics.setColor(new Color(192, 218, 214));
        graphics.fillOval(111, 182, 146, 146);
        graphics.fillOval(310, 182, 146, 146);
        graphics.fillOval(509, 182, 146, 146);
    }

    // Draw round rectangles
    private void drawRoundRectangles(Graphics2D graphics) {
        graphics.setColor(new Color(192, 218, 214));
        graphics.fillRoundRect(224, 388, 316, 46, 50, 50);
        graphics.fillRoundRect(224, 453, 316, 46, 50, 50);
    }

    // Draw icons
    private void drawIcons(ImageIcon dentist, ImageIcon patients, ImageIcon appointment, Graphics g) {
        dentist.paintIcon(this, g, 308, 169);
        patients.paintIcon(this, g, 115, 169);
        appointment.paintIcon(this, g, 508, 169);
    }

    // Draw text
    private void drawText(Graphics2D graphics) {
        try {
            File fontFile_PRegular = new File("src/fonts/splashScreenFonts/Poppins Regular 400.ttf");
            Font poppinsb = Font.createFont(Font.TRUETYPE_FONT, fontFile_PRegular).deriveFont(Font.BOLD, 12);
            graphics.setFont(poppinsb);

            // Draw text at specific positions
            drawTextAtPosition(graphics, "Add Patient", 150, 360);
            drawTextAtPosition(graphics, "Add Dentist", 350, 360);
            drawTextAtPosition(graphics, "New Appointment", 520, 360);
            
            int totalPatient = dbConnect.getCountOfPatients1() ;
            int totalApp = dbConnect.getCountOfAppointment1() ;

            drawTextAtPosition(graphics, "Total Patient Count: "+totalPatient, 250, 415);
            drawTextAtPosition(graphics, "Total Appointment Count: "+totalApp, 250, 480);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Draw text at a specific position
    private void drawTextAtPosition(Graphics2D graphics, String text, int x, int y) {
        graphics.setColor(Color.BLACK);
        graphics.drawString(text, x, y);
    }
}
