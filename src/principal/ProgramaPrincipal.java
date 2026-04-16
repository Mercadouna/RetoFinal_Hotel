package principal;
import vista.*;
import controlador.LoginControlador;
import modelo.*;

public class ProgramaPrincipal {
	
	public static void main(String[] args) {
		LoginControlador cont = new LoginControlador();
		cont.visualizarPantalla();
	}
}
   