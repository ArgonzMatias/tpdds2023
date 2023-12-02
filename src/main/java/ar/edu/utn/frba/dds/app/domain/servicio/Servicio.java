package ar.edu.utn.frba.dds.app.domain.servicio;

import ar.edu.utn.frba.dds.app.domain.sociedad.Persona;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "servicio")
@Getter
@Setter
public class Servicio {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "nombre")
    private String nombre;

    @OneToMany
    @JoinColumn(name = "servicio_id", referencedColumnName = "id")
    private List<Persona> interesados;

    public Servicio(String nombre) {
        this.nombre = nombre;
    }
    public Servicio() {}

    public void agregarInteresado(Persona persona) {
        this.interesados.add(persona);
    }
}
