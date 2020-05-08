package views;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import main.MainBPView;
import models.BusinessPlan;
import models.ConfirmationInterface;
import models.MyRemoteClient;

public class UploadConfirmationViewController 
{
	
	BusinessPlan model;
	MyRemoteClient client;
	ConfirmationInterface model2;
    @FXML
    private Button closeButton;
	public void setModel(BusinessPlan plan,MyRemoteClient client)
	{
		this.client = client;
		model = plan;
	}
	public void setModel2(ConfirmationInterface model)
	{
		model2 = model;
	}
    @FXML
    void onClickCancel(ActionEvent event) 
    {
    	if (model2 == null)
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
    	Stage stage2 = (Stage) closeButton.getScene().getWindow();
    	stage2.close();
    	}
    	else
    	{
    	model2.cancel();
    	}
    }

    @FXML
    void onClickConfirm(ActionEvent event) {
    	if(model2 == null)
    	{
    		client.uploadBP();
    	}
    	else
    	{
    		model2.confirmation();
    	}
    }





}
