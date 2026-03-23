package vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
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

		panel_Botones_Ventanas = new JPanel();
		panel_Botones_Ventanas.setBounds(0, 157, 222, 655);
		getContentPane().add(panel_Botones_Ventanas);
		panel_Botones_Ventanas.setLayout(null);

		btnNewButton_Booking = new JButton("Booking");
		btnNewButton_Booking.setBounds(59, 35, 153, 23);
		panel_Botones_Ventanas.add(btnNewButton_Booking);
		btnNewButton_Booking.addActionListener(this);

		btnNewButton_Clients = new JButton("Clients");
		btnNewButton_Clients.setBounds(59, 105, 153, 23);
		panel_Botones_Ventanas.add(btnNewButton_Clients);

		btnNewButton_Rooms = new JButton("Rooms");
		btnNewButton_Rooms.setBounds(59, 184, 153, 23);
		panel_Botones_Ventanas.add(btnNewButton_Rooms);
		btnNewButton_Rooms.addActionListener(this);

		btnNewButton_ExtraServices = new JButton("Extra Services");
		btnNewButton_ExtraServices.setBounds(59, 266, 153, 23);
		panel_Botones_Ventanas.add(btnNewButton_ExtraServices);

		btnNewButton_Exit = new JButton("Exit");
		btnNewButton_Exit.setBounds(10, 621, 89, 23);
		panel_Botones_Ventanas.add(btnNewButton_Exit);

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
		if (e.getSource() == btnNewButton_Rooms) {
			V_Room r = new V_Room(cont);
			r.setVisible(true);
			this.dispose();
		}
		
		if(e.getSource() == btnNewButton_Booking) {
			V_Booking b = new V_Booking(cont);
			b.setVisible(true);
			this.dispose();
		}
		
		if(e.getSource() == btnNewButton_Clients) {
			V_Customer c = new V_Customer(cont);
			c.setVisible(true);
			this.dispose();
		}
		
		if(e.getSource() == btnNewButton_ExtraServices) {
			V_ExtraServices ex = new V_ExtraServices(cont);
			ex.setVisible(true);
			this.dispose();
		}
	}
}
