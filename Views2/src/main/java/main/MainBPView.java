package main;

import java.io.IOException;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import models.*;
import views.BPViewController;
import views.ExitController;


public class MainBPView extends Application
{
	private MyRemoteClient client;
	
	public void setClient(MyRemoteClient newClient)
	{
		client = newClient;
	}
	
	@Override
	public void start(Stage stage) throws Exception
	{
		BusinessPlan plan = new CNTRAssessment();
		Section current = plan.root;
		current.setContent("root");
		plan.addSection(current);
		current.getChildren().get(1).setContent("goal2");;
		current = current.getChildren().get(0);
		System.out.println(current.getParent());
		current.setContent("goal");
		current.addChild(new Section("Program Goals and Student Learning Objective"));
		current.getChildren().get(0).setContent("objective1");
		current.getChildren().get(1).setContent("objective2");
		//System.out.println(plan.getAllContent().getValue());
		
		// ---------Exit View Config------------
		stage.setOnCloseRequest(
				new EventHandler<WindowEvent>() {
					@Override
					public void handle(WindowEvent event) {
						//Stop exiting by event.consume()
		    			FXMLLoader exitViewLoader = new FXMLLoader();
		    			exitViewLoader.setLocation(MainBPView.class.getResource("../views/ExitView.fxml"));
		    			Pane exitView;
						try {
							exitView = exitViewLoader.load();
						} catch (IOException e) {
							exitView = null;
							e.printStackTrace();
						}
						ExitController exitController = exitViewLoader.getController();
						//Create a new stage for a popup window and set the exit view.
		    			Scene exitScene = new Scene(exitView);
		                final Stage dialog = new Stage();
		                dialog.initModality(Modality.APPLICATION_MODAL);
		                dialog.initOwner(stage);
		                dialog.setScene(exitScene);
						exitController.setExitViewStage(dialog);
		                dialog.show();
						event.consume();
					}
		         });
		// ---------Exit View Config------------
		
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(MainBPView.class.getResource("../views/BPView.fxml"));
		BorderPane pane = loader.load();
		BPViewController cont = loader.getController();
		//cont.setModel(client.getCurrentBP());
		cont.setModel(plan,client);
		cont.setPane(pane);
		Scene sc = new Scene(pane);
		stage.setScene(sc);
		stage.show();
	}
	
	public static void main(String[] args)
	{
		launch(args);

		
	}
}
