import java.rmi.*;

public interface StatusListener extends java.rmi.Remote {

  public void statusChanged (boolean status) throws RemoteException;

}
