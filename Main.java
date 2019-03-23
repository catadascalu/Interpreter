package application;
	
import exception.MyException;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			//GridPane root = (GridPane)FXMLLoader.load(getClass().getResource("ChoosePrg.fxml"));
			//Scene scene = new Scene(root,400,400);
			//Scene scene = new Scene
			//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			FXMLLoader loader = new FXMLLoader(getClass().getResource("ChoosePrg.fxml"));
			   Scene scene = new Scene(loader.load());
			   ChoosePrgController controller = loader.getController();
			   //GridPane root = loader.load();
			   primaryStage.setScene(scene);
			   controller.setStage(primaryStage);
			primaryStage.show();
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
