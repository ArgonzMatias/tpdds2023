package ar.edu.utn.frba.dds.app.domain.prestacion;

import ar.edu.utn.frba.dds.app.domain.Establecimiento;
import ar.edu.utn.frba.dds.app.domain.Miembro;
import ar.edu.utn.frba.dds.app.domain.enums.Estado;
import ar.edu.utn.frba.dds.app.domain.sociedad.Comunidad;
import ar.edu.utn.frba.dds.app.domain.sociedad.Incidente;
import ar.edu.utn.frba.dds.app.domain.servicio.Servicio;

import ar.edu.utn.frba.dds.app.notificador.Notificador;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import ar.edu.utn.frba.dds.app.exceptions.NoHayIncidentesNoResueltos;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "prestacion_servicio")
@Getter
@Setter
public class PrestacionDeServicio {
    @Id
    @GeneratedValue
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado")
    private Estado estado;

    @ManyToOne
    @JoinColumn(name = "servicio_id", referencedColumnName = "id")
    private Servicio servicio;

    @OneToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REMOVE})
    @JoinColumn(name = "establecimiento_id", referencedColumnName = "id")
    private Establecimiento establecimiento;

    @OneToMany
    @JoinColumn(name = "pestacion_servicio_id", referencedColumnName = "id")
    private List<Comunidad> comunidadesInteresadas;

    @OneToMany(mappedBy = "prestacionDeServicio", cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REMOVE})
    private List<Incidente> incidentes;

    public PrestacionDeServicio(Estado estado, Servicio servicio, Establecimiento establecimiento) {
        this.estado = estado;
        this.servicio = servicio;
        this.establecimiento = establecimiento;
        this.comunidadesInteresadas = new ArrayList<>();
        this.incidentes = new ArrayList<>();
    }
    public PrestacionDeServicio(){}

    public void agregarComunidadInteresada(Comunidad comunidad) {
        this.comunidadesInteresadas.add(comunidad);
    }

    public void agregarIncidente(String detalle, Miembro reportante, Notificador notificador) {
        if (this.estado == Estado.DESHABILITADO) {
            this.incidentes.get(this.incidentes.size() - 1).agregarReporte(detalle, reportante);
            return;
        }
        Incidente incidente = new Incidente(detalle, reportante,this);
        this.incidentes.add(incidente);
        this.estado = Estado.DESHABILITADO;
        this.comunidadesInteresadas.forEach(comunidad -> comunidad.notificarIncidenteComunidad(incidente, notificador));
    }

    public void cerrarIncidente(Miembro cerrador){
        if(this.estado == Estado.HABILITADO && this.hayIncidentesNoResueltos()){
            throw new NoHayIncidentesNoResueltos("No hay incidentes para cerrar");
        }
        LocalDateTime fecha = LocalDateTime.now();
        this.incidentes.get(this.incidentes.size() - 1).cerrar(fecha, cerrador);
        this.estado = Estado.HABILITADO;
    }
    public boolean hayIncidentesNoResueltos(){
        return this.incidentes.stream().filter(incidente -> !incidente.getEstaResuelto()).toList().size() > 0;
    }
}
