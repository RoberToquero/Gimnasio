package com.example.gimnasio.Controllers;

import com.example.gimnasio.Main;
import com.example.gimnasio.Util.AlertUtils;
import com.example.gimnasio.Util.OperacionesDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.ActividadHorario;
import model.Usuario;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class UsuariosController implements Initializable {

    @FXML
    private ComboBox<String> cbDias;
    String[] dias={"Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado"};

    @FXML
    private TableColumn colActividad;


    @FXML
    private TableColumn colFin;

    @FXML
    private TableColumn colInicio;


    @FXML
    private TableColumn colSala;


    @FXML
    private TableColumn colId;
    @FXML
    private TableColumn colParticipantes;

    @FXML
    private Button inscribirButton;

    @FXML
    private Button salirButton;

    @FXML
    private Button verHorarioButton;


    @FXML
    private TableView<ActividadHorario> tablaActividades;


    @FXML
    private TextField txtNombre;

    OperacionesDAO operacionesdao= new OperacionesDAO();

    private String nombreUsuario;
    private int idUsuario;

    @FXML
    void inscribir(MouseEvent event) {

        if(txtNombre.getText().toString()!=null){
            String idActividadS=txtNombre.getText().toString();
            int idActividad=Integer.parseInt(idActividadS);
            ActividadHorario actividadActual=operacionesdao.obtenerActividadPorId(idActividad);
            if (actividadActual != null) {
                //Obtengo el usuario por su ID para comprobar si tiene la cuota pagada
                Usuario usuarioActual=operacionesdao.obtenerUsuarioPorId(idUsuario);
                //Verifico si el usuario tiene la cuota pagada para poderse inscribir
                if(usuarioActual.getCuota()){

                    int participantes = actividadActual.getParticipantes();
                    int limite=actividadActual.getLimiteAsistentes();

                    //Condición para controlar que la actividad no está llena y puede hacerse la inscripción
                        if(participantes<limite){
                            //Tengo que verificar las faltas del usuario y su tipo
                            if(usuarioActual.getFaltas()>0){
                                // El usuario tiene faltas, verificar si es premium
                                if(usuarioActual.getTipo().equals("Premium")){
                                    // Usuario premium con faltas, mostrar alerta una vez
                                    AlertUtils.mostrarAlerta(Alert.AlertType.INFORMATION, "PENALIZADO",
                                            "Penalizado por faltas. No puedes inscribirte");
                                }
                                else{
                                    // Usuario no premium con faltas, mostrar alerta dos veces
                                    for (int i = 0; i < 2; i++) {
                                        AlertUtils.mostrarAlerta(Alert.AlertType.INFORMATION, "PENALIZADO",
                                                "Has sido penalizado. Puedes inscribirte con restricciones.");
                                    }
                                }
                            }

                            // Incremento el número de participantes
                            int participantesActualizado = participantes + 1;
                            actividadActual.setParticipantes(participantesActualizado);

                            // Actualizo la instancia en la base de datos
                            operacionesdao.modificarActividad(actividadActual);

                            AlertUtils.mostrarAlerta(Alert.AlertType.INFORMATION, "Inscrito",
                                    " Te has registrado en la actividad elegida con éxito.");
                            vaciarCampos();
                        }
                        else{
                            AlertUtils.mostrarAlerta(Alert.AlertType.INFORMATION, "ACTIVIDAD COMPLETA",
                                    " La actividad seleccionada ha alcanzado el número máximo de usuarios y no puedes inscribirte");
                        }
                }
                else {
                    // El usuario no tiene la cuota pagada, mostrar alerta
                    AlertUtils.mostrarAlerta(Alert.AlertType.INFORMATION, "CUOTA PENDIENTE",
                            " No puedes inscribirte en actividades si tu cuota no está pagada.");
                }

            }
            else {
                // Si la actividad no existe
                AlertUtils.mostrarAlerta(Alert.AlertType.ERROR, "Error",
                        "La actividad no fue encontrada en la base de datos.");
            }
        }
    }
    @FXML
    void verHorarios(MouseEvent event) {
        String diaSeleccionado=cbDias.getValue();
        if(diaSeleccionado!=null){
            // la lista de actividades filtradas por el día
            List<ActividadHorario> actividades = operacionesdao.listarActividadesDia(diaSeleccionado);
            // ObservableList a partir de la lista de actividades
            ObservableList<ActividadHorario> data = FXCollections.observableList(actividades);

            // Limpiar la TableView
            tablaActividades.getItems().clear();
            // Agregar los datos a la TableView
            tablaActividades.setItems(data);

            //Configurar las columnas
            this.colActividad.setCellValueFactory(new PropertyValueFactory("nombreActividad"));
            this.colInicio.setCellValueFactory(new PropertyValueFactory("horaInicio"));
            this.colFin.setCellValueFactory(new PropertyValueFactory("horaFin"));
            this.colSala.setCellValueFactory(new PropertyValueFactory("sala"));
            this.colId.setCellValueFactory(new PropertyValueFactory("idActividad"));
            this.colParticipantes.setCellValueFactory(new PropertyValueFactory("participantes"));

        }


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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cbDias.getItems().addAll(dias);

    }
    @FXML
    void cargarDatos(MouseEvent event) {
        ActividadHorario actividad=tablaActividades.getSelectionModel().getSelectedItem();
        if(actividad != null){
            txtNombre.setText(String.valueOf(actividad.getIdActividad()));
        }

    }
    private void vaciarCampos(){
        txtNombre.setText("");
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
        // Obtener el ID del usuario y almacenarlo
        this.idUsuario = operacionesdao.obtenerIdUsuarioPorNombre(nombreUsuario);

    }
}
