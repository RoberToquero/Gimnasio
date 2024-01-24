package model;

import javax.persistence.*;
import static javax.persistence.GenerationType.IDENTITY;
/**
 * Clase que representa los Usuarios registrados en el gimnasio
 */
@Entity
@Table(name="Usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name= "ID_Usuario")
    private int id_Usuario;

    @Column(name = "Nombre_Usuario")
    private String nombre;

    @Column(name = "Nombre_Apellido")
    private String nombreApellido;

    @Column(name = "Email")
    private String email;

    @Column(name = "Contrasena")
    private String contrasena;

    @Column(name = "Tipo")
    private String tipo;

    @Column(name = "Cuota")
    private Boolean cuota;

    @Column(name = "Dar_de_Alta")
    private Boolean registrado;

    @Column(name = "Numero_faltas")
    private int faltas;

    @Column(name = "Acceso_Spa")
    private Boolean spa;



    public Usuario() {
    }

    public Usuario(int id_Usuario, String nombre, String nombreApellido, String email, String contrasena, String tipo, Boolean cuota, Boolean registrado, int faltas, Boolean spa) {
        this.id_Usuario = id_Usuario;
        this.nombre = nombre;
        this.nombreApellido=nombreApellido;
        this.email=email;
        this.contrasena=contrasena;
        this.tipo=tipo;
        this.cuota=cuota;
        this.registrado=registrado;
        this.faltas=faltas;
        this.spa=spa;
    }

    public Usuario(String nombre, String nombreApellido, String email, String contrasena, String tipo, Boolean cuota, Boolean registrado, int faltas) {
        this.nombre = nombre;
        this.nombreApellido = nombreApellido;
        this.email = email;
        this.contrasena = contrasena;
        this.tipo = tipo;
        this.cuota = cuota;
        this.registrado = registrado;
        this.faltas = faltas;
    }

    public Usuario(int id_Usuario, String nombre, String nombreApellido, String email, String contrasena, String tipo, Boolean cuota, Boolean registrado, int faltas) {
        this.id_Usuario = id_Usuario;
        this.nombre = nombre;
        this.nombreApellido = nombreApellido;
        this.email = email;
        this.contrasena = contrasena;
        this.tipo = tipo;
        this.cuota = cuota;
        this.registrado = registrado;
        this.faltas = faltas;
    }


    public int getId_Usuario() {
        return id_Usuario;
    }

    public void setId_Usuario(int id_Usuario) {
        this.id_Usuario = id_Usuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombreApellido() {
        return nombreApellido;
    }

    public void setNombreApellido(String nombreApellido) {
        this.nombreApellido = nombreApellido;
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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Boolean getCuota() {
        return cuota;
    }

    public void setCuota(Boolean cuota) {
        this.cuota = cuota;
    }

    public Boolean getRegistrado() {
        return registrado;
    }

    public void setRegistrado(Boolean registrado) {
        this.registrado = registrado;
    }

    public int getFaltas() {
        return faltas;
    }

    public void setFaltas(int faltas) {
        this.faltas = faltas;
    }

    public Boolean getSpa() {
        return spa;
    }

    public void setSpa(Boolean spa) {
        this.spa = spa;
    }
}
