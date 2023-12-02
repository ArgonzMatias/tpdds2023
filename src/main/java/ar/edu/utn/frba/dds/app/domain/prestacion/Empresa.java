package ar.edu.utn.frba.dds.app.domain.prestacion;

import ar.edu.utn.frba.dds.app.domain.Entidad;
import java.util.ArrayList;
import java.util.List;

import ar.edu.utn.frba.dds.app.domain.sociedad.Usuario;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "empresa")
@Getter
@Setter
public class Empresa {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "codigo")
    private  String codigo;

    @Column(name = "nombre")
    private  String nombre;

    @OneToOne
    @JoinColumn(name = "usuario_id", referencedColumnName = "id")
    private Usuario usuario;

    @OneToMany
    @JoinColumn(name = "empresa_id", referencedColumnName = "id")
    private List<Entidad> entidades;

    public Empresa(String codigo, String nombre, Usuario usuario) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.usuario = usuario;
        this.entidades = new ArrayList<>();
    }
    public Empresa(){}

    public void agregarEntidad(Entidad entidad) {
        entidades.add(entidad);
    }
}
