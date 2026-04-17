package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import controlador.LoginControlador;
import modelo.Aux_booking;
import modelo.Booking;
import modelo.Customer;
import modelo.Room;

import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class V_Menu extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel panel_Botones_Ventanas;
	private JButton btnNewButton_Booking;
	private JButton btnNewButton_Clients;
	private JButton btnNewButton_Rooms;
	private JButton btnNewButton_ExtraServices;
	private JButton btnNewButton_Exit;
	private JPanel panel_Titulo;
	private JScrollPane scrollPane;
	private JTable tableUnpaidBookings;
	private LoginControlador cont;
	private JTextField textField_editPaymentId;
	private JButton btnEditPayment;

	// ── Helper: JPanel that paints a background image ────────────────────────
	private static class ImagePanel extends JPanel {

		private static final long serialVersionUID = 1L;

		// Stores the background image to be painted on the panel
		private Image bgImage;

		public ImagePanel(String resourcePath) {

			// Makes the panel transparent so it does not cover the image with a solid colour
			setOpaque(false);

			try {
				// Looks for the image inside the project classpath using its path
				// Works both in development and when the project is exported to a .jar
				URL url = getClass().getResource(resourcePath);

				// Only loads the image if the URL was found, avoiding NullPointerException
				if (url != null)
					bgImage = new ImageIcon(url).getImage();

			} catch (Exception ignored) {
				// If the image does not exist or fails to load, the panel has no background
				// but the program does not crash
			}
		}

		@Override
		protected void paintComponent(Graphics g) {

			// Only tries to draw if the image was loaded successfully
			if (bgImage != null)
				// Draws the image stretched to the exact size of the panel (getWidth, getHeight)
				// The "this" parameter notifies the panel when the image finishes loading
				g.drawImage(bgImage, 0, 0, getWidth(), getHeight(), this);

			// Calls the original JPanel paint method so child components
			// (buttons, labels...) are drawn on top of the image
			super.paintComponent(g);
		}
	}

	private ImageIcon loadIcon(String name) {
		ImageIcon icon = null;
		try {
			// Builds the full path by prepending "/images/" to the file name
			// The leading "/" means it searches from the root of the classpath
			URL url = getClass().getResource("/images/" + name);

			// If the file was found, creates the ImageIcon ready to use on a button
			// If url is null, icon stays null
			if (url != null)
				icon = new ImageIcon(url);

		} catch (Exception ignored) {
			// If the file does not exist or fails, icon stays null without crashing
		}

		// Single return: returns the loaded icon, or null if something failed
		return icon;
	}

	/**
	 * Create the dialog.
	 */
	public V_Menu(LoginControlador controlador) {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.cont = controlador;
		setBounds(100, 100, 1060, 850);
		setResizable(false);
		getContentPane().setLayout(null);

		// ── General window background ────────────────────────────────────────
		getContentPane().setBackground(new Color(10, 20, 35));

		Font normalFont = new Font("Tahoma", Font.PLAIN, 14);

		// ── Sidebar panel with background image ──────────────────────────────
		panel_Botones_Ventanas = new ImagePanel("/images/sidebar_bg_2.png ");
		panel_Botones_Ventanas.setBounds(0, 157, 222, 655);
		getContentPane().add(panel_Botones_Ventanas);
		panel_Botones_Ventanas.setLayout(null);

		// Button colours matching the hotel theme
		Color btnBg = new Color(20, 35, 58);
		Color btnFg = new Color(230, 200, 110);
		Color btnBorder = new Color(201, 168, 76);

		btnNewButton_Booking = new JButton("  Booking");
		btnNewButton_Booking.setFont(normalFont);
		btnNewButton_Booking.setBounds(20, 202, 180, 45);
		btnNewButton_Booking.setBackground(btnBg);
		btnNewButton_Booking.setForeground(btnFg);
		btnNewButton_Booking.setBorder(BorderFactory.createLineBorder(btnBorder, 1));
		btnNewButton_Booking.setIcon(loadIcon("ico_booking.png"));
		btnNewButton_Booking.setHorizontalAlignment(SwingConstants.LEFT);
		btnNewButton_Booking.setIconTextGap(10);
		btnNewButton_Booking.setFocusPainted(false);
		panel_Botones_Ventanas.add(btnNewButton_Booking);
		btnNewButton_Booking.addActionListener(this);

		btnNewButton_Clients = new JButton("  Customer");
		btnNewButton_Clients.setFont(normalFont);
		btnNewButton_Clients.setBounds(20, 269, 180, 45);
		btnNewButton_Clients.setBackground(btnBg);
		btnNewButton_Clients.setForeground(btnFg);
		btnNewButton_Clients.setBorder(BorderFactory.createLineBorder(btnBorder, 1));
		btnNewButton_Clients.setIcon(loadIcon("ico_clients.png"));
		btnNewButton_Clients.setHorizontalAlignment(SwingConstants.LEFT);
		btnNewButton_Clients.setIconTextGap(10);
		btnNewButton_Clients.setFocusPainted(false);
		panel_Botones_Ventanas.add(btnNewButton_Clients);
		btnNewButton_Clients.addActionListener(this);

		btnNewButton_Rooms = new JButton("  Rooms");
		btnNewButton_Rooms.setFont(normalFont);
		btnNewButton_Rooms.setBounds(20, 336, 180, 45);
		btnNewButton_Rooms.setBackground(btnBg);
		btnNewButton_Rooms.setForeground(btnFg);
		btnNewButton_Rooms.setBorder(BorderFactory.createLineBorder(btnBorder, 1));
		btnNewButton_Rooms.setIcon(loadIcon("ico_rooms.png"));
		btnNewButton_Rooms.setHorizontalAlignment(SwingConstants.LEFT);
		btnNewButton_Rooms.setIconTextGap(10);
		btnNewButton_Rooms.setFocusPainted(false);
		panel_Botones_Ventanas.add(btnNewButton_Rooms);
		btnNewButton_Rooms.addActionListener(this);

		btnNewButton_ExtraServices = new JButton("  Extra Services");
		btnNewButton_ExtraServices.setFont(normalFont);
		btnNewButton_ExtraServices.setBounds(20, 406, 180, 45);
		btnNewButton_ExtraServices.setBackground(btnBg);
		btnNewButton_ExtraServices.setForeground(btnFg);
		btnNewButton_ExtraServices.setBorder(BorderFactory.createLineBorder(btnBorder, 1));
		btnNewButton_ExtraServices.setIcon(loadIcon("ico_services.png"));
		btnNewButton_ExtraServices.setHorizontalAlignment(SwingConstants.LEFT);
		btnNewButton_ExtraServices.setIconTextGap(10);
		btnNewButton_ExtraServices.setFocusPainted(false);
		panel_Botones_Ventanas.add(btnNewButton_ExtraServices);
		btnNewButton_ExtraServices.addActionListener(this);

		btnNewButton_Exit = new JButton("  Exit");
		btnNewButton_Exit.setFont(normalFont);
		btnNewButton_Exit.setBounds(20, 600, 180, 45);
		btnNewButton_Exit.setBackground(new Color(35, 15, 15)); // Dark red background to stand out from other buttons
		btnNewButton_Exit.setForeground(new Color(220, 130, 130)); // Light red text
		btnNewButton_Exit.setBorder(BorderFactory.createLineBorder(new Color(160, 70, 70), 1));
		btnNewButton_Exit.setIcon(loadIcon("ico_exit.png"));
		btnNewButton_Exit.setHorizontalAlignment(SwingConstants.LEFT);
		btnNewButton_Exit.setIconTextGap(10); // 10px gap between icon and text
		btnNewButton_Exit.setFocusPainted(false); // Removes the dotted border that appears on click
		panel_Botones_Ventanas.add(btnNewButton_Exit);
		btnNewButton_Exit.addActionListener(this);

		// ── Panel logo (esquina superior izquierda, 0,0 – 222,157) ────────────
		JPanel panel_Logo = new ImagePanel("/images/Logo_Hotel.png");
		panel_Logo.setBounds(0, 0, 222, 157);
		panel_Logo.setLayout(null);
		getContentPane().add(panel_Logo);

		ImageIcon logoIcon = loadIcon("logo.png");
		if (logoIcon != null) {
			Image scaled = logoIcon.getImage().getScaledInstance(190, 130, Image.SCALE_SMOOTH); // SCALE_SMOOTH for better image quality
			JLabel lblLogo = new JLabel(new ImageIcon(scaled));
			lblLogo.setBounds(16, 13, 190, 130);
			panel_Logo.add(lblLogo);
		}

		// ── Title / header panel with background image ───────────────────────
		panel_Titulo = new ImagePanel("/images/header_bg_1.png");
		panel_Titulo.setBounds(223, 0, 811, 157);
		getContentPane().add(panel_Titulo);
		panel_Titulo.setLayout(null);

		// ── ScrollPane with background panel ─────────────────────────────────
		Font boldFont = new Font("Tahoma", Font.BOLD, 16);

		JPanel panel_Main = new ImagePanel("/images/main_bg.png");
		panel_Main.setLayout(null);
		panel_Main.setPreferredSize(new Dimension(800, 700));
		scrollPane = new JScrollPane(panel_Main);
		scrollPane.setBounds(223, 157, 811, 655);
		scrollPane.setBorder(null);
		scrollPane.getViewport().setOpaque(false);
		getContentPane().add(scrollPane);

		JLabel lblUnpaidTitle = new JLabel("Unpaid Bookings");
		lblUnpaidTitle.setFont(boldFont);
		lblUnpaidTitle.setForeground(new Color(201, 168, 76));
		lblUnpaidTitle.setBounds(22, 11, 250, 30);
		panel_Main.add(lblUnpaidTitle);

		JLabel lblEditPaymentId = new JLabel("Booking ID:");
		lblEditPaymentId.setFont(normalFont);
		lblEditPaymentId.setForeground(new Color(230, 200, 110));
		lblEditPaymentId.setBounds(22, 50, 100, 25);
		panel_Main.add(lblEditPaymentId);

		textField_editPaymentId = new JTextField();
		textField_editPaymentId.setFont(normalFont);
		textField_editPaymentId.setColumns(10);
		textField_editPaymentId.setBounds(130, 47, 150, 30);
		textField_editPaymentId.setBackground(new Color(20, 35, 58));
		textField_editPaymentId.setForeground(new Color(230, 200, 110));
		textField_editPaymentId.setCaretColor(new Color(201, 168, 76));
		textField_editPaymentId.setBorder(BorderFactory.createLineBorder(new Color(201, 168, 76), 1));
		panel_Main.add(textField_editPaymentId);

		btnEditPayment = new JButton("  Edit Payment");
		btnEditPayment.setFont(normalFont);
		btnEditPayment.setBounds(295, 46, 170, 35);
		btnEditPayment.setBackground(new Color(20, 35, 58));
		btnEditPayment.setForeground(new Color(230, 200, 110));
		btnEditPayment.setBorder(BorderFactory.createLineBorder(new Color(201, 168, 76), 1));
		btnEditPayment.setIcon(loadIcon("ico_booking.png"));
		btnEditPayment.setHorizontalAlignment(SwingConstants.LEFT);
		btnEditPayment.setIconTextGap(8);
		btnEditPayment.setFocusPainted(false);
		panel_Main.add(btnEditPayment);
		btnEditPayment.addActionListener(this);

		tableUnpaidBookings = new JTable() {
			private static final long serialVersionUID = 1L;

			@Override
			public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
				Component c = super.prepareRenderer(renderer, row, column);
				String status = (String) getModel().getValueAt(row, getModel().getColumnCount() - 1);
				if (!isRowSelected(row)) {
					if ("GREEN".equals(status)) {
						c.setBackground(new Color(15, 50, 20));
						c.setForeground(new Color(80, 200, 100));
					} else if ("ORANGE".equals(status)) {
						c.setBackground(new Color(55, 35, 5));
						c.setForeground(new Color(220, 155, 50));
					} else if ("RED".equals(status)) {
						c.setBackground(new Color(55, 12, 12));
						c.setForeground(new Color(210, 75, 75));
					} else {
						c.setBackground(new Color(14, 26, 44));
						c.setForeground(new Color(220, 200, 150));
					}
				}
				return c;
			}
		};
		tableUnpaidBookings.setFont(normalFont);
		tableUnpaidBookings.setRowHeight(30);
		tableUnpaidBookings.setBackground(new Color(14, 26, 44));
		tableUnpaidBookings.setForeground(new Color(220, 200, 150));
		tableUnpaidBookings.setGridColor(new Color(40, 65, 100));
		tableUnpaidBookings.setSelectionBackground(new Color(201, 168, 76));
		tableUnpaidBookings.setSelectionForeground(new Color(10, 20, 35));
		tableUnpaidBookings.getTableHeader().setFont(boldFont);
		tableUnpaidBookings.getTableHeader().setBackground(new Color(10, 20, 35));
		tableUnpaidBookings.getTableHeader().setForeground(new Color(201, 168, 76));
		tableUnpaidBookings.getTableHeader().setBorder(BorderFactory.createLineBorder(new Color(201, 168, 76), 1));

		JScrollPane scrollPaneTable = new JScrollPane(tableUnpaidBookings);
		scrollPaneTable.setBounds(10, 95, 780, 555);
		scrollPaneTable.setBorder(BorderFactory.createLineBorder(new Color(201, 168, 76), 1));
		scrollPaneTable.getViewport().setBackground(new Color(14, 26, 44));
		panel_Main.add(scrollPaneTable);

		cargarTablaUnpaid();
	}

	private void cargarTablaUnpaid() {
		DefaultTableModel model = new DefaultTableModel() {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		model.addColumn("ID");
		model.addColumn("Customer");
		model.addColumn("DNI");
		model.addColumn("Phone");
		model.addColumn("Room");
		model.addColumn("Check-in");
		model.addColumn("Check-out");
		model.addColumn("STATUS");

		LocalDate today = LocalDate.now();
		ArrayList<Aux_booking> bookings = cont.viewUnpaidBookings();
		for (Aux_booking ab : bookings) {
			Customer c = ab.getCustomer();
			Room r = ab.getRoom();
			Booking b = ab.getBooking();
			String status;
			Object[] row = new Object[8];

			if (today.isBefore(b.getCheckIn())) {
				status = "GREEN";
			} else if (!today.isAfter(b.getCheckOut())) {
				status = "ORANGE";
			} else {
				status = "RED";
			}

			row[0] = b.getIdBooking();
			row[1] = c.getNameCostumer() + " " + c.getSurname();
			row[2] = c.getDni();
			row[3] = c.getPhone();
			row[4] = r.getRoomNumber();
			row[5] = b.getCheckIn();
			row[6] = b.getCheckOut();
			row[7] = status;
			model.addRow(row);
		}

		tableUnpaidBookings.setModel(model);
		tableUnpaidBookings.getColumnModel().getColumn(7).setMinWidth(0);
		tableUnpaidBookings.getColumnModel().getColumn(7).setMaxWidth(0);
		tableUnpaidBookings.getColumnModel().getColumn(7).setWidth(0);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		int opcion;
		if (e.getSource() == btnNewButton_Rooms) {
			V_Room r = new V_Room(cont);
			r.setVisible(true);
			this.dispose();
		}

		if (e.getSource() == btnNewButton_Booking) {
			V_Booking b = new V_Booking(cont);
			b.setVisible(true);
			this.dispose();
		}

		if (e.getSource() == btnNewButton_Clients) {
			V_Customer c = new V_Customer(cont);
			c.setVisible(true);
			this.dispose();
		}

		if (e.getSource() == btnNewButton_ExtraServices) {
			V_ExtraServices ex = new V_ExtraServices(cont);
			ex.setVisible(true);
			this.dispose();
		}
		if (e.getSource() == btnNewButton_Exit) {
			opcion = JOptionPane.showConfirmDialog(this, "Are you sure you want to exit?", "Confirm",
					JOptionPane.YES_NO_OPTION);
			if (opcion == JOptionPane.YES_OPTION) {
				MusicPlayer.stop();
				this.dispose();
			}
		}
		if (e.getSource() == btnEditPayment) {
			editPayment();
		}
	}

	private void editPayment() {
		boolean valido = true;
		boolean found = false;
		int bookingId = 0;
		int i = 0;
		DefaultTableModel tableModel;

		if (textField_editPaymentId.getText().trim().isEmpty()) {
			valido = false;
			JOptionPane.showMessageDialog(this, "Booking ID is empty", "Error", JOptionPane.ERROR_MESSAGE);
		}

		if (valido) {
			try {
				bookingId = Integer.parseInt(textField_editPaymentId.getText().trim());
			} catch (NumberFormatException e) {
				valido = false;
				JOptionPane.showMessageDialog(this, "Booking ID must be a valid number.", "Error",
						JOptionPane.ERROR_MESSAGE);
			}
		}

		if (valido) {
			tableModel = (DefaultTableModel) tableUnpaidBookings.getModel();
			for (i = 0; i < tableModel.getRowCount() && !found; i++) {
				if ((int) tableModel.getValueAt(i, 0) == bookingId) {
					found = true;
				}
			}
			if (!found) {
				valido = false;
				JOptionPane.showMessageDialog(this, "Unpaid booking not found.", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}

		if (valido) {
			if (cont.togglePayment(bookingId)) {
				JOptionPane.showMessageDialog(this, "Payment status updated for booking " + bookingId,
						"Success", JOptionPane.INFORMATION_MESSAGE);
				textField_editPaymentId.setText("");
				cargarTablaUnpaid();
			} else {
				JOptionPane.showMessageDialog(this, "Error updating payment status.", "Error",
						JOptionPane.ERROR_MESSAGE);
			}
		}
	}
}