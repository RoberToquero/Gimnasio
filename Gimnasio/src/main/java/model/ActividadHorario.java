package model;
import javax.persistence.*;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;
/**
 * Clase que muestra las diversas actividades con sus horarios registrados en el gimnasio
 */

@Entity
@Table(name="ActividadHorario")
public class ActividadHorario {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "ID_Actividad")
    private int idActividad;


    @Column(name = "Nombre_Actividad")
    private String nombreActividad;

    @Column(name = "Dia_Semana")
    private String diaSemana;

    @Column(name = "Hora_Inicio", nullable = false)
    private String horaInicio;

    @Column(name = "Hora_Fin", nullable = false)
    private String horaFin;

    @Column(name = "Limite_Asistentes")
    private int limiteAsistentes;

    @Column(name = "Participantes")
    private int participantes;


    @ManyToOne
    @JoinColumn(name = "ID_Monitor", referencedColumnName = "ID_Monitor")
    private Monitor monitor;// una actividad solo puede ser dada por un monitor a la vez

    @ManyToOne
    @JoinColumn(name = "ID_Sala", referencedColumnName = "ID_Sala")
    private Sala sala;

    public ActividadHorario() {
    }

    public ActividadHorario(String nombreActividad, String diaSemana, String horaInicio, String horaFin, int limiteAsistentes, int participantes, Monitor monitor, Sala sala) {
        this.nombreActividad = nombreActividad;
        this.diaSemana = diaSemana;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.limiteAsistentes = limiteAsistentes;
        this.participantes = participantes;
        this.monitor = monitor;
        this.sala = sala;
    }

    public ActividadHorario(int idActividad, String nombreActividad, String diaSemana, String horaInicio, String horaFin, int limiteAsistentes, int participantes, Monitor monitor, Sala sala) {
        this.idActividad = idActividad;
        this.nombreActividad = nombreActividad;
        this.diaSemana = diaSemana;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.limiteAsistentes = limiteAsistentes;
        this.participantes = participantes;
        this.monitor = monitor;
        this.sala = sala;
    }

    public ActividadHorario(int idActividad, int participantes) {
        this.idActividad = idActividad;
        this.participantes = participantes;
    }

    public int getIdActividad() {
        return idActividad;
    }

    public void setIdActividad(int idActividad) {
        this.idActividad = idActividad;
    }

    public String getNombreActividad() {
        return nombreActividad;
    }

    public void setNombreActividad(String nombreActividad) {
        this.nombreActividad = nombreActividad;
    }

    public String getDiaSemana() {
        return diaSemana;
    }

    public void setDiaSemana(String diaSemana) {
        this.diaSemana = diaSemana;
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    public String getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(String horaFin) {
        this.horaFin = horaFin;
    }

    public int getLimiteAsistentes() {
        return limiteAsistentes;
    }

    public void setLimiteAsistentes(int limiteAsistentes) {
        this.limiteAsistentes = limiteAsistentes;
    }

    public int getParticipantes() {
        return participantes;
    }

    public void setParticipantes(int participantes) {
        this.participantes = participantes;
    }

    public Monitor getMonitor() {
        return monitor;
    }

    public void setMonitor(Monitor monitor) {
        this.monitor = monitor;
    }

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }

    @Override
    public String toString() {
        return "ActividadHorario{" +

                 monitor +
                 sala +
                '}';
    }
}


