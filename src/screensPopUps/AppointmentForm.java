package screensPopUps;

import java.awt.BorderLayout;
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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.table.DefaultTableModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JPanel;

import com.github.lgooddatepicker.components.TimePicker;
import com.toedter.calendar.JDateChooser;

import connectDatabase.DatabaseConnection;
import screens.PanelAppointments;
import swingComponents.customizedButton.Button;

public class AppointmentForm extends JPanel {

	// Background image
	private ImageIcon background = new ImageIcon("src/image/dashboardIcons/BackgroundPanel.png");

	// Buttons for save and cancel
	private Button saveButton;
	public Button cancelButton;

	private Font poppinsBold35;
	private Font poppinsThinPlain;

	// Dropdowns for selecting patient, dentist, and treatment
	
	private JComboBox<String> patientDropdown = new JComboBox<>();
	private JComboBox<String> dentistDropdown = new JComboBox<>();
	private JComboBox<String> treatmentDropdown = new JComboBox<>();
	private DatabaseConnection dbConnect;

	// Date Chooser for appointment date
	private JDateChooser appointmentDate;

	// Time Picker for appointment time
	private TimePicker appointmentTime;
	
	DefaultTableModel tableModel;

	// Dimensions of the panel
	private static final int WIDTH = 765;
	private static final int HEIGHT = 650;

	public AppointmentForm() {
		// Initialize DatabaseConnection instance
		dbConnect = new DatabaseConnection();

		initializeComponents();
		buttonFunction();
		setLayout(new BorderLayout());
		setBounds(0, 0, WIDTH, HEIGHT);

		buttonFunction();
	}

	private void initializeComponents() {
		// Button colors
		Color buttonColor2 = new Color(105, 121, 118);
		saveButton = new Button(buttonColor2);

		Color buttonColor3 = new Color(141, 62, 62);
		cancelButton = new Button(buttonColor3);

		// Set button text and bounds
		saveButton.setText("SAVE");
		cancelButton.setText("CANCEL");
		saveButton.setBounds(260, 525, 100, 40);
		cancelButton.setBounds(395, 525, 100, 40);

		// Add buttons to the panel
		add(saveButton);
		add(cancelButton);

		// Set bounds for dropdowns
		patientDropdown.setBounds((WIDTH - 300) / 2, 170, 300, 40);
		updatePatientDropdown();

		dentistDropdown.setBounds((WIDTH - 300) / 2, 240, 300, 40);
		updateDentistDropdown();

		treatmentDropdown.setBounds((WIDTH - 300) / 2, 310, 300, 40);
		updateTreatmentDropdown();

		// Set bounds for date chooser
		appointmentDate = new JDateChooser();
		String dateFormatPattern = "yyyy-MM-dd";
		appointmentDate.setDateFormatString(dateFormatPattern);

		appointmentDate.setBounds((WIDTH - 300) / 2, 380, 300, 40);

		// Set bounds for time picker
		appointmentTime = new TimePicker();
		appointmentTime.setBounds((WIDTH - 300) / 2, 450, 300, 40);

		// Add components to the panel
		add(patientDropdown);
		add(dentistDropdown);
		add(treatmentDropdown);
		add(appointmentDate);
		add(appointmentTime);
	}

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

			File fontFile2 = new File("src/fonts/splashScreenFonts/Poppins Regular 400.ttf");
			poppinsThinPlain = Font.createFont(Font.TRUETYPE_FONT, fontFile2).deriveFont(Font.PLAIN, 12);

		} catch (IOException | FontFormatException e) {
			e.printStackTrace();
		}

		// Draw the title
		graphics.setColor(Color.black);
		graphics.setFont(poppinsBold35);

		graphics.drawString("NEW APPOINTMENT", 200, 100);

		graphics.setColor(Color.black);
		graphics.setFont(poppinsThinPlain);
		graphics.drawString("Patient Name: ", 232, 160);

		graphics.setColor(Color.black);
		graphics.setFont(poppinsThinPlain);
		graphics.drawString("Dentist Name: ", 232, 230);

		graphics.setColor(Color.black);
		graphics.setFont(poppinsThinPlain);
		graphics.drawString("Treatment: ", 232, 300);

		graphics.setColor(Color.black);
		graphics.setFont(poppinsThinPlain);
		graphics.drawString("Appointment Date: ", 232, 370);

		graphics.setColor(Color.black);
		graphics.setFont(poppinsThinPlain);
		graphics.drawString("Appointment Time: ", 232, 440);
	}
	public void buttonFunction() {
	    saveButton.addMouseListener(new MouseAdapter() {
	        @Override
	        public void mouseClicked(MouseEvent me) {
	            // Get the selected date from the JDateChooser
	            Date selectedAppointmentDate = appointmentDate.getDate();

	            // Create a SimpleDateFormat with the desired pattern
	            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

	            // Format the date to a string with the specified pattern
	            String formattedDate = dateFormat.format(selectedAppointmentDate);

	            // Get the selected time from the TimePicker
	            String selectedAppointmentTime = appointmentTime.getTime().toString();

	            String choosePatient = (String) patientDropdown.getSelectedItem();
	            String chooseDentist = (String) dentistDropdown.getSelectedItem();
	            String choosetreatment = (String) treatmentDropdown.getSelectedItem();

	            // Call the insertValuesAndRefreshTable method from DatabaseConnection
	            dbConnect.insertValuesToAppointmentAndUpdateTable(formattedDate, selectedAppointmentTime,
	                    choosePatient, chooseDentist, choosetreatment, tableModel);
	        }
	    });
	}


	// methods for dropdown
	private void updatePatientDropdown() {
		// Clear existing items in the dropdown
		patientDropdown.removeAllItems();

		// Fetch patients from the database
		List<String> patients = dbConnect.getPatientsForDropdown();

		// Add the fetched patients to the dropdown
		for (String patient : patients) {
			patientDropdown.addItem(patient);
		}
	}

	private void updateDentistDropdown() {
		// Clear existing items in the dropdown
		dentistDropdown.removeAllItems();

		// Fetch dentists from the database
		List<String> dentists = dbConnect.getDentistForDropdown();

		// Add the fetched dentists to the dropdown
		for (String dentist : dentists) {
			dentistDropdown.addItem(dentist);
		}
	}

	private void updateTreatmentDropdown() {
		// Clear existing items in the dropdown
		treatmentDropdown.removeAllItems();

		List<String> treatments = dbConnect.getTreatmentForDropdown();

		// Add treatment options to the dropdown
		for (String treatment : treatments) {
			treatmentDropdown.addItem(treatment);
		}
	}
}