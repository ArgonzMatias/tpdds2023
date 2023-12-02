package ar.edu.utn.frba.dds.app.domain;

import ar.edu.utn.frba.dds.app.domain.sociedad.Comunidad;
import ar.edu.utn.frba.dds.app.domain.enums.RolMiembro;
import ar.edu.utn.frba.dds.app.domain.sociedad.Persona;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name= "miembro")
@Getter
@Setter
public class Miembro {
    @Id
    @GeneratedValue
    private Long id;

    @OneToOne
    @JoinColumn(name = "persona_id",referencedColumnName = "id")
    private  Persona persona;

    @ManyToOne
    @JoinColumn(name = "comunidad_id", referencedColumnName = "id")
    private  Comunidad comunidad;

    @Enumerated(EnumType.STRING)
    @Column(name = "rol")
    private RolMiembro rol;

    public Miembro(Persona persona, Comunidad comunidad, RolMiembro rol) {
        this.persona = persona;
        this.comunidad = comunidad;
        this.rol = rol;
    }

    public Miembro() {

    }
}
