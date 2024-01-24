package com.example.gimnasio.Controllers;

import com.example.gimnasio.Main;
import com.example.gimnasio.Util.AlertUtils;
import com.example.gimnasio.Util.OperacionesDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


import java.io.IOException;
import java.sql.SQLException;

public class LoginController {

    @FXML
    private Button AccederButton;

    @FXML
    private Button cancelarButton;

    @FXML
    private PasswordField passwordTextField;

    @FXML
    private TextField usernameTextField;

    OperacionesDAO operacionesdao=new OperacionesDAO();

    @FXML
    void AccederButtonOnAction(ActionEvent event) throws IOException, SQLException {
        String nombre=usernameTextField.getText().toString();
        String password = passwordTextField.getText().toString();
        if(nombre!=null && password!=null && !nombre.isEmpty() && !password.isEmpty()){


                if(nombre.equals("admin")&&password.equals("123")){
                    FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("admin.fxml"));
                    Parent root = fxmlLoader.load();
                    AdminController controlador = fxmlLoader.getController();

                    Scene scene = new Scene(root);
                    Stage stage = new Stage();
                    //stage.initStyle(StageStyle.UNDECORATED);
                    stage.setScene(scene);
                    stage.show();

                    Stage myStage = (Stage) this.AccederButton.getScene().getWindow();
                    myStage.close();
                }else {
                    //Verifico si el usuario introducido existe
                    if (operacionesdao.existeUsuario(nombre, password)) {
                        //Verificar si el usuario está dado de alta en el sistema
                        if (operacionesdao.comprobarRegistro(nombre)) {
                            // El usuario está registrado, permitir el acceso
                            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("usuariosMenu.fxml"));
                            Parent root = fxmlLoader.load();
                            UsuariosController controlador = fxmlLoader.getController();

                            //Para almacenar el nombre de usuario y usarlo en UsuariosController

                            controlador.setNombreUsuario(nombre);

                            Scene scene = new Scene(root);
                            Stage stage = new Stage();
                            stage.setScene(scene);
                            stage.show();

                            Stage myStage = (Stage) this.AccederButton.getScene().getWindow();
                            myStage.close();
                        } else {
                            // El usuario no está registrado, mostrar alerta
                            AlertUtils.mostrarAlerta(Alert.AlertType.INFORMATION, "USUARIO NO REGISTRADO",
                                    "El usuario no está registrado en el sistema. Espera a que el administrador te dé de alta.");
                            vaciarCampos();
                        }

                    } else {
                         //Usuario no encontrado en la base de datos
                        AlertUtils.mostrarAlerta(Alert.AlertType.INFORMATION, "USUARIO NO ENCONTRADO",
                                "El usuario no existe en la base de datos. Por favor, inténtalo de nuevo o regístrate.");
                        vaciarCampos();
                    }
                }

        }else{
            AlertUtils.mostrarAlerta(Alert.AlertType.INFORMATION, "DATOS INCORRECTOS",
                    " Todos los campos deben estar completos.");
            vaciarCampos();
        }
    }

    @FXML
    void cancelarButtonOnAction(ActionEvent event) throws IOException {
        usernameTextField.clear();
        passwordTextField.clear();
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
    private void vaciarCampos(){
        usernameTextField.setText("");
        passwordTextField.setText("");
    }




}
