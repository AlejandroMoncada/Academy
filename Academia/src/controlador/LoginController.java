package controlador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import modelo.Conexion;


public class LoginController {
    private String num1;
    private String operator;

    @FXML 
    private TextField inputEmail;

    @FXML 
    private TextField inputPw;

    @FXML 
    private Button loginButton;

    @FXML
    private Text labelMsg;

    Connection con = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    @FXML
    
    void ingresarBtn(ActionEvent event) throws SQLException{
        String login = inputEmail.getText();
        String clave = inputPw.getText();
        Conexion conect = new Conexion();
        conect.conectar();
        if(conect.isConectado()){
            String query = "SELECT id FROM personas WHERE email ='"+login+"'AND pw = '"+clave+"'";
            
            try(Statement stm = conect.getCon().createStatement()){    
                ResultSet rst2 = stm.executeQuery("SELECT * FROM personas WHERE email ='"+login+"'AND pw = '"+clave+"'");            
                ResultSet rst = stm.executeQuery(query);
                
                if(rst.next()){
                    
                    //System.out.println("entra");
                    //Cerrrar la ventana anterior
                    //System.out.println("btnIngr: "+btnIngr);
                    Stage stage = (Stage) loginButton.getScene().getWindow();
                    stage.close();
                    //Crear la nueva ventana
                    
                    System.out.println("inicio de sesion exitoso");

                    
                    
                    // System.out.println(rst2.getInt("tipo"));

                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/HomeInstructores.fxml"));
                    Parent root = loader.load();
                    Scene scene = new Scene(root);
                    Stage teatro = new Stage();
                    teatro.setTitle("Bienvenido");
                    teatro.setScene(scene);
                    teatro.show();
                    //Scene scene = new Scene(root);
                    //stage = new Stage();
                    //stage.close();
                    //stage.setTitle("Inicio");
                    //stage.setScene(scene);
                    //FXMLHomeController data = (FXMLHomeController)loader.getController();
                    //data.setBienvenidoLbl("Hola : "+login+" Bienvenido.");
                    
                    stage.close();
                    conect.desconectar();
                }
                else{
                    labelMsg.setText("Usuario o Clave no validos");
                }
            }catch (Exception e) {
                System.out.println("ERROR: Aborting...");
                e.printStackTrace();
            }
        }
    }
}
