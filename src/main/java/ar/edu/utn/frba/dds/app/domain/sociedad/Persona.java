package ar.edu.utn.frba.dds.app.domain.sociedad;

import ar.edu.utn.frba.dds.app.domain.geografia.Localizacion;
import ar.edu.utn.frba.dds.app.domain.notificacion.FormaNotificacion;
import ar.edu.utn.frba.dds.app.domain.notificacion.MedioNotificacion;
import ar.edu.utn.frba.dds.app.notificador.Contactable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name= "persona")
@Getter
@Setter
public class Persona implements Contactable {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name="nombre")
    private String nombre;

    @Column(name = "apellido")
    private String apellido;

    @Column(name = "fechaDeNacimiento")
    private LocalDate fechaDeNacimiento;

    @Column(name = "numeroDeDocumento")
    private Integer numeroDeDocumento;

    @OneToOne
    @JoinColumn(name = "usuario_id", referencedColumnName = "id")
    private Usuario usuario;

    @OneToOne
    @JoinColumn(name = "localizacion_id",referencedColumnName = "id")
    private Localizacion localizacionDeInteres;

    @Column(name = "formaNotificacion")
    @Enumerated(EnumType.STRING)
    private FormaNotificacion formaNotificacion;

    @Enumerated(EnumType.STRING)
    @Column(name = "medioNotificacion")
    private MedioNotificacion medioNotificacion;

    @ElementCollection
    @CollectionTable(name = "horarios_disponibles_persona", joinColumns = @JoinColumn(name = "persona_id", referencedColumnName = "id"))
    @Column(name = "horarios_disponibles")
    private List<String> horariosDisponibles;

    public Persona(String nombre, String apellido, LocalDate fechaDeNacimiento, Usuario usuario, Integer numeroDeDocumento) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaDeNacimiento = fechaDeNacimiento;
        this.usuario= usuario;
        this.numeroDeDocumento = numeroDeDocumento;
        this.horariosDisponibles= new ArrayList<>();
    }

    public Persona() {}

    public List<String> horariosDisponibles(){return this.horariosDisponibles;}

    @Override
    public Usuario contacto() {
        return this.usuario;
    }

    @Override
    public FormaNotificacion formaDeContacto() {
        return this.formaNotificacion;
    }

    @Override
    public MedioNotificacion medioNotificacion() {
        return this.medioNotificacion;
    }

    public void agregarHorarioDisponible(String horario){this.horariosDisponibles.add(horario);}

}
