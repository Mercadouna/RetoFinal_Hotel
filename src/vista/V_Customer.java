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
import javax.swing.JScrollPane;
import java.util.ArrayList;
import modelo.ImplementacionBD;
import modelo.Customer;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JOptionPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import exceptions.DniException;
import exceptions.PhoneNumException;

public class V_Customer extends JDialog implements ActionListener {
	private static final long serialVersionUID = 1L;
	private JTable Tabla_Cust;
	private JTextField textField_name;
	private JTextField textField_phone;
	private JTextField textField_surname;
	private JTextField textField_ID_DNI;
	private JTextField textField;
	private LoginControlador cont;
	private JPanel titulo;
	private JButton exit;
	private JLabel lblNewLabel;
	private JPanel subtitulo;
	private JLabel lblNewLabel_1;
	private JButton search;
	private JLabel lblNewLabel_2;
	private JPanel table;
	private JPanel info;
	private JLabel lblNewLabel_1_1;
	private JLabel lblNewLabel_2_4;
	private JLabel lblNewLabel_2_1;
	private JLabel lblNewLabel_2_2;
	private JLabel lblNewLabel_2_3;
	private JButton btnAdd;
	private JButton btnDelete;
	private JButton btnEdit;
	private JButton btnClear;
	private JTextField textField_ID;

	/**
	 * Launch the application.
	 */

	private void cargarTabla() {
		DefaultTableModel model = new DefaultTableModel() {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		model.addColumn("ID");
		model.addColumn("Nombre");
		model.addColumn("Apellido");
		model.addColumn("Teléfono");
		model.addColumn("DNI");

		ImplementacionBD bd = new ImplementacionBD();
		ArrayList<Customer> customers = bd.viewCustomers();

		for (Customer c : customers) {
			Object[] row = new Object[5];
			row[0] = c.getIdCustomer();
			row[1] = c.getNameCostumer();
			row[2] = c.getSurname();
			row[3] = c.getPhone();
			row[4] = c.getDni();
			model.addRow(row);
		}

		Tabla_Cust.setModel(model);
	}

	/**
	 * Create the dialog.
	 */
	public V_Customer(LoginControlador cont) {
		this.cont = cont;
		setBounds(100, 100, 620, 420);
		getContentPane().setLayout(null);
		{
			titulo = new JPanel();
			titulo.setBounds(10, 11, 586, 22);
			getContentPane().add(titulo);
			titulo.setLayout(null);

			exit = new JButton("Exit");
			exit.addActionListener(this);
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
			search.addActionListener(this);
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
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(10, 82, 586, 78);
			getContentPane().add(scrollPane);

			Tabla_Cust = new JTable();
			scrollPane.setViewportView(Tabla_Cust);
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

			lblNewLabel_2_4 = new JLabel("Name:");
			lblNewLabel_2_4.setBounds(20, 44, 44, 12);
			info.add(lblNewLabel_2_4);

			lblNewLabel_2_1 = new JLabel("Phone:");
			lblNewLabel_2_1.setBounds(20, 116, 44, 12);
			info.add(lblNewLabel_2_1);

			lblNewLabel_2_2 = new JLabel("Surname:");
			lblNewLabel_2_2.setBounds(112, 44, 67, 12);
			info.add(lblNewLabel_2_2);

			lblNewLabel_2_3 = new JLabel("ID (DNI):");
			lblNewLabel_2_3.setBounds(114, 116, 44, 12);
			info.add(lblNewLabel_2_3);

			textField_name = new JTextField();
			textField_name.setColumns(10);
			textField_name.setBounds(6, 66, 96, 18);
			info.add(textField_name);

			textField_phone = new JTextField();
			textField_phone.setColumns(10);
			textField_phone.setBounds(6, 138, 96, 18);
			info.add(textField_phone);

			textField_surname = new JTextField();
			textField_surname.setColumns(10);
			textField_surname.setBounds(112, 66, 96, 18);
			info.add(textField_surname);

			textField_ID_DNI = new JTextField();
			textField_ID_DNI.setColumns(10);
			textField_ID_DNI.setBounds(112, 138, 96, 18);
			info.add(textField_ID_DNI);

			btnAdd = new JButton(" Add +");
			btnAdd.setFont(new Font("Times New Roman", Font.PLAIN, 10));
			btnAdd.addActionListener(this);
			btnAdd.setBounds(357, 66, 84, 20);
			info.add(btnAdd);

			btnDelete = new JButton("Delete -");
			btnDelete.addActionListener(this);
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
			
			textField_ID = new JTextField();
			textField_ID.setColumns(10);
			textField_ID.setBounds(240, 65, 96, 18);
			info.add(textField_ID);
			
			JLabel lblNewLabel_2_3_1 = new JLabel("ID:");
			lblNewLabel_2_3_1.setBounds(244, 43, 44, 12);
			info.add(lblNewLabel_2_3_1);
		}

		cargarTabla();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == exit) {
			V_Menu m = new V_Menu(cont);
			m.setVisible(true);
			this.dispose();
		}

		if (e.getSource() == textField) {
		}

		if (e.getSource() == search) {
		}

		if (e.getSource() == textField_name) {
		}

		if (e.getSource() == textField_phone) {
		}

		if (e.getSource() == textField_surname) {
		}

		if (e.getSource() == textField_ID_DNI) {
		}

		if (e.getSource() == btnAdd) {
			boolean valido = true;
			String name = textField_name.getText();
			String surname = textField_surname.getText();
			String phoneStr = textField_phone.getText();
			String dni = textField_ID_DNI.getText();
			int phoneInt = 0;

			if (name.isEmpty() || surname.isEmpty() || phoneStr.isEmpty() || dni.isEmpty()) {
				valido = false;
				JOptionPane.showMessageDialog(this, "Todos los campos deben estar rellenos.", "Error",
						JOptionPane.ERROR_MESSAGE);
			}

			if (valido) {
				try {
					if (!phoneStr.matches("\\d{9}")) {
						throw new PhoneNumException("Formato de teléfono incorrecto (deben ser 9 dígitos).");
					}
					phoneInt = Integer.parseInt(phoneStr);
				} catch (PhoneNumException ex) {
					valido = false;
					JOptionPane.showMessageDialog(this, ex.getMessage(), "Error de formato", JOptionPane.ERROR_MESSAGE);
				} catch (NumberFormatException ex) {
					valido = false;
					JOptionPane.showMessageDialog(this, "El teléfono debe contener solo números.", "Error de formato",
							JOptionPane.ERROR_MESSAGE);
				}
			}

			if (valido) {
				try {
					if (!dni.matches("\\d{8}[A-Za-z]")) {
						throw new DniException("Formato de DNI incorrecto (8 números y 1 letra).");
					}
				} catch (DniException ex) {
					valido = false;
					JOptionPane.showMessageDialog(this, ex.getMessage(), "Error de formato", JOptionPane.ERROR_MESSAGE);
				}
			}

			if (valido) {
				if (cont.checkDni(dni)) {
					valido = false;
					JOptionPane.showMessageDialog(this, "El DNI ya existe en la base de datos.", "Error",
							JOptionPane.ERROR_MESSAGE);
				} else if (cont.checkPhone(phoneInt)) {
					valido = false;
					JOptionPane.showMessageDialog(this, "El teléfono ya existe en la base de datos.", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}

			if (valido) {
				cont.addCostumer(name, surname, phoneInt, dni);
				JOptionPane.showMessageDialog(this, "Cliente añadido con éxito.", "Éxito",
						JOptionPane.INFORMATION_MESSAGE);
				cargarTabla();
			}
		}

		if (e.getSource() == btnDelete) {
			cont.deleteCostumer(Integer.parseInt(textField_ID.getText()));
		}

		if (e.getSource() == btnEdit) {
		}

		if (e.getSource() == btnClear) {
		}
	}
}
