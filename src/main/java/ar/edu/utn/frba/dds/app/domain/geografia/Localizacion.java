package ar.edu.utn.frba.dds.app.domain.geografia;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name= "localizacion")
@Getter
@Setter
public class Localizacion {
  @Id
  @GeneratedValue
  private Long id;

  @Column(name="provincia")
  private String provincia;

  @Column(name="departamento")
  private String departamento;

  @Column(name="municipio")
  private String municipio;

  public Localizacion(){}

  public Localizacion(String provincia){
    this.provincia = provincia;
  }

}
