package views;

import java.io.IOException;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import main.MainBPView;
import models.BYBPlan;
import models.BusinessPlan;
import models.CNTRAssessment;
import models.MyRemote;
import models.MyRemoteClient;
import models.Section;
import models.VMOSA;
import models.ViewTransitionModel;
import models.ViewTransitionModelInterface;
import models.ViewTransitionaModelInterface;

public class SelectorControllor implements Initializable {

	ViewTransitionModelInterface model;
	BusinessPlan plan;
	MyRemoteClient client;
	Stage  stage;
	@FXML private TableView<BusinessPlan> tableView;
	@FXML private TableColumn<BusinessPlan,Integer> year;
	@FXML private TableColumn<BusinessPlan,String> department;
	
	
	@FXML
    void createNewBP(ActionEvent event) {
    			
    	System.out.println("create new BP");

    }
	
	public void setModel(ViewTransitionModelInterface newModel, MyRemoteClient client)
	{
		model = newModel;
		this.client = client;
	}
	@FXML
	void onClickView(ActionEvent event) {
			/*FXMLLoader loader = new FXMLLoader();
			loader.setLocation(LoginController.class.getResource("../views/BPView.fxml"));
			try {
				//initialize storedBP
				BusinessPlan plan = new CNTRAssessment();
				Section current = plan.root;
				current.setContent("root");
				plan.addSection(current);
				current.getChildren().get(1).setContent("goal2");;
				current = current.getChildren().get(0);
				//System.out.println(current.getParent());
				current.setContent("goal");
				current.addChild(new Section("Program Goals and Student Learning Objective"));
				current.getChildren().get(0).setContent("objective1");
				current.getChildren().get(1).setContent("objective2");
				BorderPane pane = loader.load();
				BPViewController cont = loader.getController();
				//cont.setModel(client.getCurrentBP());
				cont.setModel(plan,client);
				cont.setPane(pane);
				Scene sc = new Scene(pane);
				stage = new Stage();
				stage.setScene(sc);
				stage.show();
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
		BusinessPlan selectedBP = tableView.getSelectionModel().getSelectedItem();
		if(client.getCurrentBP().isEditable)
    	{
    		

    		FXMLLoader loader = new FXMLLoader();
    		loader.setLocation(MainBPView.class.getResource("../views/BPView.fxml"));
    		BorderPane pane;
			try {
				pane = loader.load();
	    		BPViewController cont = loader.getController();
	    		//cont.setModel(client.getCurrentBP());
	    		cont.setModel(selectedBP,client);
	    		cont.setPane(pane);
	    		cont.setTransitionalModel(model);
	    		Scene sc = new Scene(pane);
	    		stage.setScene(sc);
	    		stage.show();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

    	}
    	else
    	{
    		FXMLLoader loader = new FXMLLoader();
    		loader.setLocation(MainBPView.class.getResource("../views/NonEditableView.fxml"));
    		BorderPane pane;
			try {
				pane = loader.load();
	    		NonEditableViewController cont = loader.getController();
	    		//cont.setModel(client.getCurrentBP());
	    		cont.setModel(client.getCurrentBP(),client);
	    		cont.setPane(pane);
	    		cont.setTransitionalModel(model);
	    		Scene sc = new Scene(pane);
	    		stage.setScene(sc);
	    		stage.show();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			
			}
    		
    	}

}
	  
	 

	@FXML//idk if this is necessary. It was just a function to get bp selected
	void selectBusinessPlan(MouseEvent event) 
	{
	    	System.out.println("View");
	    		
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		//this should grab the client's current BP and display it on the table
		client.askForBP(2000);
		client.getCurrentBP();
		year.setCellValueFactory(new PropertyValueFactory<BusinessPlan,Integer>("year"));
		department.setCellValueFactory(new PropertyValueFactory<BusinessPlan,String>("department"));
		ObservableList<BusinessPlan> bplans = FXCollections.observableArrayList();
		bplans.add(plan);
		tableView.setItems(bplans);
		
	}
	
}

