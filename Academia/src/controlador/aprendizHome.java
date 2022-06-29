package controlador;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class aprendizHome {
	@FXML
	private Button btnInscripcion;
	
	@FXML
	void inscribirme(MouseEvent event)  throws IOException{
		  try{
	            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/inscribirCurso.fxml"));
	            
				
	            Parent root = loader.load();
	            Scene scene = new Scene(root);
	            Stage teatro = new Stage();
	            teatro.setTitle("Inscripciones");
	            teatro.setScene(scene);
	            teatro.show();
	            
	        }catch(IOException e){
	            e.printStackTrace();
	        
	    }
	}
	
}	
	
