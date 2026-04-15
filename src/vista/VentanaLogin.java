package vista;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import controlador.LoginControlador;
import java.awt.Toolkit;

public class VentanaLogin extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField campoUsuario;
	private JPasswordField campoContrasena;
	private JButton btnLogin;
	private JLabel lblNewLabel_1;
	private LoginControlador cont;
	private JLabel lblNewLabel;
	private JLabel Contarseña;

	// ── Helper: carga un ImageIcon desde /images/ ─────────────────────────────
	private ImageIcon loadIcon(String name) {
		ImageIcon icon = null;
		try {
			URL url = getClass().getResource("/images/" + name);
			if (url != null)
				icon = new ImageIcon(url);
		} catch (Exception ignored) {
		}
		return icon;
	}

	// ── Helper: aplica estilo hotel a un JTextField ───────────────────────────
	private void styleTextField(JTextField tf) {
		tf.setBackground(new Color(20, 35, 58));
		tf.setForeground(new Color(230, 200, 110));
		tf.setCaretColor(new Color(201, 168, 76));
		tf.setBorder(BorderFactory.createLineBorder(new Color(201, 168, 76), 1));
	}

	public VentanaLogin(LoginControlador controlador) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaLogin.class.getResource("/images/Logo_Hotel.png")));
		this.cont = controlador;
		setTitle("Hotel Management System - Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1060, 860);
		setResizable(false);

		// ── Fondo: JLabel con imagen ocupa todo el contentPane ────────────────
		// Al usar un JLabel como contentPane no hace falta sobreescribir Graphics
		ImageIcon bgIcon = loadIcon("login_bg_2.png");
		JLabel background = new JLabel();

		if (bgIcon != null) {
			Image scaled = bgIcon.getImage().getScaledInstance(1060, 860, Image.SCALE_SMOOTH);
			background.setIcon(new ImageIcon(scaled));
		}
		background.setLayout(null); // permite posicionar hijos con setBounds
		background.setBounds(0, 0, 1060, 860);
		setContentPane(background); // sustituye el contentPane por el JLabel

		// ── Logo centrado en la parte superior ────────────────────────────────
		ImageIcon logoIcon = loadIcon("Logo_Hotel.png");
		if (logoIcon != null) {
			Image scaled = logoIcon.getImage().getScaledInstance(280, 280, Image.SCALE_SMOOTH);
			JLabel lblLogo = new JLabel(new ImageIcon(scaled));
			lblLogo.setBounds(390, 80, 280, 280);
			background.add(lblLogo);
		}

		Font normalFont = new Font("Tahoma", Font.PLAIN, 14);
		Font boldFont = new Font("Tahoma", Font.BOLD, 14);
		Color labelColor = new Color(230, 200, 110);

		// ── Etiqueta Usuario ──────────────────────────────────────────────────
		lblNewLabel = new JLabel("Usuario");
		lblNewLabel.setFont(boldFont);
		lblNewLabel.setForeground(labelColor);
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setBounds(406, 490, 130, 20);
		background.add(lblNewLabel);

		// ── Campo Usuario ─────────────────────────────────────────────────────
		campoUsuario = new JTextField();
		campoUsuario.setFont(normalFont);
		campoUsuario.setColumns(10);
		campoUsuario.setBounds(406, 515, 245, 30);
		styleTextField(campoUsuario);
		background.add(campoUsuario);

		// ── Etiqueta Contraseña ───────────────────────────────────────────────
		Contarseña = new JLabel("Contraseña");
		Contarseña.setFont(boldFont);
		Contarseña.setForeground(labelColor);
		Contarseña.setHorizontalAlignment(SwingConstants.RIGHT);
		Contarseña.setBounds(406, 560, 130, 20);
		background.add(Contarseña);

		// ── Campo Contraseña ──────────────────────────────────────────────────
		campoContrasena = new JPasswordField();
		campoContrasena.setFont(normalFont);
		campoContrasena.setBounds(406, 585, 245, 30);
		styleTextField(campoContrasena);
		background.add(campoContrasena);

		// ── Botón Login ───────────────────────────────────────────────────────
		btnLogin = new JButton("Login");
		btnLogin.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnLogin.setBounds(453, 640, 151, 38);
		btnLogin.setBackground(new Color(20, 35, 58));
		btnLogin.setForeground(new Color(230, 200, 110));
		btnLogin.setBorder(BorderFactory.createLineBorder(new Color(201, 168, 76), 1));
		btnLogin.setFocusPainted(false);
		btnLogin.addActionListener(this);
		background.add(btnLogin);

		// ── Label de mensaje de error / feedback ──────────────────────────────
		lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setFont(normalFont);
		lblNewLabel_1.setForeground(new Color(220, 130, 130));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(363, 698, 330, 23);
		background.add(lblNewLabel_1);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String user;
		String password;
		if (e.getSource() == btnLogin) {
			user = campoUsuario.getText();
			password = new String(campoContrasena.getPassword());
			if (cont.login(user, password)) {
				V_Menu m = new V_Menu(cont);
				m.setVisible(true);
				this.dispose();
			} else {
				JOptionPane.showMessageDialog(this, "Usuario o contraseña incorrectos");
			}
		}
	}
}