package screens;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import screensPopUps.AppointmentForm;
import screensPopUps.DentistProfileForm;
import screensPopUps.PatientProfileForm;
import screensPopUps.TreatmentForm;

public class FramePanelHolder extends JFrame {

	private PanelDashboard dashboardPanel;
	private PanelAppointments appointmentsPanel;
	private PanelPatientRecord patientRecordPanel;
	private PanelDentistRecord dentistRecordPanel;
	private PanelTreatment treatmentPanel;
	private PatientProfileForm patientProfileForm;
	private AppointmentForm appointmentForm;
	private DentistProfileForm dentistProfileForm;
	private TreatmentForm treatmentForm;
	private LoginScreen loginScreen;

	private JLabel dashboardLabel, appointmentsLabel, patientRecordLabel, dentistRecordLabel, treatmentLabel,
			logoutLabel;
	private JPanel dashboardNavigationPanel, appointmentsNavigationPanel, patientsNavigationPanel,
			dentistsNavigationPanel, treatmentNavigationPanel, logoutNavigationPanel;

	public FramePanelHolder() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setUndecorated(true);
		setBounds(0, 0, 1000, 650);
		setLocationRelativeTo(null);
		setBackground(new Color(0, 0, 0, 0));
		getContentPane().setLayout(null);

		JPanel panelMainContent = new JPanel();
		panelMainContent.setLayout(null);
		panelMainContent.setBounds(235, 0, 765, 650);
		getContentPane().add(panelMainContent);

		JPanel navigation = new JPanel(); // Adjust the corner radius as needed
		navigation.setBounds(0, 0, 235, 650);
		navigation.setBackground(new Color(104, 118, 116));
		getContentPane().add(navigation);
		navigation.setLayout(null);

		dashboardPanel = new PanelDashboard();
		appointmentsPanel = new PanelAppointments();
		patientRecordPanel = new PanelPatientRecord();
		dentistRecordPanel = new PanelDentistRecord();
		treatmentPanel = new PanelTreatment();
		appointmentForm = new AppointmentForm();
		patientProfileForm = new PatientProfileForm();
		dentistProfileForm = new DentistProfileForm();
		treatmentForm = new TreatmentForm();

		dashboardNavigationPanel = new JPanel();
		dashboardNavigationPanel.setLayout(null);
		dashboardNavigationPanel.setBackground(new Color(104, 118, 116));
		dashboardNavigationPanel.setBounds(0, 174, 253, 42);
		dashboardNavigationPanel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		navigation.add(dashboardNavigationPanel);
		dashboardNavigationPanel.addMouseListener(new PanelButtonMouseAdapter(dashboardNavigationPanel) {
			@Override
			public void mouseClicked(MouseEvent e) {
				menuClicked(dashboardPanel);
			}
		});

		appointmentsNavigationPanel = new JPanel();
		appointmentsNavigationPanel.setBackground(new Color(104, 118, 116));
		appointmentsNavigationPanel.setBounds(0, 219, 253, 42);
		appointmentsNavigationPanel.setLayout(null);
		appointmentsNavigationPanel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		navigation.add(appointmentsNavigationPanel);
		appointmentsNavigationPanel.addMouseListener(new PanelButtonMouseAdapter(appointmentsNavigationPanel) {
			@Override
			public void mouseClicked(MouseEvent e) {
				menuClicked(appointmentsPanel);
			}
		});

		patientsNavigationPanel = new JPanel();
		patientsNavigationPanel.setBackground(new Color(104, 118, 116));
		patientsNavigationPanel.setBounds(0, 264, 253, 42);
		patientsNavigationPanel.setLayout(null);
		patientsNavigationPanel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		navigation.add(patientsNavigationPanel);
		patientsNavigationPanel.addMouseListener(new PanelButtonMouseAdapter(patientsNavigationPanel) {
			@Override
			public void mouseClicked(MouseEvent e) {
				menuClicked(patientRecordPanel);
			}
		});

		dentistsNavigationPanel = new JPanel();
		dentistsNavigationPanel.setBackground(new Color(104, 118, 116));
		dentistsNavigationPanel.setBounds(0, 309, 253, 42);
		dentistsNavigationPanel.setLayout(null);
		dentistsNavigationPanel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		navigation.add(dentistsNavigationPanel);
		dentistsNavigationPanel.addMouseListener(new PanelButtonMouseAdapter(dentistsNavigationPanel) {
			@Override
			public void mouseClicked(MouseEvent e) {
				menuClicked(dentistRecordPanel);
			}
		});

		treatmentNavigationPanel = new JPanel();
		treatmentNavigationPanel.setBackground(new Color(104, 118, 116));
		treatmentNavigationPanel.setBounds(0, 354, 253, 42);
		treatmentNavigationPanel.setLayout(null);
		treatmentNavigationPanel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		navigation.add(treatmentNavigationPanel);
		treatmentNavigationPanel.addMouseListener(new PanelButtonMouseAdapter(treatmentNavigationPanel) {
			@Override
			public void mouseClicked(MouseEvent e) {
				menuClicked(treatmentPanel);
			}
		});

		logoutNavigationPanel = new JPanel();
		logoutNavigationPanel.setBackground(new Color(104, 118, 116));
		logoutNavigationPanel.setBounds(0, 500, 253, 42);
		logoutNavigationPanel.setLayout(null);
		logoutNavigationPanel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		navigation.add(logoutNavigationPanel);
		logoutNavigationPanel.addMouseListener(new PanelButtonMouseAdapter(logoutNavigationPanel) {
			@Override
			public void mouseClicked(MouseEvent e) {
				logout();
			}
		});

		try {
			File fontFile_PBold = new File("src/fonts/splashScreenFonts/Poppins ExtraBold 800.ttf");
			File fontFile_PRegular = new File("src/fonts/splashScreenFonts/Poppins Regular 400.ttf");
			Font poppinsb = Font.createFont(Font.TRUETYPE_FONT, fontFile_PRegular).deriveFont(Font.BOLD, 12);

			ImageIcon dashboardIcon = new ImageIcon(new ImageIcon("src/image/dashboardIcons/DashboardIcon.png")
					.getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH));
			JLabel dashboardIconn = new JLabel(dashboardIcon);
			ImageIcon appointmentsIcon = new ImageIcon(new ImageIcon("src/image/dashboardIcons/AppointmentIcon1.png")
					.getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH));
			JLabel appointmentsIconn = new JLabel(appointmentsIcon);
			ImageIcon patientIcon = new ImageIcon(new ImageIcon("src/image/dashboardIcons/PatientIcon1.png").getImage()
					.getScaledInstance(16, 16, Image.SCALE_SMOOTH));
			JLabel patientIconn = new JLabel(patientIcon);
			ImageIcon dentistIcon = new ImageIcon(new ImageIcon("src/image/dashboardIcons/DentistIcon1.png").getImage()
					.getScaledInstance(16, 16, Image.SCALE_SMOOTH));
			JLabel dentistIconn = new JLabel(dentistIcon);
			ImageIcon treatmentIcon = new ImageIcon(new ImageIcon("src/image/dashboardIcons/TreatmentIcon.jpg")
					.getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH));
			JLabel treatmentIconn = new JLabel(treatmentIcon);
			ImageIcon logoutIcon = new ImageIcon(new ImageIcon("src/image/dashboardIcons/logoutIcon.png").getImage()
					.getScaledInstance(16, 16, Image.SCALE_SMOOTH));
			JLabel logoutIconn = new JLabel(logoutIcon);

			dashboardLabel = new JLabel("DASHBOARD");
			dashboardLabel.setFont(poppinsb);
			dashboardLabel.setForeground(new Color(255, 255, 255));
			dashboardLabel.setBounds(75, 13, 134, 14);
			dashboardNavigationPanel.add(dashboardLabel);

			appointmentsLabel = new JLabel("APPOINTMENTS");
			appointmentsLabel.setBounds(75, 13, 134, 14);
			appointmentsLabel.setFont(poppinsb);
			appointmentsLabel.setForeground(new Color(255, 255, 255));
			appointmentsNavigationPanel.add(appointmentsLabel);

			patientRecordLabel = new JLabel("PATIENT RECORD");
			patientRecordLabel.setFont(poppinsb);
			patientRecordLabel.setForeground(new Color(255, 255, 255));
			patientRecordLabel.setBounds(75, 13, 143, 14);
			patientsNavigationPanel.add(patientRecordLabel);

			dentistRecordLabel = new JLabel("DENTIST RECORD");
			dentistRecordLabel.setFont(poppinsb);
			dentistRecordLabel.setForeground(new Color(255, 255, 255));
			dentistRecordLabel.setBounds(75, 13, 110, 14);
			dentistsNavigationPanel.add(dentistRecordLabel);

			treatmentLabel = new JLabel("TREATMENTS");
			treatmentLabel.setFont(poppinsb);
			treatmentLabel.setForeground(new Color(255, 255, 255));
			treatmentLabel.setBounds(75, 13, 110, 14);
			treatmentNavigationPanel.add(treatmentLabel);

			logoutLabel = new JLabel("Logout");
			logoutLabel.setFont(poppinsb);
			logoutLabel.setForeground(new Color(255, 255, 255));
			logoutLabel.setBounds(93, 13, 110, 14);
			logoutNavigationPanel.add(logoutLabel);

			dashboardIconn.setBounds(45, 7, 25, 25);
			dashboardNavigationPanel.add(dashboardIconn);
			appointmentsIconn.setBounds(45, 7, 25, 25);
			appointmentsNavigationPanel.add(appointmentsIconn);
			patientIconn.setBounds(45, 7, 25, 25);
			patientsNavigationPanel.add(patientIconn);
			dentistIconn.setBounds(45, 7, 25, 25);
			dentistsNavigationPanel.add(dentistIconn);
			treatmentIconn.setBounds(45, 7, 25, 25);
			treatmentNavigationPanel.add(treatmentIconn);
			logoutIconn.setBounds(57, 7, 25, 25);
			logoutNavigationPanel.add(logoutIconn);

		} catch (Exception e) {
			e.printStackTrace();
		}

		panelMainContent.add(dashboardPanel);
		panelMainContent.add(appointmentsPanel);
		panelMainContent.add(patientRecordPanel);
		panelMainContent.add(dentistRecordPanel);
		panelMainContent.add(treatmentPanel);
		panelMainContent.add(appointmentForm);
		panelMainContent.add(patientProfileForm);
		panelMainContent.add(dentistProfileForm);
		panelMainContent.add(treatmentForm);

		menuClicked(dashboardPanel);
		functions();

	}

	public void functions() {

		appointmentsPanel.editButton.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				menuClicked(appointmentForm);
			}

		});

		patientRecordPanel.editButton.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				menuClicked(patientProfileForm);

			}

		});

		dentistRecordPanel.editButton.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				menuClicked(dentistProfileForm);
			}

		});

		treatmentPanel.editButton.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				menuClicked(treatmentForm);
			}

		});
	}

	public void menuClicked(JPanel panel) {
		dashboardPanel.setVisible(false);
		appointmentsPanel.setVisible(false);
		patientRecordPanel.setVisible(false);
		dentistRecordPanel.setVisible(false);
		treatmentPanel.setVisible(false);
		appointmentForm.setVisible(false);
		patientProfileForm.setVisible(false);
		dentistProfileForm.setVisible(false);
		treatmentForm.setVisible(false);

		panel.setVisible(true);
	}

	private class PanelButtonMouseAdapter extends MouseAdapter {
		JPanel panel;

		public PanelButtonMouseAdapter(JPanel panel) {
			this.panel = panel;
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			panel.setBackground(new Color(164, 198, 193));
		}

		@Override
		public void mouseExited(MouseEvent e) {
			panel.setBackground(new Color(104, 118, 116));
		}

		@Override
		public void mousePressed(MouseEvent e) {
			panel.setBackground(new Color(164, 198, 193));
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			panel.setBackground(new Color(104, 118, 116));
		}

	}

	private void logout() {
		// Perform any cleanup or logout operations here

		// Close the current frame
		dispose();

		// Create and show the LoginScreen
		loginScreen = new LoginScreen();
		loginScreen.setVisible(true);
	}

}
