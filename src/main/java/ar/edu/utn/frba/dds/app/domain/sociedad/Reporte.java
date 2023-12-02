package ar.edu.utn.frba.dds.app.domain.sociedad;

import ar.edu.utn.frba.dds.app.domain.Miembro;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name= "reporte")
@Getter
@Setter
public class Reporte {
  @Id
  @GeneratedValue
  private Long id;

  @Column(name = "detalle")
  private  String detalle;

  @Column(name = "fecha")
  private  LocalDateTime fecha;

  @OneToOne
  @JoinColumn(name = "miembro_id", referencedColumnName = "id")
  private Miembro reportante;

  public Reporte(String detalle, Miembro reportante){
    this.detalle = detalle;
    this.fecha = LocalDateTime.now();
    this.reportante = reportante;
  }

  public Reporte() {}
}
