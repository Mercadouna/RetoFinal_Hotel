package vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class ExtraAServices extends JDialog {

	private static final long serialVersionUID = 1L;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ExtraAServices dialog = new ExtraAServices();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ExtraAServices() {
		setBounds(100, 100, 1060, 850);
		getContentPane().setLayout(null);
		{
			JPanel panel = new JPanel();
			panel.setBounds(29, 42, 1015, 237);
			getContentPane().add(panel);
			panel.setLayout(null);
		}
	}

}
