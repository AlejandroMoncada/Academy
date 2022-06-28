package controlador;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import modelo.Conexion;

public class registerController {
	Conexion con = new Conexion();
	
	@FXML
	private ComboBox<String>  cmbSelector;
	
	@FXML
	private TextField inputId;
	
	@FXML
	private TextField inputName;
	
	@FXML
	private TextField inputLastName;

	@FXML
	private TextField inputEmail;
	
	@FXML
	private TextField inputPassword;
	
	@FXML
	private TextField inputAge;
	
	@FXML
	private Button btnRegister;
	
	@FXML
	private Text txtEntrada;
	
	@FXML
	void registrarse(MouseEvent event) throws SQLException, IOException{
		
			String inpId = inputId.getText();
			String inpName = inputName.getText();
			String inpLastName = inputLastName.getText();
			String inpEmail = inputEmail.getText();
			String inpPassword = inputPassword.getText();
			String inpAge = inputAge.getText();
			String cmbType = cmbSelector.getValue();
	
			if(inpId==null || inpId.isEmpty())
		        txtEntrada.setText("Debe ingresar una Identificacion valida");
		    else if(inpName==null || inpName.isEmpty())
		        txtEntrada.setText("Debe ingresar un Nombre valido");
		    else if(inpLastName==null || inpLastName.isEmpty())
		        txtEntrada.setText("Debe ingresar un Apellido valido");
		    else if(inpEmail==null || inpEmail.isEmpty())
		        txtEntrada.setText("Debe ingresar un Email valido");
		    else if(inpPassword==null || inpPassword.isEmpty())
		        txtEntrada.setText("Debe ingresar un Contraseña valida");
		    else if(inpAge==null || inpAge.isEmpty())
		        txtEntrada.setText("Debe ingresar una Edad valida");
		    else if(cmbType==null || cmbType.isEmpty())
		        txtEntrada.setText("Debe ingresar un tipo valido");
		    else {
		    	if(cmbType == "Instructor"){
					String query = "INSERT into personas (id,nombre,apellido,email,edad,pw,tipo) values ('"+inpId+"','"+inpName+"','"+inpLastName+"','"+inpEmail+"','"+inpAge+"','"+inpPassword+"','"+1+"')";
					con.conectar();
					Answer(query);
				    
                    //System.out.println("entra");
                    //Cerrrar la ventana anterior
                    //System.out.println("btnIngr: "+btnIngr);
                    Stage stage = (Stage) btnRegister.getScene().getWindow();
                    stage.close();
                    //Crear la nueva ventana
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("../vista/HomeInstructores.fxml"));
                    Parent root = loader.load();
                    Scene scene = new Scene(root);
                    stage = new Stage();
                    //stage.close();
                    stage.setTitle("Home - Instructores");
                    stage.setScene(scene);
                    //FXMLHomeController data = (FXMLHomeController)loader.getController();
                    //data.setBienvenidoLbl("Hola : "+login+" Bienvenido.");
                    
                    stage.show();
                    con.desconectar();
                    
		    	}else if(cmbType == "Aprendiz"){
		    		String query = "INSERT into personas (id,nombre,apellido,email,edad,pw,tipo) values ('"+inpId+"','"+inpName+"','"+inpLastName+"','"+inpEmail+"','"+inpAge+"','"+inpPassword+"','"+0+"')";
					con.conectar();
					Answer(query);
					
					 //System.out.println("entra");
                    //Cerrrar la ventana anterior
                    //System.out.println("btnIngr: "+btnIngr);
                    Stage stage = (Stage) btnRegister.getScene().getWindow();
                    stage.close();
                    //Crear la nueva ventana
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("../vista/HomeApredizes.fxml"));
                    Parent root = loader.load();
                    Scene scene = new Scene(root);
                    stage = new Stage();
                    //stage.close();
                    stage.setTitle("Home - Aprendices");
                    stage.setScene(scene);
                    //FXMLHomeController data = (FXMLHomeController)loader.getController();
                    //data.setBienvenidoLbl("Hola : "+login+" Bienvenido.");
                    
                    stage.show();
                    con.desconectar();
		    	}
		    }
		}	
	
		
	private void Answer(String query){
		 try (Statement stm = con.getCon().createStatement()){
	            int rest = stm.executeUpdate(query);
	            if(rest != 0){
	                txtEntrada.setText("Datos Registrados con exito");
	                restaurarDatos();
	            }
	            else{
	                txtEntrada.setText("Error al grabar los datos por favor verifique");
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        con.desconectar();
		} 
	
	private void restaurarDatos(){
	    inputName.clear();
	    inputLastName.clear();
	    inputEmail.clear();
	    inputPassword.clear();
	    inputAge.clear();
	}
	
	@FXML
	void initialize(){ 
		cmbSelector.getItems().clear();
		cmbSelector.getItems().addAll("Instructor", "Aprendiz");
	  
	}
}
