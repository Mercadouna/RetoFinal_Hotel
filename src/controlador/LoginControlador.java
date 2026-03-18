package controlador;

import modelo.ImplementacionBD;
import modelo.*;
import vista.V_1;
import vista.VentanaLogin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class LoginControlador {
	AdminDAO dao = new ImplementacionBD();

	public void visualizarPantalla() {
		V_1 ven = new V_1(this);
		ven.setVisible(true);	
	}
}
