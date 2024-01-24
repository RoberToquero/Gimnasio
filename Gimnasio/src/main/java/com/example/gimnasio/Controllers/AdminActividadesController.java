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

import model.ActividadHorario;
import model.Monitor;
import model.Sala;

import javax.transaction.SystemException;

public class AdminActividadesController implements Initializable {
    @FXML
    private AnchorPane ap;

    @FXML
    private Button borrarButton;

    @FXML
    private BorderPane bp;

    @FXML
    private ComboBox<String> cbActividad;
    String[] actividades={"Cardio", "Crossfit", "Yoga", "Pilates", "Spinning", "Zumba"};

    @FXML
    private ComboBox<String> cbDias;
    String[] dias={"Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado"};

    @FXML
    private TableColumn colActividad;

    @FXML
    private TableColumn colFin;

    @FXML
    private TableColumn colId;

    @FXML
    private TableColumn colInicio;

    @FXML
    private TableColumn colLimite;

    @FXML
    private TableColumn colMonitor;

    @FXML
    private TableColumn colParticipantes;

    @FXML
    private TableColumn colSala;

    @FXML
    private Button homeButton;

    @FXML
    private Button horariosButton;

    @FXML
    private Button insertButton;

    @FXML
    private Button modificarButton;

    @FXML
    private Button monitoresButton;

    @FXML
    private Button salasButton;

    @FXML
    private Button salirButton;

    @FXML
    private TableView<ActividadHorario> tablaHorarios;

    @FXML
    private TextField textHoraFin;

    @FXML
    private TextField textHoraInicio;
    @FXML
    private TextField textId;

    @FXML
    private TextField textIdSala;

    @FXML
    private TextField textLimite;

    @FXML
    private TextField textNombreMonitor;

    @FXML
    private TextField textParticipantes;

    @FXML
    private Button usuariosButton;

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
    void salas(MouseEvent event) {
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
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cbDias.getItems().addAll(dias);
        cbActividad.getItems().addAll(actividades);
        desactivarBotones();
        textId.setDisable(true);



    }
    @FXML
    void borrarHorario(MouseEvent event) {
        int idActividad=Integer.parseInt(textId.getText().toString());
        operacionesdao.borrarActividad(idActividad);
        AlertUtils.mostrarAlerta(Alert.AlertType.INFORMATION, "DATOS CORRECTOS",
                " La actividad se ha eliminado con éxito.");
        vaciarCampos();
        desactivarBotones();
    }
    @FXML
    void insertarHorario(MouseEvent event) throws SystemException {
        String actividad = cbActividad.getValue() != null ? cbActividad.getValue().toString() : "";
        String dia = cbDias.getValue() !=null ? cbDias.getValue().toString() : "";
        String inicio=textHoraInicio.getText().toString();
        String fin=textHoraFin.getText().toString();
        String participantes=textParticipantes.getText().toString();
        String limite=textLimite.getText().toString();
        String id_monitor=textNombreMonitor.getText().toString();
        String sala=textIdSala.getText().toString();

        if(actividad.isEmpty() || dia.isEmpty() || inicio.isEmpty() || fin.isEmpty() ||
                id_monitor.isEmpty()||sala.isEmpty()||participantes.isEmpty()||limite.isEmpty()){
            AlertUtils.mostrarAlerta(Alert.AlertType.INFORMATION, "DATOS ERRÓNEOS",
                    "Todos los campos deben estar completos. No se añadió la actividad");
            vaciarCampos();
            return;
        }

        //PARA OBTENER EL ID DEL MONITOR Y PODER CREAR UN OBJETO

        int idMonitor=Integer.parseInt(id_monitor);
        Monitor monitor= operacionesdao.obtenerMonitorPorID(idMonitor);

        //PARA OBTENER EL ID DE LA SALA Y PODER CREAR UN OBJETO HORARIO

        int idSala=Integer.parseInt(sala);
        Sala sala1= operacionesdao.obtenerSalaPorID(idSala);

        //COMPROBAR SI LA SALA EXISTE

        Sala salaActual = operacionesdao.obtenerSalaPorID(idSala);

        if (salaActual == null) {
            AlertUtils.mostrarAlerta(Alert.AlertType.INFORMATION, "SALA NO ENCONTRADA",
                    "La sala con el ID proporcionado no existe. Verifica el ID e inténtalo de nuevo.");
            vaciarCampos();
            return;
        }
        //COMPROBAR SI EL MONITOR EXISTE
        Monitor monitorExiste=operacionesdao.obtenerMonitorPorID(idMonitor);
        if(monitorExiste==null){
            AlertUtils.mostrarAlerta(Alert.AlertType.INFORMATION, "MONITOR NO ENCONTRADO",
                    "El monitor con el ID proporcionado no existe. Verifica el ID e inténtalo de nuevo.");
            vaciarCampos();
            return;
        }

        // Impresiones para verificar los valores de los parámetros
        System.out.println("Dia: " + dia);
        System.out.println("Inicio: " + inicio);
        System.out.println("Sala: " + sala1); // Asegúrate de que toString() en Sala proporcione información útil
        System.out.println("Monitor: " + monitor); // Asegúrate de que toString() en Monitor proporcione información útil




        //COMPROBAR SI EL MONITOR EXCEDE EL NUMERO DE CLASES EN EL MISMO DIA
        if (operacionesdao.excedeLimiteClasesEnMismoDia(monitor, dia)) {
            AlertUtils.mostrarAlerta(Alert.AlertType.INFORMATION, "LÍMITE DE CLASES",
                    "El monitor ha alcanzado el límite de clases para este día.");
            vaciarCampos();
            return;
        }

            // VERIFICAR si existe una actividad en el mismo horario Y CON EL MISMO MONITOR
        if (operacionesdao.existeActividadEnHorarioConIdYDiferenteSala(dia, inicio, sala1, monitor, Integer.parseInt(participantes))) {
            AlertUtils.mostrarAlerta(Alert.AlertType.INFORMATION, "CONFLICTO DE HORARIO",
                    "Ya hay una actividad programada con el mismo horario y monitor, pero en una sala diferente. " +
                            "Modifica monitor.");
            vaciarCampos();
            return;
        }

        // VERIFICAR si existe una actividad en el mismo horario Y CON LA MISMA SALA
        if (operacionesdao.existeActividadEnHorarioConIdYDiferenteMonitor(dia, inicio, sala1, monitor, Integer.parseInt(participantes))) {
            AlertUtils.mostrarAlerta(Alert.AlertType.INFORMATION, "CONFLICTO DE HORARIO",
                    "Ya hay una actividad programada con el mismo horario y sala. " +
                            "Modifica sala.");
            vaciarCampos();
            return;
        }

        //VERIFICAR SI EXISTE UNA ACTIVIDAD CON MISMO HORARIO, MISMO MONITOR Y MISMA SALA
        if (operacionesdao.existeActividadEnHorarioConIdYMonitorYSala(dia, inicio, sala1, monitor, Integer.parseInt(participantes))) {
            AlertUtils.mostrarAlerta(Alert.AlertType.INFORMATION, "CONFLICTO DE HORARIO",
                    "Ya hay una actividad programada con el mismo horario, monitor y sala. Modifica ambos valores ");
            vaciarCampos();
            return;
        }

        // Obtengo la actividad actual por ID para evitar inscribir una actividad que supere el límite de participantes
        ActividadHorario actividadActual = operacionesdao.obtenerActividadPorId(Integer.parseInt(participantes));

        if (actividadActual != null) {
            int limiteActual = actividadActual.getLimiteAsistentes();
            int participantesActuales = actividadActual.getParticipantes();

            // Compara si la suma de los nuevos participantes y los participantes actuales supera el límite
            if (Integer.parseInt(participantes) + participantesActuales > limiteActual) {
                AlertUtils.mostrarAlerta(Alert.AlertType.INFORMATION, "ACTIVIDAD COMPLETA",
                        " La actividad seleccionada tiene demasiados usuarios y no puede crearse");
                vaciarCampos();
                return;
            }
        }

        ActividadHorario actividad1=new ActividadHorario(actividad,dia,inicio,fin,Integer.parseInt(limite),
                Integer.parseInt(participantes),monitor,sala1);
        operacionesdao.anadirActividad(actividad1);
        AlertUtils.mostrarAlerta(Alert.AlertType.INFORMATION, "ACTIVIDAD AÑADIDA",
                "La actividad se ha añadido con éxito.");
        vaciarCampos();

    }
    @FXML
    void modificarHorario(MouseEvent event) {
        if (textId.getText().toString() != null) {
            String idActividadS = textId.getText().toString();
            int idActividad = Integer.parseInt(idActividadS);
            String actividad = cbActividad.getValue() != null ? cbActividad.getValue().toString() : "";
            String dia = cbDias.getValue() !=null ? cbDias.getValue().toString() : "";
            String inicio=textHoraInicio.getText().toString();
            String fin=textHoraFin.getText().toString();
            String participantes=textParticipantes.getText().toString();
            String limite=textLimite.getText().toString();
            String id_monitor=textNombreMonitor.getText().toString();
            String sala=textIdSala.getText().toString();

            if(actividad.isEmpty() || dia.isEmpty() || inicio.isEmpty() || fin.isEmpty() ||
                    id_monitor.isEmpty()||sala.isEmpty()||participantes.isEmpty()||limite.isEmpty()){
                AlertUtils.mostrarAlerta(Alert.AlertType.INFORMATION, "DATOS ERRÓNEOS",
                        "Todos los campos deben estar completos. No se añadió la actividad");
                vaciarCampos();
                return;
            }

            //PARA OBTENER EL ID DEL MONITOR Y PODER CREAR UN OBJETO

            int idMonitor=Integer.parseInt(id_monitor);
            Monitor monitor= operacionesdao.obtenerMonitorPorID(idMonitor);

            //PARA OBTENER EL ID DE LA SALA Y PODER CREAR UN OBJETO HORARIO

            int idSala=Integer.parseInt(sala);
            Sala sala1= operacionesdao.obtenerSalaPorID(idSala);

            //COMPROBAR SI LA SALA EXISTE

            Sala salaActual = operacionesdao.obtenerSalaPorID(idSala);

            if (salaActual == null) {
                AlertUtils.mostrarAlerta(Alert.AlertType.INFORMATION, "SALA NO ENCONTRADA",
                        "La sala con el ID proporcionado no existe. Verifica el ID e inténtalo de nuevo.");
                vaciarCampos();
                return;
            }
            //COMPROBAR SI EL MONITOR EXISTE
            Monitor monitorExiste=operacionesdao.obtenerMonitorPorID(idMonitor);
            if(monitorExiste==null){
                AlertUtils.mostrarAlerta(Alert.AlertType.INFORMATION, "MONITOR NO ENCONTRADO",
                        "El monitor con el ID proporcionado no existe. Verifica el ID e inténtalo de nuevo.");
                vaciarCampos();
                return;
            }

            // Impresiones para verificar los valores de los parámetros
            System.out.println("Dia: " + dia);
            System.out.println("Inicio: " + inicio);
            System.out.println("Sala: " + sala1); // Asegúrate de que toString() en Sala proporcione información útil
            System.out.println("Monitor: " + monitor); // Asegúrate de que toString() en Monitor proporcione información útil


            //COMPROBAR SI EL MONITOR EXCEDE EL NUMERO DE CLASES EN EL MISMO DIA
            if (operacionesdao.excedeLimiteClasesEnMismoDia(monitor, dia)) {
                AlertUtils.mostrarAlerta(Alert.AlertType.INFORMATION, "LÍMITE DE CLASES",
                        "El monitor ha alcanzado el límite de clases para este día.");
                vaciarCampos();
                return;
            }

            // VERIFICAR si existe una actividad en el mismo horario Y CON EL MISMO MONITOR
            if (operacionesdao.existeActividadEnHorarioConIdYDiferenteSala(dia, inicio, sala1, monitor, Integer.parseInt(participantes))) {
                AlertUtils.mostrarAlerta(Alert.AlertType.INFORMATION, "CONFLICTO DE HORARIO",
                        "Ya hay una actividad programada con el mismo horario y monitor, pero en una sala diferente. " +
                                "Modifica monitor.");
                vaciarCampos();
                return;
            }

            // VERIFICAR si existe una actividad en el mismo horario Y CON LA MISMA SALA
            if (operacionesdao.existeActividadEnHorarioConIdYDiferenteMonitor(dia, inicio, sala1, monitor, Integer.parseInt(participantes))) {
                AlertUtils.mostrarAlerta(Alert.AlertType.INFORMATION, "CONFLICTO DE HORARIO",
                        "Ya hay una actividad programada con el mismo horario y sala. " +
                                "Modifica sala.");
                vaciarCampos();
                return;
            }



            // OBTENGO la actividad actual por ID para evitar modificar una actividad que supere el límite de participantes
            ActividadHorario actividadActual = operacionesdao.obtenerActividadPorId(idActividad);

            if (actividadActual != null) {
                int limiteActual = actividadActual.getLimiteAsistentes();
                int participantesActuales = actividadActual.getParticipantes();

                // Compara si la suma de los nuevos participantes y los participantes actuales supera el límite
                if (Integer.parseInt(participantes) + participantesActuales > limiteActual) {
                    AlertUtils.mostrarAlerta(Alert.AlertType.INFORMATION, "ACTIVIDAD COMPLETA",
                            " La actividad seleccionada ha alcanzado el número máximo de usuarios y no puede modificarse");
                    vaciarCampos();
                    return;
                }
            }


            ActividadHorario actividad1 = new ActividadHorario(idActividad, actividad, dia, inicio, fin, Integer.parseInt(limite),
                    Integer.parseInt(participantes), monitor, sala1);

            operacionesdao.modificarActividad(actividad1);
            AlertUtils.mostrarAlerta(Alert.AlertType.INFORMATION, "DATOS CORRECTOS",
                    " La actividad se ha modificado con éxito.");
            vaciarCampos();
        }
    }
    @FXML
    void mostrarHorario(MouseEvent event) {
        String diaSeleccionado = cbDias.getValue();

        if (diaSeleccionado != null) {
            // la lista de actividades filtradas por el día
            List<ActividadHorario> actividades = operacionesdao.listarActividadesDia(diaSeleccionado);

            // ObservableList a partir de la lista de actividades
            ObservableList<ActividadHorario> data = FXCollections.observableList(actividades);

            // Limpiar la TableView
            tablaHorarios.getItems().clear();

            // Agregar los datos a la TableView
            tablaHorarios.setItems(data);

            // Configurar las columnas
            this.colActividad.setCellValueFactory(new PropertyValueFactory("nombreActividad"));
            this.colInicio.setCellValueFactory(new PropertyValueFactory("horaInicio"));
            this.colFin.setCellValueFactory(new PropertyValueFactory("horaFin"));
            this.colMonitor.setCellValueFactory(new PropertyValueFactory("monitor"));
            this.colSala.setCellValueFactory(new PropertyValueFactory("sala"));
            this.colParticipantes.setCellValueFactory(new PropertyValueFactory("participantes"));
            this.colLimite.setCellValueFactory(new PropertyValueFactory("limiteAsistentes"));
            this.colId.setCellValueFactory(new PropertyValueFactory("idActividad"));
        }

    }
        @FXML
        void cargarDatos(MouseEvent event) {
            ActividadHorario actividad=tablaHorarios.getSelectionModel().getSelectedItem();
            if(actividad != null){
                cbActividad.setValue(actividad.getNombreActividad());
                textHoraInicio.setText(actividad.getHoraInicio());
                textHoraFin.setText(actividad.getHoraFin());
                textNombreMonitor.setText(actividad.getMonitor().toString());
                textIdSala.setText(actividad.getSala().toString());
                textLimite.setText(String.valueOf(actividad.getLimiteAsistentes()));
                textParticipantes.setText(String.valueOf(actividad.getParticipantes()));
                textId.setText(String.valueOf(actividad.getIdActividad()));
                activarBotones();
        }

    }
    public void vaciarCampos(){
        cbActividad.setValue("");
        textHoraInicio.setText("");
        textHoraFin.setText("");
        textNombreMonitor.setText("");
        textIdSala.setText("");
        textLimite.setText("");
        textParticipantes.setText("");
        textId.setText("");
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
