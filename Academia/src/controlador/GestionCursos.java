package controlador;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import modelo.Conexion;

public class GestionCursos {
    String estadoCurso;
    int nombreCurso;
    String query;
    Conexion con = new Conexion();
    
    @FXML
    private Button btnBorrar;

    @FXML
    private Button btnNuevo;

    @FXML
    private Button btntActualizar;

    @FXML
    private Button buscarImg;

    @FXML
    private Text txtEntrada;

    @FXML
    private TextField txtName;

    @FXML
    private ComboBox<String> cmbEstado;

    @FXML
    private ComboBox<String> cmbHorario;
    
    

    @FXML
    private TextField txtIdentifi;

    

    @FXML
    void buscarImgClick(MouseEvent event) throws SQLException {
        System.out.println("entra al void buscarimgclick");
        //1. Recuperar el dato que se excriba en txtIdentifi
        //2. Validar que se escribio algo
        //3. consultar en la base de datos por un registro con esa identificacion
            //==> Se encuentra
                    //Desplegar los datos
                    //Activar los botones Eliminar y Actualizar
                    //Desactivar el boton nuevo
            //==> No se encuentra
                    //notifico
        String nam = txtName.getText();
        if (nam==null || nam.isEmpty()) {
            txtEntrada.setText("Error, Digite un nombre valido.");
        }
        else{
            con.conectar();
            try(Statement stm = con.getCon().createStatement()){
                int ident = Integer.parseInt(txtName.getText());
                ResultSet rta = stm.executeQuery("select * from cursos where nombre = '"+ident+"'");
                if(rta.next()){
                    txtName.setText(rta.getString("nombre"));
                    estadoCurso = rta.getString("estado");
                    
                    cmbEstado.setValue(rta.getString("estado"));
                    if (estadoCurso.equalsIgnoreCase("1")) {
                        btnBorrar.setText("Cerrar");
                    } else {
                        btnBorrar.setText("Abrir");
                    }
                    btnBorrar.setDisable(false);
                    btntActualizar.setDisable(false);
                    txtEntrada.setText("");
                }
                else
                    txtEntrada.setText("No se encontro registro que coincida con la identificación");
            }catch (Exception e) {
                System.out.println("se fue al catch del try de buscarimgclick!!!!!!!!!!!!!");
                e.printStackTrace();

            }
            con.desconectar();
        }

    }

    @FXML
    void clickActualizar(ActionEvent event) throws SQLException {
        //Recuperar los valores suministrados
        //Preparar la consulta a utilizar (Actualizar todo los campos)
        //Ejecuta la consulta
        
        String ide = txtIdentifi.getText();
        String nom = txtName.getText();
        
        String gen = cmbEstado.getValue();
        if(ide==null || ide.isEmpty())
            txtEntrada.setText("Debe ingresar una Identificacion valida");
        else if(nom==null || nom.isEmpty())
            txtEntrada.setText("Debe ingresar un Nombre valido");
        
        else if(gen==null || gen.isEmpty())
            txtEntrada.setText("Debe ingresar un genero valido");
        else{
            String query1 = "UPDATE clientes set cedula = '"+ide+"', nombre = '"+nom+"' , genero = '"+gen+"' where cedula = "+ide;
            con.conectar();
            System.out.println("voy bien");
            try(Statement stm = con.getCon().createStatement()){
            //Ejecuta comando de accion
            int res = stm.executeUpdate(query1);
            if(res!=0){
                txtEntrada.setText("Registro Actualizado con exito");
            }else
                txtEntrada.setText("Error al Actualizar registro");
            restaurarDatos();
            } 
            con.desconectar();
        }

    }

    @FXML
    void clickBorrar(ActionEvent event) throws SQLException {
        //Recuperar el texto del boton
        //Preparar la consulta a utilizar (Borrar --> estado=I, recuperar--> estado =A)
        //Ejecuta la consulta
        String acc = btnBorrar.getText();
        if ("Borrar".equalsIgnoreCase(acc)) {
            query = "UPDATE clientes set estado = 'I' where id="+nombreCurso;
        } else {
            query = "UPDATE clientes set estado = 'A' where id="+nombreCurso;
        }
        con.conectar();
        try(Statement stm = con.getCon().createStatement()){
        //Ejecuta comando de accion
        int res = stm.executeUpdate(query);
        if(res!=0){
            txtEntrada.setText("Registro Recuperado/Borrado con exito");
        }else
            txtEntrada.setText("Error al recuperar/Borrar registro");
        restaurarDatos();
        }
        con.desconectar();
    }
    
    private void restaurarDatos(){
        
        txtName.clear();
        
        txtIdentifi.clear();
        cmbEstado.setValue("M");
        btnBorrar.setDisable(true);
        btnBorrar.setText("Borrar");
        btntActualizar.setDisable(true);
    }

    @FXML
    void clickNuevo(ActionEvent event) throws SQLException {
        //Recuperar datos del formulario
        //Validar Datos
        //Preparar la insercion
        String ide = txtIdentifi.getText();
        String nom = txtName.getText();
        
        String gen = cmbEstado.getValue();
        if(ide==null || ide.isEmpty())
            txtEntrada.setText("Debe ingresar una Identificacion valida");
        else if(nom==null || nom.isEmpty())
            txtEntrada.setText("Debe ingresar un Nombre valido");

        else if(gen==null || gen.isEmpty())
            txtEntrada.setText("Debe ingresar un genero valido");
        else{
            String query1 = "insert into clientes (cedula,nombre,apellido,genero,estado)values ('"+ide+"','"+nom+"','"+gen+"','A')";
            con.conectar();
            try (Statement stm = con.getCon().createStatement()){
                int rest = stm.executeUpdate(query1);
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

    }
    
    @FXML
    void initialize(){ 
        // Metodo de javafx que sirve para inicializar combox y demas en java apenas se abra una ventana
        btnBorrar.setDisable(true);
        btntActualizar.setDisable(true);
        // Para inicializar valores de combo de genero
        cmbEstado.getItems().clear();
        cmbEstado.getItems().addAll("Cerrado", "Inscripciones abiertas");
        cmbEstado.setValue("Inscripciones abiertas");

        cmbHorario.getItems().clear();
        cmbHorario.getItems().addAll("Ma�ana", "Tarde","Noche");
        cmbHorario.setValue("Mañana");
    }

}