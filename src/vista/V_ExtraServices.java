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
import java.awt.Color;
public class V_ExtraServices extends JDialog {
	private static final long serialVersionUID = 1L;
	private JTable Tabla_Cust;
	private JTextField textField_2;
	private JTextField textField_4;
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
	public V_ExtraServices() {
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
			
			JLabel lblNewLabel = new JLabel("Extra Service Managment");
			lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 13));
			lblNewLabel.setBounds(10, 4, 163, 12);
			TITULO.add(lblNewLabel);
		}
		{
			JPanel SUBTITULO = new JPanel();
			SUBTITULO.setBounds(10, 43, 586, 106);
			getContentPane().add(SUBTITULO);
			SUBTITULO.setLayout(null);
			
			JLabel lblNewLabel_1_1 = new JLabel("Breakfast");
			lblNewLabel_1_1.setBackground(new Color(192, 192, 192));
			lblNewLabel_1_1.setBounds(69, 27, 95, 19);
			SUBTITULO.add(lblNewLabel_1_1);
			
			JLabel lblNewLabel_1_2 = new JLabel("Media Pension");
			lblNewLabel_1_2.setBackground(new Color(192, 192, 192));
			lblNewLabel_1_2.setBounds(185, 27, 95, 19);
			SUBTITULO.add(lblNewLabel_1_2);
			
			JLabel lblNewLabel_1_3 = new JLabel("Parking");
			lblNewLabel_1_3.setBounds(305, 27, 95, 19);
			SUBTITULO.add(lblNewLabel_1_3);
			
			JLabel lblNewLabel_1_4 = new JLabel("Airport Transport");
			lblNewLabel_1_4.setBounds(122, 68, 95, 19);
			SUBTITULO.add(lblNewLabel_1_4);
			
			JLabel lblNewLabel_1_5 = new JLabel("Spa & Wellness");
			lblNewLabel_1_5.setBounds(432, 27, 95, 19);
			SUBTITULO.add(lblNewLabel_1_5);
			
			JLabel lblNewLabel_1_6 = new JLabel("Room Service");
			lblNewLabel_1_6.setBounds(252, 68, 95, 19);
			SUBTITULO.add(lblNewLabel_1_6);
			
			JLabel lblNewLabel_1_7 = new JLabel("Baby Cradle");
			lblNewLabel_1_7.setBounds(371, 68, 95, 19);
			SUBTITULO.add(lblNewLabel_1_7);
			
			JLabel lblNewLabel_1_8 = new JLabel("Service Catalog:");
			lblNewLabel_1_8.setFont(new Font("Times New Roman", Font.BOLD, 12));
			lblNewLabel_1_8.setBounds(10, 5, 92, 13);
			SUBTITULO.add(lblNewLabel_1_8);
		}
		{
			JPanel TABLA = new JPanel();
			TABLA.setBounds(10, 159, 586, 78);
			getContentPane().add(TABLA);
			TABLA.setLayout(null);
			
			Tabla_Cust = new JTable();
			Tabla_Cust.setBounds(208, 5, 0, 0);
			TABLA.add(Tabla_Cust);
		}
		{
			JPanel INFO = new JPanel();
			INFO.setBounds(10, 247, 586, 126);
			getContentPane().add(INFO);
			INFO.setLayout(null);
			
			JLabel lblNewLabel_1 = new JLabel("Extra Service:");
			lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 12));
			lblNewLabel_1.setBounds(6, 10, 92, 13);
			INFO.add(lblNewLabel_1);
			
			JLabel lblNewLabel_2_1 = new JLabel("Service name:");
			lblNewLabel_2_1.setBounds(16, 33, 82, 12);
			INFO.add(lblNewLabel_2_1);
			
			JLabel lblNewLabel_2_3 = new JLabel("Price (€):");
			lblNewLabel_2_3.setBounds(120, 33, 44, 12);
			INFO.add(lblNewLabel_2_3);
			
			textField_2 = new JTextField();
			textField_2.setColumns(10);
			textField_2.setBounds(10, 55, 96, 18);
			INFO.add(textField_2);
			
			textField_4 = new JTextField();
			textField_4.setColumns(10);
			textField_4.setBounds(119, 55, 96, 18);
			INFO.add(textField_4);
			
			JButton btnAadir = new JButton(" Add +");
			btnAadir.setFont(new Font("Times New Roman", Font.PLAIN, 10));
			btnAadir.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				}
			});
			btnAadir.setBounds(357, 30, 84, 20);
			INFO.add(btnAadir);
			
			JButton btnEliminar = new JButton("Delete -");
			btnEliminar.setFont(new Font("Times New Roman", Font.PLAIN, 10));
			btnEliminar.setBounds(451, 30, 84, 20);
			INFO.add(btnEliminar);
			
			JButton btnEdit = new JButton("Edit []");
			btnEdit.setFont(new Font("Times New Roman", Font.PLAIN, 10));
			btnEdit.setBounds(357, 69, 84, 20);
			INFO.add(btnEdit);
			
			JButton btnClear = new JButton("Clear []");
			btnClear.setBounds(451, 68, 84, 20);
			INFO.add(btnClear);
		}
		
		cargarTabla();
	}
}
