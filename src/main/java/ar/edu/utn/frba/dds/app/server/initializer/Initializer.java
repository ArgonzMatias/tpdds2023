package ar.edu.utn.frba.dds.app.server.initializer;

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
import ar.edu.utn.frba.dds.app.domain.sociedad.Incidente;
import ar.edu.utn.frba.dds.app.domain.sociedad.Persona;
import ar.edu.utn.frba.dds.app.domain.sociedad.Usuario;
import ar.edu.utn.frba.dds.app.repositories.Repository;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;

import java.time.LocalDate;
import java.util.List;

public class Initializer implements WithSimplePersistenceUnit {
    public static void init() {
        Repository repository = new Repository();
        List<Usuario> u = repository.buscarTodos(Usuario.class.getName());
        if(u.isEmpty()) {
            Usuario usuarioAdmin = new Usuario("admin@gmail.com", "admin", RolUsuario.ADMINISTRADOR, "1122224444");
            Usuario usuarioMiembro = new Usuario("miembro@gmail.com", "miembro", RolUsuario.MIEMBRO, "1122224444");

            Persona personaAdmin = new Persona("Administrador", "Maximo", LocalDate.of(2001, 5, 15), usuarioAdmin, 43460280);
            Persona personaMiembro = new Persona("Miembro", "Minimo", LocalDate.of(2001, 5, 15), usuarioMiembro, 44460280);

            Comunidad comunidad = new Comunidad("VIAJEROS DEL ROCA");
            Comunidad comunidad1 = new Comunidad("VIAJEROS DEL SARMIENTO");

            Miembro miembro = new Miembro(personaAdmin, comunidad, RolMiembro.AFECTADO);
            comunidad.agregarMiembro(miembro);

            Servicio banios = new Servicio("Baños");
            Servicio molinetes = new Servicio("Molinetes");
            Servicio rampaDiscapacitados = new Servicio("Rampa Discapacitados");
            Servicio ascensor = new Servicio("Ascensor");

            Localizacion localizacion = new Localizacion("Buenos Aires");
            TipoDeEntidad ferrocarriles = new TipoDeEntidad("Ferrocarriles", "Estaciones");
            TipoDeEntidad subterraneos = new TipoDeEntidad("Subterraneo", "Estaciones");

            Entidad lineaRoca = new Entidad("ROCA", "Linea Roca", ferrocarriles);
            Entidad linaSarmiento = new Entidad("SARMIENTO", "Linea Sarmiento", ferrocarriles);
            Entidad lineaC = new Entidad("LINEA C","Linea C",subterraneos);
            Entidad lineaB = new Entidad("LINEA B","Linea B",subterraneos);

            Establecimiento estacionConstitucion = new Establecimiento("Estacion Constitucion", localizacion, lineaRoca);
            Establecimiento estacionBurzaco = new Establecimiento("Estacion Burzaco", localizacion, lineaRoca);
            Establecimiento estacionOnce = new Establecimiento("Estacion Once", localizacion, linaSarmiento);
            Establecimiento estacionSubteConstitucion = new Establecimiento("Estacion de subte Constitucion",localizacion,lineaC);
            Establecimiento estacionDiagonalNorte = new Establecimiento("Estacion Diagonal Norte",localizacion,lineaC);
            Establecimiento estacionRetiro = new Establecimiento("Estacion Retiro",localizacion,lineaC);
            Establecimiento estacionCarlosPellegrini = new Establecimiento("Estacion Carlos Pellegrini",localizacion,lineaB);
            Establecimiento estacionMedrano = new Establecimiento("Estacion Medrano",localizacion,lineaB);

            PrestacionDeServicio baniosConstitucion = new PrestacionDeServicio(Estado.HABILITADO, banios, estacionConstitucion);
            baniosConstitucion.agregarComunidadInteresada(comunidad);
            PrestacionDeServicio baniosBurzaco = new PrestacionDeServicio(Estado.HABILITADO, banios, estacionBurzaco);
            PrestacionDeServicio baniosOnce = new PrestacionDeServicio(Estado.HABILITADO, banios, estacionOnce);
            PrestacionDeServicio baniosDiagonalNorte = new PrestacionDeServicio(Estado.HABILITADO, banios, estacionDiagonalNorte);
            PrestacionDeServicio baniosMedrano = new PrestacionDeServicio(Estado.HABILITADO, banios, estacionMedrano);

            PrestacionDeServicio molinetesConstitucion = new PrestacionDeServicio(Estado.HABILITADO, molinetes, estacionConstitucion);
            PrestacionDeServicio molinetesBurzaco = new PrestacionDeServicio(Estado.HABILITADO, molinetes, estacionBurzaco);
            PrestacionDeServicio molinetesOnce = new PrestacionDeServicio(Estado.HABILITADO, molinetes, estacionOnce);
            PrestacionDeServicio molinetesRetiro = new PrestacionDeServicio(Estado.HABILITADO, molinetes, estacionRetiro);
            PrestacionDeServicio molinetesPellegrini = new PrestacionDeServicio(Estado.HABILITADO, molinetes, estacionCarlosPellegrini);

            PrestacionDeServicio rampaConstitucion = new PrestacionDeServicio(Estado.HABILITADO, rampaDiscapacitados, estacionConstitucion);
            PrestacionDeServicio rampaBurzaco = new PrestacionDeServicio(Estado.HABILITADO, rampaDiscapacitados, estacionBurzaco);
            PrestacionDeServicio rampaOnce = new PrestacionDeServicio(Estado.HABILITADO, rampaDiscapacitados, estacionOnce);
            PrestacionDeServicio rampaSubteConstitucion = new PrestacionDeServicio(Estado.HABILITADO, rampaDiscapacitados, estacionSubteConstitucion);

            PrestacionDeServicio ascensorDiagonalNorte = new PrestacionDeServicio(Estado.HABILITADO,ascensor,estacionDiagonalNorte);


            Incidente incidente = new Incidente("Inodoro perdiendo", miembro, baniosConstitucion);
            Incidente incidente1 = new Incidente("Molinete trabado", miembro, molinetesBurzaco);
            Incidente incidente2 = new Incidente("Pozo profundo", miembro, rampaOnce);
            Incidente incidente3 = new Incidente("Molinete robado",miembro,molinetesRetiro);
            Incidente incidente4 = new Incidente("Banio tapado",miembro,baniosDiagonalNorte);
            Incidente incidente5 = new Incidente("Ascensor no abre",miembro,ascensorDiagonalNorte);

            repository.guardar(usuarioAdmin);
            repository.guardar(usuarioMiembro);
            repository.guardar(personaAdmin);
            repository.guardar(personaMiembro);
            repository.guardar(banios);
            repository.guardar(molinetes);
            repository.guardar(rampaDiscapacitados);
            repository.guardar(ascensor);
            repository.guardar(baniosBurzaco);
            repository.guardar(baniosOnce);
            repository.guardar(baniosDiagonalNorte);
            repository.guardar(baniosMedrano);
            repository.guardar(molinetesBurzaco);
            repository.guardar(molinetesConstitucion);
            repository.guardar(molinetesOnce);
            repository.guardar(molinetesRetiro);
            repository.guardar(molinetesPellegrini);
            repository.guardar(rampaBurzaco);
            repository.guardar(rampaConstitucion);
            repository.guardar(rampaOnce);
            repository.guardar(rampaSubteConstitucion);
            repository.guardar(ascensorDiagonalNorte);
            repository.guardar(comunidad);
            repository.guardar(comunidad1);
            repository.guardar(miembro);
            repository.guardar(localizacion);
            repository.guardar(incidente);
            repository.guardar(incidente1);
            repository.guardar(incidente2);
            repository.guardar(incidente3);
            repository.guardar(incidente4);
            repository.guardar(incidente5);
        }
    }

    private Initializer iniciarTransaccion() {
        entityManager().getTransaction().begin();
        return this;
    }

    private void commitearTransaccion() {
        entityManager().getTransaction().commit();
    }
}
