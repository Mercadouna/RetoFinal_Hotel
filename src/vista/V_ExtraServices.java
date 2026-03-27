package vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import controlador.LoginControlador;
import modelo.Aux_booking;
import modelo.Booking;
import modelo.Customer;
import modelo.ExtraService;
import modelo.Room;

import java.awt.Color;

public class V_ExtraServices extends JDialog implements ActionListener {
	private static final long serialVersionUID = 1L;
	private JTable Tabla_Cust;
	private JTextField textField_BookingId;
	private JTextField textField_Extra_Service_ID;
	private JPanel titulo;
	private JButton exit;
	private JLabel lblNewLabel;
	private JPanel subtitulo;
	private JLabel lblNewLabel_1_1;
	private JLabel lblNewLabel_1_2;
	private JLabel lblNewLabel_1_3;
	private JLabel lblNewLabel_1_4;
	private JLabel lblNewLabel_1_5;
	private JLabel lblNewLabel_1_6;
	private JLabel lblNewLabel_1_7;
	private JLabel lblNewLabel_1_8;
	private JPanel tabla;
	private JPanel info;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2_1;
	private JLabel lblNewLabel_2_3;
	private JButton btn_Search;
	private JButton btnAdd;
	private LoginControlador cont;
	private JTable table_Extra_Services;
	private JButton btnDelete; 

	private void cargarTabla() {
		DefaultTableModel model = new DefaultTableModel() {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		model.addColumn("ID");
		model.addColumn("Customer");
		model.addColumn("Room / ID");
		model.addColumn("Type");
		model.addColumn("Check-in");
		model.addColumn("Check-out");
		model.addColumn("Paid");

		ArrayList<Aux_booking> bookings = cont.viewBookings();
		for (Aux_booking ab : bookings) {
			Customer c = ab.getCustomer();
			Room r = ab.getRoom();
			Booking b = ab.getBooking();

			Object[] row = new Object[7];
			row[0] = b.getIdBooking();
			row[1] = c.getNameCostumer() + " " + c.getSurname() + " - " + c.getIdCustomer();
			row[2] = r.getRoomNumber() + " - " + r.getIdRoom();
			row[3] = r.getTypeRoom();
			row[4] = b.getCheckIn();
			row[5] = b.getCheckOut();
			row[6] = b.isPaid();
			model.addRow(row);
		}
		Tabla_Cust.setModel(model);
	}

	private void cargarTablaExtraServices(int idBooking) {
		DefaultTableModel model = new DefaultTableModel() {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		model.addColumn("ID");
		model.addColumn("Name");
		model.addColumn("Price");

		ArrayList<ExtraService> extraServices = cont.viewBookingExtraServices(idBooking);
		for (ExtraService extraService : extraServices) {
			Object[] row = new Object[3];
			row[0] = extraService.getIdService();
			row[1] = extraService.getNameService();
			row[2] = extraService.getPrice();
			model.addRow(row);
		}
		table_Extra_Services.setModel(model);
	}

	/**
	 * Create the dialog.
	 */
	public V_ExtraServices(LoginControlador cont) {
		this.cont = cont;
		setBounds(100, 100, 1500, 850);

		Font normalFont = new Font("Tahoma", Font.PLAIN, 14);
		Font boldFont = new Font("Tahoma", Font.BOLD, 16);
		getContentPane().setLayout(null);

		{
			titulo = new JPanel();
			titulo.setBounds(10, 10, 1464, 40);
			getContentPane().add(titulo);
			titulo.setLayout(null);

			exit = new JButton("Exit");
			exit.addActionListener(this);
			exit.setFont(normalFont);
			exit.setBounds(1354, 0, 100, 35);
			titulo.add(exit);

			lblNewLabel = new JLabel("Extra Service Management");
			lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 18));
			lblNewLabel.setBounds(10, 5, 250, 25);
			titulo.add(lblNewLabel);
		}
		{
			subtitulo = new JPanel();
			subtitulo.setBounds(10, 60, 1464, 50);
			getContentPane().add(subtitulo);
			subtitulo.setLayout(null);

			lblNewLabel_1_8 = new JLabel("Service Catalog:");
			lblNewLabel_1_8.setFont(boldFont);
			lblNewLabel_1_8.setBounds(10, 15, 150, 20);
			subtitulo.add(lblNewLabel_1_8);

			lblNewLabel_1_1 = new JLabel("Breakfast");
			lblNewLabel_1_1.setFont(normalFont);
			lblNewLabel_1_1.setBounds(225, 15, 100, 20);
			subtitulo.add(lblNewLabel_1_1);

			lblNewLabel_1_2 = new JLabel("Media Pension");
			lblNewLabel_1_2.setFont(normalFont);
			lblNewLabel_1_2.setBounds(380, 15, 110, 20);
			subtitulo.add(lblNewLabel_1_2);

			lblNewLabel_1_3 = new JLabel("Parking");
			lblNewLabel_1_3.setFont(normalFont);
			lblNewLabel_1_3.setBounds(566, 15, 100, 20);
			subtitulo.add(lblNewLabel_1_3);

			lblNewLabel_1_4 = new JLabel("Airport Transport");
			lblNewLabel_1_4.setFont(normalFont);
			lblNewLabel_1_4.setBounds(726, 15, 120, 20);
			subtitulo.add(lblNewLabel_1_4);

			lblNewLabel_1_5 = new JLabel("Spa & Wellness");
			lblNewLabel_1_5.setFont(normalFont);
			lblNewLabel_1_5.setBounds(951, 15, 110, 20);
			subtitulo.add(lblNewLabel_1_5);

			lblNewLabel_1_6 = new JLabel("Room Service");
			lblNewLabel_1_6.setFont(normalFont);
			lblNewLabel_1_6.setBounds(1143, 15, 110, 20);
			subtitulo.add(lblNewLabel_1_6);

			lblNewLabel_1_7 = new JLabel("Baby Cradle");
			lblNewLabel_1_7.setFont(normalFont);
			lblNewLabel_1_7.setBounds(1325, 15, 100, 20);
			subtitulo.add(lblNewLabel_1_7);
		}
		{
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(10, 120, 1056, 460);
			getContentPane().add(scrollPane);

			Tabla_Cust = new JTable();
			Tabla_Cust.setFont(normalFont);
			Tabla_Cust.setRowHeight(30);
			Tabla_Cust.getTableHeader().setFont(boldFont);
			scrollPane.setViewportView(Tabla_Cust);
		}
		{
			info = new JPanel();
			info.setBounds(10, 600, 1464, 200);
			getContentPane().add(info);
			info.setLayout(null);

			lblNewLabel_1 = new JLabel("Extra Service:");
			lblNewLabel_1.setFont(boldFont);
			lblNewLabel_1.setBounds(10, 5, 120, 25);
			info.add(lblNewLabel_1);

			lblNewLabel_2_1 = new JLabel("Booking ID:");
			lblNewLabel_2_1.setFont(normalFont);
			lblNewLabel_2_1.setBounds(20, 35, 100, 20);
			info.add(lblNewLabel_2_1);

			textField_BookingId = new JTextField();
			textField_BookingId.setFont(normalFont);
			textField_BookingId.setColumns(10);
			textField_BookingId.setBounds(20, 60, 180, 30);
			info.add(textField_BookingId);

			lblNewLabel_2_3 = new JLabel("Extra Service ID:");
			lblNewLabel_2_3.setFont(normalFont);
			lblNewLabel_2_3.setBounds(751, 35, 151, 20);
			info.add(lblNewLabel_2_3);

			textField_Extra_Service_ID = new JTextField();
			textField_Extra_Service_ID.setFont(normalFont);
			textField_Extra_Service_ID.setColumns(10);
			textField_Extra_Service_ID.setBounds(751, 60, 180, 30);
			info.add(textField_Extra_Service_ID);

			btn_Search = new JButton("Search");
			btn_Search.setFont(normalFont);
			btn_Search.addActionListener(this);
			btn_Search.setBounds(235, 57, 120, 35);
			info.add(btn_Search);

			btnAdd = new JButton("Add []");
			btnAdd.setFont(normalFont);
			btnAdd.setBounds(971, 57, 120, 35);
			info.add(btnAdd);
			
			btnDelete = new JButton("Delete []");
			btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 14));
			btnDelete.setBounds(1134, 55, 120, 35);
			info.add(btnDelete);
			btnAdd.addActionListener(this);
		}

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(1076, 121, 398, 460);
		getContentPane().add(scrollPane);

		table_Extra_Services = new JTable();
		scrollPane.setViewportView(table_Extra_Services);

		cargarTabla();

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == exit) {
			V_Menu m = new V_Menu(cont);
			m.setVisible(true);
			this.dispose();
		}

		if (e.getSource() == btn_Search) {
			searchBooking();
		}

		if (e.getSource() == btnAdd) {
			
		}
		
		if (e.getSource() == btnDelete) {
			
		}
	}

	private void searchBooking() {
		int bookingId;
		if (textField_BookingId.getText().isEmpty()) {
			JOptionPane.showMessageDialog(this, "Please fill in all the fields.");
		} else {
			try {
				bookingId = Integer.parseInt(textField_BookingId.getText());
				if (cont.checkBookingExists(bookingId)) {

					JOptionPane.showMessageDialog(this, "Booking found.");
					cargarTablaExtraServices(bookingId);
				} else {
					JOptionPane.showMessageDialog(this, "Booking not found.");
				}
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(this, "Please enter a valid number.");
			}

		}
	}
}
