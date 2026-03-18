package vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import controlador.LoginControlador;

import java.util.ArrayList;
import modelo.ImplementacionBD;
import modelo.Room;

public class V_Room extends JDialog {

	private static final long serialVersionUID = 1L;
	private JTable table;
	private LoginControlador cont;

	/**
	 * Create the dialog.
	 */
	public V_Room(LoginControlador controlador) {
		this.cont=controlador;
		setBounds(100, 100, 1060, 860);
		getContentPane().setLayout(null);

		JScrollPane scrollPane_roomsTable = new JScrollPane();
		scrollPane_roomsTable.setBounds(10, 101, 1024, 720);
		getContentPane().add(scrollPane_roomsTable);

		table = new JTable();
		scrollPane_roomsTable.setViewportView(table);

		cargarTabla();
	}

	private void cargarTabla() {
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("ID");
		model.addColumn("Número");
		model.addColumn("Tipo");
		model.addColumn("Estado");
		model.addColumn("Precio");
		model.addColumn("Capacidad");

		ImplementacionBD bd = new ImplementacionBD();
		ArrayList<Room> rooms = bd.viewRooms();

		for (Room room : rooms) {
			Object[] row = new Object[6];
			row[0] = room.getIdRoom();
			row[1] = room.getRoomNumber();
			row[2] = room.getTypeRoom();
			row[3] = room.getStatusRoom();
			row[4] = room.getPricePerNight();
			row[5] = room.getQuantPers();
			model.addRow(row);
		}

		table.setModel(model);
	}
}
