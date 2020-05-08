package views;
import java.io.IOException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import models.MyRemote;
import models.MyRemoteClient;
import models.MyRemoteImpl;
import models.ViewTransitionModelInterface;

public class LoginController implements Initializable{
	
	ViewTransitionModelInterface model;
	MyRemoteClient client;
	Registry registry;
	
	public void setModel(ViewTransitionModelInterface newModel, MyRemoteClient newClient)
	{
		model = newModel;
		client = newClient;
		//this.registry = registry;
		
	}
	
	@FXML
    private ChoiceBox<String> server;
	
    @FXML
    private TextField username;
    @FXML
    private Label serverLabel;
    @FXML
    private PasswordField password;

    @FXML
    void onClickLogin(ActionEvent event) {
    	
    	//authenticate
    	boolean loggedIn = client.askForLogin(username.accessibleTextProperty().get(), password.accessibleTextProperty().get());
		if(loggedIn)model.showBusinessPlans("../views/businessPlansByYear.fxml");
		else System.out.println("login");
		
		//since I start the server in the main method, i don't know what to do with the 
		//drop down menu
    }
    
   
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		server.getItems().addAll("Default","Other");
		
		
		
	}

}

