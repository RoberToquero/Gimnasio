package com.example.gimnasio.Controllers;

import com.example.gimnasio.Main;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class AdminController implements Initializable {
    @FXML
    private AnchorPane ap;

    @FXML
    private BorderPane bp;
    @FXML
    private Button salirButton;

    @FXML
    private Button homeButton;

    @FXML
    private Button monitoresButton;

    @FXML
    private Button salasButton;

    @FXML
    private Button usuariosButton;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    @FXML
    void home(MouseEvent event) {
        bp.setCenter(ap);
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


}
