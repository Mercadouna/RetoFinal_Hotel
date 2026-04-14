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

	// ── Helper: JPanel que pinta una imagen de fondo ─────────────────────────
	private static class ImagePanel extends JPanel {

		private static final long serialVersionUID = 1L;

		// Almacena la imagen de fondo que se pintará en el panel
		private Image bgImage;

		public ImagePanel(String resourcePath) {

			// Hace el panel transparente para que no tape la imagen con un color sólido
			setOpaque(false);

			try {
				// Busca la imagen dentro del classpath del proyecto usando su ruta
				// Funciona tanto en desarrollo como si el proyecto se exporta a .jar
				URL url = getClass().getResource(resourcePath);

				// Solo carga la imagen si la URL se encontró, evitando NullPointerException
				if (url != null)
					bgImage = new ImageIcon(url).getImage();

			} catch (Exception ignored) {
				// Si la imagen no existe o falla la carga, el panel queda sin fondo
				// pero el programa no se rompe
			}
		}

		@Override
		protected void paintComponent(Graphics g) {

			// Solo intenta dibujar si la imagen se cargó correctamente
			if (bgImage != null)
				// Dibuja la imagen estirada al tamaño exacto del panel (getWidth, getHeight)
				// El parámetro "this" avisa al panel cuando la imagen termina de cargar
				g.drawImage(bgImage, 0, 0, getWidth(), getHeight(), this);

			// Llama al pintado original de JPanel para que los componentes
			// hijos (botones, labels...) se dibujen encima de la imagen
			super.paintComponent(g);
		}
	}

	private ImageIcon loadIcon(String name) {
		ImageIcon icon = null;
		try {
			// Construye la ruta completa añadiendo el prefijo "/images/" al nombre del
			// archivo
			// La barra inicial "/" indica que busca desde la raíz del classpath
			URL url = getClass().getResource("/images/" + name);

			// Si encontró el archivo, crea el ImageIcon listo para usar en un botón
			// Si url es null, icon se queda como null
			if (url != null)
				icon = new ImageIcon(url);

		} catch (Exception ignored) {
			// Si el archivo no existe o falla, icon se queda null sin romper el programa
		}

		// Único return: devuelve el icono cargado, o null si algo falló
		return icon;
	}

	/**
	 * Create the dialog.
	 */
	public V_Menu(LoginControlador controlador) {
		this.cont = controlador;
		setBounds(100, 100, 1060, 850);
		getContentPane().setLayout(null);

		// ── Fondo general de la ventana ──────────────────────────────────────
		getContentPane().setBackground(new Color(10, 20, 35));

		Font normalFont = new Font("Tahoma", Font.PLAIN, 14);

		// ── Panel lateral con imagen de fondo ─────────────────────────────────
		panel_Botones_Ventanas = new ImagePanel("/images/sidebar_bg_2.png ");
		panel_Botones_Ventanas.setBounds(0, 157, 222, 655);
		getContentPane().add(panel_Botones_Ventanas);
		panel_Botones_Ventanas.setLayout(null);

		// Colores de botón acordes al tema hotel
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

		btnNewButton_Exit = new JButton("  Exit"); // Crea el botón con el texto "Exit"
		btnNewButton_Exit.setFont(normalFont); // Aplica la fuente Tahoma 14px definida arriba
		btnNewButton_Exit.setBounds(20, 600, 180, 45); // Posición (x=20, y=600) y tamaño (180x45 píxeles)
		btnNewButton_Exit.setBackground(new Color(35, 15, 15)); // Fondo rojo oscuro para diferenciarlo del
																// resto de botones
		btnNewButton_Exit.setForeground(new Color(220, 130, 130)); // Texto en rojo claro
		btnNewButton_Exit.setBorder(BorderFactory.createLineBorder(new Color(160, 70, 70), 1)); // Borde
																								// de
																								// 1px
																								// en
																								// rojo
																								// medio
		btnNewButton_Exit.setIcon(loadIcon("ico_exit.png")); // Carga y asigna el icono de salida desde /images/
		btnNewButton_Exit.setHorizontalAlignment(SwingConstants.LEFT); // Alinea el icono y el texto a la
																		// izquierda
		btnNewButton_Exit.setIconTextGap(10); // Espacio de 10px entre el icono y el texto
		btnNewButton_Exit.setFocusPainted(false); // Elimina el borde punteado que aparece al hacer clic
		panel_Botones_Ventanas.add(btnNewButton_Exit); // Añade el botón al panel lateral
		btnNewButton_Exit.addActionListener(this); // Registra la clase actual como listener para detectar el clic

		// ── Panel logo (esquina superior izquierda, 0,0 – 222,157) ────────────
		JPanel panel_Logo = new ImagePanel("/images/Logo_Hotel.png");
		panel_Logo.setBounds(0, 0, 222, 157);
		panel_Logo.setLayout(null);
		getContentPane().add(panel_Logo);

		ImageIcon logoIcon = loadIcon("logo.png");
		if (logoIcon != null) {
			Image scaled = logoIcon.getImage().getScaledInstance(190, 130, Image.SCALE_SMOOTH); // SCALE_SMOOTH es para
																								// que la imagen
																								// aparezca con mejor
																								// calidad.
			JLabel lblLogo = new JLabel(new ImageIcon(scaled));
			lblLogo.setBounds(16, 13, 190, 130);
			panel_Logo.add(lblLogo);
		}

		// ── Panel título / header con imagen de fondo ─────────────────────────
		panel_Titulo = new ImagePanel("/images/header_bg_1.png");
		panel_Titulo.setBounds(223, 0, 811, 157);
		getContentPane().add(panel_Titulo);
		panel_Titulo.setLayout(null);

		// ── ScrollPane con panel de fondo ────────────────────────────────────
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
		scrollPaneTable.setBounds(10, 50, 780, 600);
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
			opcion = JOptionPane.showConfirmDialog(this, "Seguro que quieres salir?", "Confirm", JOptionPane.YES_NO_OPTION);
			if (opcion == JOptionPane.YES_OPTION) {
				this.dispose();
			}
		}
	}
}