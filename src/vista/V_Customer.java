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

		ArrayList<Customer> customers = cont.viewCustomers();
		for (Customer customer : customers) {
			model.addRow(new Object[] { customer.getIdCustomer(), customer.getNameCostumer(), customer.getSurname(),
					customer.getPhone(), customer.getDni() });
		}

		Tabla_Cust.setModel(model);
	}

	/**
	 * Create the dialog.
	 */
	public V_Customer(LoginControlador cont) {
		this.cont = cont;
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
			exit.addActionListener(this);
			exit.setFont(normalFont);
			exit.setBounds(910, 0, 100, 35);
			titulo.add(exit);

			lblNewLabel = new JLabel("Client Management");
			lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 18));
			lblNewLabel.setBounds(10, 5, 200, 25);
			titulo.add(lblNewLabel);
		}
		{
			subtitulo = new JPanel();
			subtitulo.setBounds(10, 60, 1024, 40);
			getContentPane().add(subtitulo);
			subtitulo.setLayout(null);

			lblNewLabel_1 = new JLabel("Search for Customer: ");
			lblNewLabel_1.setFont(normalFont);
			lblNewLabel_1.setBounds(10, 5, 150, 25);
			subtitulo.add(lblNewLabel_1);

			textField = new JTextField();
			textField.setFont(normalFont);
			textField.setBounds(160, 2, 250, 30);
			subtitulo.add(textField);
			textField.setColumns(10);

			search = new JButton("Search");
			search.addActionListener(this);
			search.setFont(normalFont);
			search.setBounds(420, 0, 100, 35);
			subtitulo.add(search);

			lblNewLabel_2 = new JLabel("Total:");
			lblNewLabel_2.setFont(normalFont);
			lblNewLabel_2.setBounds(550, 5, 80, 25);
			subtitulo.add(lblNewLabel_2);
		}
		{
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(10, 110, 1024, 470);
			getContentPane().add(scrollPane);

			Tabla_Cust = new JTable();
			Tabla_Cust.setFont(normalFont);
			Tabla_Cust.setRowHeight(30);
			Tabla_Cust.getTableHeader().setFont(boldFont);
			Tabla_Cust.addMouseListener(new java.awt.event.MouseAdapter() {
				@Override
				public void mouseClicked(java.awt.event.MouseEvent e) {
					int selectedRow = Tabla_Cust.getSelectedRow();
					if (selectedRow >= 0) {
						textField_ID.setText(Tabla_Cust.getValueAt(selectedRow, 0).toString());
						textField_name.setText(Tabla_Cust.getValueAt(selectedRow, 1).toString());
						textField_surname.setText(Tabla_Cust.getValueAt(selectedRow, 2).toString());
						textField_phone.setText(Tabla_Cust.getValueAt(selectedRow, 3).toString());
						textField_ID_DNI.setText(Tabla_Cust.getValueAt(selectedRow, 4).toString());
					}
				}
			});
			scrollPane.setViewportView(Tabla_Cust);
		}
		{
			info = new JPanel();
			info.setBounds(10, 600, 1024, 200);
			getContentPane().add(info);
			info.setLayout(null);

			lblNewLabel_1 = new JLabel("Client: ");
			lblNewLabel_1.setFont(boldFont);
			lblNewLabel_1.setBounds(10, 5, 120, 25);
			info.add(lblNewLabel_1);

			lblNewLabel_2_4 = new JLabel("Name:");
			lblNewLabel_2_4.setFont(normalFont);
			lblNewLabel_2_4.setBounds(20, 35, 100, 20);
			info.add(lblNewLabel_2_4);

			textField_name = new JTextField();
			textField_name.setFont(normalFont);
			textField_name.setColumns(10);
			textField_name.setBounds(20, 60, 180, 30);
			info.add(textField_name);

			lblNewLabel_2_2 = new JLabel("Surname:");
			lblNewLabel_2_2.setFont(normalFont);
			lblNewLabel_2_2.setBounds(230, 35, 100, 20);
			info.add(lblNewLabel_2_2);

			textField_surname = new JTextField();
			textField_surname.setFont(normalFont);
			textField_surname.setColumns(10);
			textField_surname.setBounds(230, 60, 180, 30);
			info.add(textField_surname);

			JLabel lblNewLabel_2_3_1 = new JLabel("ID:");
			lblNewLabel_2_3_1.setFont(normalFont);
			lblNewLabel_2_3_1.setBounds(440, 35, 100, 20);
			info.add(lblNewLabel_2_3_1);

			textField_ID = new JTextField();
			textField_ID.setFont(normalFont);
			textField_ID.setColumns(10);
			textField_ID.setBounds(440, 60, 180, 30);
			info.add(textField_ID);

			lblNewLabel_2_1 = new JLabel("Phone:");
			lblNewLabel_2_1.setFont(normalFont);
			lblNewLabel_2_1.setBounds(20, 100, 100, 20);
			info.add(lblNewLabel_2_1);

			textField_phone = new JTextField();
			textField_phone.setFont(normalFont);
			textField_phone.setColumns(10);
			textField_phone.setBounds(20, 125, 180, 30);
			info.add(textField_phone);

			lblNewLabel_2_3 = new JLabel("ID (DNI):");
			lblNewLabel_2_3.setFont(normalFont);
			lblNewLabel_2_3.setBounds(230, 100, 100, 20);
			info.add(lblNewLabel_2_3);

			textField_ID_DNI = new JTextField();
			textField_ID_DNI.setFont(normalFont);
			textField_ID_DNI.setColumns(10);
			textField_ID_DNI.setBounds(230, 125, 180, 30);
			info.add(textField_ID_DNI);

			btnAdd = new JButton(" Add +");
			btnAdd.setFont(normalFont);
			btnAdd.addActionListener(this);
			btnAdd.setBounds(680, 55, 120, 35);
			info.add(btnAdd);

			btnDelete = new JButton("Delete -");
			btnDelete.addActionListener(this);
			btnDelete.setFont(normalFont);
			btnDelete.setBounds(820, 55, 120, 35);
			info.add(btnDelete);

			btnEdit = new JButton("Edit []");
			btnEdit.setFont(normalFont);
			btnEdit.setBounds(680, 125, 120, 35);
			info.add(btnEdit);
			btnEdit.addActionListener(this);

			btnClear = new JButton("Clear []");
			btnClear.setFont(normalFont);
			btnClear.setBounds(820, 125, 120, 35);
			info.add(btnClear);
			btnClear.addActionListener(this);
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

		if (e.getSource() == search) {
		}

		if (e.getSource() == btnAdd) {
			add();
		}

		if (e.getSource() == btnDelete) {
			delete();
		}

		if (e.getSource() == btnEdit) {
			edit();
		}

		if (e.getSource() == btnClear) {
			clear();
		}
	}

	private void delete() {
		if (textField_ID.getText().trim().isEmpty()) {
			JOptionPane.showMessageDialog(this, "Please enter an ID to delete.", "Error",
					JOptionPane.ERROR_MESSAGE);
		} else {
			try {
				int id = Integer.parseInt(textField_ID.getText().trim());
				if (cont.deleteCostumer(id)) {
					JOptionPane.showMessageDialog(this, "Deleted successfully.", "Success",
							JOptionPane.INFORMATION_MESSAGE);
					cargarTabla();
				} else {
					JOptionPane.showMessageDialog(this, "Id no exist.", "Error", JOptionPane.ERROR_MESSAGE);
				}
			} catch (NumberFormatException ex) {
				JOptionPane.showMessageDialog(this, "The ID must be a valid number.", "Error",
						JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	private void add() {
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

	private void clear() {
		textField_name.setText("");
		textField_surname.setText("");
		textField_phone.setText("");
		textField_ID_DNI.setText("");
		textField_ID.setText("");
	}

	private void edit() {
		boolean valido = true;
		int id = 0;
		String name = textField_name.getText();
		String surname = textField_surname.getText();
		String phoneStr = textField_phone.getText();
		String dni = textField_ID_DNI.getText();
		int phoneInt = 0;

		if (name.isEmpty() || surname.isEmpty() || phoneStr.isEmpty() || dni.isEmpty()
				|| textField_ID.getText().trim().isEmpty()) {
			valido = false;
			JOptionPane.showMessageDialog(this, "Todos los campos deben estar rellenos.", "Error",
					JOptionPane.ERROR_MESSAGE);
		}

		if (valido) {
			try {
				id = Integer.parseInt(textField_ID.getText().trim());
			} catch (NumberFormatException ex) {
				valido = false;
				JOptionPane.showMessageDialog(this, "The ID must be a valid number.", "Error",
						JOptionPane.ERROR_MESSAGE);
			}
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
			if (cont.checkDni(dni, id)) {
				valido = false;
				JOptionPane.showMessageDialog(this, "El DNI ya existe en la base de datos.", "Error",
						JOptionPane.ERROR_MESSAGE);
			} else if (cont.checkPhone(phoneInt, id)) {
				valido = false;
				JOptionPane.showMessageDialog(this, "El teléfono ya existe en la base de datos.", "Error",
						JOptionPane.ERROR_MESSAGE);
			}
		}

		if (valido) {
			try {
				if (cont.editCostumer(id, textField_name.getText(), textField_surname.getText(),
						Integer.parseInt(textField_phone.getText()), textField_ID_DNI.getText())) {
					JOptionPane.showMessageDialog(this, "Edited successfully.", "Success",
							JOptionPane.INFORMATION_MESSAGE);
					cargarTabla();
				} else {
					JOptionPane.showMessageDialog(this, "Id no exist.", "Error", JOptionPane.ERROR_MESSAGE);
				}
			} catch (NumberFormatException ex) {
				JOptionPane.showMessageDialog(this, "The ID must be a valid number.", "Error",
						JOptionPane.ERROR_MESSAGE);
			}
		}
	}
}
