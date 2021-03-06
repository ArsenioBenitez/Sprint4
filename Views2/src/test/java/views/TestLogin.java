package views;


import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.assertions.api.Assertions;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import javafx.beans.property.StringProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import main.MainBPView2;
import models.BusinessPlan;
import models.ConfirmationInterface;
import models.MyRemote;
import models.MyRemoteClient;
import models.MyRemoteImpl;
import models.Person;
import models.VMOSA;
import models.ViewTransitionModel;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(ApplicationExtension.class)
class TestLogin {
	MyRemoteClient client;
	@Start
	private void start (Stage stage) throws InterruptedException
	{
		//something is messing up with this part bc it cannot find the user 
		//create new person and bp
		BusinessPlan BP = new VMOSA();
		BP.setYear(2020);
		BP.setDepartment("CS");
		BP.isEditable=false;
				
		ArrayList <BusinessPlan> storedBP=new ArrayList<BusinessPlan>();
		storedBP.add(BP);
				
				//initialize storedUser
		Person mike=new Person("mike","tyson", "MAT", true);
				
		ArrayList <Person> storedUser=new ArrayList<Person>();
		storedUser.add(mike);
		try {
			Registry registry = LocateRegistry.createRegistry(1199);
			MyRemoteImpl server = new MyRemoteImpl();
			
			server.setStoredBP(storedBP);
			server.setStoredUser(storedUser);
        	MyRemote stub = (MyRemote) UnicastRemoteObject.exportObject(server, 0);
			registry.rebind("MyRemote", stub);
			MyRemote serverInterface=(MyRemote) registry.lookup("MyRemote");
			client=new MyRemoteClient(serverInterface);
			//client.Hello();
			
		} catch (RemoteException | NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(MainBPView2.class.getResource("../views/loginPage.fxml"));
		
		BorderPane view;
		try {
			view = loader.load();
			ViewTransitionModel vm = new ViewTransitionModel(view);
			LoginController cont = loader.getController();
			cont.setModel(vm,client);
			Scene s = new Scene(view);
			stage.setScene(s);
			stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	
	}
	
	private void enterUsername(FxRobot robot, String username) {
		robot.clickOn("#username");
		robot.write(username);
	}
	private void enterPass(FxRobot robot, String password) {
		robot.clickOn("#password");
		robot.write(password);
	}
	@Test
	void testUsername(FxRobot robot) {
		//tell him to enter a username and password
		try {
			Thread.sleep(1000);
			enterUsername(robot,"mike");
			enterPass(robot,"tyson");
			Thread.sleep(1000);
			robot.clickOn("#loginButton");
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//fail("Not yet implemented");
	}

}
