package ar.edu.utn.frba.dds.app.domain;

import java.util.ArrayList;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

import ar.edu.utn.frba.dds.app.domain.geografia.Localizacion;
import ar.edu.utn.frba.dds.app.domain.sociedad.Persona;
import javax.persistence.*;

@Entity
@Table(name = "entidad")
@Getter
@Setter
public class Entidad {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "codigo")
    private  String codigo;

    @Column(name = "nombre")
    private  String nombre;

    @OneToMany
    @JoinColumn(name = "entidad_id", referencedColumnName = "id")
    private List<Persona> interesados;

    @OneToMany
    @JoinColumn(name = "entidad_id", referencedColumnName = "id")
    private List<Establecimiento> establecimientos;

    @OneToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REMOVE})
    @JoinColumn(name = "tipo_entidad_id", referencedColumnName = "id")
    private  TipoDeEntidad tipoDeEntidad;

    @OneToOne
    @JoinColumn(name = "localizacion_id", referencedColumnName = "id")
    private Localizacion localizacion;

    @Column(name = "tiempo_cierre_promedio")
    private Double tiempoDeCierrePromedio;

    public Entidad(String codigo,String nombre, TipoDeEntidad tipoEntidad) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.tipoDeEntidad = tipoEntidad;
        this.interesados = new ArrayList<>();
        this.establecimientos = new ArrayList<>();
    }
    public Entidad(){}


    public void agregarInteresado(Persona persona) {
        this.interesados.add(persona);
    }

    public void agregarEstablecimiento(Establecimiento establecimiento){
        this.establecimientos.add(establecimiento);
    }

}
