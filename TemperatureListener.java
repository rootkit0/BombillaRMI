import java.rmi.*;

public interface TemperatureListener extends java.rmi.Remote {

  public void temperatureChanged(double temperatura) throws RemoteException;
  
}
