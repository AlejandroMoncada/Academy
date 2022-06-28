package controlador;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class InstruHome {

	@FXML
	private Text welcomeText;

	@FXML
	private Button cursosButton;
	
	@FXML
	void Cursos(MouseEvent event)  throws IOException{
		  try{
	            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/GestionCursos.fxml"));
	            
				
	            Parent root = loader.load();
	            Scene scene = new Scene(root);
	            Stage teatro = new Stage();
	            teatro.setTitle("Inicio - instructores");
	            teatro.setScene(scene);
	            teatro.show();
	            
	        }catch(IOException e){
	            e.printStackTrace();
	        
	    }
	    
	}
	
}
