package controlador;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import modelo.Conexion;

public class inscripcionCursos {
	 Connection con = null;
	 Conexion conect = new Conexion();
	 String dato;
	
	@FXML
	private ComboBox <String> cmbCurso;
	
	@FXML
	private Text hrsInicio;
	
	@FXML
	private Text hrsFinal;
	
	@FXML
	private Text fechaInicio;
	
	@FXML
	private Text fechaFinal;
	
	@FXML
	private Text estado;
	
	@FXML
	private Button btnCurso;
	
	@FXML
	void ComboBox(MouseEvent event) throws SQLException, IOException{
		System.out.println("OLA");
	}
	
	
	@FXML
	void initialize() throws SQLException {
   //Declaro variable
        
        ResultSet rst;
        //Conectarme a la base de datos        
        conect.conectar();
        System.out.println("Voy bien antes del combo");
        //Preparar para recuperar datos de la base de datos. Tabla clientes
        String query = "SELECT nombre from cursos order by nombre ASC";
        try (Statement stm = conect.getCon().createStatement()){ //Preparo el area para las consultas
            System.out.println("Voy bien de la consulta combo");
            rst = stm.executeQuery(query);
            System.out.println("Voy bien dentro combo");
            while (rst.next()) {
                dato = String.format("%s", rst.getString("nombre"));
                cmbCurso.getItems().add(dato);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
	
}
