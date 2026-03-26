package vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.JLabel;

public class V_Room extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JTable table;
	private LoginControlador cont;
	private JScrollPane scrollPane_roomsTable;
	private JPanel titulo;
	private JButton exit;
	private JLabel lblRooms;

	/**
	 * Create the dialog.
	 */
	public V_Room(LoginControlador controlador) {
		this.cont = controlador;
		setBounds(100, 100, 1060, 860);
		getContentPane().setLayout(null);

		Font normalFont = new Font("Tahoma", Font.PLAIN, 14);
		Font boldFont = new Font("Tahoma", Font.BOLD, 16);

		scrollPane_roomsTable = new JScrollPane();
		scrollPane_roomsTable.setBounds(10, 101, 1024, 720);
		getContentPane().add(scrollPane_roomsTable);

		table = new JTable();
		table.setFont(normalFont);
		table.setRowHeight(30);
		table.getTableHeader().setFont(boldFont);
		scrollPane_roomsTable.setViewportView(table);

		titulo = new JPanel();
		titulo.setLayout(null);
		titulo.setBounds(10, 23, 1024, 40);
		getContentPane().add(titulo);

		exit = new JButton("Exit");
		exit.setFont(new Font("Tahoma", Font.PLAIN, 14));
		exit.setBounds(910, 0, 100, 35);
		titulo.add(exit);
		exit.addActionListener(this);

		lblRooms = new JLabel("Rooms");
		lblRooms.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblRooms.setBounds(10, 5, 200, 25);
		titulo.add(lblRooms);

		cargarTabla();
	}

	private void cargarTabla() {
		DefaultTableModel model = new DefaultTableModel();
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
