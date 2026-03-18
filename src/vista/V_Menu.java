package vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;

public class V_Menu extends JDialog {

	private static final long serialVersionUID = 1L;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			V_Menu dialog = new V_Menu();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public V_Menu() {
		setBounds(100, 100, 1060, 850);
		getContentPane().setLayout(null);
		
		JPanel panel_Botones_Ventanas = new JPanel();
		panel_Botones_Ventanas.setBounds(0, 157, 222, 655);
		getContentPane().add(panel_Botones_Ventanas);
		panel_Botones_Ventanas.setLayout(null);
		
		JButton btnNewButton_Booking = new JButton("Booking");
		btnNewButton_Booking.setBounds(59, 35, 153, 23);
		panel_Botones_Ventanas.add(btnNewButton_Booking);
		
		JButton btnNewButton_Clients = new JButton("Clients");
		btnNewButton_Clients.setBounds(59, 105, 153, 23);
		panel_Botones_Ventanas.add(btnNewButton_Clients);
		
		JButton btnNewButton_Rooms = new JButton("Rooms");
		btnNewButton_Rooms.setBounds(59, 184, 153, 23);
		panel_Botones_Ventanas.add(btnNewButton_Rooms);
		
		JButton btnNewButton_ExtraServices = new JButton("Extra Services");
		btnNewButton_ExtraServices.setBounds(59, 266, 153, 23);
		panel_Botones_Ventanas.add(btnNewButton_ExtraServices);
		
		JButton btnNewButton_Exit = new JButton("Exit");
		btnNewButton_Exit.setBounds(10, 621, 89, 23);
		panel_Botones_Ventanas.add(btnNewButton_Exit);
		
		JPanel panel_Titulo = new JPanel();
		panel_Titulo.setBounds(223, 0, 811, 157);
		getContentPane().add(panel_Titulo);
		panel_Titulo.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(223, 157, 811, 655);
		getContentPane().add(scrollPane);
	}
}
