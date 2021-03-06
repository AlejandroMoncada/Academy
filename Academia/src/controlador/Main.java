package controlador;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

	@Override
	public void start(Stage stage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/vistaPrincipal.fxml"));
	        Parent root = loader.load();      
	        Scene scene = new Scene(root);
	        // stage.getIcons().add(new Image("images/logo.png"));
	        stage.setTitle("Academia");
	        stage.setScene(scene);
	        stage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
