package vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.swing.JButton;
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
import modelo.ImplementacionBD;
import modelo.Room;
import javax.swing.JComboBox;

public class V_Booking extends JDialog implements ActionListener {
	private static final long serialVersionUID = 1L;
	private JTable Tabla_Cust;
	private JTextField textField_room_id;
	private JTextField textField_client_id;
	private JTextField textField_check_in;
	private JTextField textField_check_out;
	private JPanel titulo;
	private JButton exit;
	private JLabel lblNewLabel;
	private JPanel subtitulo;
	private JLabel lblNewLabel_1;
	private JButton Buscar;
	private JLabel lblNewLabel_2;
	private JComboBox comboBox;
	private JComboBox comboBox_paid;
	private JComboBox comboBox1;
	private JPanel table;
	private JPanel info;
	private JLabel lblNewLabel_2_1_1;
	private JLabel lblNewLabel_2_2_1;
	private JButton btnAadir;
	private JButton btnClear;
	private JLabel lblNewLabel_2_1;
	private JLabel lblNewLabel_2_3;
	private JLabel lblNewLabel_2_4;
	private JButton btnDelete;
	private LoginControlador cont;
	private JTextField textField_BookingId;
	private JScrollPane scrollPane;

	private void cargarTabla() {
		DefaultTableModel model = new DefaultTableModel() {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		model.addColumn("ID");
		model.addColumn("Customer / ID");
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

	/**
	 * Create the dialog.
	 */
	public V_Booking(LoginControlador cont) {
		this.cont = cont;
		setBounds(100, 100, 1060, 850);
		getContentPane().setLayout(null);
		Font normalFont = new Font("Tahoma", Font.PLAIN, 14);
		Font boldFont = new Font("Tahoma", Font.BOLD, 16);
		{
			titulo = new JPanel();
			titulo.setBounds(10, 10, 1024, 40);
			getContentPane().add(titulo);
			titulo.setLayout(null);

			exit = new JButton("Exit");
			exit.setBounds(910, 0, 100, 35);
			exit.setFont(normalFont);
			titulo.add(exit);
			exit.addActionListener(this);

			lblNewLabel = new JLabel("Booking Management");
			lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 18));
			lblNewLabel.setBounds(10, 5, 200, 25);
			titulo.add(lblNewLabel);
		}
		{
			subtitulo = new JPanel();
			subtitulo.setBounds(10, 60, 1024, 40);
			getContentPane().add(subtitulo);
			subtitulo.setLayout(null);

			lblNewLabel_1 = new JLabel("Sort by state:");
			lblNewLabel_1.setFont(normalFont);
			lblNewLabel_1.setBounds(10, 5, 100, 25);
			subtitulo.add(lblNewLabel_1);

			comboBox1 = new JComboBox();
			comboBox1.setFont(normalFont); 
			comboBox1.setBounds(120, 2, 150, 30);
			subtitulo.add(comboBox1);

			Buscar = new JButton("Search");
			Buscar.setFont(normalFont);
			Buscar.addActionListener(this);
			Buscar.setBounds(290, 0, 100, 35);
			subtitulo.add(Buscar);

			lblNewLabel_2 = new JLabel("Total:");
			lblNewLabel_2.setFont(normalFont);
			lblNewLabel_2.setBounds(550, 5, 80, 25);
			subtitulo.add(lblNewLabel_2);
		}
		{
			scrollPane = new JScrollPane();
			scrollPane.setBounds(10, 110, 1024, 470);
			getContentPane().add(scrollPane);
			Tabla_Cust = new JTable();
			Tabla_Cust.setFont(normalFont);
			Tabla_Cust.setRowHeight(30);
			Tabla_Cust.getTableHeader().setFont(boldFont);
			scrollPane.setViewportView(Tabla_Cust);
		}
		{
			info = new JPanel();
			info.setBounds(10, 600, 1024, 200);
			getContentPane().add(info);
			info.setLayout(null);

			lblNewLabel_1 = new JLabel("Booking:");
			lblNewLabel_1.setFont(boldFont);
			lblNewLabel_1.setBounds(10, 5, 120, 25);
			info.add(lblNewLabel_1);

			lblNewLabel_2_1_1 = new JLabel("Room ID:");
			lblNewLabel_2_1_1.setFont(normalFont);
			lblNewLabel_2_1_1.setBounds(20, 35, 100, 20);
			info.add(lblNewLabel_2_1_1);

			textField_room_id = new JTextField();
			textField_room_id.setFont(normalFont);
			textField_room_id.setColumns(10);
			textField_room_id.setBounds(20, 60, 180, 30);
			info.add(textField_room_id);

			lblNewLabel_2_2_1 = new JLabel("Check-in (DD/MM/YY):");
			lblNewLabel_2_2_1.setFont(normalFont);
			lblNewLabel_2_2_1.setBounds(230, 35, 180, 20);
			info.add(lblNewLabel_2_2_1);

			textField_check_in = new JTextField();
			textField_check_in.setFont(normalFont);
			textField_check_in.setColumns(10);
			textField_check_in.setBounds(230, 60, 180, 30);
			info.add(textField_check_in);

			lblNewLabel_2_4 = new JLabel("Payment State:");
			lblNewLabel_2_4.setFont(normalFont);
			lblNewLabel_2_4.setBounds(440, 35, 100, 20);
			info.add(lblNewLabel_2_4);

			comboBox_paid = new JComboBox();
			comboBox_paid.setFont(normalFont);
			comboBox_paid.setBounds(440, 60, 180, 30);
			comboBox_paid.addItem("Paid");
			comboBox_paid.addItem("Not Paid");
			info.add(comboBox_paid);

			lblNewLabel_2_1 = new JLabel("Client ID:");
			lblNewLabel_2_1.setFont(normalFont);
			lblNewLabel_2_1.setBounds(20, 100, 100, 20);
			info.add(lblNewLabel_2_1);

			textField_client_id = new JTextField();
			textField_client_id.setFont(normalFont);
			textField_client_id.setColumns(10);
			textField_client_id.setBounds(20, 125, 180, 30);
			info.add(textField_client_id);

			lblNewLabel_2_3 = new JLabel("Check-out (DD/MM/YY):");
			lblNewLabel_2_3.setFont(normalFont);
			lblNewLabel_2_3.setBounds(230, 100, 180, 20);
			info.add(lblNewLabel_2_3);

			textField_check_out = new JTextField();
			textField_check_out.setFont(normalFont);
			textField_check_out.setColumns(10);
			textField_check_out.setBounds(230, 125, 180, 30);
			info.add(textField_check_out);

			btnAadir = new JButton(" Add +");
			btnAadir.setFont(normalFont);
			btnAadir.addActionListener(this);
			btnAadir.setBounds(680, 55, 120, 35);
			info.add(btnAadir);

			btnDelete = new JButton("Delete -");
			btnDelete.addActionListener(this);
			btnDelete.setFont(normalFont);
			btnDelete.setBounds(820, 55, 120, 35);
			info.add(btnDelete);

			btnClear = new JButton("Clear []");
			btnClear.setFont(normalFont);
			btnClear.setBounds(680, 122, 120, 35);
			info.add(btnClear);
			btnClear.addActionListener(this);

			JLabel lblNewLabel_2_4_1 = new JLabel("Booking ID (delete):");
			lblNewLabel_2_4_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lblNewLabel_2_4_1.setBounds(440, 101, 180, 20);
			info.add(lblNewLabel_2_4_1);

			textField_BookingId = new JTextField();
			textField_BookingId.setFont(new Font("Tahoma", Font.PLAIN, 14));
			textField_BookingId.setColumns(10);
			textField_BookingId.setBounds(440, 125, 180, 30);
			info.add(textField_BookingId);
		}
		cargarTabla();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == exit) {
			V_Menu m = new V_Menu(cont);
			m.setVisible(true);
			this.dispose();
		}
		if (e.getSource() == Buscar) {

		}
		if (e.getSource() == btnAadir) {
			addBooking();
		}
		if (e.getSource() == btnDelete) {
			deleteBooking();
		}

		if (e.getSource() == btnClear) {
			clearFields(); 
		}
	}

	private void addBooking() {
		boolean valido = true;
		int roomNumber = 0;
		int customerId = 0;
		LocalDate checkIn = null;
		LocalDate checkOut = null;
		boolean paid = false;

		try {
			roomNumber = Integer.parseInt(textField_room_id.getText().trim());
			customerId = Integer.parseInt(textField_client_id.getText().trim());
		} catch (NumberFormatException e) {
			valido = false;
			JOptionPane.showMessageDialog(this, "Room ID and Client ID must be valid numbers.", "Format Error",
					JOptionPane.ERROR_MESSAGE);
		}

		if (valido) {
			try {
				checkIn = LocalDate.parse(textField_check_in.getText().trim());
				checkOut = LocalDate.parse(textField_check_out.getText().trim());
			} catch (java.time.format.DateTimeParseException e) {
				valido = false;
				JOptionPane.showMessageDialog(this, "Invalid date format. Please use YYYY-MM-DD.", "Format Error",
						JOptionPane.ERROR_MESSAGE);
			}
		}

		if (valido) {
			if (checkIn.isBefore(LocalDate.now())) {
				valido = false;
				JOptionPane.showMessageDialog(this, "Check-in date cannot be before today.", "Error",
						JOptionPane.ERROR_MESSAGE);
			} else if (!checkOut.isAfter(checkIn)) {
				valido = false;
				JOptionPane.showMessageDialog(this, "Check-out date must be after check-in date.", "Error",
						JOptionPane.ERROR_MESSAGE);
			}
		}

		if (valido) {
			if (!cont.checkRoomExists(roomNumber)) {
				valido = false;
				JOptionPane.showMessageDialog(this, "Room ID does not exist.", "Error", JOptionPane.ERROR_MESSAGE);
			} else if (!cont.checkCustomerExists(customerId)) {
				valido = false;
				JOptionPane.showMessageDialog(this, "Client ID does not exist.", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}

		if (valido) {
			if (!cont.isRoomAvailable(roomNumber, checkIn, checkOut)) {
				valido = false;
				JOptionPane.showMessageDialog(this, "Room is not available for the selected dates.", "Error",
						JOptionPane.ERROR_MESSAGE);
			} else if (!cont.isCustomerAvailable(customerId, checkIn, checkOut)) {
				valido = false;
				JOptionPane.showMessageDialog(this,
						"Client is not available or has conflicting bookings for these dates.", "Error",
						JOptionPane.ERROR_MESSAGE);
			}
		}

		if (valido) {
			if (comboBox_paid.getSelectedItem().equals("Paid")) {
				paid = true;
			}

			if (cont.createBooking(roomNumber, customerId, checkIn, checkOut, paid)) {
				JOptionPane.showMessageDialog(this, "Booking added successfully", "Success",
						JOptionPane.INFORMATION_MESSAGE);
				cargarTabla();
			} else {
				JOptionPane.showMessageDialog(this, "Error al añadir la reserva", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	private void clearFields() {
		textField_room_id.setText("");
		textField_client_id.setText("");
		textField_check_in.setText("");
		textField_check_out.setText("");
		textField_BookingId.setText("");
		comboBox_paid.setSelectedIndex(0);
	}

	private void deleteBooking() {
		int bookingId = 0;
		boolean valido = true;

		if (textField_BookingId.getText().trim().isEmpty() && valido) {
			valido = false;
			JOptionPane.showMessageDialog(this, "Booking ID is empty", "Error", JOptionPane.ERROR_MESSAGE);
		}

		if (valido) {
			try {
				bookingId = Integer.parseInt(textField_BookingId.getText());
			} catch (NumberFormatException e) {
				valido = false;
				JOptionPane.showMessageDialog(this, "Booking ID must be a valid number.", "Error",
						JOptionPane.ERROR_MESSAGE);
			}
		}

		if (valido) {
			bookingId = Integer.parseInt(textField_BookingId.getText());
			if (!cont.checkBookingExists(bookingId)) {
				valido = false;
				JOptionPane.showMessageDialog(this, "Booking ID does not exist.", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}

		if (valido) {
			if (JOptionPane.showConfirmDialog(this,
					"Seguro que quieres eliminar la reserva?") == JOptionPane.NO_OPTION) {
				valido = false;
			}
		}

		if (valido) {
			bookingId = Integer.parseInt(textField_BookingId.getText());
			if (cont.deleteBooking(bookingId)) {
				JOptionPane.showMessageDialog(this, "deleted successfully " + bookingId,
						"Success", JOptionPane.INFORMATION_MESSAGE);
				cargarTabla();
			} else {
				JOptionPane.showMessageDialog(this, "Error al eliminar la reserva", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
}
