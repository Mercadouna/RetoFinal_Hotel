package vista;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controlador.LoginControlador;

import javax.swing.JTable;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
public class V_Customer extends JDialog implements ActionListener{
	private static final long serialVersionUID = 1L;
	private JTable Tabla_Cust;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField;
	private LoginControlador cont;
	private JPanel titulo;
	private JButton exit;
	private JLabel lblNewLabel ;
	private JPanel subtitulo ;
	private JLabel lblNewLabel_1 ;
	private JButton search ;
	private JLabel lblNewLabel_2 ;
	private JPanel table ;
	private JPanel info ;
	private JLabel lblNewLabel_1_1 ;
	private JLabel lblNewLabel_2_4 ;
	private JLabel lblNewLabel_2_1 ;
	private JLabel lblNewLabel_2_2 ;
	private JLabel lblNewLabel_2_3 ;
	private JButton btnAdd;
	private JButton btnDelete;
	private JButton btnEdit;
	private	JButton btnClear;
	/**
	 * Launch the application.
	 */
	
	
	
	

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
	public V_Customer(LoginControlador cont) {
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
			
			lblNewLabel = new JLabel("Client Managment");
			lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 13));
			lblNewLabel.setBounds(10, 4, 139, 12);
			titulo.add(lblNewLabel);
		}
		{
			subtitulo = new JPanel();
			subtitulo.setBounds(10, 43, 586, 23);
			getContentPane().add(subtitulo);
			subtitulo.setLayout(null);
			
			lblNewLabel_1 = new JLabel("Search for client: ");
			lblNewLabel_1.setBounds(10, 5, 95, 12);
			subtitulo.add(lblNewLabel_1);
			
			search = new JButton("Search");
			search.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				}
			});
			search.setBounds(204, 1, 84, 20);
			subtitulo.add(search);
			
			lblNewLabel_2 = new JLabel("Total:");
			lblNewLabel_2.setBounds(319, 5, 44, 12);
			subtitulo.add(lblNewLabel_2);
			
			textField = new JTextField();
			textField.setBounds(98, 2, 96, 18);
			subtitulo.add(textField);
			textField.setColumns(10);
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
			
			lblNewLabel_1 = new JLabel("Client: ");
			lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 12));
			lblNewLabel_1.setBounds(10, 10, 92, 13);
			info.add(lblNewLabel_1);
			
			lblNewLabel_2_4= new JLabel("Name:");
			lblNewLabel_2_4.setBounds(20, 44, 44, 12);
			info.add(lblNewLabel_2_4);
			
			lblNewLabel_2_1 = new JLabel("Phone:");
			lblNewLabel_2_1.setBounds(20, 116, 44, 12);
			info.add(lblNewLabel_2_1);
			
			lblNewLabel_2_2 = new JLabel("Surname:");
			lblNewLabel_2_2.setBounds(112, 44, 67, 12);
			info.add(lblNewLabel_2_2);
			
			lblNewLabel_2_3 = new JLabel("ID:");
			lblNewLabel_2_3.setBounds(114, 116, 44, 12);
			info.add(lblNewLabel_2_3);
			
			textField_1 = new JTextField();
			textField_1.setColumns(10);
			textField_1.setBounds(6, 66, 96, 18);
			info.add(textField_1);
			
			textField_2 = new JTextField();
			textField_2.setColumns(10);
			textField_2.setBounds(6, 138, 96, 18);
			info.add(textField_2);
			
			textField_3 = new JTextField();
			textField_3.setColumns(10);
			textField_3.setBounds(112, 66, 96, 18);
			info.add(textField_3);
			
			textField_4 = new JTextField();
			textField_4.setColumns(10);
			textField_4.setBounds(112, 138, 96, 18);
			info.add(textField_4);
			
			JButton btnAdd = new JButton(" Add +");
			btnAdd.setFont(new Font("Times New Roman", Font.PLAIN, 10));
			btnAdd.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				}
			});
			btnAdd.setBounds(357, 66, 84, 20);
			info.add(btnAdd);
			
			btnDelete = new JButton("Delete -");
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
		
		if (e.getSource() == textField) {}
		
		if (e.getSource() == search) {}
		
		if (e.getSource() == textField_1) {}
		
		if (e.getSource() == textField_2) {}
		
		if (e.getSource() == textField_3) {}
		
		if (e.getSource() == textField_4) {}
		
		if (e.getSource() == btnAdd) {}
		
		if (e.getSource() == btnDelete) {}
		
		if (e.getSource() == btnEdit) {}
		
		if (e.getSource() == btnClear) {}
	}
}
