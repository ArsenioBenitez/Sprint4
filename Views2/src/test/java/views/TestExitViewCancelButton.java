package views;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import main.MainBPView;



@ExtendWith(ApplicationExtension.class)
public class TestExitViewCancelButton 
{
	
//	int cloneCalled = 0;
//	int uploadCalled = 0;
//	int closeCalled = 0;
//	int removeCalled = 0;
//	int selectCalled = 0;
//	int addCalled = 0;
	Stage primaryStage;
	
	@Start
	private void start(Stage stage) throws Exception
	{
		this.primaryStage = stage;		
		Platform.setImplicitExit(false);
		FXMLLoader exitViewLoader = new FXMLLoader();
		System.out.println(MainBPView.class.getResource("../views/ExitView.fxml"));
		exitViewLoader.setLocation(MainBPView.class.getResource("../views/ExitView.fxml"));
		Pane exitView = exitViewLoader.load();
		Scene exitScene = new Scene(exitView);
		this.primaryStage.setScene(exitScene);
		ExitController cont = exitViewLoader.getController();
		cont.setExitViewStage(stage);
		System.out.println(cont.toString());
		this.primaryStage.show();
	}
	
	@Test
	void testButtons(FxRobot robot)
	{
	    robot.clickOn("#cancelQuitButton");
		
	};
	
	
}
