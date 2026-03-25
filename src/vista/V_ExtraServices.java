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
import javax.swing.JScrollPane;
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
		setBounds(100, 100, 1060, 850);
		getContentPane().setLayout(null);
		
		Font normalFont = new Font("Tahoma", Font.PLAIN, 14);
		Font boldFont = new Font("Tahoma", Font.BOLD, 16);

		{
			titulo = new JPanel();
			titulo.setBounds(10, 10, 1024, 40);
			getContentPane().add(titulo);
			titulo.setLayout(null);

			exit = new JButton("Exit");
			exit.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				}
			});
			exit.setFont(normalFont);
			exit.setBounds(910, 0, 100, 35);
			titulo.add(exit);

			lblNewLabel = new JLabel("Extra Service Management");
			lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 18));
			lblNewLabel.setBounds(10, 5, 250, 25);
			titulo.add(lblNewLabel);
		}
		{
			subtitulo = new JPanel();
			subtitulo.setBounds(10, 60, 1024, 50);
			getContentPane().add(subtitulo);
			subtitulo.setLayout(null);

			lblNewLabel_1_8 = new JLabel("Service Catalog:");
			lblNewLabel_1_8.setFont(boldFont);
			lblNewLabel_1_8.setBounds(10, 15, 150, 20);
			subtitulo.add(lblNewLabel_1_8);

			lblNewLabel_1_1 = new JLabel("Breakfast");
			lblNewLabel_1_1.setFont(normalFont);
			lblNewLabel_1_1.setBounds(170, 15, 100, 20);
			subtitulo.add(lblNewLabel_1_1);

			lblNewLabel_1_2 = new JLabel("Media Pension");
			lblNewLabel_1_2.setFont(normalFont);
			lblNewLabel_1_2.setBounds(280, 15, 110, 20);
			subtitulo.add(lblNewLabel_1_2);

			lblNewLabel_1_3 = new JLabel("Parking");
			lblNewLabel_1_3.setFont(normalFont);
			lblNewLabel_1_3.setBounds(400, 15, 100, 20);
			subtitulo.add(lblNewLabel_1_3);

			lblNewLabel_1_4 = new JLabel("Airport Transport");
			lblNewLabel_1_4.setFont(normalFont);
			lblNewLabel_1_4.setBounds(500, 15, 120, 20);
			subtitulo.add(lblNewLabel_1_4);

			lblNewLabel_1_5 = new JLabel("Spa & Wellness");
			lblNewLabel_1_5.setFont(normalFont);
			lblNewLabel_1_5.setBounds(630, 15, 110, 20);
			subtitulo.add(lblNewLabel_1_5);

			lblNewLabel_1_6 = new JLabel("Room Service");
			lblNewLabel_1_6.setFont(normalFont);
			lblNewLabel_1_6.setBounds(750, 15, 110, 20);
			subtitulo.add(lblNewLabel_1_6);

			lblNewLabel_1_7 = new JLabel("Baby Cradle");
			lblNewLabel_1_7.setFont(normalFont);
			lblNewLabel_1_7.setBounds(870, 15, 100, 20);
			subtitulo.add(lblNewLabel_1_7);
		}
		{
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(10, 120, 1024, 460);
			getContentPane().add(scrollPane);

			Tabla_Cust = new JTable();
			Tabla_Cust.setFont(normalFont);
			Tabla_Cust.setRowHeight(30);
			Tabla_Cust.getTableHeader().setFont(boldFont);
			scrollPane.setViewportView(Tabla_Cust);
		}
		{
			info = new JPanel();
			info.setBounds(10, 600, 1024, 200);
			getContentPane().add(info);
			info.setLayout(null);

			lblNewLabel_1 = new JLabel("Extra Service:");
			lblNewLabel_1.setFont(boldFont);
			lblNewLabel_1.setBounds(10, 5, 120, 25);
			info.add(lblNewLabel_1);

			lblNewLabel_2_1 = new JLabel("Service name:");
			lblNewLabel_2_1.setFont(normalFont);
			lblNewLabel_2_1.setBounds(20, 35, 100, 20);
			info.add(lblNewLabel_2_1);

			textField_service_name = new JTextField();
			textField_service_name.setFont(normalFont);
			textField_service_name.setColumns(10);
			textField_service_name.setBounds(20, 60, 180, 30);
			info.add(textField_service_name);

			lblNewLabel_2_3 = new JLabel("Price (€):");
			lblNewLabel_2_3.setFont(normalFont);
			lblNewLabel_2_3.setBounds(230, 35, 100, 20);
			info.add(lblNewLabel_2_3);

			textField_price = new JTextField();
			textField_price.setFont(normalFont);
			textField_price.setColumns(10);
			textField_price.setBounds(230, 60, 180, 30);
			info.add(textField_price);

			btnAdd = new JButton(" Add +");
			btnAdd.setFont(normalFont);
			btnAdd.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				}
			});
			btnAdd.setBounds(680, 55, 120, 35);
			info.add(btnAdd);

			btnDelete = new JButton("Delete -");
			btnDelete.setFont(normalFont);
			btnDelete.setBounds(820, 55, 120, 35);
			info.add(btnDelete);

			btnEdit = new JButton("Edit []");
			btnEdit.setFont(normalFont);
			btnEdit.setBounds(680, 125, 120, 35);
			info.add(btnEdit);

			btnClear = new JButton("Clear []");
			btnClear.setFont(normalFont);
			btnClear.setBounds(820, 125, 120, 35);
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
