package model;
import javax.persistence.*;
import java.util.List;
import java.util.Set;

/**
 * Clase que representa las salas registrados en el gimnasio
 */

@Entity
@Table(name = "sala")
public class Sala {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Sala")
    private int idSala;

    @OneToMany(mappedBy = "sala", cascade =CascadeType.ALL)
    private List<ActividadHorario> actividades;


    public Sala() {
    }

    public Sala(int idSala, List<ActividadHorario> actividades) {
        this.idSala = idSala;
        this.actividades = actividades;
    }

    public int getIdSala() {
        return idSala;
    }

    public void setIdSala(int idSala) {
        this.idSala = idSala;
    }

    public List<ActividadHorario> getActividades() {
        return actividades;
    }

    public void setActividades(List<ActividadHorario> actividades) {
        this.actividades = actividades;
    }

    @Override
    public String toString() {
        String id_sala=String.valueOf(idSala);
        return id_sala;
    }
}
