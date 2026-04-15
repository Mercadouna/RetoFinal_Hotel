package vista;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import controlador.LoginControlador;
import modelo.Room;

public class V_Room extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JTable table;
	private LoginControlador cont;
	private JScrollPane scrollPane_roomsTable;
	private JPanel titulo;
	private JButton exit;
	private JLabel lblRooms;

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

	/**
	 * Create the dialog.
	 */
	public V_Room(LoginControlador controlador) {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.cont = controlador;
		setBounds(100, 100, 1060, 860);
		setResizable(false);
		getContentPane().setLayout(null);

		// Fondo general de la ventana en navy oscuro
		getContentPane().setBackground(new Color(10, 20, 35));

		Font normalFont = new Font("Tahoma", Font.PLAIN, 14);
		Font boldFont = new Font("Tahoma", Font.BOLD, 16);

		// ── Panel título con fondo decorativo ─────────────────────────────────
		titulo = new ImagePanel("/images/cust_titulo_bg.png");
		titulo.setLayout(null);
		titulo.setBounds(10, 11, 1024, 65);
		getContentPane().add(titulo);

		// Botón Exit: tonos rojizos
		exit = new JButton("  Exit");
		exit.setFont(normalFont);
		exit.setBounds(914, 16, 100, 35);
		exit.setBackground(new Color(35, 15, 15));
		exit.setForeground(new Color(220, 130, 130));
		exit.setBorder(BorderFactory.createLineBorder(new Color(160, 70, 70), 1));
		exit.setIcon(loadIcon("ico_exit.png"));
		exit.setHorizontalAlignment(SwingConstants.LEFT);
		exit.setIconTextGap(8);
		exit.setFocusPainted(false);
		titulo.add(exit);
		exit.addActionListener(this);

		// Etiqueta título en dorado claro
		lblRooms = new JLabel("Rooms");
		lblRooms.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblRooms.setForeground(new Color(230, 200, 110));
		lblRooms.setBounds(34, 21, 200, 25);
		titulo.add(lblRooms);

		// ── Tabla con estilo hotel ─────────────────────────────────────────────
		scrollPane_roomsTable = new JScrollPane();
		scrollPane_roomsTable.setBounds(10, 87, 1024, 734);
		scrollPane_roomsTable.setBorder(BorderFactory.createLineBorder(new Color(201, 168, 76), 1));
		getContentPane().add(scrollPane_roomsTable);

		table = new JTable();
		table.setFont(normalFont);
		table.setRowHeight(30);
		table.setBackground(new Color(14, 26, 44));
		table.setForeground(new Color(220, 200, 150));
		table.setGridColor(new Color(40, 65, 100));
		table.setSelectionBackground(new Color(201, 168, 76));
		table.setSelectionForeground(new Color(10, 20, 35));

		// Cabecera en estilo hotel
		table.getTableHeader().setFont(boldFont);
		table.getTableHeader().setBackground(new Color(10, 20, 35));
		table.getTableHeader().setForeground(new Color(201, 168, 76));
		table.getTableHeader().setBorder(BorderFactory.createLineBorder(new Color(201, 168, 76), 1));

		scrollPane_roomsTable.setViewportView(table);
		scrollPane_roomsTable.getViewport().setBackground(new Color(14, 26, 44));

		cargarTabla();
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
		model.addColumn("Room Number");
		model.addColumn("Type");
		model.addColumn("Status");
		model.addColumn("Price");

		ArrayList<Room> rooms = cont.viewRooms();
		for (Room room : rooms) {
			Object[] row = new Object[6];
			row[0] = room.getIdRoom();
			row[1] = room.getRoomNumber();
			row[2] = room.getTypeRoom();
			row[3] = room.getStatusRoom();
			row[4] = room.getPricePerNight();
			model.addRow(row);
		}

		table.setModel(model);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == exit) {
			V_Menu m = new V_Menu(cont);
			m.setVisible(true);
			this.dispose();
		}
	}
}