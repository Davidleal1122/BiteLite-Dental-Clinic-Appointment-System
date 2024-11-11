package screensPopUps;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import connectDatabase.DatabaseConnection;
import screens.PanelDentistRecord;
import screens.PanelPatientRecord;
import swingComponents.customizedButton.Button;
import swingComponents.customizedTextFields.CustomizedTextField;

public class DentistProfileForm extends JPanel {

	// Background image
	ImageIcon background = new ImageIcon("src/image/dashboardIcons/BackgroundPanel.png");

	// Buttons for Save and Cancel actions
	private Button saveButton;
	private Button updateButton;
	private Button clearButton;
	public Button cancelButton;
	private Font poppinsBold35;

	// TextFields for user input
	public static CustomizedTextField firstNameField;
	public static CustomizedTextField lastNameField;
	public static CustomizedTextField contactNumberField;
	public static CustomizedTextField emailField;
	private DatabaseConnection dbConnect;

	private static final int WIDTH = 765;
	private static final int HEIGHT = 650;
	
	

	// Constructor
	public DentistProfileForm() {
		initializeComponents();
		buttonFunction();
		updateFunction();
		clearButton();
		setLayout(new BorderLayout());
		setBounds(0, 0, WIDTH, HEIGHT);
	}

	// Method to initialize UI components
	private void initializeComponents() {
		// Button colors
				Color buttonColor1 = new Color(164, 198, 193);
				Color buttonColor2 = new Color(105, 121, 118);
				Color buttonColor3 = new Color(141, 62, 62);

				// Create Save and Cancel buttons
				saveButton = new Button(buttonColor1);
				updateButton = new Button(buttonColor2);
				clearButton = new Button(buttonColor2);
				cancelButton = new Button(buttonColor3);

				// Set text for buttons
				saveButton.setText("SAVE");
				updateButton.setText("UPDATE");
				clearButton.setText("CLEAR");
				cancelButton.setText("CANCEL");

				// Set bounds for buttons
				saveButton.setBounds(125, 455, 100, 40);
				updateButton.setBounds(260, 455, 100, 40);
				clearButton.setBounds(395, 455, 100, 40);
				cancelButton.setBounds(530, 455, 100, 40);

				// Add buttons to the panel
				add(saveButton);
				add(updateButton);
				add(clearButton);
				add(cancelButton);

		// Create and configure TextFields
		firstNameField = createConfiguredTextField((WIDTH - 300) / 2, 150, "First Name");
		lastNameField = createConfiguredTextField((WIDTH - 300) / 2, 200, "Last Name");
		contactNumberField = createConfiguredTextField((WIDTH - 300) / 2, 250, "Contact Number");
		emailField = createConfiguredTextField((WIDTH - 300) / 2, 300, "Email Address");
		

		// Add TextFields to the panel
		add(firstNameField);
		add(lastNameField);
		add(contactNumberField);
		add(emailField);
	}

	// Method to create and configure a TextField with common settings
	private CustomizedTextField createConfiguredTextField(int x, int y, String hint) {
		CustomizedTextField textField = new CustomizedTextField();
		textField.setBounds(x, y, 300, 40);
		textField.setHint(hint);
		textField.setColor(new Color(139, 164, 160));
		return textField;
	}

	// Override paintComponent method to customize panel appearance
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D graphics = (Graphics2D) g.create();
		graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		graphics.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

		// Draw a white rounded rectangle as the background
		graphics.setColor(new Color(255, 255, 255));
		graphics.fillRoundRect(0, 0, 765, HEIGHT, 20, 20);

		// Draw the background image
		background.paintIcon(this, g, 0, 0);

		// Fonts
		try {

			File fontFile1 = new File("src/fonts/splashScreenFonts/Poppins ExtraBold 800.ttf");
			poppinsBold35 = Font.createFont(Font.TRUETYPE_FONT, fontFile1).deriveFont(Font.PLAIN, 35);

		} catch (IOException | FontFormatException e) {
			e.printStackTrace();
		}

		// Draw the title
		graphics.setColor(Color.black);
		graphics.setFont(poppinsBold35);
		graphics.drawString("DENTIST FORM", 253, 100);
	}
	private void clearButton() {
		clearButton.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {

    firstNameField.setText("");
    lastNameField.setText("");
    contactNumberField.setText("");
    emailField.setText("");
}	    });
}
private void updateFunction() {
	updateButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (firstNameField.getText().equals("") || lastNameField.getText().equals("") ||
                contactNumberField.getText().equals("") || emailField.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Please Fill Complete Information!");
            } else {
                try {
                	 
                	 DefaultTableModel up = (DefaultTableModel) PanelPatientRecord.table.getModel(); 
                     int selectedRow = PanelDentistRecord.table.getSelectedRow(); 
                	if (selectedRow != -1) {
                	dbConnect = new DatabaseConnection();
                	Class.forName("com.mysql.cj.jdbc.Driver");
                    String sql = "Update dentist set first_name=?, last_name=?, contact_number=?, email=? where dentist_id=?";
                    
                   
                    
                    dbConnect.connect = DriverManager.getConnection(dbConnect.url, dbConnect.user,dbConnect.password);
                    dbConnect.pst = dbConnect.connect.prepareStatement(sql);
                    
                    int id = Integer.parseInt(up.getValueAt(selectedRow, 0).toString()); 
                    
                    dbConnect.pst.setString(1, firstNameField.getText());
                    dbConnect.pst.setString(2, lastNameField.getText());
                    dbConnect.pst.setInt(3, Integer.parseInt(contactNumberField.getText()));
                    dbConnect.pst.setString(4, emailField.getText());
                    dbConnect.pst.setInt(5, id); 
                    
                    dbConnect.pst.executeUpdate();
                    dbConnect.refreshDentistTable();
                    JOptionPane.showMessageDialog(null, "Record Updated!");
                    firstNameField.setText("");
                    lastNameField.setText("");
                    contactNumberField.setText("");
                    emailField.setText("");
                    

                }else {
                	JOptionPane.showMessageDialog(null, "Please select a row to update!");
                }
                }
                	catch (SQLException | NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
                    ex.printStackTrace();
                } catch (ClassNotFoundException e1) {
                	e1.printStackTrace();
				} 

            }
        }
    });
}

	public void buttonFunction() {

		saveButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent me) {
				
				dbConnect = new DatabaseConnection();
				
				String firstName = firstNameField.getText();
	            String lastName = lastNameField.getText();
	            String contactNumber = contactNumberField.getText();
	            String email = emailField.getText();
	            
	            if (firstNameField.getText().equals("") || lastNameField.getText().equals("") ||
	                    contactNumberField.getText().equals("") || emailField.getText().equals("")) {
	                    JOptionPane.showMessageDialog(null, "Please Fill Complete Information!");
	                } else {
	            // Call the insertValues method from DatabaseConnection
	            dbConnect.insertValuesToDentist(firstName, lastName, contactNumber, email);
	            dbConnect.refreshDentistTable();
	            firstNameField.setText("");
                lastNameField.setText("");
                contactNumberField.setText("");
                emailField.setText("");
                
	        }}
	    });
		
	}}