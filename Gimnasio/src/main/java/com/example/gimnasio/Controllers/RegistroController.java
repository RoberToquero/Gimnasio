package com.example.gimnasio.Controllers;

import com.example.gimnasio.Main;
import com.example.gimnasio.Util.AlertUtils;
import com.example.gimnasio.Util.OperacionesDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Usuario;


import javax.transaction.SystemException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class RegistroController implements Initializable {

    @FXML
    private Button borrarButton;

    @FXML
    private Button cancelarButton;

    @FXML
    private TextField emailTextField;

    @FXML
    private TextField nombreTextField;


    @FXML
    private PasswordField passwordTextField;


    @FXML
    private Button registrarseButton;


    @FXML
    private TextField userTextField;

    @FXML
    private ComboBox<String> cbTipo;
    String[] tipoUsuario={"Premium", "Regular", "Ocasional"};



    @FXML
    void borrar(ActionEvent event) {
        nombreTextField.clear();
        emailTextField.clear();
        userTextField.clear();
        passwordTextField.clear();
        cbTipo.setValue("");



    }

    @FXML
    void cancelar(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("inicio.fxml"));
        Parent root = fxmlLoader.load();
        InicioController controlador = fxmlLoader.getController();

        Scene scene = new Scene(root);
        Stage stage = new Stage();
        //stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(scene);
        stage.show();

        Stage myStage = (Stage) this.cancelarButton.getScene().getWindow();
        myStage.close();

    }
    OperacionesDAO operacionesdao=new OperacionesDAO();

    @FXML
    void registrarse(ActionEvent event) throws SystemException {
        String nombreApellido=nombreTextField.getText().toString();
        String email=emailTextField.getText().toString();
        //Verifico si el valor del combobox es null antes de llamar a toString
        String tipo = (cbTipo.getValue() != null) ? cbTipo.getValue().toString() : "";
        String nombreUsuario=userTextField.getText().toString();
        String contrasena=passwordTextField.getText().toString();

        Boolean registro=false;
        Boolean cuota=false;
        int faltas=0;

        if(nombreApellido.isEmpty() || nombreUsuario.isEmpty() || email.isEmpty() || contrasena.isEmpty() || tipo==null){
            AlertUtils.mostrarAlerta(Alert.AlertType.INFORMATION, "DATOS ERRÓNEOS",
                    "Todos los campos deben estar completos. No se añadió el usuario");
            vaciarCampos();
            return;
        }
        //COMPRUEBO SI ESE NOMBRE DE USUARIO YA EXISTE

        int idUsuarioExistente = operacionesdao.obtenerIdUsuarioPorNombre(nombreUsuario);

        if (idUsuarioExistente != -1) {
            // El nombre de usuario ya existe, mostrar alerta y no registrar
            AlertUtils.mostrarAlerta(Alert.AlertType.INFORMATION, "NOMBRE DE USUARIO EXISTENTE",
                    "El nombre de usuario ya está registrado. Por favor, elige otro nombre de usuario.");
            vaciarCampos();
            return;
        }

        Usuario usuario=new Usuario(nombreUsuario,nombreApellido,email,contrasena,tipo,cuota,registro,faltas);
        operacionesdao.anadirUsuario(usuario);
        AlertUtils.mostrarAlerta(Alert.AlertType.INFORMATION, "DATOS CORRECTOS",
                " El usuario se añadió con éxito. Espera a que nuestro administrador confirme tus datos para acceder.");
        vaciarCampos();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cbTipo.getItems().addAll(tipoUsuario);

    }
    private void vaciarCampos(){
        nombreTextField.setText("");
        emailTextField.setText("");
        cbTipo.setValue("");
        userTextField.setText("");
        passwordTextField.setText("");
    }
}
