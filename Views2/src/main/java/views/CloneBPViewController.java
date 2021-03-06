package views;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import main.MainBPView;
import models.BusinessPlan;
import models.ConfirmationInterface;
import models.MyRemoteClient;
import models.Section;


public class CloneBPViewController 
{

	BusinessPlan model; 
	Section current;
	MyRemoteClient client;
	ConfirmationInterface model2;
	int year;
	@FXML
    private Button closeButton;
	public void setModel(BusinessPlan plan, MyRemoteClient client)
	{
		this.client = client;
		model = plan; 
		
	}
	public void setModel2(ConfirmationInterface model)
	{
		model2 = model;
	}
 
    @FXML
    private TextField yearText;

    private void getYear()
    {
    	year = Integer.parseInt(yearText.textProperty().getValue());
    }
    @FXML
    void onClickConfirm(ActionEvent event) 
    {
    	if(model2 == null)
    	{
    	getYear();
    	client.cloneBP(year,model);
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(BPViewController.class.getResource("../views/BPView.fxml"));
		Pane pane;
		try {
			pane = loader.load();
			BPViewController cont = loader.getController();
			Scene sc = new Scene(pane);
			Stage stage = new Stage();
			cont.setModel(model, client);
			stage.setScene(sc);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Stage stage = (Stage) closeButton.getScene().getWindow();
    	stage.close();
    	}
    	else
    	{
    		model2.confirmation();
    	}
    }
    @FXML
    void onClickCancel(ActionEvent event) 
    {
    	if(model2 == null)
    	{
    	Stage stage = (Stage) closeButton.getScene().getWindow();
    	stage.close();
    	FXMLLoader loader = new FXMLLoader();
		loader.setLocation(MainBPView.class.getResource("../views/BPView.fxml"));
		BorderPane pane;
		try {
			pane = loader.load();
			BPViewController cont = loader.getController();
			cont.setModel(model, client);
			cont.setPane(pane);
			Scene sc = new Scene(pane);
			stage.setScene(sc);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
    	}
    	else
    	{
    		model2.cancel();
    	}
			
    }
	
}
