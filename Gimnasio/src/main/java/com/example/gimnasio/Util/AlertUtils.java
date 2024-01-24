package com.example.gimnasio.Util;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class AlertUtils {

    public static void mostrarError(String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.ERROR);
        alerta.setContentText(mensaje);
        alerta.show();
    }

    public static void mostrarAlerta(Alert.AlertType tipo, String titulo, String mensaje) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText((String)null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }

    public static boolean mostrarConfirmacion(String titulo, String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
        alerta.setTitle(titulo);
        alerta.setHeaderText((String)null);
        alerta.setContentText(mensaje);
        ButtonType botonConfirmar = new ButtonType("Confirmar");
        ButtonType botonCancelar = new ButtonType("Cancelar", ButtonType.CANCEL.getButtonData());
        alerta.getButtonTypes().setAll(new ButtonType[]{botonConfirmar, botonCancelar});
        return alerta.showAndWait().orElse(botonCancelar) == botonConfirmar;
    }
}
