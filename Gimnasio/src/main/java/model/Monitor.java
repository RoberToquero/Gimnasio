package model;


import javax.persistence.*;
import java.util.List;
import static javax.persistence.GenerationType.IDENTITY;
/**
 * Clase que representa los Monitores disponibles en el gimnasio
 */
@Entity
@Table(name="monitor")
public class Monitor {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "ID_Monitor")
    private int id_monitor;

    @Column(name = "Nombre")
    private String nombre;

    @Column(name = "Email")
    private String email;

    @Column(name = "Contrasena")
    private String contrasena;

    @OneToMany(mappedBy = "monitor", cascade = CascadeType.ALL, fetch = FetchType.EAGER)//1 monitor puede tener varias actividades
    private List<ActividadHorario> actividades;//lista de actividades

    public Monitor() {
    }

    public Monitor(int id_monitor, String nombre, String email, String contrasena) {
        this.id_monitor = id_monitor;
        this.nombre = nombre;
        this.email = email;
        this.contrasena = contrasena;
    }
    public Monitor(String nombre, String email, String contrasena) {
        this.nombre = nombre;
        this.email = email;
        this.contrasena = contrasena;
    }

    public Monitor(int id_monitor, String nombre, String email, String contrasena, List<ActividadHorario> actividades) {
        this.id_monitor = id_monitor;
        this.nombre = nombre;
        this.email = email;
        this.contrasena = contrasena;
        this.actividades = actividades;
    }

    public int getId_monitor() {
        return id_monitor;
    }

    public void setID_Monitor(int id_monitor) {
        this.id_monitor = id_monitor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public List<ActividadHorario> getActividades() {
        return actividades;
    }

    public void setActividades(List<ActividadHorario> actividades) {
        this.actividades = actividades;
    }

    @Override
    public String toString() {
        String id_monitor_int=String.valueOf(id_monitor);
        return id_monitor_int;
    }
}
