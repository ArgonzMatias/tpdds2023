package ar.edu.utn.frba.dds.app.domain.notificacion;

import java.time.LocalDateTime;

import ar.edu.utn.frba.dds.app.domain.sociedad.Usuario;
import ar.edu.utn.frba.dds.app.notificador.Contactable;
import ar.edu.utn.frba.dds.app.notificador.Notificable;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name= "notificacion")
@Getter
@Setter
public class Notificacion {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "fecha")
    private LocalDateTime fecha;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado")
    private EstadoNotificacion estado;

    @ElementCollection
    @CollectionTable(name = "horarios_disponibles_notificacion", joinColumns = @JoinColumn(name = "notificacion_id", referencedColumnName = "id"))
    @Column(name = "horarios_disponibles")
    private List<String> horariosDisponibles;


    @OneToOne
    @JoinColumn(name = "usuario_id", referencedColumnName = "id")
    private Usuario contacto;

    @Enumerated(EnumType.STRING)
    @Column(name = "medio")
    private MedioNotificacion medioNotificacion;

    @Column(name = "datos")
    private String datos;

    public Notificacion(Contactable contactable, Notificable notificable, EstadoNotificacion estado) {
        this.estado = estado;
        this.fecha = LocalDateTime.now();
        this.horariosDisponibles = contactable.horariosDisponibles();
        this.contacto = contactable.contacto();
        this.medioNotificacion = contactable.medioNotificacion();
        this.datos = notificable.getDatos();
    }
    public  Notificacion(){}
}
