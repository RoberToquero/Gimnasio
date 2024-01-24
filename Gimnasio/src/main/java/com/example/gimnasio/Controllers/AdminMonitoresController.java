package com.example.gimnasio.Controllers;

import com.example.gimnasio.Util.AlertUtils;
import com.example.gimnasio.Main;
import com.example.gimnasio.Util.OperacionesDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
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
import model.Monitor;

import javax.transaction.SystemException;
import java.util.List;
//CLASE QUE SIRVE PARA AÑADIR, MODIFICAR Y BORRAR MONITORES
public class AdminMonitoresController implements Initializable{
    @FXML
    private Button anadirButton;

    @FXML
    private AnchorPane ap;

    @FXML
    private Button borrarButton;

    @FXML
    private BorderPane bp;

    @FXML
    private TableColumn contrasenaColumn;

    @FXML
    private TableColumn  emailColumn;


    @FXML
    private Button homeButton;

    @FXML
    private TableColumn idColumn;


    @FXML
    private Button modificarButton;

    @FXML
    private TableView<Monitor> monitorTable;

    @FXML
    private Button monitoresButton;

    @FXML
    private TableColumn nombreColumn;

    @FXML
    private Button salasButton;

    @FXML
    private Button salirButton;

    @FXML
    private Button usuariosButton;
    @FXML
    private TextField emailTF;
    @FXML
    private TextField idTF;
    @FXML
    private TextField nombreTF;

    @FXML
    private TextField passwordTF;

    OperacionesDAO operacionesdao=new OperacionesDAO();


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
    void monitores(MouseEvent event) {
        bp.setCenter(ap);


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
    void usuarios(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("admin_usuarios.fxml"));
        Parent root = fxmlLoader.load();
        AdminUsuariosController controlador = fxmlLoader.getController();

        Scene scene = new Scene(root);
        Stage stage = new Stage();
        //stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(scene);
        stage.show();

        Stage myStage = (Stage) this.usuariosButton.getScene().getWindow();
        myStage.close();


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
        int idMonitor= Integer.parseInt(idTF.getText().toString());

        operacionesdao.borrarMonitor(idMonitor);
        AlertUtils.mostrarAlerta(Alert.AlertType.INFORMATION, "DATOS CORRECTOS",
                " El monitor se ha eliminado con éxito.");
        actualizarTableView();
        activarCampos();
        vaciarCampos();

    }
    @FXML
    void anadir(MouseEvent event) throws SystemException {
        String nombre=nombreTF.getText().toString();
        String email=emailTF.getText().toString();
        String contrasena=passwordTF.getText().toString();

        if(nombre.isEmpty() || email.isEmpty() || contrasena.isEmpty()){
            AlertUtils.mostrarAlerta(Alert.AlertType.INFORMATION, "DATOS ERRÓNEOS",
                    "Todos los campos deben estar completos. No se añadió el monitor");
            actualizarTableView();
            vaciarCampos();
            return;
        }
        //NO PONGO EL ID PORQUE TE LO ASIGNA AUTOMATICAMENTE LA BASE DE DATOS AL SER AUTOINCREMENT
        Monitor monitor=new Monitor(nombre,email,contrasena);
        //LLAMO AL METODO DE OPERACIONES
        operacionesdao.anadirMonitor(monitor);
        AlertUtils.mostrarAlerta(Alert.AlertType.INFORMATION, "DATOS CORRECTOS",
                " El monitor se ha añadido con éxito.");
        actualizarTableView();
        activarCampos();
        vaciarCampos();

    }
    @FXML
    void modificar(MouseEvent event) {

        if(idTF.getText().toString()!=null){
            String idMonitorS=idTF.getText().toString();
            int idMonitor=Integer.parseInt(idMonitorS);
            String nombre=nombreTF.getText().toString();
            String email=emailTF.getText().toString();
            String contrasena=passwordTF.getText().toString();
            Monitor monitor=new Monitor(idMonitor,nombre,email,contrasena);

            operacionesdao.modificarMonitor(monitor);
            AlertUtils.mostrarAlerta(Alert.AlertType.INFORMATION, "DATOS CORRECTOS",
                    " El monitor se ha modificado con éxito.");
            actualizarTableView();
            activarCampos();
            vaciarCampos();
        }

    }

    public void actualizarTableView(){ //METODO PARA QUE SE ME CARGUE Los datos de la tabla (Poner en Initialize)
        List<Monitor> monitores = operacionesdao.listarMonitores();
        ObservableList<Monitor> data = FXCollections.observableList(monitores);

        //Agregar los datos a la TableView
        monitorTable.setItems(data);
        this.idColumn.setCellValueFactory(new PropertyValueFactory("id_monitor"));
        this.nombreColumn.setCellValueFactory(new PropertyValueFactory("nombre"));
        this.emailColumn.setCellValueFactory(new PropertyValueFactory("email"));
        this.contrasenaColumn.setCellValueFactory(new PropertyValueFactory("contrasena"));
    }
    public void cargarDatos(){ //MÉTODO QUE SE UTILIZA PARA QUE AL PULSAR EN LA TABLA SE MUESTREN LOS DATOS EN LOS CAMPOS
        Monitor monitor=monitorTable.getSelectionModel().getSelectedItem();
        if(monitor != null){
            idTF.setText(String.valueOf(monitor.getId_monitor()));
            nombreTF.setText(monitor.getNombre());
            emailTF.setText(monitor.getEmail());
            passwordTF.setText(monitor.getContrasena());
            activarBotones();

        }
    }
    public void vaciarCampos(){
        idTF.setText("");
        nombreTF.setText("");
        emailTF.setText("");
        passwordTF.setText("");
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        actualizarTableView();
        desactivarBotones();


    }
    private void activarCampos(){

        idTF.setDisable(false);
        nombreTF.setDisable(false);
        emailTF.setDisable(false);
        passwordTF.setDisable(false);

    }
    private void desactivarBotones(){
        modificarButton.setDisable(true);
        borrarButton.setDisable(true);
    }
    private void activarBotones(){
        modificarButton.setDisable(false);
        borrarButton.setDisable(false);
    }




}
