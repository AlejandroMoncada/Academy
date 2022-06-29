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
	private Text txtEntrada;
	
	@FXML
	void buscar(MouseEvent event) throws SQLException, IOException{
		  String combo = cmbCurso.getValue();
		    if (combo==null || combo.isEmpty()) {
		        txtEntrada.setText("Seleccione un curso a buscar");
		    }
		    else{
		    	conect.conectar();
		        try(Statement stm = conect.getCon().createStatement()){
		            String nombreCurso = cmbCurso.getValue();
		            ResultSet rta = stm.executeQuery("SELECT c.nombre, hr.fechaEntrada, hr.fechaSalida, hr.horaEntrada, hr.horaSalida, e.nombre FROM cursos as c JOIN horarios as hr JOIN estados as e WHERE c.horario = hr.id AND c.estado = e.id AND c.nombre = '"+nombreCurso+"';");
		            if(rta.next()){
		            	hrsInicio.setText(rta.getString("horaEntrada"));
		            	hrsFinal.setText(rta.getString("horaSalida"));
		            	fechaInicio.setText(rta.getString("fechaEntrada"));
		            	fechaFinal.setText(rta.getString("fechaSalida"));
		            	cmbCurso.setValue(rta.getString("c.nombre"));
		            	estado.setText(rta.getString("e.nombre"));
		            	String estadoCurso = estado.getText();
		            	
		            	System.out.println(estadoCurso);
		            	System.out.println("Antes del if");
		                if(estadoCurso.equalsIgnoreCase("Cerrado")){
		                	System.out.println("dentro del if");
		                	 btnCurso.setDisable(true);
		                } else {
		                	 btnCurso.setDisable(false);
		                }
		                System.out.println("fuera del if");
		            }
		            else
		                txtEntrada.setText("No se encontro registro que coincida con la identificación");
		        }
		        conect.desconectar();
		    }
		
	}
	
	@FXML
	void btnInscripcion(MouseEvent event) throws SQLException, IOException{
		//String query = "SELECT id FROM cursos where id == '"+cmbCurso+"';";
		conect.conectar();
		 try(Statement stm = conect.getCon().createStatement()){
			 String nombreCurso = cmbCurso.getValue();
			 ResultSet rta = stm.executeQuery("SELECT id FROM cursos where nombre = '"+nombreCurso+"';");
			 if(rta.next()) {
				 int idcurso = rta.getInt("id");
				 int idpersona = 1;
				 ResultSet rt = stm.executeQuery("INSERT into personas_cursos (persona,curso) values ("+idpersona+","+idcurso+");");
				 if(rt.next()) {
					 
				 }
			 }
			 
		 }
		
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
