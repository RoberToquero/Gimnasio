package com.example.gimnasio.Controllers;

import com.example.gimnasio.Main;
import com.example.gimnasio.Util.OperacionesDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.ActividadHorario;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class MonitoresController implements Initializable {

    @FXML
    private ComboBox<String> cbDia;
    String[] dias={"Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado"};

    @FXML
    private TableColumn colActividad;

    @FXML
    private TableColumn colHoraFin;

    @FXML
    private TableColumn colHoraInicio;

    @FXML
    private TableColumn colID;

    @FXML
    private TableColumn colSala;

    @FXML
    private TableView<ActividadHorario> tablaHorario;

    @FXML
    private Button volverButton;
    @FXML
    private Button verHorarioButton;

    private int monitorId;
    OperacionesDAO operacionesdao=new OperacionesDAO();


    @FXML
    void volver(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("inicio.fxml"));
        Parent root = fxmlLoader.load();
        InicioController controlador = fxmlLoader.getController();

        Scene scene = new Scene(root);
        Stage stage = new Stage();
        //stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(scene);
        stage.show();

        Stage myStage = (Stage) this.volverButton.getScene().getWindow();
        myStage.close();

    }
    @FXML
    void verHorarios(MouseEvent event) {
        String diaSeleccionado=cbDia.getValue();
        if(diaSeleccionado!=null){
            // la lista de actividades filtradas por el día
            List<ActividadHorario> actividades = operacionesdao.listarActividadesDiaYMonitor(diaSeleccionado, monitorId);
            // ObservableList a partir de la lista de actividades
            ObservableList<ActividadHorario> data = FXCollections.observableList(actividades);

            // Limpiar la TableView
            tablaHorario.getItems().clear();
            // Agregar los datos a la TableView
            tablaHorario.setItems(data);

            //Configurar las columnas
            this.colActividad.setCellValueFactory(new PropertyValueFactory("nombreActividad"));
            this.colHoraInicio.setCellValueFactory(new PropertyValueFactory("horaInicio"));
            this.colHoraFin.setCellValueFactory(new PropertyValueFactory("horaFin"));
            this.colSala.setCellValueFactory(new PropertyValueFactory("sala"));
            this.colID.setCellValueFactory(new PropertyValueFactory("monitor"));

        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cbDia.getItems().addAll(dias);
    }

    public void setMonitorId(int monitorId){
        this.monitorId=monitorId;
    }
}
