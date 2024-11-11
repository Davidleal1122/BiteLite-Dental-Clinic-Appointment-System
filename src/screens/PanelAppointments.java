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
import java.sql.Date;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import connectDatabase.DatabaseConnection;
import swingComponents.customizedButton.Button;
import swingComponents.customizedTextFields.CustomizedTextField;

public class PanelAppointments extends JPanel {

	ImageIcon background = new ImageIcon("src/image/dashboardIcons/BackgroundPanel.png");
	ImageIcon searchIcon = new ImageIcon("src/image/dashboardIcons/searchIcon.png");

	JPanel panel;
	int width = 765;
	int height = 650;

	CustomizedTextField searchField;
	public static JTable table;
	public static DefaultTableModel model;

	private static final Color BUTTON_COLOR_1 = new Color(164, 198, 193);
	private static final Color BUTTON_COLOR_2 = new Color(105, 121, 118);
	private static final Color BUTTON_COLOR_3 = new Color(141, 62, 62);

	public PanelAppointments() {

		setSize(width, height);
		initializeComponents();
		setLayout(new BorderLayout());
		
	

	}

	Color buttonColor1 = new Color(164, 198, 193);
	Button newButton = new Button(buttonColor1);

	Color buttonColor2 = new Color(105, 121, 118);
	Button editButton = new Button(buttonColor2);

	Color buttonColor3 = new Color(141, 62, 62);
	Button cancelButton = new Button(buttonColor3);

	Color buttonColor4 = new Color(139, 164, 160);
	Button refreshButton = new Button(buttonColor4);

	DatabaseConnection dbConnect = new DatabaseConnection();

	private void initializeComponents() {

		// search Icon
		JLabel searchIcons = new JLabel(searchIcon);
		searchIcons.setBounds(500, 30, 50, 50);

		// search Area
		searchField = new CustomizedTextField();
		searchField.setBounds(540, 43, 150, 30);
		searchField.setHint("Search");
		searchField.setColor(new Color(0, 0, 0, 0));

		add(searchIcons);
		add(searchField);

		// Create the JTable with the DefaultTableModel
		
		
		model = new DefaultTableModel();
		dbConnect.selectingTableFromAppointment(model); // Use the correct method here

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
		table.getColumnModel().getColumn(1).setPreferredWidth(100);
		table.getColumnModel().getColumn(2).setPreferredWidth(100);
		table.getColumnModel().getColumn(3).setPreferredWidth(100);
		table.getColumnModel().getColumn(4).setPreferredWidth(150);
		table.getColumnModel().getColumn(5).setPreferredWidth(150);
		table.getColumnModel().getColumn(6).setPreferredWidth(100);
		table.getColumnModel().getColumn(7).setPreferredWidth(150);
		table.getColumnModel().getColumn(8).setPreferredWidth(150);
		table.getColumnModel().getColumn(9).setPreferredWidth(100);
		table.getColumnModel().getColumn(10).setPreferredWidth(150);

		final TableRowSorter<TableModel> sorter = new TableRowSorter<>(model);
		table.setRowSorter(sorter);

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(85, 100, 600, 450);
		add(scrollPane);

		initializeButtons();
		buttonFunction();
	}

	private void initializeButtons() {

		refreshButton = new Button(BUTTON_COLOR_1);
		refreshButton.setText("REFRESH");
		refreshButton.setBounds(85, 50, 80, 30);

		newButton = new Button(BUTTON_COLOR_1);
		newButton.setText("NEW");
		newButton.setBounds(190, 575, 100, 40);

		editButton = new Button(BUTTON_COLOR_2);
		editButton.setText("EDIT");
		editButton.setBounds(325, 575, 100, 40);

		cancelButton = new Button(BUTTON_COLOR_3);
		cancelButton.setText("DELETE");
		cancelButton.setBounds(460, 575, 100, 40);
		cancelButton.addActionListener(new ActionListener() { 

        	public void actionPerformed(ActionEvent arg0) { 

        	 dbConnect.deleteFunctionForAppointment(table);; 

        	}}); 
		add(refreshButton);
		add(newButton);
		add(editButton);
		add(cancelButton);
		
		
	}

	public void paintComponent(Graphics h) {
		Graphics2D graphics1 = (Graphics2D) h.create();
		graphics1.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		graphics1.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

		// White
		graphics1.setColor(new Color(255, 255, 255));
		graphics1.fillRoundRect(0, 0, 765, height, 20, 20);

		graphics1.setColor(new Color(255, 255, 255));
		graphics1.fillRoundRect(0, 0, 765, height, 20, 20);

		// Background
		background.paintIcon(this, h, 0, 0);
	}

	 public void buttonFunction() {
	        refreshButton.addMouseListener(new MouseAdapter() {
	            @Override
	            public void mouseClicked(MouseEvent me) {
	                //dbConnect.selectingTableFromAppointment(model);
	               
	                dbConnect.selectingTableFromAppointment(model);
	                table.setModel(model);

	                
	                table.getColumnModel().getColumn(0).setPreferredWidth(100);
	        		table.getColumnModel().getColumn(1).setPreferredWidth(100);
	        		table.getColumnModel().getColumn(2).setPreferredWidth(100);
	        		table.getColumnModel().getColumn(3).setPreferredWidth(100);
	        		table.getColumnModel().getColumn(4).setPreferredWidth(150);
	        		table.getColumnModel().getColumn(5).setPreferredWidth(150);
	        		table.getColumnModel().getColumn(6).setPreferredWidth(100);
	        		table.getColumnModel().getColumn(7).setPreferredWidth(150);
	        		table.getColumnModel().getColumn(8).setPreferredWidth(150);
	        		table.getColumnModel().getColumn(9).setPreferredWidth(100);
	        		table.getColumnModel().getColumn(10).setPreferredWidth(150);
	            }
	        });
	    
	}

}
