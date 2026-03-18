package vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;

public class V_Room extends JDialog {

	private static final long serialVersionUID = 1L;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			V_Room dialog = new V_Room();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public V_Room() {
		setBounds(100, 100, 1060, 860);
		getContentPane().setLayout(null);
		
		JScrollPane scrollPane_roomsTable = new JScrollPane();
		scrollPane_roomsTable.setBounds(0, 101, 1044, 720);
		getContentPane().add(scrollPane_roomsTable);
	}
}
