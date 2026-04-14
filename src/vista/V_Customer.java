package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.net.URL;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.DefaultTableCellRenderer;

import controlador.LoginControlador;

import java.util.ArrayList;
import modelo.ImplementacionBD; 
import modelo.Customer;
import javax.swing.JComboBox;
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
	private LoginControlador cont;
	private JPanel titulo;
	private JButton exit;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
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
	private JLabel lblNewLabel_2_3_1;
	private JScrollPane scrollPane;

	// ── Helper: JPanel que pinta una imagen de fondo ─────────────────────────
	private static class ImagePanel extends JPanel {
		private static final long serialVersionUID = 1L;

		// Almacena la imagen de fondo que se pintará en el panel
		private Image bgImage;

		public ImagePanel(String resourcePath) {
			// Hace el panel transparente para que no tape la imagen con un color sólido
			setOpaque(false);
			try {
				// Busca la imagen dentro del classpath del proyecto usando su ruta
				URL url = getClass().getResource(resourcePath);
				// Solo carga la imagen si la URL se encontró, evitando NullPointerException
				if (url != null)
					bgImage = new ImageIcon(url).getImage();
			} catch (Exception ignored) {
				// Si la imagen no existe o falla la carga, el panel queda sin fondo
			}
		}

		@Override
		protected void paintComponent(Graphics g) {
			// Solo intenta dibujar si la imagen se cargó correctamente
			if (bgImage != null)
				// Dibuja la imagen estirada al tamaño exacto del panel
				g.drawImage(bgImage, 0, 0, getWidth(), getHeight(), this);
			// Llama al pintado original para que los componentes hijos se dibujen encima
			super.paintComponent(g);
		}
	}

	// ── Helper: carga un ImageIcon desde /images/ ─────────────────────────────
	private ImageIcon loadIcon(String name) {
		ImageIcon icon = null;
		try {
			// Construye la ruta completa desde la raíz del classpath
			URL url = getClass().getResource("/images/" + name);
			// Si encontró el archivo, crea el ImageIcon listo para usar
			if (url != null)
				icon = new ImageIcon(url);
		} catch (Exception ignored) {
			// Si falla, icon se queda null sin romper el programa
		}
		// Único return: devuelve el icono cargado, o null si algo falló
		return icon;
	}

	// ── Helper: aplica estilo hotel a un JTextField ───────────────────────────
	private void styleTextField(JTextField tf) {
	}

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

		// Fondo general de la ventana en navy oscuro
		getContentPane().setBackground(new Color(10, 20, 35));

		Font normalFont = new Font("Tahoma", Font.PLAIN, 14);
		Font boldFont = new Font("Tahoma", Font.BOLD, 16);

		// Colores reutilizables del tema hotel
		Color btnBg = new Color(20, 35, 58);
		Color btnFg = new Color(230, 200, 110);
		Color btnBorder = new Color(201, 168, 76);

		{
			// ── Panel título con fondo decorativo ─────────────────────────────
			titulo = new ImagePanel("/images/cust_titulo_bg.png");
			titulo.setBounds(10, 10, 1024, 60);
			getContentPane().add(titulo);
			titulo.setLayout(null);

			// Etiqueta del título en dorado claro
			lblNewLabel = new JLabel("Customer Management");
			lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 18));
			lblNewLabel.setForeground(new Color(230, 200, 110));
			lblNewLabel.setBounds(24, 19, 220, 25);
			titulo.add(lblNewLabel);

			// Botón Exit: tonos rojizos para diferenciarlo
			exit = new JButton("  Exit");
			exit.setBounds(914, 14, 100, 35);
			titulo.add(exit);
			exit.addActionListener(this);
			exit.setFont(normalFont);
			exit.setBackground(new Color(35, 15, 15));
			exit.setForeground(new Color(220, 130, 130));
			exit.setBorder(BorderFactory.createLineBorder(new Color(160, 70, 70), 1));
			exit.setIcon(loadIcon("ico_exit.png"));
			exit.setHorizontalAlignment(SwingConstants.LEFT);
			exit.setIconTextGap(8);
			exit.setFocusPainted(false);
		}
		{
			// ── Tabla con estilo hotel ─────────────────────────────────────────
			scrollPane = new JScrollPane();
			scrollPane.setBounds(10, 110, 1024, 470);
			scrollPane.setBorder(BorderFactory.createLineBorder(new Color(201, 168, 76), 1));
			getContentPane().add(scrollPane);

			Tabla_Cust = new JTable();
			Tabla_Cust.setFont(normalFont);
			Tabla_Cust.setRowHeight(30);
			Tabla_Cust.setBackground(new Color(14, 26, 44));
			Tabla_Cust.setForeground(new Color(220, 200, 150));
			Tabla_Cust.setGridColor(new Color(40, 65, 100));
			Tabla_Cust.setSelectionBackground(new Color(201, 168, 76));
			Tabla_Cust.setSelectionForeground(new Color(10, 20, 35));

			// Cabecera de la tabla en estilo hotel
			Tabla_Cust.getTableHeader().setFont(boldFont);
			Tabla_Cust.getTableHeader().setBackground(new Color(10, 20, 35));
			Tabla_Cust.getTableHeader().setForeground(new Color(201, 168, 76));
			Tabla_Cust.getTableHeader().setBorder(BorderFactory.createLineBorder(new Color(201, 168, 76), 1));

			Tabla_Cust.addMouseListener(new java.awt.event.MouseAdapter() { // esto es para cuando seleccionamos una
																			// fila, se copie toda la info.
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
			scrollPane.getViewport().setBackground(new Color(14, 26, 44));
		}
		{
			// ── Panel info / formulario con fondo decorativo ──────────────────
			info = new ImagePanel("/images/cust_info_bg.png");
			info.setBounds(10, 600, 1024, 200);
			getContentPane().add(info);
			info.setLayout(null);

			lblNewLabel_1 = new JLabel("Client: ");
			lblNewLabel_1.setFont(boldFont);
			lblNewLabel_1.setForeground(new Color(201, 168, 76));
			lblNewLabel_1.setBounds(10, 5, 120, 25);
			info.add(lblNewLabel_1);

			lblNewLabel_2_4 = new JLabel("Name:");
			lblNewLabel_2_4.setFont(normalFont);
			lblNewLabel_2_4.setForeground(new Color(230, 200, 110));
			lblNewLabel_2_4.setBounds(20, 35, 100, 20);
			info.add(lblNewLabel_2_4);

			textField_name = new JTextField();
			textField_name.setFont(normalFont);
			textField_name.setColumns(10);
			textField_name.setBounds(20, 60, 180, 30);
			styleTextField(textField_name);
			info.add(textField_name);

			lblNewLabel_2_2 = new JLabel("Surname:");
			lblNewLabel_2_2.setFont(normalFont);
			lblNewLabel_2_2.setForeground(new Color(230, 200, 110));
			lblNewLabel_2_2.setBounds(230, 35, 100, 20);
			info.add(lblNewLabel_2_2);

			textField_surname = new JTextField();
			textField_surname.setFont(normalFont);
			textField_surname.setColumns(10);
			textField_surname.setBounds(230, 60, 180, 30);
			styleTextField(textField_surname);
			info.add(textField_surname);

			lblNewLabel_2_3_1 = new JLabel("ID:");
			lblNewLabel_2_3_1.setFont(normalFont);
			lblNewLabel_2_3_1.setForeground(new Color(230, 200, 110));
			lblNewLabel_2_3_1.setBounds(440, 35, 100, 20);
			info.add(lblNewLabel_2_3_1);

			textField_ID = new JTextField();
			textField_ID.setFont(normalFont);
			textField_ID.setColumns(10);
			textField_ID.setBounds(440, 60, 180, 30);
			styleTextField(textField_ID);
			info.add(textField_ID);

			lblNewLabel_2_1 = new JLabel("Phone:");
			lblNewLabel_2_1.setFont(normalFont);
			lblNewLabel_2_1.setForeground(new Color(230, 200, 110));
			lblNewLabel_2_1.setBounds(20, 100, 100, 20);
			info.add(lblNewLabel_2_1);

			textField_phone = new JTextField();
			textField_phone.setFont(normalFont);
			textField_phone.setColumns(10);
			textField_phone.setBounds(20, 125, 180, 30);
			styleTextField(textField_phone);
			info.add(textField_phone);

			lblNewLabel_2_3 = new JLabel("ID (DNI):");
			lblNewLabel_2_3.setFont(normalFont);
			lblNewLabel_2_3.setForeground(new Color(230, 200, 110));
			lblNewLabel_2_3.setBounds(230, 100, 100, 20);
			info.add(lblNewLabel_2_3);

			textField_ID_DNI = new JTextField();
			textField_ID_DNI.setFont(normalFont);
			textField_ID_DNI.setColumns(10);
			textField_ID_DNI.setBounds(230, 125, 180, 30);
			styleTextField(textField_ID_DNI);
			info.add(textField_ID_DNI);

			// Botón Add: tonos verdes
			btnAdd = new JButton("  Add +");
			btnAdd.setFont(normalFont);
			btnAdd.addActionListener(this);
			btnAdd.setBounds(680, 55, 120, 35);
			btnAdd.setBackground(new Color(15, 40, 20));
			btnAdd.setForeground(new Color(100, 200, 120));
			btnAdd.setBorder(BorderFactory.createLineBorder(new Color(70, 160, 90), 1));
			btnAdd.setIcon(loadIcon("ico_add.png"));
			btnAdd.setHorizontalAlignment(SwingConstants.LEFT);
			btnAdd.setIconTextGap(8);
			btnAdd.setFocusPainted(false);
			info.add(btnAdd);

			// Botón Delete: tonos rojizos
			btnDelete = new JButton("  Delete");
			btnDelete.addActionListener(this);
			btnDelete.setFont(normalFont);
			btnDelete.setBounds(820, 55, 120, 35);
			btnDelete.setBackground(new Color(35, 15, 15));
			btnDelete.setForeground(new Color(220, 130, 130));
			btnDelete.setBorder(BorderFactory.createLineBorder(new Color(160, 70, 70), 1));
			btnDelete.setIcon(loadIcon("ico_delete.png"));
			btnDelete.setHorizontalAlignment(SwingConstants.LEFT);
			btnDelete.setIconTextGap(8);
			btnDelete.setFocusPainted(false);
			info.add(btnDelete);

			// Botón Edit: tonos dorados
			btnEdit = new JButton("  Edit");
			btnEdit.setFont(normalFont);
			btnEdit.setBounds(680, 125, 120, 35);
			btnEdit.setBackground(btnBg);
			btnEdit.setForeground(btnFg);
			btnEdit.setBorder(BorderFactory.createLineBorder(btnBorder, 1));
			btnEdit.setIcon(loadIcon("ico_edit.png"));
			btnEdit.setHorizontalAlignment(SwingConstants.LEFT);
			btnEdit.setIconTextGap(8);
			btnEdit.setFocusPainted(false);
			info.add(btnEdit);
			btnEdit.addActionListener(this);

			// Botón Clear: tonos dorados
			btnClear = new JButton("  Clear");
			btnClear.setFont(normalFont);
			btnClear.setBounds(820, 125, 120, 35);
			btnClear.setBackground(btnBg);
			btnClear.setForeground(btnFg);
			btnClear.setBorder(BorderFactory.createLineBorder(btnBorder, 1));
			btnClear.setIcon(loadIcon("ico_clear.png"));
			btnClear.setHorizontalAlignment(SwingConstants.LEFT);
			btnClear.setIconTextGap(8);
			btnClear.setFocusPainted(false);
			info.add(btnClear);
			btnClear.addActionListener(this);
		}

		cargarTabla();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		getContentPane().add(scrollPane); 
		getContentPane().add(titulo); 
		getContentPane().add(info);

		// TODO Auto-generated method stub
		if (e.getSource() == exit) {
			V_Menu m = new V_Menu(cont);
			m.setVisible(true);
			this.dispose();
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
					JOptionPane.showMessageDialog(this, "Deleted successfully.", "Success ",
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