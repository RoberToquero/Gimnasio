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

public class EmpleadosLoginController {

    @FXML
    private Button AccederButton;

    @FXML
    private Button cancelarButton;

    @FXML
    private TextField nombreTextField;

    @FXML
    private TextField numEmpleadoTextField;

    @FXML
    private PasswordField passwordTextField;

    OperacionesDAO operacionesdao= new OperacionesDAO();

    @FXML
    void AccederButtonOnAction(ActionEvent event) throws IOException {
        String idEmpleadoS = numEmpleadoTextField.getText().trim();
        String nombre = nombreTextField.getText().trim();
        String password = passwordTextField.getText().trim();

        if (!idEmpleadoS.isEmpty() && !nombre.isEmpty() && !password.isEmpty()) {
            try {
                int idEmpleado = Integer.parseInt(idEmpleadoS);

                if (operacionesdao.existeMonitor(idEmpleado, nombre, password)) {
                    FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("monitoresMenu.fxml"));
                    Parent root = fxmlLoader.load();
                    MonitoresController controlador = fxmlLoader.getController();

                    //Paso el Id del monitor a MonitoresController
                    controlador.setMonitorId(idEmpleado);

                    Scene scene = new Scene(root);
                    Stage stage = new Stage();
                    // stage.initStyle(StageStyle.UNDECORATED);
                    stage.setScene(scene);
                    stage.show();

                    Stage myStage = (Stage) this.AccederButton.getScene().getWindow();
                    myStage.close();
                } else {
                    // Usuario no encontrado en la base de datos
                    AlertUtils.mostrarAlerta(Alert.AlertType.INFORMATION, "EMPLEADO NO ENCONTRADO",
                            "El empleado no existe en la base de datos. Por favor, inténtalo de nuevo.");
                    vaciarCampos();
                }
            } catch (NumberFormatException e) {
                // El ID del monitor no es un número válido
                AlertUtils.mostrarAlerta(Alert.AlertType.INFORMATION, "ID DE EMPLEADO INVÁLIDO",
                        "El ID del empleado debe ser un número entero válido.");
                vaciarCampos();
            } catch (IOException e) {
                // Manejar IOException
                e.printStackTrace();
            }
        } else {
            AlertUtils.mostrarAlerta(Alert.AlertType.INFORMATION, "DATOS INCORRECTOS",
                    "Todos los campos deben estar completos.");
            vaciarCampos();
        }
    }

    @FXML
    void cancelarButtonOnAction(ActionEvent event) throws IOException {
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
        nombreTextField.setText("");
        numEmpleadoTextField.setText("");
        passwordTextField.setText("");
    }

}
