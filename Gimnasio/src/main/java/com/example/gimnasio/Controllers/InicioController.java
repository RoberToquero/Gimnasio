package com.example.gimnasio.Controllers;


import com.example.gimnasio.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class InicioController {

    @FXML
    private Button EntrarButton;

    @FXML
    private Button RegistroButton;

    @FXML
    private Button empleadosButton;

    @FXML
    void Entrar(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("login.fxml"));
        Parent root = fxmlLoader.load();
        LoginController controlador = fxmlLoader.getController();

        Scene scene = new Scene(root);
        Stage stage = new Stage();
        //stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(scene);
        stage.show();

        Stage myStage = (Stage) this.EntrarButton.getScene().getWindow();
        myStage.close();


    }

    @FXML
    void Registro(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("registro.fxml"));
        Parent root = fxmlLoader.load();
        RegistroController controlador1 = fxmlLoader.getController();

        Scene scene = new Scene(root);
        Stage stage = new Stage();
        //stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(scene);
        stage.show();

        Stage myStage = (Stage) this.RegistroButton.getScene().getWindow();
        myStage.close();

    }
    @FXML
    void empleados(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("empleados_login.fxml"));
        Parent root = fxmlLoader.load();
        EmpleadosLoginController controlador2 = fxmlLoader.getController();

        Scene scene = new Scene(root);
        Stage stage = new Stage();
        //stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(scene);
        stage.show();

        Stage myStage = (Stage) this.empleadosButton.getScene().getWindow();
        myStage.close();


    }

}