package ar.edu.utn.frba.dds.app.incidentes;

import ar.edu.utn.frba.dds.app.domain.Entidad;
import ar.edu.utn.frba.dds.app.domain.Establecimiento;
import ar.edu.utn.frba.dds.app.domain.Miembro;
import ar.edu.utn.frba.dds.app.domain.TipoDeEntidad;
import ar.edu.utn.frba.dds.app.domain.enums.Estado;
import ar.edu.utn.frba.dds.app.domain.enums.RolMiembro;
import ar.edu.utn.frba.dds.app.domain.enums.RolUsuario;
import ar.edu.utn.frba.dds.app.domain.geografia.Localizacion;
import ar.edu.utn.frba.dds.app.domain.prestacion.PrestacionDeServicio;
import ar.edu.utn.frba.dds.app.domain.servicio.Servicio;
import ar.edu.utn.frba.dds.app.domain.sociedad.Comunidad;
import ar.edu.utn.frba.dds.app.domain.sociedad.Persona;
import ar.edu.utn.frba.dds.app.domain.sociedad.Usuario;
import ar.edu.utn.frba.dds.app.notificador.Notificador;
import ar.edu.utn.frba.dds.app.notificador.clients.TwilioWhatsappClient;
import ar.edu.utn.frba.dds.app.repositories.Repository;
import java.time.LocalDate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class IncidentesTest {
  private Establecimiento establecimiento;
  private PrestacionDeServicio prestacionDeServicio;
  private Servicio servicio;
  private Comunidad comunidad;
  private Miembro miembro;
  private Notificador notificador;

  @BeforeEach
  public void init() {
    String provincia = "Ciudad Autónoma de Buenos Aires";
    Entidad entidad = new Entidad("0000","Trenes Argentinos",new TipoDeEntidad("Transporte","Test"));
    this.establecimiento = new Establecimiento("Constitucion", new Localizacion(provincia), entidad);
    this.servicio = new Servicio("Baños");
    this.prestacionDeServicio = new PrestacionDeServicio(Estado.HABILITADO,servicio,establecimiento);
    this.comunidad = new Comunidad("Unidos por el Roca");
    Usuario usuario = new Usuario("pepitoTester@gmail.com", "pepTest2005", RolUsuario.ADMINISTRADOR, "+5491160262209");
    Persona persona = new Persona("Pepito","Tester", LocalDate.of(2001,5,20),usuario,2000000);
    this.miembro = new Miembro(persona, this.comunidad, RolMiembro.AFECTADO);
    this.comunidad.agregarMiembro(miembro);
    Repository repositorioNotificaciones = new Repository();
    this.notificador = new Notificador(repositorioNotificaciones);
  }

  @Test
  public void crearIncidenteNuncaReportado() {
    prestacionDeServicio.agregarIncidente("Se rompio el inodoro", miembro, notificador);
    Assertions.assertEquals(1,prestacionDeServicio.getIncidentes().size());
  }
  @Test
  public void crearIncidenteReportado() {
    prestacionDeServicio.agregarIncidente("Se rompio el inodoro", miembro, notificador);
    prestacionDeServicio.agregarIncidente("Se rompio el inodoro", miembro, notificador);
    Assertions.assertEquals(1,prestacionDeServicio.getIncidentes().size());
    Assertions.assertEquals(1,prestacionDeServicio.getIncidentes().get(0).getReportes().size());
  }

  @Test
  public void cerrarIncidente(){
    prestacionDeServicio.agregarIncidente("Se rompio el inodoro", miembro, notificador);
    prestacionDeServicio.cerrarIncidente(miembro);
    Assertions.assertTrue(prestacionDeServicio.getIncidentes().get(0).getEstaResuelto());
  }
  @Test
  public void SiSeCierraSeCreaNuevoIncidente(){
    prestacionDeServicio.agregarIncidente("Se rompio el inodoro", miembro, notificador);
    prestacionDeServicio.cerrarIncidente(miembro);
    prestacionDeServicio.agregarIncidente("Se volvio a romper el inodoro", miembro, notificador);
    Assertions.assertEquals(2,prestacionDeServicio.getIncidentes().size());
  }
  @Test
  public void enviarMensaje() {
    TwilioWhatsappClient.enviarMensaje("+5491160262209", "Incidente: Escalera de acoyte rota");
  }
  //todo mock de mensajes
}
