package controlador;

<<<<<<< HEAD

=======
>>>>>>> f406ad74639574a2691705f3f061697bcc303b05
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
<<<<<<< HEAD
=======
	
>>>>>>> f406ad74639574a2691705f3f061697bcc303b05
	@Override
	public void start(Stage stage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/vistaPrincipal.fxml"));
	        Parent root = loader.load();      
	        Scene scene = new Scene(root);
	        // stage.getIcons().add(new Image("images/logo.png"));
	        stage.setTitle("Login");
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
