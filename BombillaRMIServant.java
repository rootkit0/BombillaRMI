import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;

// Implementacion Servidor
public class BombillaRMIServant extends UnicastRemoteObject implements BombillaRMI {
	
	private static final long serialVersionUID = 1;
	private boolean luzOn;
	
	// Constructor.
	public BombillaRMIServant() throws RemoteException {
		// Asignar valor por defecto = off
		setBombilla(false);
	}

	// Metodo remoto -> Enciende la Bombilla.
	public void on() throws RemoteException {
		// Encender Bombilla.
		setBombilla(true);
	}

	// Metodo remoto -> Apagar la Bombilla.	
	public void off() throws RemoteException {
		// Apagar Bombilla.
		setBombilla(false);
	}

	// Metodo remoto -> Devuelve el estado de la Bombilla.	
	public boolean isOn() throws RemoteException {
		return getBombilla();
	}
	
	// Metodo local -> Modificar el estado de la bombilla.
	public void setBombilla(boolean valor) {
		luzOn = valor;
	}
	
	// Metodo local -> Devovler el estado de la bombilla.
	public boolean getBombilla() {
		return(luzOn);
	}
}
