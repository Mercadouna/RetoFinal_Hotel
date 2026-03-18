package vista;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
public class V_Customer extends JDialog {
	private static final long serialVersionUID = 1L;
	private JTable Tabla_Cust;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField;
	/**
	 * Launch the application.
	 */
	
	
	
	
	public static void main(String[] args) {
		try {
			V_Customer dialog = new V_Customer();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
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
	public V_Customer() {
		setBounds(100, 100, 620, 420);
		getContentPane().setLayout(null);
		{
			JPanel TITULO = new JPanel();
			TITULO.setBounds(10, 11, 586, 22);
			getContentPane().add(TITULO);
			TITULO.setLayout(null);
			
			JButton Exit = new JButton("Exit");
			Exit.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				}
			});
			Exit.setBounds(492, 0, 84, 20);
			TITULO.add(Exit);
			
			JLabel lblNewLabel = new JLabel("Client Managment");
			lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 13));
			lblNewLabel.setBounds(10, 4, 139, 12);
			TITULO.add(lblNewLabel);
		}
		{
			JPanel SUBTITULO = new JPanel();
			SUBTITULO.setBounds(10, 43, 586, 23);
			getContentPane().add(SUBTITULO);
			SUBTITULO.setLayout(null);
			
			JLabel lblNewLabel_1 = new JLabel("Search for client: ");
			lblNewLabel_1.setBounds(10, 5, 95, 12);
			SUBTITULO.add(lblNewLabel_1);
			
			JButton Buscar = new JButton("Search");
			Buscar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				}
			});
			Buscar.setBounds(204, 1, 84, 20);
			SUBTITULO.add(Buscar);
			
			JLabel lblNewLabel_2 = new JLabel("Total:");
			lblNewLabel_2.setBounds(319, 5, 44, 12);
			SUBTITULO.add(lblNewLabel_2);
			
			textField = new JTextField();
			textField.setBounds(98, 2, 96, 18);
			SUBTITULO.add(textField);
			textField.setColumns(10);
		}
		{
			JPanel TABLA = new JPanel();
			TABLA.setBounds(10, 82, 586, 78);
			getContentPane().add(TABLA);
			TABLA.setLayout(null);
			
			Tabla_Cust = new JTable();
			Tabla_Cust.setBounds(208, 5, 0, 0);
			TABLA.add(Tabla_Cust);
		}
		{
			JPanel INFO = new JPanel();
			INFO.setBounds(10, 170, 586, 203);
			getContentPane().add(INFO);
			INFO.setLayout(null);
			
			JLabel lblNewLabel_1 = new JLabel("Client: ");
			lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 12));
			lblNewLabel_1.setBounds(10, 10, 92, 13);
			INFO.add(lblNewLabel_1);
			
			JLabel lblNewLabel_2 = new JLabel("Name:");
			lblNewLabel_2.setBounds(20, 44, 44, 12);
			INFO.add(lblNewLabel_2);
			
			JLabel lblNewLabel_2_1 = new JLabel("Phone:");
			lblNewLabel_2_1.setBounds(20, 116, 44, 12);
			INFO.add(lblNewLabel_2_1);
			
			JLabel lblNewLabel_2_2 = new JLabel("Surname:");
			lblNewLabel_2_2.setBounds(112, 44, 67, 12);
			INFO.add(lblNewLabel_2_2);
			
			JLabel lblNewLabel_2_3 = new JLabel("ID:");
			lblNewLabel_2_3.setBounds(114, 116, 44, 12);
			INFO.add(lblNewLabel_2_3);
			
			textField_1 = new JTextField();
			textField_1.setColumns(10);
			textField_1.setBounds(6, 66, 96, 18);
			INFO.add(textField_1);
			
			textField_2 = new JTextField();
			textField_2.setColumns(10);
			textField_2.setBounds(6, 138, 96, 18);
			INFO.add(textField_2);
			
			textField_3 = new JTextField();
			textField_3.setColumns(10);
			textField_3.setBounds(112, 66, 96, 18);
			INFO.add(textField_3);
			
			textField_4 = new JTextField();
			textField_4.setColumns(10);
			textField_4.setBounds(112, 138, 96, 18);
			INFO.add(textField_4);
			
			JButton btnAadir = new JButton(" Add +");
			btnAadir.setFont(new Font("Times New Roman", Font.PLAIN, 10));
			btnAadir.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				}
			});
			btnAadir.setBounds(357, 66, 84, 20);
			INFO.add(btnAadir);
			
			JButton btnEliminar = new JButton("Delete -");
			btnEliminar.setFont(new Font("Times New Roman", Font.PLAIN, 10));
			btnEliminar.setBounds(451, 66, 84, 20);
			INFO.add(btnEliminar);
			
			JButton btnEdit = new JButton("Edit []");
			btnEdit.setFont(new Font("Times New Roman", Font.PLAIN, 10));
			btnEdit.setBounds(357, 123, 84, 20);
			INFO.add(btnEdit);
			
			JButton btnClear = new JButton("Clear []");
			btnClear.setBounds(451, 122, 84, 20);
			INFO.add(btnClear);
		}
		
		cargarTabla();
	}
}
