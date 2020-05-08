package main;


import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import models.*;
import views.LoginController;

public class MainBPView2 extends Application
{
	private MyRemoteClient client;
	
	public void setClient(MyRemoteClient newClient)
	{
		client = newClient;
	}
	
	@Override
	public void start(Stage stage) throws Exception
	{
		BusinessPlan BP = new VMOSA();
		BP.setYear(2020);
		BP.setDepartment("CS");
		BP.isEditable=false;
		
		BusinessPlan BPauto = new VMOSA();
		BPauto.year = 2030;
		BPauto.department = "MATH";
		BPauto.isEditable=true;
		//System.out.println(plan.getAllContent().getValue());
		// create a new client 
		
		ArrayList <BusinessPlan> storedBP=new ArrayList<BusinessPlan>();
		storedBP.add(BP);
		storedBP.add(BPauto);
		
		//initialize storedUser
		Person wynnie= new Person("wynnie","wynnie","CS", true);
		Person tom = new Person("Tom","Tom","CS",true);
		ArrayList <Person> storedUser=new ArrayList<Person>();
		storedUser.add(tom);
		storedUser.add(wynnie);
		try {
			Registry registry = LocateRegistry.createRegistry(1199);
			MyRemoteImpl server = new MyRemoteImpl();
			
			server.setStoredBP(storedBP);
			server.setStoredUser(storedUser);
        	MyRemote stub = (MyRemote) UnicastRemoteObject.exportObject(server, 0);
			registry.rebind("MyRemote", stub);
			MyRemote serverInterface=(MyRemote) registry.lookup("MyRemote");
			client=new MyRemoteClient(serverInterface);
			client.Hello();
			
		} catch (RemoteException | NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(MainBPView2.class.getResource("../views/loginPage.fxml"));
		
		BorderPane view = loader.load();
		
	
		LoginController cont = loader.getController();
		
		ViewTransitionModel vm = new ViewTransitionModel(view);
		cont.setModel(vm,client);
		Scene s = new Scene(view);
		stage.setScene(s);
		stage.show();
		
		/*loader.setLocation(MainBPView.class.getResource("../views/BPView.fxml"));
		BorderPane pane = loader.load();
		BPViewController cont = loader.getController();
		//cont.setModel(client.getCurrentBP());
		cont.setModel(plan,client);
		cont.setPane(pane);
		Scene sc = new Scene(pane);
		stage.setScene(sc);
		stage.show();*/
	}
	
	public static void main(String[] args)
	{
		launch(args);

		
	}
}
