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

	public VentanaLogin(LoginControlador controlador) {
        // Guardamos el controlador en la variable de instancia
        this.cont = controlador;

        // ── 2. CONFIGURAR LA VENTANA PRINCIPAL (JFrame) ────────────────
        setTitle("Hotel Management System - Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // cerrar programa al cerrar ventana
        
        
		setBounds(100, 100, 1060, 860);
		getContentPane().setLayout(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		campoUsuario = new JTextField();
		campoUsuario.setBounds(567, 519, 105, 19);
		contentPane.add(campoUsuario);
		campoUsuario.setColumns(10);

		campoContrasena = new JPasswordField();
		campoContrasena.setBounds(567, 570, 105, 19);
		contentPane.add(campoContrasena);

		btnLogin = new JButton("Login");
		btnLogin.setBounds(477, 639, 85, 21);
		contentPane.add(btnLogin);
		btnLogin.addActionListener(this);

		lblNewLabel = new JLabel("Usuario");
		lblNewLabel.setBounds(406, 521, 109, 16);
		contentPane.add(lblNewLabel);

		Contarseña = new JLabel("Contarseña");
		Contarseña.setBounds(406, 572, 109, 16);
		contentPane.add(Contarseña);

		lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBounds(363, 698, 274, 23);
		contentPane.add(lblNewLabel_1);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {

	}
}

