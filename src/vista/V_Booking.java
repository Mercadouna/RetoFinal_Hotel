package vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controlador.LoginControlador;

import javax.swing.JComboBox;

public class V_Booking extends JDialog implements ActionListener {
	private static final long serialVersionUID = 1L;
	private JTable Tabla_Cust;
	private JTextField textField_room_id;
	private JTextField textField_client_id;
	private JTextField textField_check_in;
	private JTextField textField_check_out;
	private JPanel titulo;
	private JButton exit;
	private JLabel lblNewLabel;
	private JPanel subtitulo;
	private JLabel lblNewLabel_1;
	private JButton Buscar;
	private JLabel lblNewLabel_2;
	private JComboBox comboBox;
	private JComboBox comboBox_paid;
	private JComboBox comboBox1;
	private JPanel table;
	private JPanel info;
	private JLabel lblNewLabel_2_1_1;
	private JLabel lblNewLabel_2_2_1;
	private JButton btnAadir;
	private JButton btnEdit;
	private JButton btnClear;
	private JLabel lblNewLabel_2_1;
	private JLabel lblNewLabel_2_3;
	private JLabel lblNewLabel_2_4;
	private JButton btnDelete;
	private LoginControlador cont;



	private void cargarTabla() {
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("ID");
		model.addColumn("Número");
		model.addColumn("Tipo");
		model.addColumn("Estado");
		model.addColumn("Precio");
		model.addColumn("Capacidad");
	}

	/**
	 * Create the dialog.
	 */
	public V_Booking(LoginControlador cont) {
		this.cont=cont;
		setBounds(100, 100, 620, 420);
		getContentPane().setLayout(null);
		{
			titulo = new JPanel();
			titulo.setBounds(10, 11, 586, 22);
			getContentPane().add(titulo);
			titulo.setLayout(null);

			exit = new JButton("Exit");
			exit.setBounds(492, 0, 84, 20);
			titulo.add(exit);
			exit.addActionListener(this);

			lblNewLabel = new JLabel("Booking Managment");
			lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 13));
			lblNewLabel.setBounds(10, 4, 139, 12);
			titulo.add(lblNewLabel);
		}
		{
			subtitulo = new JPanel();
			subtitulo.setBounds(10, 43, 586, 23);
			getContentPane().add(subtitulo);
			subtitulo.setLayout(null);

			lblNewLabel_1 = new JLabel("Sort by state:");
			lblNewLabel_1.setBounds(10, 5, 95, 12);
			subtitulo.add(lblNewLabel_1);

			Buscar = new JButton("Search");
			Buscar.addActionListener(this);
			Buscar.setBounds(204, 1, 84, 20);
			subtitulo.add(Buscar);

			lblNewLabel_2 = new JLabel("Total:");
			lblNewLabel_2.setBounds(319, 5, 44, 12);
			subtitulo.add(lblNewLabel_2);

			comboBox1 = new JComboBox();
			comboBox1.setBounds(99, 1, 95, 20);
			subtitulo.add(comboBox1);
		}
		{
			table = new JPanel();
			table.setBounds(10, 82, 586, 78);
			getContentPane().add(table);
			table.setLayout(null);

			Tabla_Cust = new JTable();
			Tabla_Cust.setBounds(208, 5, 0, 0);
			table.add(Tabla_Cust);
		}
		{
			info = new JPanel();
			info.setBounds(10, 170, 586, 203);
			getContentPane().add(info);
			info.setLayout(null);

			lblNewLabel_1 = new JLabel("Booking");
			lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 12));
			lblNewLabel_1.setBounds(10, 10, 92, 13);
			info.add(lblNewLabel_1);

			lblNewLabel_2_1_1 = new JLabel("Room ID:");
			lblNewLabel_2_1_1.setBounds(20, 44, 58, 12);
			info.add(lblNewLabel_2_1_1);

			lblNewLabel_2_1 = new JLabel("Client ID:");
			lblNewLabel_2_1.setBounds(20, 116, 58, 12);
			info.add(lblNewLabel_2_1);

			lblNewLabel_2_2_1 = new JLabel("Check-in (DD/MM/YY):");
			lblNewLabel_2_2_1.setBounds(98, 44, 107, 12);
			info.add(lblNewLabel_2_2_1);

			lblNewLabel_2_3 = new JLabel("Check-out (DD/MM/YY):");
			lblNewLabel_2_3.setBounds(98, 116, 122, 12);
			info.add(lblNewLabel_2_3);

			textField_room_id = new JTextField();
			textField_room_id.setColumns(10);
			textField_room_id.setBounds(6, 66, 96, 18);
			info.add(textField_room_id);

			textField_client_id = new JTextField();
			textField_client_id.setColumns(10);
			textField_client_id.setBounds(6, 138, 96, 18);
			info.add(textField_client_id);

			textField_check_in = new JTextField();
			textField_check_in.setColumns(10);
			textField_check_in.setBounds(111, 66, 96, 18);
			info.add(textField_check_in);

			textField_check_out = new JTextField();
			textField_check_out.setColumns(10);
			textField_check_out.setBounds(112, 138, 96, 18);
			info.add(textField_check_out);

			btnAadir = new JButton(" Add +");
			btnAadir.setFont(new Font("Times New Roman", Font.PLAIN, 10));
			btnAadir.addActionListener(this);

			btnAadir.setBounds(357, 66, 84, 20);
			info.add(btnAadir);

			btnDelete = new JButton("Delete -");
			btnDelete.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				}
			});
			btnDelete.setFont(new Font("Times New Roman", Font.PLAIN, 10));
			btnDelete.setBounds(451, 66, 84, 20);
			info.add(btnDelete);

			btnEdit = new JButton("Edit []");
			btnEdit.setFont(new Font("Times New Roman", Font.PLAIN, 10));
			btnEdit.setBounds(357, 123, 84, 20);
			info.add(btnEdit);

			btnClear = new JButton("Clear []");
			btnClear.setBounds(451, 122, 84, 20);
			info.add(btnClear);

			lblNewLabel_2_4 = new JLabel("Payment State:");
			lblNewLabel_2_4.setBounds(218, 45, 92, 12);
			info.add(lblNewLabel_2_4);

			comboBox_paid = new JComboBox();
			comboBox_paid.setBounds(217, 65, 95, 20);
			info.add(comboBox_paid);
		}

		cargarTabla();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == exit) {
			V_Menu m = new V_Menu (cont);
			m.setVisible(true);
			this.dispose();
		}
		
		if (e.getSource() == comboBox1) {}
		
		if (e.getSource() == Buscar) {}
		
		if (e.getSource() == textField_room_id) {}
		
		if (e.getSource() == textField_client_id) {}
		
		if (e.getSource() == textField_check_in) {}
		
		if (e.getSource() == textField_check_out) {}
		
		if (e.getSource() == comboBox_paid) {}
		
		if (e.getSource() == btnAadir) {}
		
		if (e.getSource() == btnDelete) {}
		
		if (e.getSource() == btnEdit) {}
		
		if (e.getSource() == btnClear) {}
		
		

	}
}
