package com.example.gimnasio.Util;

import model.ActividadHorario;
import model.Monitor;
import model.Sala;
import model.Usuario;

import javax.transaction.SystemException;
import java.util.List;
public interface OperacionesInterface {
    List<Monitor> listarMonitores();
    void anadirMonitor(Monitor monitor) throws SystemException;

    void modificarMonitor(Monitor monitor);
    void borrarMonitor(int id_monitor);
    Monitor obtenerMonitorPorID(int id_monitor);

    Sala obtenerSalaPorID(int id_sala);

    List<ActividadHorario> listarActividades();
    void anadirActividad(ActividadHorario actividad) throws SystemException;

    void modificarActividad(ActividadHorario actividad);

    void borrarActividad(int id_actividad);

    ActividadHorario obtenerActividadPorId(int id_actividad);

    List<ActividadHorario> listarActividadesDia(String dia);

    List<Usuario> listarUsuarios();

    void anadirUsuario(Usuario usuario) throws SystemException;

    void modificarUsuario(Usuario usuario);

    void borrarUsuario(int id_usuario);

    Boolean existeUsuario(String nombreUsuario, String contrasena);

    Boolean comprobarRegistro(String nombreUsuario);

    Boolean existeUsuarioPorNombre(String nombre);

    Boolean existeUsuarioPorNombreExcluyendoId(String nombre, int id);

     Boolean existeActividadEnHorarioConIdYDiferenteSala(String diaSemana, String horaInicio, Sala sala, Monitor monitor, int idActividad);

    Boolean existeActividadEnHorarioConIdYDiferenteMonitor(String diaSemana, String horaInicio, Sala sala, Monitor monitor, int idActividad);

    Boolean existeActividadEnHorarioConIdYMonitorYSala(String diaSemana, String horaInicio, Sala sala, Monitor monitor, int idActividad);


    Boolean excedeLimiteClasesEnMismoDia(Monitor monitor, String dia);

    Boolean existeMonitor(int idMonitor, String nombre, String contrasena);

    List<ActividadHorario> listarActividadesDiaYMonitor(String dia, int monitorId);

    int obtenerIdUsuarioPorNombre(String nombreUsuario);

    Usuario obtenerUsuarioPorId(int id_Usuario);






}
