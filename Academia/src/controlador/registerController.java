package controlador;

import java.sql.SQLException;
import java.sql.Statement;

import javafx.fxml.FXML;
<<<<<<< HEAD
=======
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
>>>>>>> f406ad74639574a2691705f3f061697bcc303b05
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
<<<<<<< HEAD
=======
import javafx.stage.Stage;
>>>>>>> f406ad74639574a2691705f3f061697bcc303b05
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
	void registrarse(MouseEvent event) throws SQLException{
			String inpId = inputId.getText();
			String inpName = inputName.getText();
			String inpLastName = inputLastName.getText();
			String inpEmail = inputEmail.getText();
			String inpPassword = inputPassword.getText();
			String inpAge = inputAge.getText();
<<<<<<< HEAD
	
=======
			//String cmbType = cmbSelector.getValue();
			
			
>>>>>>> f406ad74639574a2691705f3f061697bcc303b05
			if(inpId==null || inpId.isEmpty())
		        txtEntrada.setText("Debe ingresar una Identificacion valida");
		    else if(inpName==null || inpName.isEmpty())
		        txtEntrada.setText("Debe ingresar un Nombre valido");
		    else if(inpLastName==null || inpLastName.isEmpty())
		        txtEntrada.setText("Debe ingresar un Apellido valido");
		    else if(inpEmail==null || inpEmail.isEmpty())
		        txtEntrada.setText("Debe ingresar un Email valido");
		    else if(inpPassword==null || inpPassword.isEmpty())
		        txtEntrada.setText("Debe ingresar un Contrase�a valida");
		    else if(inpAge==null || inpAge.isEmpty())
		        txtEntrada.setText("Debe ingresar una Edad valida");
<<<<<<< HEAD
=======
		    //else if(cmbType==null || cmbType.isEmpty())
		        //txtEntrada.setText("Debe ingresar un tipo de persona valido");
>>>>>>> f406ad74639574a2691705f3f061697bcc303b05
		    else{
				String query = "INSERT into personas (id,nombre,apellido,email,edad,pw) values ('"+inpId+"','"+inpName+"','"+inpLastName+"','"+inpEmail+"','"+inpAge+"','"+Integer.parseInt(inpPassword)+"')";
				con.conectar();
				
				 try (Statement stm = con.getCon().createStatement()){
			            int rest = stm.executeUpdate(query);
			            if(rest != 0){
			                txtEntrada.setText("Datos Registrados con exito");
			                restaurarDatos();
<<<<<<< HEAD
=======
			                
			                Stage stage = (Stage) btnRegister.getScene().getWindow();
		                    stage.close();
			                
			                FXMLLoader loader = new FXMLLoader(getClass().getResource("../vista/vistaPrincipal.fxml"));
		                    Parent root = loader.load();
		                    Scene scene = new Scene(root);
		                    stage = new Stage();
		                   
		                    stage.setTitle("Iniciar Sesion");
		                    stage.setScene(scene);
		                  
		                    stage.show();
		                    con.desconectar();
>>>>>>> f406ad74639574a2691705f3f061697bcc303b05
			            }
			            else{
			                txtEntrada.setText("Error al grabar los datos por favor verifique");
			            }
			        } catch (Exception e) {
			            e.printStackTrace();
			        }
			        con.desconectar();
			}
		}
	
	private void restaurarDatos(){
	    inputName.clear();
	    inputLastName.clear();
	    inputEmail.clear();
	    inputPassword.clear();
	    inputAge.clear();
	}
	
	@FXML
	void cmbRegister(){ 
		cmbSelector.getItems().clear();
		cmbSelector.getItems().addAll("Instructor", "Aprendiz");
<<<<<<< HEAD
	    //cmbSelector.setValue("M");
=======
	    cmbSelector.setValue("Tipo de persona");
>>>>>>> f406ad74639574a2691705f3f061697bcc303b05
	}
}
