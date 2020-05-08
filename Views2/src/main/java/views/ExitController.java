package views;



import javafx.application.Platform;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;


import javafx.stage.Stage;
import models.*;

public class ExitController {
	
	Stage exitViewStage;
	BusinessPlan model; 
	Section current;
	MyRemoteClient client;
	ConfirmationInterface model2;
	public void setExitViewStage(Stage exitViewStage) {
		this.exitViewStage = exitViewStage;
	}
	
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
    void onCancelQuit(ActionEvent event) {
		if(model2 == null)
		{
		// Close the exit window if the cancel button is pressed.
		this.exitViewStage.close();
		}
		else
		{
			model2.cancel();
		}
    }

    @FXML
    void onConfirmQuit(ActionEvent event) 
    {	
    	if(model2 == null)
    	{
    	//Close the exit window if the quit button is pressed.
		this.exitViewStage.close();
		//----Insert a model's code to save data.----

		//-------------------------------------------
    	Platform.exit();
        System.exit(0);
    	}
    	else
    	{
    		model2.confirmation();
    	}
    
    
    }
}
