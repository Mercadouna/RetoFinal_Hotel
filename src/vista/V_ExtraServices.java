package vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controlador.LoginControlador;

import java.awt.Color;

public class V_ExtraServices extends JDialog implements ActionListener{
	private static final long serialVersionUID = 1L;
	private JTable Tabla_Cust;
	private JTextField textField_service_name;
	private JTextField textField_price;
	private JPanel titulo;
	private JButton exit;
	private JLabel lblNewLabel;
	private JPanel subtitulo;
	private JLabel lblNewLabel_1_1;
	private JLabel lblNewLabel_1_2;
	private JLabel lblNewLabel_1_3;
	private JLabel lblNewLabel_1_4;
	private JLabel lblNewLabel_1_5;
	private JLabel lblNewLabel_1_6;
	private JLabel lblNewLabel_1_7;
	private JLabel lblNewLabel_1_8;
	private JPanel tabla;
	private JPanel info;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2_1;
	private JLabel lblNewLabel_2_3;
	private JButton btnAdd;
	private JButton btnDelete;
	private JButton btnEdit;
	private JButton btnClear;
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
	public V_ExtraServices(LoginControlador cont) {
		this.cont=cont;
		setBounds(100, 100, 620, 420);
		getContentPane().setLayout(null);
		{
			titulo = new JPanel();
			titulo.setBounds(10, 11, 586, 22);
			getContentPane().add(titulo);
			titulo.setLayout(null);

			exit = new JButton("Exit");
			exit.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				}
			});
			exit.setBounds(492, 0, 84, 20);
			titulo.add(exit);

			lblNewLabel = new JLabel("Extra Service Managment");
			lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 13));
			lblNewLabel.setBounds(10, 4, 163, 12);
			titulo.add(lblNewLabel);
		}
		{
			subtitulo = new JPanel();
			subtitulo.setBounds(10, 43, 586, 106);
			getContentPane().add(subtitulo);
			subtitulo.setLayout(null);

			lblNewLabel_1_1 = new JLabel("Breakfast");
			lblNewLabel_1_1.setBackground(new Color(192, 192, 192));
			lblNewLabel_1_1.setBounds(69, 27, 95, 19);
			subtitulo.add(lblNewLabel_1_1);

			lblNewLabel_1_2 = new JLabel("Media Pension");
			lblNewLabel_1_2.setBackground(new Color(192, 192, 192));
			lblNewLabel_1_2.setBounds(185, 27, 95, 19);
			subtitulo.add(lblNewLabel_1_2);

			lblNewLabel_1_3 = new JLabel("Parking");
			lblNewLabel_1_3.setBounds(305, 27, 95, 19);
			subtitulo.add(lblNewLabel_1_3);

			lblNewLabel_1_4 = new JLabel("Airport Transport");
			lblNewLabel_1_4.setBounds(122, 68, 95, 19);
			subtitulo.add(lblNewLabel_1_4);

			lblNewLabel_1_5 = new JLabel("Spa & Wellness");
			lblNewLabel_1_5.setBounds(432, 27, 95, 19);
			subtitulo.add(lblNewLabel_1_5);

			lblNewLabel_1_6 = new JLabel("Room Service");
			lblNewLabel_1_6.setBounds(252, 68, 95, 19);
			subtitulo.add(lblNewLabel_1_6);

			lblNewLabel_1_7 = new JLabel("Baby Cradle");
			lblNewLabel_1_7.setBounds(371, 68, 95, 19);
			subtitulo.add(lblNewLabel_1_7);

			lblNewLabel_1_8 = new JLabel("Service Catalog:");
			lblNewLabel_1_8.setFont(new Font("Times New Roman", Font.BOLD, 12));
			lblNewLabel_1_8.setBounds(10, 5, 92, 13);
			subtitulo.add(lblNewLabel_1_8);
		}
		{
			tabla = new JPanel();
			tabla.setBounds(10, 159, 586, 78);
			getContentPane().add(tabla);
			tabla.setLayout(null);

			Tabla_Cust = new JTable();
			Tabla_Cust.setBounds(208, 5, 0, 0);
			tabla.add(Tabla_Cust);
		}
		{
			info = new JPanel();
			info.setBounds(10, 247, 586, 126);
			getContentPane().add(info);
			info.setLayout(null);

			lblNewLabel_1 = new JLabel("Extra Service:");
			lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 12));
			lblNewLabel_1.setBounds(6, 10, 92, 13);
			info.add(lblNewLabel_1);

			lblNewLabel_2_1 = new JLabel("Service name:");
			lblNewLabel_2_1.setBounds(16, 33, 82, 12);
			info.add(lblNewLabel_2_1);

			lblNewLabel_2_3 = new JLabel("Price (€):");
			lblNewLabel_2_3.setBounds(120, 33, 44, 12);
			info.add(lblNewLabel_2_3);

			textField_service_name = new JTextField();
			textField_service_name.setColumns(10);
			textField_service_name.setBounds(10, 55, 96, 18);
			info.add(textField_service_name);

			textField_price = new JTextField();
			textField_price.setColumns(10);
			textField_price.setBounds(119, 55, 96, 18);
			info.add(textField_price);

			btnAdd = new JButton(" Add +");
			btnAdd.setFont(new Font("Times New Roman", Font.PLAIN, 10));
			btnAdd.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				}
			});
			btnAdd.setBounds(357, 30, 84, 20);
			info.add(btnAdd);

			btnDelete = new JButton("Delete -");
			btnDelete.setFont(new Font("Times New Roman", Font.PLAIN, 10));
			btnDelete.setBounds(451, 30, 84, 20);
			info.add(btnDelete);

			btnEdit = new JButton("Edit []");
			btnEdit.setFont(new Font("Times New Roman", Font.PLAIN, 10));
			btnEdit.setBounds(357, 69, 84, 20);
			info.add(btnEdit);

			btnClear = new JButton("Clear []");
			btnClear.setBounds(451, 68, 84, 20);
			info.add(btnClear);
		}

		cargarTabla();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == exit) {
			V_Menu m = new V_Menu (cont);
			m.setVisible(true);
			this.dispose();
		}
		
		if (e.getSource() == textField_service_name) {}
		
		if (e.getSource() == textField_price) {}

		if (e.getSource() == btnAdd) {}
		
		if (e.getSource() == btnDelete) {}
		
		if (e.getSource() == btnEdit) {}
		
		if (e.getSource() == btnClear) {}
		
	}
}
