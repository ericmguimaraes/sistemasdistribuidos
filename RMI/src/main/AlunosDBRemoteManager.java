package main;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class AlunosDBRemoteManager {

	static AlunosDB alunosDB;

	public AlunosDBRemoteManager() {
		super();
		alunosDB = new AlunosDB();
	}

	public static void main(String[] args) {
		try {
			String name = "gerenciador";
			if (System.getSecurityManager() == null) {
				System.setSecurityManager(new SecurityManager());
			}
			RMInterface outsider = (RMInterface) UnicastRemoteObject.exportObject((Remote) alunosDB, 8412);
			Registry registry = LocateRegistry.getRegistry();
            registry.rebind(name, (Remote) outsider);
            System.out.println("ComputeEngine bound");
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

}
