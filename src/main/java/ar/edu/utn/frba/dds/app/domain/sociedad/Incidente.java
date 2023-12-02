package ar.edu.utn.frba.dds.app.domain.sociedad;

import ar.edu.utn.frba.dds.app.domain.Miembro;
import ar.edu.utn.frba.dds.app.domain.prestacion.PrestacionDeServicio;
import ar.edu.utn.frba.dds.app.exceptions.ReporteIncidenteResueltoException;
import ar.edu.utn.frba.dds.app.notificador.Notificable;
import lombok.Getter;
import lombok.Setter;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;


@Entity
@Table(name = "incidente")
@Getter
@Setter
public class Incidente implements Notificable {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "detalle")
    private String detalle;

    @Column(name = "fecha_apertura", columnDefinition = "TIMESTAMP")
    private LocalDateTime fechaApertura;

    @OneToOne
    @JoinColumn(name = "iniciador_id", referencedColumnName = "id")
    private Miembro iniciador;

    @Column(name = "fecha_cierre")
    private LocalDateTime fechaCierre;

    @OneToOne
    @JoinColumn(name = "cerrador_id", referencedColumnName = "id")
    private Miembro cerrador;

    @Column(name = "esta_resuelto")
    private Boolean estaResuelto;

    @OneToMany
    @JoinColumn(name = "incidente_id", referencedColumnName = "id")
    private List<Reporte> reportes;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "prestacion_servicio_id", referencedColumnName = "id")
    private PrestacionDeServicio prestacionDeServicio;

    public void setPrestacionDeServicio(PrestacionDeServicio prestacionDeServicio) {
        this.prestacionDeServicio = prestacionDeServicio;
    }

    public Incidente(String detalle, Miembro iniciador, PrestacionDeServicio prestacionDeServicio) {
        this.detalle = detalle;
        this.fechaApertura = LocalDateTime.now();
        this.iniciador = iniciador;
        this.reportes = new ArrayList<>();
        this.estaResuelto = false;
        this.prestacionDeServicio = prestacionDeServicio;
    }

    public Incidente() {

    }
    public void agregarReporte(String detalle, Miembro reportante){
        if(this.estaResuelto) {
            throw new ReporteIncidenteResueltoException("El reporte con el siguiente detalle ya se encuentra resuelto: " + this.detalle);
        }
        this.reportes.add(new Reporte(detalle, reportante));
    }
    public void cerrar(LocalDateTime fechaCierre, Miembro cerrador){
        this.fechaCierre = fechaCierre;
        this.cerrador = cerrador;
        this.estaResuelto = true;
    }

    @Override
    public String getDatos() {
        // TODO implements me
        return null;
    }

    public Long minutosAbierto() {

        Duration duracion = Duration.between(this.fechaApertura,this.fechaCierre);

        long horas = duracion.toHours();
        long minutos = duracion.toMinutesPart();

        return (horas * 60) + minutos;
    }
}
