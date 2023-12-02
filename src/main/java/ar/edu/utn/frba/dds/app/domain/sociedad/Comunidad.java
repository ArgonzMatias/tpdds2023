package ar.edu.utn.frba.dds.app.domain.sociedad;

import ar.edu.utn.frba.dds.app.domain.Miembro;
import ar.edu.utn.frba.dds.app.notificador.Notificador;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name= "comunidad")
@Getter
@Setter
public class Comunidad {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name= "nombre")
    private  String nombre;

    @OneToMany(mappedBy = "comunidad",cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REMOVE})
    private List<Miembro> miembros;

    public Comunidad(String nombre) {
        this.nombre = nombre;
        this.miembros = new ArrayList<>();
    }

    public Comunidad() {}
    public void agregarMiembro(Miembro miembro){
        this.miembros.add(miembro);
    }
    public void notificarIncidenteComunidad(Incidente incidente, Notificador notificador){
        this.miembros.forEach(miembro -> notificador.enviarNotificacionSiCorresponde(miembro.getPersona(), incidente));
    }

    public int getCantidadTotalDeMiembros() {
        return this.miembros.size();
    }
}

