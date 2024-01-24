package com.example.gimnasio.Controllers;
import com.example.gimnasio.Main;
import com.example.gimnasio.Util.AlertUtils;
import com.example.gimnasio.Util.OperacionesDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import model.Usuario;

import javax.transaction.SystemException;

public class AdminUsuariosController implements Initializable{
    @FXML
    private AnchorPane ap;

    @FXML
    private Button borrarButton;

    @FXML
    private BorderPane bp;

    @FXML
    private ComboBox<String> cbPagado;
    String[] pagado={"Pagado", "No pagado"};

    @FXML
    private ComboBox<String> cbTipo;
    String[] tipoUsuario={"Premium", "Regular", "Ocasional"};

    @FXML
    private ComboBox<String> cbRegistrado;
    String[] registrado={"Si", "No"};

    @FXML
    private ComboBox<String> cbSpa;
    String[] spa={"Si", "No"};

    @FXML
    private TableColumn colContrasena;

    @FXML
    private TableColumn colEmail;

    @FXML
    private TableColumn colFaltas;

    @FXML
    private TableColumn colId;

    @FXML
    private TableColumn colNombreApellido;

    @FXML
    private TableColumn colNombreUsuario;

    @FXML
    private TableColumn colPago;

    @FXML
    private TableColumn colRegistrado;

    @FXML
    private TableColumn colTipo;

    @FXML
    private Button homeButton;

    @FXML
    private Button insertarButton;

    @FXML
    private Button modificarButton;

    @FXML
    private Button monitoresButton;

    @FXML
    private Button salasButton;

    @FXML
    private Button salirButton;

    @FXML
    private TableView<Usuario> tablaUsuarios;

    @FXML
    private TextField textId;

    @FXML
    private TextField txtContrasena;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtFaltas;

    @FXML
    private TextField txtNombreApellido;

    @FXML
    private TextField txtNombreUsuario;


    @FXML
    private Button usuariosButton;

    OperacionesDAO operacionesdao=new OperacionesDAO();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cbTipo.getItems().addAll(tipoUsuario);
        cbRegistrado.getItems().addAll(registrado);
        cbPagado.getItems().addAll(pagado);
        cbSpa.setDisable(true);
        textId.setDisable(true);
        actualizarTableView();
        desactivarBotones();

    }
    @FXML
    void home(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("admin.fxml"));
        Parent root = fxmlLoader.load();
        AdminController controlador = fxmlLoader.getController();

        Scene scene = new Scene(root);
        Stage stage = new Stage();
        //stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(scene);
        stage.show();

        Stage myStage = (Stage) this.homeButton.getScene().getWindow();
        myStage.close();

    }
    @FXML
    void monitores(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("admin_monitores.fxml"));
        Parent root = fxmlLoader.load();
        AdminMonitoresController controlador = fxmlLoader.getController();

        Scene scene = new Scene(root);
        Stage stage = new Stage();
        //stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(scene);
        stage.show();

        Stage myStage = (Stage) this.monitoresButton.getScene().getWindow();
        myStage.close();

    }
    @FXML
    void salas(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("admin_actividades.fxml"));
        Parent root = fxmlLoader.load();
        AdminActividadesController controlador = fxmlLoader.getController();

        Scene scene = new Scene(root);
        Stage stage = new Stage();
        //stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(scene);
        stage.show();

        Stage myStage = (Stage) this.salasButton.getScene().getWindow();
        myStage.close();

    }

    @FXML
    void usuarios(MouseEvent event) {
        bp.setCenter(ap);


    }
    @FXML
    void salir(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("inicio.fxml"));
        Parent root = fxmlLoader.load();
        InicioController controlador = fxmlLoader.getController();

        Scene scene = new Scene(root);
        Stage stage = new Stage();
        //stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(scene);
        stage.show();

        Stage myStage = (Stage) this.salirButton.getScene().getWindow();
        myStage.close();

    }
    @FXML
    void borrar(MouseEvent event) {
        int idUsuario=Integer.parseInt(textId.getText().toString());
        operacionesdao.borrarUsuario(idUsuario);
        AlertUtils.mostrarAlerta(Alert.AlertType.INFORMATION, "DATOS CORRECTOS",
                " El usuario se ha eliminado con éxito.");
        actualizarTableView();
        vaciarCampos();
        desactivarBotones();


    }
    @FXML
    void modificar(MouseEvent event) {
        if(textId.getText().toString()!=null){
            String idUsuarioS=textId.getText().toString();
            int idUsuario=Integer.parseInt(idUsuarioS);
            String nombreApellido=txtNombreApellido.getText().toString();
            String nombreUsuario=txtNombreUsuario.getText().toString();
            String email=txtEmail.getText().toString();
            String contrasena=txtContrasena.getText().toString();
            String tipo=(cbTipo.getValue() != null) ? cbTipo.getValue().toString() : "";
            String pagado=(cbPagado.getValue() !=null) ? cbPagado.getValue().toString() : "";
            Boolean registro;
            Boolean cuota;

            if (pagado.equals("Pagado")){
                cuota=true;
            }
            else{
                cuota=false;
            }
            String registrado=(cbRegistrado.getValue() !=null) ? cbRegistrado.getValue().toString() : "";

            if(registrado.equals("Si")){
                registro=true;
            }
            else{
                registro=false;
            }
            String faltas=txtFaltas.getText().toString();


            // Verifico si el nuevo nombre de usuario ya está registrado (excluyendo el usuario actual)
            if (operacionesdao.existeUsuarioPorNombreExcluyendoId(nombreUsuario, idUsuario)) {
                AlertUtils.mostrarAlerta(Alert.AlertType.INFORMATION, "USUARIO EXISTENTE",
                        "El nombre de usuario '" + nombreUsuario + "' ya está registrado por otro usuario.");
                return; // No modificar el usuario y salir del método
            }
            if(nombreApellido.isEmpty() || nombreUsuario.isEmpty() || email.isEmpty() || contrasena.isEmpty() || tipo==null
                    || pagado==null || registrado==null || faltas.isEmpty()){
                AlertUtils.mostrarAlerta(Alert.AlertType.INFORMATION, "DATOS ERRÓNEOS",
                        "Todos los campos deben estar completos. No se modificó el usuario");
                actualizarTableView();
                vaciarCampos();
                return;
            }

            int falta=Integer.parseInt(faltas);

            Usuario usuario=new Usuario(idUsuario,nombreUsuario,nombreApellido,email,contrasena,tipo,cuota,registro,falta);
            operacionesdao.modificarUsuario(usuario);
            AlertUtils.mostrarAlerta(Alert.AlertType.INFORMATION, "DATOS CORRECTOS",
                    " El usuario se ha modificado con éxito.");
            actualizarTableView();
            vaciarCampos();
            desactivarBotones();

        }

    }
    @FXML
    void insertar(MouseEvent event) throws SystemException {
        String nombreApellido=txtNombreApellido.getText().toString();
        String nombreUsuario=txtNombreUsuario.getText().toString();
        String email=txtEmail.getText().toString();
        String contrasena=txtContrasena.getText().toString();
        String tipo=(cbTipo.getValue() != null) ? cbTipo.getValue().toString() : "";
        String pagado=(cbPagado.getValue() !=null) ? cbPagado.getValue().toString() : "";
        Boolean registro;
        Boolean cuota;

        if (pagado.equals("Pagado")){
            cuota=true;
        }
        else{
            cuota=false;
        }
        String registrado=(cbRegistrado.getValue() !=null) ? cbRegistrado.getValue().toString() : "";

        if(registrado.equals("Si")){
            registro=true;
        }
        else{
            registro=false;
        }
        String faltas=txtFaltas.getText().toString();


        // VERIFICO SI EL USUARIO YA EXISTE PARA NO REPETIR NOMBRE DE USUARIO en la base de datos
        if (operacionesdao.existeUsuarioPorNombre(nombreUsuario)) {
            AlertUtils.mostrarAlerta(Alert.AlertType.INFORMATION, "USUARIO EXISTENTE",
                    "El usuario con el nombre de usuario '" + nombreUsuario + "' ya está registrado." +
                            " Por favor elige otro nombre de usuario.");
            return; // No añadir el usuario y salir del método
        }



        if(nombreApellido.isEmpty() || nombreUsuario.isEmpty() || email.isEmpty() || contrasena.isEmpty() || tipo==null
        || pagado==null || registrado==null || faltas.isEmpty()){
            AlertUtils.mostrarAlerta(Alert.AlertType.INFORMATION, "DATOS ERRÓNEOS",
                    "Todos los campos deben estar completos. No se añadió el usuario");
            actualizarTableView();
            vaciarCampos();
            return;
        }
        int falta=Integer.parseInt(faltas);
        Usuario usuario=new Usuario(nombreUsuario,nombreApellido,email,contrasena,tipo,cuota,registro,falta);
        operacionesdao.anadirUsuario(usuario);
        AlertUtils.mostrarAlerta(Alert.AlertType.INFORMATION, "DATOS CORRECTOS",
                " El usuario se ha añadido con éxito.");
        actualizarTableView();
        vaciarCampos();
        desactivarBotones();


    }
    @FXML
    void cargarDatos(MouseEvent event) {
        Usuario usuario=tablaUsuarios.getSelectionModel().getSelectedItem();
        if(usuario != null){
            textId.setText(String.valueOf(usuario.getId_Usuario()));
            txtNombreApellido.setText(usuario.getNombreApellido());
            txtNombreUsuario.setText(usuario.getNombre());
            txtEmail.setText(usuario.getEmail());
            txtContrasena.setText(usuario.getContrasena());
            txtFaltas.setText(String.valueOf(usuario.getFaltas()));
            cbTipo.setValue(usuario.getTipo());
            if(usuario.getCuota()==true){
                cbPagado.setValue("Pagado");
            }
            else{
                cbPagado.setValue("No pagado");
            }
            if(usuario.getRegistrado()==true){
                cbRegistrado.setValue("Si");
            }
            else{
                cbRegistrado.setValue("No");
            }
            if(cbTipo.getValue().equals("Premium")){
                cbSpa.setValue("Si");

            }
            else{
                cbSpa.setValue("No");
            }
            activarBotones();

        }

    }
    public void actualizarTableView(){

            List<Usuario> usuarios = operacionesdao.listarUsuarios();
            ObservableList<Usuario> data = FXCollections.observableList(usuarios);


            // Agregar los datos a la TableView
            tablaUsuarios.setItems(data);

            // Configurar las columnas
            this.colId.setCellValueFactory(new PropertyValueFactory("id_Usuario"));
            this.colNombreUsuario.setCellValueFactory(new PropertyValueFactory("nombre"));
            this.colNombreApellido.setCellValueFactory(new PropertyValueFactory("nombreApellido"));
            this.colEmail.setCellValueFactory(new PropertyValueFactory("email"));
            this.colContrasena.setCellValueFactory(new PropertyValueFactory("contrasena"));
            this.colTipo.setCellValueFactory(new PropertyValueFactory("tipo"));
            this.colPago.setCellValueFactory(new PropertyValueFactory("cuota"));
            this.colRegistrado.setCellValueFactory(new PropertyValueFactory("registrado"));
            this.colFaltas.setCellValueFactory(new PropertyValueFactory("faltas"));

    }
    private void desactivarBotones(){
        modificarButton.setDisable(true);
        borrarButton.setDisable(true);
    }
    public void activarBotones(){
        modificarButton.setDisable(false);
        borrarButton.setDisable(false);
    }
    public void vaciarCampos(){
        txtNombreApellido.setText("");
        txtNombreUsuario.setText("");
        txtEmail.setText("");
        txtContrasena.setText("");
        txtFaltas.setText("");
        cbPagado.setValue("");
        cbSpa.setValue("");
        cbRegistrado.setValue("");
        cbTipo.setValue("");
        textId.setText("");
    }

}
