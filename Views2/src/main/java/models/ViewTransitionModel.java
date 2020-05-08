package models;


import java.io.IOException;


import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import main.MainBPView2;
import views.LoginController;
import views.SelectorControllor;

public class ViewTransitionModel implements ViewTransitionModelInterface {

	BorderPane bpView;
	public ViewTransitionModel(BorderPane nextView)
	{
		bpView = nextView;
	}
	@Override
	public void showBusinessPlans(String path) {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(ViewTransitionModel.class.getResource(path));
		try {
			BorderPane view = loader.load();
			bpView.setCenter(view);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


}
