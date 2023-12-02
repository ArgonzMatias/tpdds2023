package ar.edu.utn.frba.dds.app.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "tipo_entidad")
@Getter
@Setter
public class TipoDeEntidad {
  @Id
  @GeneratedValue
  private Long id;

  @Column(name = "nombre")
  private String nombre;

  @Column(name = "tipo_entidad")
  private String tipoEntidades;

  public TipoDeEntidad(String nombre, String tipoEntidades){
    this.nombre = nombre;
    this.tipoEntidades = tipoEntidades;
  }

  public TipoDeEntidad(){}
}
