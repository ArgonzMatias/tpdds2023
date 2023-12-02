package ar.edu.utn.frba.dds.app.domain.prestacion;

import java.util.ArrayList;
import java.util.List;

import ar.edu.utn.frba.dds.app.domain.sociedad.Usuario;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "organismo_control")
@Getter
@Setter
public class OrganismoDeControl {
  @Id
  @GeneratedValue
  private Long id;

  @Column(name = "codigo")
  private  String codigo;

  @Column(name = "nombre")
  private  String nombre;

  @OneToOne
  @JoinColumn(name = "usuario_id", referencedColumnName = "id")
  private  Usuario usuario;

  @OneToMany
  @JoinColumn(name = "organismo_control_id", referencedColumnName = "id")
  private List<Empresa> empresas;

  public OrganismoDeControl(String codigo, String nombre, Usuario usuario){
    this.codigo = codigo;
    this.nombre = nombre;
    this.empresas = new ArrayList<>();
    this.usuario = usuario;
  }
  public OrganismoDeControl(){}

  public void agregarEmpresaPrestadora(Empresa empresa) {
    this.empresas.add(empresa);
  }
}
