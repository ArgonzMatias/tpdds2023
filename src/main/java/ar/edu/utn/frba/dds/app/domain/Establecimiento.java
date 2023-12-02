package ar.edu.utn.frba.dds.app.domain;

import ar.edu.utn.frba.dds.app.domain.geografia.Localizacion;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "establecimiento")
@Getter
@Setter
public class Establecimiento {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "nombre")
    private String nombre;

    @OneToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REMOVE})
    @JoinColumn(name = "localizacion_id", referencedColumnName = "id")
    private Localizacion localizacion;

    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REMOVE})
    @JoinColumn(name = "entidad_id")
    private Entidad entidad;


    public Establecimiento(String nombre, Localizacion localizacion, Entidad entidad) {
        this.nombre = nombre;
        this.localizacion = localizacion;
        this.entidad = entidad;
    }
    public Establecimiento(){}
}
