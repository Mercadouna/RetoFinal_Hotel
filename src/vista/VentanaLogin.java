package vista;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controlador.LoginControlador;

import javax.swing.JLabel;
import javax.swing.JTable;

public class VentanaLogin extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField campoUsuario;
	private JPasswordField campoContrasena;
	private JButton btnLogin;
	private JLabel lblNewLabel_1;
	private LoginControlador cont;
	private JLabel lblNewLabel;
	private JLabel Contarseña;
	private int alto;
	private int ancho;
	private JTable table;

	public VentanaLogin(LoginControlador controlador) {
        // Guardamos el controlador en la variable de instancia
        this.cont = controlador;
        this.setUndecorated(true);

        // ── 1. OBTENER EL TAMAÑO REAL DEL MONITOR ─────────────────────
        // Toolkit es la clase de Java que habla con el sistema operativo.
        // getScreenSize() devuelve un objeto Dimension con ancho y alto.
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        ancho = screenSize.width; 
        alto  = screenSize.height; 

        // ── 2. CONFIGURAR LA VENTANA PRINCIPAL (JFrame) ────────────────
        setTitle("Hotel Management System - Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // cerrar programa al cerrar ventana
        setResizable(false);       // no se puede redimensionar arrastrando el borde

        // Tamaño = toda la pantalla
        setSize(ancho, alto);
        // Posición = esquina superior izquierda (0,0)
        setLocation(0, 0);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		campoUsuario = new JTextField();
		campoUsuario.setBounds(200, 98, 105, 19);
		contentPane.add(campoUsuario);
		campoUsuario.setColumns(10);

		campoContrasena = new JPasswordField();
		campoContrasena.setBounds(200, 148, 105, 19);
		contentPane.add(campoContrasena);

		btnLogin = new JButton("Login");
		btnLogin.setBounds(160, 209, 85, 21);
		contentPane.add(btnLogin);
		btnLogin.addActionListener(this);

		lblNewLabel = new JLabel("Usuario");
		lblNewLabel.setBounds(78, 100, 109, 16);
		contentPane.add(lblNewLabel);

		Contarseña = new JLabel("Contarseña");
		Contarseña.setBounds(78, 151, 109, 16);
		contentPane.add(Contarseña);

		lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBounds(78, 240, 274, 23);
		contentPane.add(lblNewLabel_1);
		
		JPanel panel = new JPanel();
		panel.setBounds(437, 307, 392, 345);
		contentPane.add(panel);
		panel.setLayout(null);
		
		table = new JTable();
		table.setBounds(196, 5, 0, 0);
		panel.add(table);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {

	}
}

