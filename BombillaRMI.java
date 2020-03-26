import java.rmi.*;

public interface BombillaRMI extends java.rmi.Remote {
	public void on() throws RemoteException;
	public void off() throws RemoteException;
	public boolean isOn() throws RemoteException;

	//Metodos get y set para la temperatura y el consumo
	public double getTemperature() throws RemoteException;
	public void setTemperature(double temperature) throws RemoteException;
	public double getConsumption() throws RemoteException;
	public void setConsumption(double temperature) throws RemoteException;
}
