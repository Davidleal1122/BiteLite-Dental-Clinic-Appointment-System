package connectDatabase;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import screens.PanelAppointments;
import screens.PanelDentistRecord;
import screens.PanelPatientRecord;
import screens.PanelTreatment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;

public class DatabaseConnection {

	public Connection connect;
	public String url = "jdbc:mysql://localhost:3306/dentalclinicappointmentsystem";
	public String user = "root";
	public String password = "2203182809Leal";
	private Statement stmt;
	public PreparedStatement pst;

	private ResultSet rs;
	private ResultSetMetaData rsmd;

	public void connect() throws ClassNotFoundException, SQLException {
		// Register the driver
		Class.forName("com.mysql.cj.jdbc.Driver");

		// Establish the connection
		connect = DriverManager.getConnection(url, user, password);
		System.out.println("Connection created");
	}
	public void deleteFunctionForPatient(JTable table) {
		DefaultTableModel up = (DefaultTableModel) table.getModel();
		int i = table.getSelectedRow();
		
		
		if (i != -1) {//check if a row is selected
			try {
				int id = Integer.parseInt(up.getValueAt(i, 0).toString());
				int deleteRecord = JOptionPane.showConfirmDialog(null, "Do you want to delete this record?",
						"Warning", JOptionPane.YES_NO_OPTION);
				
				if (deleteRecord == JOptionPane.YES_OPTION) {
					try {
						Class.forName("com.mysql.cj.jdbc.Driver");
						connect = DriverManager.getConnection(url, user, password);

						pst = connect.prepareStatement("delete from patient where patient_id =?");
						pst.setInt(1, id);
						pst.executeUpdate();

						JOptionPane.showMessageDialog(null, "Patient record deleted");
						refreshPatiendTable();
					} catch (ClassNotFoundException | SQLException ex) {
						ex.printStackTrace(); 
					} finally {
						try {
							if (connect != null) {
								connect.close();
							}
						} catch (SQLException ex) {
							ex.printStackTrace();
						}
					}
				}
			} catch (NumberFormatException ex) {
				ex.printStackTrace(); 
			}
		}
	}
	
	public void deleteFunctionForTreatment(JTable table) {
		DefaultTableModel up = (DefaultTableModel) table.getModel();
		int i = table.getSelectedRow();

		if (i != -1) {
			try {
				int id = Integer.parseInt(up.getValueAt(i, 0).toString());
				int deleteRecord = JOptionPane.showConfirmDialog(null, "Do you want to delete this record?",
						"Warning", JOptionPane.YES_NO_OPTION);

				if (deleteRecord == JOptionPane.YES_OPTION) {
					try {
						Class.forName("com.mysql.cj.jdbc.Driver");
						connect = DriverManager.getConnection(url, user, password);

						pst = connect.prepareStatement("delete from treatment where treatment_id =?");
						pst.setInt(1, id);
						pst.executeUpdate();

						JOptionPane.showMessageDialog(null, "Treatment record deleted");
						refreshTreatmentTable();
					} catch (ClassNotFoundException | SQLException ex) {
						ex.printStackTrace();
					} finally {
						try {
							if (connect != null) {
								connect.close();
							}
						} catch (SQLException ex) {
							ex.printStackTrace(); 
						}
					}
				}
			} catch (NumberFormatException ex) {
				ex.printStackTrace(); 
			}
		}
	}
	
	public void deleteFunctionForDentist(JTable table) {
		DefaultTableModel up = (DefaultTableModel) table.getModel();
		int i = table.getSelectedRow();

		if (i != -1) {
			try {
				int id = Integer.parseInt(up.getValueAt(i, 0).toString());
				int deleteRecord = JOptionPane.showConfirmDialog(null, "Do you want to delete this record?",
						"Warning", JOptionPane.YES_NO_OPTION);

				if (deleteRecord == JOptionPane.YES_OPTION) {
					try {
						Class.forName("com.mysql.cj.jdbc.Driver");
						connect = DriverManager.getConnection(url, user, password);

						pst = connect.prepareStatement("delete from dentist where dentist_id =?");
						pst.setInt(1, id);
						pst.executeUpdate();

						JOptionPane.showMessageDialog(null, "Dentist record deleted");
						refreshDentistTable();
					} catch (ClassNotFoundException | SQLException ex) {
						ex.printStackTrace(); 
					} finally {
						try {
							if (connect != null) {
								connect.close();
							}
						} catch (SQLException ex) {
							ex.printStackTrace(); 
						}
					}
				}
			} catch (NumberFormatException ex) {
				ex.printStackTrace(); 
			}
		}
	}
	
	public void deleteFunctionForAppointment(JTable table) {
		
			DefaultTableModel up = (DefaultTableModel) table.getModel();
			int i = table.getSelectedRow();

			if (i != -1) {
				try {
					int id = Integer.parseInt(up.getValueAt(i, 0).toString());
					int deleteRecord = JOptionPane.showConfirmDialog(null, "Do you want to delete this record?",
							"Warning", JOptionPane.YES_NO_OPTION);

					if (deleteRecord == JOptionPane.YES_OPTION) {
						try {
							Class.forName("com.mysql.cj.jdbc.Driver");
							connect = DriverManager.getConnection(url, user, password);

							pst = connect.prepareStatement("delete from appointment where appointment_id =?");
							pst.setInt(1, id);
							pst.executeUpdate();

							JOptionPane.showMessageDialog(null, "Treatment record deleted");
							refreshAppointmentTable();
						} catch (ClassNotFoundException | SQLException ex) {
							ex.printStackTrace(); 
						} finally {
							try {
								if (connect != null) {
									connect.close();
								}
							} catch (SQLException ex) {
								ex.printStackTrace();
							}
						}
					}
				} catch (NumberFormatException ex) {
					ex.printStackTrace();
				}
			}
		}
	public void refreshPatiendTable() {

		// Get the existing table model
		DefaultTableModel patientTableUpdate = (DefaultTableModel) PanelPatientRecord.table.getModel();

		// Clear existing rows
		patientTableUpdate.setRowCount(0);

		// Fetch data from the database and add it to the model
		selectingTableFromPatient(patientTableUpdate);

		PanelPatientRecord.table.getColumnModel().getColumn(0).setPreferredWidth(100);
		PanelPatientRecord.table.getColumnModel().getColumn(1).setPreferredWidth(100);
		PanelPatientRecord.table.getColumnModel().getColumn(2).setPreferredWidth(100);
		PanelPatientRecord.table.getColumnModel().getColumn(3).setPreferredWidth(200);
		PanelPatientRecord.table.getColumnModel().getColumn(4).setPreferredWidth(100);
		PanelPatientRecord.table.getColumnModel().getColumn(5).setPreferredWidth(100);
		// Notify the table that the data has changed
		patientTableUpdate.fireTableDataChanged();

	}
	
	public void refreshTreatmentTable() {
		
	    // Get the existing table model
	    DefaultTableModel treatmentTableUpdate = (DefaultTableModel) PanelTreatment.table.getModel();

	    // Clear existing rows
	    treatmentTableUpdate.setRowCount(0);

	    // Fetch data from the database and add it to the model
	    selectingTableFromTreatment(treatmentTableUpdate);

	    PanelTreatment.table.getColumnModel().getColumn(0).setPreferredWidth(200);
	    PanelTreatment.table.getColumnModel().getColumn(1).setPreferredWidth(200);
	    PanelTreatment.table.getColumnModel().getColumn(2).setPreferredWidth(200);
	    // Notify the table that the data has changed
	    treatmentTableUpdate.fireTableDataChanged();


	}

	public void refreshAppointmentTable() {
	    // Get the existing table model
	    DefaultTableModel appointmentTableModel = (DefaultTableModel) PanelAppointments.table.getModel();

	    // Clear existing rows
	    appointmentTableModel.setRowCount(0);

	    // Fetch data from the database and add it to the model
	    selectingTableFromAppointment(appointmentTableModel);
	    PanelAppointments.table.getColumnModel().getColumn(0).setPreferredWidth(100);
	    PanelAppointments.table.getColumnModel().getColumn(1).setPreferredWidth(100);
	    PanelAppointments.table.getColumnModel().getColumn(2).setPreferredWidth(100);
	    PanelAppointments.table.getColumnModel().getColumn(3).setPreferredWidth(100);
	    PanelAppointments.table.getColumnModel().getColumn(4).setPreferredWidth(150);
	    PanelAppointments.table.getColumnModel().getColumn(5).setPreferredWidth(150);
	    PanelAppointments.table.getColumnModel().getColumn(6).setPreferredWidth(100);
	    PanelAppointments.table.getColumnModel().getColumn(7).setPreferredWidth(150);	
		PanelAppointments.table.getColumnModel().getColumn(8).setPreferredWidth(150);
		PanelAppointments.table.getColumnModel().getColumn(9).setPreferredWidth(100);
		PanelAppointments.table.getColumnModel().getColumn(10).setPreferredWidth(150);
	    // Notify the table that the data has changed
	    appointmentTableModel.fireTableDataChanged();
	    
	}

	public void refreshDentistTable() {
		
	    // Get the existing table model
	    DefaultTableModel dentistTableUpdate = (DefaultTableModel) PanelDentistRecord.table.getModel();

	    // Clear existing rows
	    dentistTableUpdate.setRowCount(0);

	    // Fetch data from the database and add it to the model
	    selectingTableFromDentist(dentistTableUpdate);

	    PanelDentistRecord.table.getColumnModel().getColumn(0).setPreferredWidth(100);
	    PanelDentistRecord.table.getColumnModel().getColumn(1).setPreferredWidth(200);
	    PanelDentistRecord.table.getColumnModel().getColumn(2).setPreferredWidth(200);
	    PanelDentistRecord.table.getColumnModel().getColumn(3).setPreferredWidth(200);
	    PanelDentistRecord.table.getColumnModel().getColumn(4).setPreferredWidth(200);
	    // Notify the table that the data has changed
	    dentistTableUpdate.fireTableDataChanged();


	}

	//Responsible for counting total appointment in database
	public int getCountOfAppointment1() {
	    String query = "SELECT COUNT(appointment_id) FROM appointment";

	    try (Connection connection = DriverManager.getConnection(url, user, password);
	         PreparedStatement preparedStatement = connection.prepareStatement(query);
	         ResultSet resultSet = preparedStatement.executeQuery()) {

	        if (resultSet.next()) {
	            int count = resultSet.getInt(1);
	            return count;
	        }
	    } catch (SQLException e) {
	        e.printStackTrace(); 
	    }

	    return -1;
	}
	//Responsible for counting total patients in database
	public int getCountOfPatients1() {
	    String query = "SELECT COUNT(patient_id) FROM patient";

	    try (Connection connection = DriverManager.getConnection(url, user, password);
	         PreparedStatement preparedStatement = connection.prepareStatement(query);
	         ResultSet resultSet = preparedStatement.executeQuery()) {

	        if (resultSet.next()) {
	            int count = resultSet.getInt(1);
	            return count;
	        }
	    } catch (SQLException e) {
	        e.printStackTrace(); 
	    }

	    return -1;
	}

	
	public void patientTableUpdate( JTable table) {
		 try (Connection connection = DriverManager.getConnection(url, user, password)) {
		        String query = "SELECT * FROM patient";
      
		        PreparedStatement pst = connection.prepareStatement(query);
	            try (ResultSet rs = pst.executeQuery()) {
		                ResultSetMetaData rsd = rs.getMetaData();
		                int c = rsd.getColumnCount();
		                DefaultTableModel d = (DefaultTableModel) table.getModel();
		                d.setRowCount(0);

		                while (rs.next()) {
		                    Vector<String> v2 = new Vector<String>();
		                    for (int i = 1; i <= c; i++) {
		                        v2.add(rs.getString("patient_id"));
		                        v2.add(rs.getString("first_name"));
		                        v2.add(rs.getString("last_name"));
		                        v2.add(rs.getString("address"));
		                        v2.add(rs.getString("contact_number"));
		                        v2.add(rs.getString("email"));
		                    }
		                    d.addRow(v2);
		                }
		            
	            }
		    } catch (SQLException ex) {
		        ex.printStackTrace();
		    }
		}
	public void table_SearchTreatment(String search, JTable table) {
	    try (Connection connection = DriverManager.getConnection(url, user, password)) {
	        String query = "SELECT * FROM treatment";

	        if (!search.isEmpty()) {
	            query += " WHERE treatment_name LIKE ?";
	        }

	        try (PreparedStatement pst = connection.prepareStatement(query)) {
	            if (!search.isEmpty()) {
	                pst.setString(1, "%" + search + "%");
	            }

	            try (ResultSet rs = pst.executeQuery()) {
	                ResultSetMetaData rsd = rs.getMetaData();
	                int c = rsd.getColumnCount();
	                DefaultTableModel d = (DefaultTableModel) table.getModel();
	                d.setRowCount(0);

	                while (rs.next()) {
	                    Vector<String> v2 = new Vector<String>();
	                    for (int i = 1; i <= c; i++) {
	                        v2.add(rs.getString("treatment_id"));
	                        v2.add(rs.getString("treatment_name"));
	                        v2.add(rs.getString("treatment_cost"));
	                      
	                    }
	                    d.addRow(v2);
	                }
	            }
	        }
	    } catch (SQLException ex) {
	        ex.printStackTrace();
	    }
	}public void table_SearchDentist(String search, JTable table) {
	    try (Connection connection = DriverManager.getConnection(url, user, password)) {
	        String query = "SELECT * FROM dentist";

	        if (!search.isEmpty()) {
	            query += " WHERE first_name LIKE ?";
	        }

	        try (PreparedStatement pst = connection.prepareStatement(query)) {
	            if (!search.isEmpty()) {
	                pst.setString(1, "%" + search + "%");
	            }

	            try (ResultSet rs = pst.executeQuery()) {
	                ResultSetMetaData rsd = rs.getMetaData();
	                int c = rsd.getColumnCount();
	                DefaultTableModel d = (DefaultTableModel) table.getModel();
	                d.setRowCount(0);

	                while (rs.next()) {
	                    Vector<String> v2 = new Vector<String>();
	                    for (int i = 1; i <= c; i++) {
	                        v2.add(rs.getString("dentist_id"));
	                        v2.add(rs.getString("first_name"));
	                        v2.add(rs.getString("last_name"));
	                        v2.add(rs.getString("contact_number"));
	                        v2.add(rs.getString("email"));
	                    }
	                    d.addRow(v2);
	                }
	            }
	        }
	    } catch (SQLException ex) {
	        ex.printStackTrace();
	    }
	}
	public void table_Search(String search, JTable table) {
	    try (Connection connection = DriverManager.getConnection(url, user, password)) {
	        String query = "SELECT * FROM patient";

	        if (!search.isEmpty()) {
	            query += " WHERE first_name LIKE ?";
	        }

	        try (PreparedStatement pst = connection.prepareStatement(query)) {
	            if (!search.isEmpty()) {
	                pst.setString(1, "%" + search + "%");
	            }

	            try (ResultSet rs = pst.executeQuery()) {
	                ResultSetMetaData rsd = rs.getMetaData();
	                int c = rsd.getColumnCount();
	                DefaultTableModel d = (DefaultTableModel) table.getModel();
	                d.setRowCount(0);

	                while (rs.next()) {
	                    Vector<String> v2 = new Vector<String>();
	                    for (int i = 1; i <= c; i++) {
	                        v2.add(rs.getString("patient_id"));
	                        v2.add(rs.getString("first_name"));
	                        v2.add(rs.getString("last_name"));
	                        v2.add(rs.getString("address"));
	                        v2.add(rs.getString("contact_number"));
	                        v2.add(rs.getString("email"));
	                    }
	                    d.addRow(v2);
	                }
	            }
	        }
	    } catch (SQLException ex) {
	        ex.printStackTrace();
	    }
	}
	public void insertValuesToPatient(String firstName, String lastName, String address, String contactNumber,
			String email) {
		try (Connection connection = DriverManager.getConnection(url, user, password);
				PreparedStatement statement = connection.prepareStatement(
						"INSERT INTO dentist(first_name, last_name, address, contact_number, email) VALUES (?, ?, ?, ?, ?)")) {

			statement.setString(1, firstName);
			statement.setString(2, lastName);
			statement.setString(3, address);
			statement.setString(4, contactNumber);
			statement.setString(5, email);

			statement.execute();

			JOptionPane.showMessageDialog(null, "Successfully added new patient");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void insertValuesToDentist(String firstName, String lastName, String contactNumber, String email) {
		try (Connection connection = DriverManager.getConnection(url, user, password);
				PreparedStatement statement = connection.prepareStatement(
						"INSERT INTO dentist(first_name, last_name, contact_number, email) VALUES (?, ?, ?, ?)")) {

			statement.setString(1, firstName);
			statement.setString(2, lastName);
			statement.setString(3, contactNumber);
			statement.setString(4, email);

			statement.execute();

			JOptionPane.showMessageDialog(null, "Successfully added new dentist");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void insertValuesToTreatment(String treatmentName, String treatmentCost) {
		try (Connection connection = DriverManager.getConnection(url, user, password);
				PreparedStatement statement = connection
						.prepareStatement("INSERT INTO treatment(treatment_name, treatment_cost) VALUES (?, ?)")) {

			statement.setString(1, treatmentName);
			statement.setString(2, treatmentCost);

			statement.execute();

			JOptionPane.showMessageDialog(null, "Successfully added new treatment");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void insertValuesToAppointmentAndUpdateTable(String appointmentDate, String appointmentTime,
	        String choosePatient, String chooseDentist, String choosetreatment, DefaultTableModel tableModel) {
	    try (Connection connection = DriverManager.getConnection(url, user, password);
	            PreparedStatement statement = connection.prepareStatement(
	                    "INSERT INTO appointment(appointment_date, appointment_time, patient_id, dentist_id, treatment_id) "
	                            + "VALUES (?, ?, (SELECT patient_id FROM patient WHERE CONCAT(first_name, ' ', last_name) = ? LIMIT 1), "
	                            + "(SELECT dentist_id FROM dentist WHERE CONCAT(first_name, ' ', last_name) = ? LIMIT 1), "
	                            + "(SELECT treatment_id FROM treatment WHERE treatment_name = ? LIMIT 1))")) {

	        // Convert appointmentDate to java.sql.Date
	        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	        Date parsedDate = sdf.parse(appointmentDate);
	        java.sql.Date sqlAppointmentDate = new java.sql.Date(parsedDate.getTime());

	        statement.setDate(1, sqlAppointmentDate);
	        statement.setString(2, appointmentTime);
	        statement.setString(3, choosePatient);
	        statement.setString(4, chooseDentist);
	        statement.setString(5, choosetreatment);

	        statement.execute();

	        // After inserting, update the JTable
	        selectingTableFromAppointment(tableModel);

	        JOptionPane.showMessageDialog(null, "Successfully added new appointment");

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	
	public void selectingTableFromAppointment(DefaultTableModel tableModel) {
        String query = "SELECT \r\n"
                + "    appointment.appointment_id, \r\n"
                + "    appointment.appointment_date, \r\n"
                + "    appointment.appointment_time, \r\n"
                + "    appointment.patient_id,  -- Specify the table alias\r\n"
                + "    patient.first_name, \r\n"
                + "    patient.last_name, \r\n"
                + "    appointment.dentist_id, \r\n"
                + "    dentist.first_name, \r\n"
                + "    dentist.last_name, \r\n"
                + "    appointment.treatment_id, \r\n"
                + "    treatment.treatment_name\r\n"
                + "FROM \r\n"
                + "    appointment\r\n"
                + "INNER JOIN \r\n"
                + "    patient ON appointment.patient_id = patient.patient_id\r\n"
                + "INNER JOIN \r\n"
                + "    dentist ON appointment.dentist_id = dentist.dentist_id\r\n"
                + "INNER JOIN \r\n"
                + "    treatment ON appointment.treatment_id = treatment.treatment_id;";
        try {
            connect = DriverManager.getConnection(url, user, password);
            stmt = connect.createStatement();
            rs = stmt.executeQuery(query);
            rsmd = rs.getMetaData();

            int cols = rsmd.getColumnCount();
            String[] colName = new String[cols];
            for (int i = 0; i < cols; i++)
                colName[i] = rsmd.getColumnName(i + 1);
            tableModel.setColumnIdentifiers(colName);
            tableModel.setRowCount(0);

            while (rs.next()) {
                String appointment_id = rs.getString(1);
                String appointment_date = rs.getString(2);
                String appointment_time = rs.getString(3);
                String patient_id = rs.getString(4);
                String patient_first_name = rs.getString(5);
                String patient_last_name = rs.getString(6);
                String dentist_id = rs.getString(7);
                String dentist_first_name = rs.getString(8);
                String dentist_last_name = rs.getString(9);
                String treatment_id = rs.getString(10);
                String treatment_name = rs.getString(11);

                String[] row = {appointment_id, appointment_date, appointment_time, patient_id,
                        patient_first_name, patient_last_name, dentist_id, dentist_first_name,
                        dentist_last_name, treatment_id, treatment_name};
                tableModel.addRow(row);
            }
            stmt.close();
            connect.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


	
	

	public void selectingTableFromPatient(DefaultTableModel tableModel) {
		String query = "SELECT * FROM patient ";

		try {
			connect = DriverManager.getConnection(url, user, password);
			stmt = connect.createStatement();
			rs = stmt.executeQuery(query);
			rsmd = rs.getMetaData();

			int cols = rsmd.getColumnCount();
			String[] colName = new String[cols];
			for (int i = 0; i < cols; i++)
				colName[i] = rsmd.getColumnName(i + 1);
			tableModel.setColumnIdentifiers(colName);
			String patient_id, first_name, last_name, address, contact_number, email;
			tableModel.setRowCount(0);

			while (rs.next()) {
				patient_id = rs.getString(1);
				first_name = rs.getString(2);
				last_name = rs.getString(3);
				address = rs.getString(4);
				contact_number = rs.getString(5);
				email = rs.getString(6);

				String[] row = { patient_id, first_name, last_name, address, contact_number, email };
				tableModel.addRow(row);
			}
			stmt.close();
			connect.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void selectingTableFromDentist(DefaultTableModel tableModel) {
		String query = "SELECT * FROM dentist ";

		try {
			connect = DriverManager.getConnection(url, user, password);
			stmt = connect.createStatement();
			rs = stmt.executeQuery(query);
			rsmd = rs.getMetaData();

			int cols = rsmd.getColumnCount();
			String[] colName = new String[cols];
			for (int i = 0; i < cols; i++)
				colName[i] = rsmd.getColumnName(i + 1);
			tableModel.setColumnIdentifiers(colName);
			String dentist_id, first_name, last_name, contact_number, email;
			tableModel.setRowCount(0);

			while (rs.next()) {
				dentist_id = rs.getString(1);
				first_name = rs.getString(2);
				last_name = rs.getString(3);
				contact_number = rs.getString(4);
				email = rs.getString(5);
				String[] row = {dentist_id, first_name, last_name, contact_number, email };
				tableModel.addRow(row);
			}
			stmt.close();
			connect.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void selectingTableFromTreatment(DefaultTableModel tableModel) {
	    String query = "SELECT * FROM treatment ";

	    try {
	        connect = DriverManager.getConnection(url, user, password);
	        stmt = connect.createStatement();
	        rs = stmt.executeQuery(query);
	        rsmd = rs.getMetaData();

	        int cols = rsmd.getColumnCount();
	        String[] colName = new String[cols];
	        for (int i = 0; i < cols; i++)
	            colName[i] = rsmd.getColumnName(i + 1);
	        
	        // Add this code to initialize the table columns
	        for (String col : colName) {
	            tableModel.addColumn(col);}
	        tableModel.setColumnIdentifiers(colName);
	        String treatment_id, treatment_name, treatment_cost;
	        tableModel.setRowCount(0);

	        while (rs.next()) {
	            treatment_id = rs.getString(1);
	            treatment_name = rs.getString(2);
	            treatment_cost = rs.getString(3);

	            String[] row = { treatment_id, treatment_name, treatment_cost };
	            tableModel.addRow(row);
	        }
	        stmt.close();
	        connect.close();

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}


	public List<String> getPatientsForDropdown() {
		List<String> patientsOption = new ArrayList<>();

		String query = "SELECT first_name, last_name FROM patient ";

		try (Connection connection = DriverManager.getConnection(url, user, password);
				PreparedStatement statement = connection.prepareStatement(query);
				ResultSet resultSet = statement.executeQuery()) {

			while (resultSet.next()) {
				String firstName = resultSet.getString("first_name");
				String lastName = resultSet.getString("last_name");
				String fullName = firstName + " " + lastName;
				patientsOption.add(fullName);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return patientsOption;
	}

	public List<String> getDentistForDropdown() {
		List<String> dentistOption = new ArrayList<>();

		String query = "SELECT first_name, last_name FROM dentist ";

		try (Connection connection = DriverManager.getConnection(url, user, password);
				PreparedStatement statement = connection.prepareStatement(query);
				ResultSet resultSet = statement.executeQuery()) {

			while (resultSet.next()) {
				String firstName = resultSet.getString("first_name");
				String lastName = resultSet.getString("last_name");
				String fullName = firstName + " " + lastName;
				dentistOption.add(fullName);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return dentistOption;
	}

	public List<String> getTreatmentForDropdown() {
		List<String> treatmentOption = new ArrayList<>();

		String query = "SELECT treatment_name FROM treatment ";

		try (Connection connection = DriverManager.getConnection(url, user, password);
				PreparedStatement statement = connection.prepareStatement(query);
				ResultSet resultSet = statement.executeQuery()) {

			while (resultSet.next()) {
				String treatmentName = resultSet.getString("treatment_name");

				treatmentOption.add(treatmentName);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return treatmentOption;
	}
	public int getCountOfAppointment() {
		// TODO Auto-generated method stub
		return 0;
	}
	public int getCountOfPatients() {
		// TODO Auto-generated method stub
		return 0;
	}
}