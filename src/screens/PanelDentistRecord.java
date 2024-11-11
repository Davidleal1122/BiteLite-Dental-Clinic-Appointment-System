package screens;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import swingComponents.customizedButton.Button;
import swingComponents.customizedTextFields.CustomizedTextField;
import connectDatabase.DatabaseConnection;
import screens.PanelPatientRecord;
import screensPopUps.DentistProfileForm;
import screensPopUps.PatientProfileForm;

public class PanelDentistRecord extends JPanel {

	// Icons and dimensions
	ImageIcon background = new ImageIcon("src/image/dashboardIcons/BackgroundPanel.png");
	ImageIcon searchIcon = new ImageIcon("src/image/dashboardIcons/searchIcon.png");
	int width = 765;
	int height = 650;

	// Components
	CustomizedTextField searchField;
	public static JTable table;
	private DefaultTableModel model;

	// Buttons with colors
		Color buttonColor2 = new Color(105, 121, 118);
		Color buttonColor3 = new Color(141, 62, 62);

		Button editButton = new Button(buttonColor2);
		Button deleteButton = new Button(buttonColor3);

	DatabaseConnection dbConnect = new DatabaseConnection();

	public PanelDentistRecord() {
		setBounds(0, 0, width, height);
		initializeComponents();
		setLayout(new BorderLayout());
	}

	// Initialize and set up components
	private void initializeComponents() {
		initializeButtons();
		initializeSearchArea();
		initializeTable();
	}

	// Initialize buttons with colors and set positions
	private void initializeButtons() {
		editButton.setText("EDIT");
		deleteButton.setText("DELETE");
		//delete function
		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dbConnect.deleteFunctionForDentist(table);
			}
		});

		editButton.setBounds(260, 575, 100, 40);
		deleteButton.setBounds(395, 575, 100, 40);

		add(editButton);
		add(deleteButton);
	}

	// Initialize search icon and search field
	private void initializeSearchArea() {
		JLabel searchIcons = new JLabel(searchIcon);
		searchIcons.setBounds(500, 30, 50, 50);

		searchField = new CustomizedTextField();
		searchField.setBounds(540, 43, 150, 30);
		searchField.setHint("Search");
		searchField.setColor(new Color(0, 0, 0, 0));
		  searchField.addActionListener(new ActionListener() { 

	        	public void actionPerformed(ActionEvent arg0) { 

	        	 dbConnect.table_SearchDentist(searchField.getText(),table); 

	        	}}); 
		add(searchIcons);
		add(searchField);
	}

	// Initialize the JTable and add it to a JScrollPane
	private void initializeTable() {
		model = new DefaultTableModel();
		dbConnect.selectingTableFromDentist(model);

		table = new JTable(model) {
			@Override
			public boolean isCellEditable(int row, int column) {
				// Make all cells not editable
				return false;
			}
		};

		table.setBorder(new LineBorder(new Color(0, 0, 0)));
		table.setFillsViewportHeight(true);

		table.getTableHeader().setReorderingAllowed(true);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		table.getColumnModel().getColumn(0).setPreferredWidth(100);
		table.getColumnModel().getColumn(1).setPreferredWidth(200);
		table.getColumnModel().getColumn(2).setPreferredWidth(200);
		table.getColumnModel().getColumn(3).setPreferredWidth(200);
		table.getColumnModel().getColumn(4).setPreferredWidth(200);
		table.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent e) {

				DefaultTableModel up = (DefaultTableModel) table.getModel();

				int i = table.getSelectedRow();
				DentistProfileForm.firstNameField.setText(getStringValue(up.getValueAt(i, 1)));
				DentistProfileForm.lastNameField.setText(getStringValue(up.getValueAt(i, 2)));
				DentistProfileForm.contactNumberField.setText(getStringValue(up.getValueAt(i, 3)));
				DentistProfileForm.emailField.setText(getStringValue(up.getValueAt(i, 4)));

			}

		});
		final TableRowSorter<TableModel> sorter = new TableRowSorter<>(model);
		table.setRowSorter(sorter);

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(85, 100, 600, 450);
		add(scrollPane);

		initializeButtons();
	}

	private String getStringValue(Object value) {
		return (value != null) ? value.toString() : "";
	}


	@Override
	protected void paintComponent(Graphics g) {
		// Draw the panel with rounded corners
		Graphics2D graphics = (Graphics2D) g.create();
		setGraphicsRenderingHints(graphics);

		// White background with rounded corners
		graphics.setColor(new Color(255, 255, 255));
		graphics.fillRoundRect(0, 0, width, height, 20, 20);

		// Background image
		background.paintIcon(this, g, 0, 0);
	}

	// Set up graphics rendering hints
	private void setGraphicsRenderingHints(Graphics2D graphics) {
		graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		graphics.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
	    }
}