package vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controlador.LoginControlador;

import javax.swing.JScrollPane;

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
	private LoginControlador cont;

	/**
	 * Create the dialog.
	 */
	public V_Menu(LoginControlador controlador) {
		this.cont = controlador;
		setBounds(100, 100, 1060, 850);
		getContentPane().setLayout(null);

		Font normalFont = new Font("Tahoma", Font.PLAIN, 14);

		panel_Botones_Ventanas = new JPanel();
		panel_Botones_Ventanas.setBounds(0, 157, 222, 655);
		getContentPane().add(panel_Botones_Ventanas);
		panel_Botones_Ventanas.setLayout(null);

		btnNewButton_Booking = new JButton("Booking");
		btnNewButton_Booking.setFont(normalFont);
		btnNewButton_Booking.setBounds(20, 35, 180, 45);
		panel_Botones_Ventanas.add(btnNewButton_Booking);
		btnNewButton_Booking.addActionListener(this);

		btnNewButton_Clients = new JButton("Clients");
		btnNewButton_Clients.setFont(normalFont);
		btnNewButton_Clients.setBounds(20, 105, 180, 45);
		panel_Botones_Ventanas.add(btnNewButton_Clients);
		btnNewButton_Clients.addActionListener(this);

		btnNewButton_Rooms = new JButton("Rooms");
		btnNewButton_Rooms.setFont(normalFont);
		btnNewButton_Rooms.setBounds(20, 184, 180, 45);
		panel_Botones_Ventanas.add(btnNewButton_Rooms);
		btnNewButton_Rooms.addActionListener(this);

		btnNewButton_ExtraServices = new JButton("Extra Services");
		btnNewButton_ExtraServices.setFont(normalFont);
		btnNewButton_ExtraServices.setBounds(20, 266, 180, 45);
		panel_Botones_Ventanas.add(btnNewButton_ExtraServices);
		btnNewButton_ExtraServices.addActionListener(this);

		btnNewButton_Exit = new JButton("Exit");
		btnNewButton_Exit.setFont(normalFont);
		btnNewButton_Exit.setBounds(20, 600, 180, 45);
		panel_Botones_Ventanas.add(btnNewButton_Exit);
		btnNewButton_Exit.addActionListener(this);

		panel_Titulo = new JPanel();
		panel_Titulo.setBounds(223, 0, 811, 157);
		getContentPane().add(panel_Titulo);
		panel_Titulo.setLayout(null);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(223, 157, 811, 655);
		getContentPane().add(scrollPane);
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
			opcion = JOptionPane.showConfirmDialog(this, "Seguro que quieres salir? ");
			if (opcion == JOptionPane.YES_OPTION) {
				this.dispose();
			}
		}
	}
}
