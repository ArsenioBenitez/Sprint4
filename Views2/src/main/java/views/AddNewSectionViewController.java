package views;

import java.io.IOException;
import java.util.Arrays;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import main.MainBPView;
import models.BusinessPlan;
import models.ConfirmationInterface;
import models.MyRemoteClient;
import models.Section;

public class AddNewSectionViewController 
{
	BusinessPlan model; 
	Section parent;
	MyRemoteClient client;
	ConfirmationInterface model2;
	public void setModel(BusinessPlan plan, MyRemoteClient client)
	{
		this.client = client;
		model = plan; 
	}
	public void setParent(Section parent)
	{
		this.parent = parent;
		setName();
	}
	public void setModel2(ConfirmationInterface model)
	{
		model2 = model;
	}

    @FXML
    private Button closeButton;
    
    @FXML
    private Label SectionNameLable;

    @FXML
    private TextArea newContent;
    
    private void setName() 
    {
    	String[] sections = model.getSectionNames();
    	int ind = Arrays.asList(sections).indexOf(parent.getName());
    	SectionNameLable.textProperty().setValue(sections[ind+1]);
    	
    }
    public void setName2(String section)
    {
    	SectionNameLable.textProperty().setValue(section);
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
    	else {
    		model2.cancel();
    	}
    }

    @FXML
    void onClikcConfirm(ActionEvent event) 
    {    	
    	if(model2 == null)
    	{
    	model.addSection(parent);
    	parent.getChildren().get(parent.getChildren().size()-1).setContent(newContent.textProperty().getValue());
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
    	else {
    		model2.confirmation();
    	}


    }

}
