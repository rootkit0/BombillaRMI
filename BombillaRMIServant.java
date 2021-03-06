import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;

// Implementacion Servidor
public class BombillaRMIServant extends UnicastRemoteObject implements BombillaRMI {

	private static final long serialVersionUID = 1;
	private boolean luzOn;
	private double temperature = 0;
	private double consumption = 0;

	private Vector<TemperatureListener> temperatureListeners = new Vector<TemperatureListener>();
	private Vector<StatusListener> statusListeners = new Vector<StatusListener>();

	// Constructor.
	public BombillaRMIServant() throws RemoteException {
		// Asignar valor por defecto = off
		setBombilla(false);
	}

	// Metodo remoto -> Enciende la Bombilla.
	public void on() throws RemoteException {
		// Encender Bombilla.
		setBombilla(true);
		statusChanged(true);
	}

	// Metodo remoto -> Apagar la Bombilla.
	public void off() throws RemoteException {
		// Apagar Bombilla.
		setBombilla(false);
		statusChanged(false);
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
		return (luzOn);
	}

	// Metodos get y set para la temperatura y el consumo
	public double getTemperature() throws RemoteException {
		return this.temperature;
	}
	public void setTemperature(double temperature) throws RemoteException {
		this.temperature = temperature;
		temperatureChanged(temperature);
	}
	public double getConsumption() throws RemoteException {
		return this.consumption;
	}
	public void setConsumption(double consumption) throws RemoteException {
		this.consumption = consumption;
	}

	// Metodos add y remove para los listeners
	public void addTemperatureListener(TemperatureListener listener) throws RemoteException {
		System.out.println("Anadiendo temperature listener: " + listener);
		temperatureListeners.add(listener);
	}
	public void removeTemperatureListener(TemperatureListener listener) throws RemoteException {
		System.out.println("Quitando temperature listener: " + listener);
		temperatureListeners.remove(listener);
	}
	public void addStatusListener(StatusListener listener) throws RemoteException {
		System.out.println("Anadiendo status listener: " + listener);
		statusListeners.add(listener);
	}
	public void removeStatusListener(StatusListener listener) throws RemoteException {
		System.out.println("Quitando status listener -" + listener);
		statusListeners.remove(listener);
	}

	// Llamar a los listeners para informar que la temperatura ha cambiado
	public void temperatureChanged(double temperature) {
		for (int i = 0; i < temperatureListeners.size(); ++i) {
			TemperatureListener listener = temperatureListeners.elementAt(i);
			try {
				listener.temperatureChanged(temperature);
			} catch (RemoteException e) {
				System.out.println("No se ha podido acceder al listener");
			}
		}
	}

	// Llamar a los listeners para informar que el status ha cambiado
	public void statusChanged(boolean status) {
		for (int i = 0; i < statusListeners.size(); ++i) {
			StatusListener listener = statusListeners.elementAt(i);
			try {
				listener.statusChanged(status);
			} catch (RemoteException e) {
				System.out.println("No se ha podido acceder al listener");
			}
		}
	}
}
