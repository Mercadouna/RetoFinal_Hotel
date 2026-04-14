package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controlador.LoginControlador;
import modelo.Aux_booking;
import modelo.Booking;
import modelo.Customer;
import modelo.ExtraService;
import modelo.Room;

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

	// ── Helper: JPanel que pinta una imagen de fondo ─────────────────────────
	private static class ImagePanel extends JPanel {
		private static final long serialVersionUID = 1L;
		private Image bgImage;

		public ImagePanel(String resourcePath) {
			setOpaque(false);
			try {
				URL url = getClass().getResource(resourcePath);
				if (url != null)
					bgImage = new ImageIcon(url).getImage();
			} catch (Exception ignored) {
			}
		}

		@Override
		protected void paintComponent(Graphics g) {
			if (bgImage != null)
				g.drawImage(bgImage, 0, 0, getWidth(), getHeight(), this);
			super.paintComponent(g);
		}
	}

	// ── Helper: carga un ImageIcon desde /images/ ─────────────────────────────
	private ImageIcon loadIcon(String name) {
		ImageIcon icon = null;
		try {
			URL url = getClass().getResource("/images/" + name);
			if (url != null)
				icon = new ImageIcon(url);
		} catch (Exception ignored) {
		}
		return icon;
	}

	// ── Helper: aplica estilo hotel a un JTextField ───────────────────────────
	private void styleTextField(JTextField tf) {
		tf.setBackground(new Color(20, 35, 58));
		tf.setForeground(new Color(230, 200, 110));
		tf.setCaretColor(new Color(201, 168, 76));
		tf.setBorder(BorderFactory.createLineBorder(new Color(201, 168, 76), 1));
	}

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
		// Altura aumentada a 920 para acomodar los iconos en el subtítulo
		setBounds(100, 100, 1500, 920);

		Font normalFont = new Font("Tahoma", Font.PLAIN, 14);
		Font boldFont = new Font("Tahoma", Font.BOLD, 16);
		getContentPane().setLayout(null);

		// Fondo general de la ventana en navy oscuro
		getContentPane().setBackground(new Color(10, 20, 35));

		Color btnBg = new Color(20, 35, 58);
		Color btnFg = new Color(230, 200, 110);
		Color btnBorder = new Color(201, 168, 76);

		{
			// ── Panel título ──────────────────────────────────────────────────
			titulo = new ImagePanel("/images/extra_titulo_bg.png");
			titulo.setBounds(10, 10, 1464, 40);
			getContentPane().add(titulo);
			titulo.setLayout(null);

			// Botón Exit: tonos rojizos
			exit = new JButton("  Exit");
			exit.addActionListener(this);
			exit.setFont(normalFont);
			exit.setBounds(1354, 2, 100, 35);
			exit.setBackground(new Color(35, 15, 15));
			exit.setForeground(new Color(220, 130, 130));
			exit.setBorder(BorderFactory.createLineBorder(new Color(160, 70, 70), 1));
			exit.setIcon(loadIcon("ico_exit.png"));
			exit.setHorizontalAlignment(SwingConstants.LEFT);
			exit.setIconTextGap(8);
			exit.setFocusPainted(false);
			titulo.add(exit);

			// Título en dorado claro
			lblNewLabel = new JLabel("Extra Service Management");
			lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 18));
			lblNewLabel.setForeground(new Color(230, 200, 110));
			lblNewLabel.setBounds(10, 5, 280, 25);
			titulo.add(lblNewLabel);
		}
		{
			// ── Panel catálogo: icono + nombre para cada servicio ─────────────
			// Altura aumentada a 110 para que los iconos de 52px sean visibles
			subtitulo = new ImagePanel("/images/extra_subtitulo_bg.png");
			subtitulo.setBounds(20, 61, 1464, 110);
			getContentPane().add(subtitulo);
			subtitulo.setLayout(null);

			// Etiqueta "Service Catalog:" alineada verticalmente al centro
			lblNewLabel_1_8 = new JLabel("Service Catalog:");
			lblNewLabel_1_8.setFont(boldFont);
			lblNewLabel_1_8.setForeground(new Color(201, 168, 76));
			lblNewLabel_1_8.setBounds(10, 35, 155, 25);
			subtitulo.add(lblNewLabel_1_8);

			Font svcFont = new Font("Tahoma", Font.PLAIN, 11);
			Color svcColor = new Color(230, 200, 110);

			// ── Breakfast ─────────────────────────────────────────────────────
			JLabel imgBreakfast = new JLabel();
			ImageIcon rawBreakfast = loadIcon("01-breakfast.png");
			if (rawBreakfast != null)
				imgBreakfast
						.setIcon(new ImageIcon(rawBreakfast.getImage().getScaledInstance(52, 52, Image.SCALE_SMOOTH)));
			imgBreakfast.setHorizontalAlignment(SwingConstants.CENTER);
			imgBreakfast.setBounds(255, 8, 70, 52);
			subtitulo.add(imgBreakfast);

			JLabel txtBreakfast = new JLabel("1 - Breakfast");
			txtBreakfast.setFont(svcFont);
			txtBreakfast.setForeground(svcColor);
			txtBreakfast.setHorizontalAlignment(SwingConstants.CENTER);
			txtBreakfast.setBounds(255, 62, 70, 38);
			subtitulo.add(txtBreakfast);

			// ── Media Pension ─────────────────────────────────────────────────
			JLabel imgMediaPension = new JLabel();
			ImageIcon rawMediaPension = loadIcon("02-media-pension.png");
			if (rawMediaPension != null)
				imgMediaPension.setIcon(
						new ImageIcon(rawMediaPension.getImage().getScaledInstance(52, 52, Image.SCALE_SMOOTH)));
			imgMediaPension.setHorizontalAlignment(SwingConstants.CENTER);
			imgMediaPension.setBounds(421, 8, 70, 52);
			subtitulo.add(imgMediaPension);

			JLabel txtMediaPension = new JLabel("2 - Media Pension");
			txtMediaPension.setFont(svcFont);
			txtMediaPension.setForeground(svcColor);
			txtMediaPension.setHorizontalAlignment(SwingConstants.CENTER);
			txtMediaPension.setBounds(409, 62, 94, 38);
			subtitulo.add(txtMediaPension);

			// ── Parking ───────────────────────────────────────────────────────
			JLabel imgParking = new JLabel();
			ImageIcon rawParking = loadIcon("03-parking.png");
			if (rawParking != null)
				imgParking.setIcon(new ImageIcon(rawParking.getImage().getScaledInstance(52, 52, Image.SCALE_SMOOTH)));
			imgParking.setHorizontalAlignment(SwingConstants.CENTER);
			imgParking.setBounds(595, 8, 70, 52);
			subtitulo.add(imgParking);

			JLabel txtParking = new JLabel("3 - Parking");
			txtParking.setFont(svcFont);
			txtParking.setForeground(svcColor);
			txtParking.setHorizontalAlignment(SwingConstants.CENTER);
			txtParking.setBounds(595, 62, 70, 38);
			subtitulo.add(txtParking);

			// ── Airport Transport ─────────────────────────────────────────────
			JLabel imgAirport = new JLabel();
			ImageIcon rawAirport = loadIcon("04-airport-transport.png");
			if (rawAirport != null)
				imgAirport.setIcon(new ImageIcon(rawAirport.getImage().getScaledInstance(52, 52, Image.SCALE_SMOOTH)));
			imgAirport.setHorizontalAlignment(SwingConstants.CENTER);
			imgAirport.setBounds(777, 8, 70, 52);
			subtitulo.add(imgAirport);

			JLabel txtAirport = new JLabel("4 - Airport Transport");
			txtAirport.setFont(svcFont);
			txtAirport.setForeground(svcColor);
			txtAirport.setHorizontalAlignment(SwingConstants.CENTER);
			txtAirport.setBounds(768, 62, 103, 38);
			subtitulo.add(txtAirport);

			// ── Spa & Wellness ────────────────────────────────────────────────
			JLabel imgSpa = new JLabel();
			ImageIcon rawSpa = loadIcon("05-spa-wellness.png");
			if (rawSpa != null)
				imgSpa.setIcon(new ImageIcon(rawSpa.getImage().getScaledInstance(52, 52, Image.SCALE_SMOOTH)));
			imgSpa.setHorizontalAlignment(SwingConstants.CENTER);
			imgSpa.setBounds(990, 8, 70, 52);
			subtitulo.add(imgSpa);

			JLabel txtSpa = new JLabel("5 - Spa & Wellness");
			txtSpa.setFont(svcFont);
			txtSpa.setForeground(svcColor);
			txtSpa.setHorizontalAlignment(SwingConstants.CENTER);
			txtSpa.setBounds(967, 62, 121, 38);
			subtitulo.add(txtSpa);

			// ── Room Service ──────────────────────────────────────────────────
			JLabel imgRoomService = new JLabel();
			ImageIcon rawRoomService = loadIcon("06-room-service.png");
			if (rawRoomService != null)
				imgRoomService.setIcon(
						new ImageIcon(rawRoomService.getImage().getScaledInstance(52, 52, Image.SCALE_SMOOTH)));
			imgRoomService.setHorizontalAlignment(SwingConstants.CENTER);
			imgRoomService.setBounds(1173, 8, 70, 52);
			subtitulo.add(imgRoomService);

			JLabel txtRoomService = new JLabel("6 - Room Service");
			txtRoomService.setFont(svcFont);
			txtRoomService.setForeground(svcColor);
			txtRoomService.setHorizontalAlignment(SwingConstants.CENTER);
			txtRoomService.setBounds(1165, 62, 94, 38);
			subtitulo.add(txtRoomService);

			// ── Baby Cradle ───────────────────────────────────────────────────
			JLabel imgCradle = new JLabel();
			ImageIcon rawCradle = loadIcon("07-baby-cradle.png");
			if (rawCradle != null)
				imgCradle.setIcon(new ImageIcon(rawCradle.getImage().getScaledInstance(52, 52, Image.SCALE_SMOOTH)));
			imgCradle.setHorizontalAlignment(SwingConstants.CENTER);
			imgCradle.setBounds(1354, 8, 70, 52);
			subtitulo.add(imgCradle);

			JLabel txtCradle = new JLabel("7 - Baby Cradle");
			txtCradle.setFont(svcFont);
			txtCradle.setForeground(svcColor);
			txtCradle.setHorizontalAlignment(SwingConstants.CENTER);
			txtCradle.setBounds(1330, 62, 110, 38);
			subtitulo.add(txtCradle);
		}
		{
			// ── Tabla principal de reservas (desplazada 60px hacia abajo) ─────
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(10, 180, 1056, 400);
			scrollPane.setBorder(BorderFactory.createLineBorder(new Color(201, 168, 76), 1));
			getContentPane().add(scrollPane);

			Tabla_Cust = new JTable();
			Tabla_Cust.setFont(normalFont);
			Tabla_Cust.setRowHeight(30);
			Tabla_Cust.setBackground(new Color(14, 26, 44));
			Tabla_Cust.setForeground(new Color(220, 200, 150));
			Tabla_Cust.setGridColor(new Color(40, 65, 100));
			Tabla_Cust.setSelectionBackground(new Color(201, 168, 76));
			Tabla_Cust.setSelectionForeground(new Color(10, 20, 35));
			Tabla_Cust.getTableHeader().setFont(boldFont);
			Tabla_Cust.getTableHeader().setBackground(new Color(10, 20, 35));
			Tabla_Cust.getTableHeader().setForeground(new Color(201, 168, 76));
			Tabla_Cust.getTableHeader().setBorder(BorderFactory.createLineBorder(new Color(201, 168, 76), 1));
			scrollPane.setViewportView(Tabla_Cust);
			scrollPane.getViewport().setBackground(new Color(14, 26, 44));
		}
		{
			// ── Panel formulario ──────────────────────────────────────────────
			info = new ImagePanel("/images/extra_info_bg.png");
			info.setBounds(10, 600, 1464, 200);
			getContentPane().add(info);
			info.setLayout(null);

			lblNewLabel_1 = new JLabel("Extra Service:");
			lblNewLabel_1.setFont(boldFont);
			lblNewLabel_1.setForeground(new Color(201, 168, 76));
			lblNewLabel_1.setBounds(10, 5, 150, 25);
			info.add(lblNewLabel_1);

			lblNewLabel_2_1 = new JLabel("Booking ID:");
			lblNewLabel_2_1.setFont(normalFont);
			lblNewLabel_2_1.setForeground(new Color(230, 200, 110));
			lblNewLabel_2_1.setBounds(20, 35, 120, 20);
			info.add(lblNewLabel_2_1);

			textField_BookingId = new JTextField();
			textField_BookingId.setFont(normalFont);
			textField_BookingId.setColumns(10);
			textField_BookingId.setBounds(20, 60, 180, 30);
			styleTextField(textField_BookingId);
			info.add(textField_BookingId);

			// Botón Search en tonos dorados
			btn_Search = new JButton("  Search");
			btn_Search.setFont(normalFont);
			btn_Search.addActionListener(this);
			btn_Search.setBounds(220, 57, 120, 35);
			btn_Search.setBackground(btnBg);
			btn_Search.setForeground(btnFg);
			btn_Search.setBorder(BorderFactory.createLineBorder(btnBorder, 1));
			btn_Search.setFocusPainted(false);
			info.add(btn_Search);

			lblNewLabel_2_3 = new JLabel("Extra Service ID:");
			lblNewLabel_2_3.setFont(normalFont);
			lblNewLabel_2_3.setForeground(new Color(230, 200, 110));
			lblNewLabel_2_3.setBounds(751, 35, 160, 20);
			info.add(lblNewLabel_2_3);

			textField_Extra_Service_ID = new JTextField();
			textField_Extra_Service_ID.setFont(normalFont);
			textField_Extra_Service_ID.setColumns(10);
			textField_Extra_Service_ID.setBounds(751, 60, 180, 30);
			styleTextField(textField_Extra_Service_ID);
			info.add(textField_Extra_Service_ID);

			// Botón Add: tonos verdes
			btnAdd = new JButton("  Add +");
			btnAdd.setFont(normalFont);
			btnAdd.setBounds(971, 57, 120, 35);
			btnAdd.addActionListener(this);
			btnAdd.setBackground(new Color(15, 40, 20));
			btnAdd.setForeground(new Color(100, 200, 120));
			btnAdd.setBorder(BorderFactory.createLineBorder(new Color(70, 160, 90), 1));
			btnAdd.setIcon(loadIcon("ico_add.png"));
			btnAdd.setHorizontalAlignment(SwingConstants.LEFT);
			btnAdd.setIconTextGap(8);
			btnAdd.setFocusPainted(false);
			info.add(btnAdd);

			// Botón Delete: tonos rojizos
			btnDelete = new JButton("  Delete");
			btnDelete.setFont(normalFont);
			btnDelete.setBounds(1110, 57, 120, 35);
			btnDelete.setBackground(new Color(35, 15, 15));
			btnDelete.setForeground(new Color(220, 130, 130));
			btnDelete.setBorder(BorderFactory.createLineBorder(new Color(160, 70, 70), 1));
			btnDelete.setIcon(loadIcon("ico_delete.png"));
			btnDelete.setHorizontalAlignment(SwingConstants.LEFT);
			btnDelete.setIconTextGap(8);
			btnDelete.setFocusPainted(false);
			btnDelete.addActionListener(this);
			info.add(btnDelete);
		}

		// ── Tabla de servicios extra (panel derecho, desplazado 60px) ─────────
		JScrollPane scrollPaneExtra = new JScrollPane();
		scrollPaneExtra.setBounds(1076, 181, 398, 400);
		scrollPaneExtra.setBorder(BorderFactory.createLineBorder(new Color(201, 168, 76), 1));
		getContentPane().add(scrollPaneExtra);

		table_Extra_Services = new JTable();
		table_Extra_Services.setFont(normalFont);
		table_Extra_Services.setRowHeight(30);
		table_Extra_Services.setBackground(new Color(14, 26, 44));
		table_Extra_Services.setForeground(new Color(220, 200, 150));
		table_Extra_Services.setGridColor(new Color(40, 65, 100));
		table_Extra_Services.setSelectionBackground(new Color(201, 168, 76));
		table_Extra_Services.setSelectionForeground(new Color(10, 20, 35));
		table_Extra_Services.getTableHeader().setFont(boldFont);
		table_Extra_Services.getTableHeader().setBackground(new Color(10, 20, 35));
		table_Extra_Services.getTableHeader().setForeground(new Color(201, 168, 76));
		table_Extra_Services.getTableHeader().setBorder(BorderFactory.createLineBorder(new Color(201, 168, 76), 1));
		scrollPaneExtra.setViewportView(table_Extra_Services);
		scrollPaneExtra.getViewport().setBackground(new Color(14, 26, 44));

		cargarTabla();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == exit) {
			V_Menu m = new V_Menu(cont);
			m.setVisible(true);
			this.dispose();
		}
		if (e.getSource() == btn_Search) {
			searchBooking();
		}
		if (e.getSource() == btnAdd) {
			addExtraServiceToBooking();
		}
		if (e.getSource() == btnDelete) {
			deleteExtraServiceFromBooking();
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

	private void addExtraServiceToBooking() {
		int bookingId;
		int extraServiceId;
		if (textField_BookingId.getText().isEmpty() || textField_Extra_Service_ID.getText().isEmpty()) {
			JOptionPane.showMessageDialog(this, "Please fill in all the fields.");
		} else {
			try {
				bookingId = Integer.parseInt(textField_BookingId.getText());
				extraServiceId = Integer.parseInt(textField_Extra_Service_ID.getText());
				if (cont.checkBookingExists(bookingId)) {
					if (cont.checkExtraServiceExists(extraServiceId)) {
						if (cont.checkExtraServiceInBooking(bookingId, extraServiceId)) {
							JOptionPane.showMessageDialog(this,
									"This extra service is already assigned to the booking.", "Error",
									JOptionPane.ERROR_MESSAGE);
						} else {
							if (cont.addExtraServiceToBooking(bookingId, extraServiceId)) {
								JOptionPane.showMessageDialog(this, "Extra service added successfully.");
								cargarTablaExtraServices(bookingId);
							} else {
								JOptionPane.showMessageDialog(this, "Error al añadir servicio extra.");
							}
						}
					} else {
						JOptionPane.showMessageDialog(this, "Extra service not found.");
					}
				} else {
					JOptionPane.showMessageDialog(this, "Booking not found.");
				}
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(this, "Please enter a valid number.");
			}
		}
	}

	private void deleteExtraServiceFromBooking() {
		int bookingId = 0;
		int extraServiceId = 0;
		boolean valido = true;

		if (textField_BookingId.getText().trim().isEmpty() && valido) {
			valido = false;
			JOptionPane.showMessageDialog(this, "Booking ID is empty.", "Error", JOptionPane.ERROR_MESSAGE);
		}

		if (textField_Extra_Service_ID.getText().trim().isEmpty() && valido) {
			valido = false;
			JOptionPane.showMessageDialog(this, "Extra Service ID is empty.", "Error", JOptionPane.ERROR_MESSAGE);
		}

		if (valido) {
			try {
				bookingId = Integer.parseInt(textField_BookingId.getText().trim());
				extraServiceId = Integer.parseInt(textField_Extra_Service_ID.getText().trim());
			} catch (NumberFormatException e) {
				valido = false;
				JOptionPane.showMessageDialog(this, "IDs must be valid numbers.", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}

		if (valido) {
			if (!cont.checkBookingExists(bookingId)) {
				valido = false;
				JOptionPane.showMessageDialog(this, "Booking not found.", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}

		if (valido) {
			if (!cont.checkExtraServiceExists(extraServiceId)) {
				valido = false;
				JOptionPane.showMessageDialog(this, "Extra service not found.", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}

		if (valido) {
			if (!cont.checkExtraServiceInBooking(bookingId, extraServiceId)) {
				valido = false;
				JOptionPane.showMessageDialog(this, "This extra service is not assigned to the booking.", "Error",
						JOptionPane.ERROR_MESSAGE);
			}
		}

		if (valido) {
			if (JOptionPane.showConfirmDialog(this,
					"Are you sure you want to remove this extra service from the booking?", "Confirm",
					JOptionPane.YES_NO_OPTION) == JOptionPane.NO_OPTION) {
				valido = false;
			}
		}

		if (valido) {
			if (cont.deleteExtraServiceFromBooking(bookingId, extraServiceId)) {
				JOptionPane.showMessageDialog(this, "Extra service removed successfully.", "Success",
						JOptionPane.INFORMATION_MESSAGE);
				cargarTablaExtraServices(bookingId);
			} else {
				JOptionPane.showMessageDialog(this, "Error removing extra service from booking.", "Error",
						JOptionPane.ERROR_MESSAGE);
			}
		}
	}
}