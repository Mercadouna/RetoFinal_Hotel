package vista;

import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controlador.LoginControlador;
import exceptions.MusicPlayerException;

import java.awt.FlowLayout;

public class V_1 extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private LoginControlador cont;

	public V_1(LoginControlador loginControlador) {
		this.cont = loginControlador;
		try {
			MusicPlayer.play();
		} catch (MusicPlayerException e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}

		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		contentPane = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				URL imgUrl = V_1.class.getResource("/images/Hotel_defin.gif");
				if (imgUrl != null) {
					Image img = new ImageIcon(imgUrl).getImage();
					g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
				}
			}
		};
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		this.setFocusable(true);
		this.requestFocusInWindow();

		this.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					VentanaLogin login = new VentanaLogin(cont);
					login.setVisible(true);
					dispose();
				}
			}
		});
	}

}
