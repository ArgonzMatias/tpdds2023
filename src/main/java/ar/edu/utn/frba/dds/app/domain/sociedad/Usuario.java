package ar.edu.utn.frba.dds.app.domain.sociedad;

import ar.edu.utn.frba.dds.app.domain.enums.RolUsuario;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "usuario")
@Getter
@Setter
public class Usuario {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "correo")
    private String correoElectronico;

    @Column(name = "contrasenia")
    private String contrasenia;

    @Enumerated(EnumType.STRING)
    @Column(name = "rol")
    private RolUsuario rol;

    @Column(name = "numero")
    private String numero;

    public Usuario(String correoElectronico, String contrasenia, RolUsuario rol, String numero) {
        this.correoElectronico = correoElectronico;
        this.contrasenia = contrasenia;
        this.rol = rol;
        this.numero = numero;
    }

    public Usuario() {

    }
}
