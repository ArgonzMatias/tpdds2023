package ar.edu.utn.frba.dds.app.georef;

import ar.edu.utn.frba.dds.app.entities.georef.Departamentos;
import ar.edu.utn.frba.dds.app.entities.georef.Municipios;
import ar.edu.utn.frba.dds.app.entities.georef.Provincias;
import ar.edu.utn.frba.dds.app.entities.georef.UbicacionGeografica;
import ar.edu.utn.frba.dds.app.services.georef.GeorefServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class GeoRefTest {

  @Test
  public void provinciasTest() throws IOException {
    GeorefServiceImpl serv = GeorefServiceImpl.instancia();
    Provincias provincias = serv.listadoDeProvincias();
    provincias.provincias.forEach(ubicacionGeografica -> System.out.print(ubicacionGeografica.nombre));
    Assertions.assertEquals(24,provincias.provincias.toArray().length);
  }

  @Test
  public void departamentosTest() throws IOException {
    GeorefServiceImpl serv = GeorefServiceImpl.instancia();
    Provincias provincias = serv.listadoDeProvincias();
    UbicacionGeografica provincia = provincias.getProvinciaDeNombre("Ciudad Autónoma de Buenos Aires");
    Departamentos departamentos = serv.listadoDeDepartamenotsDeProvincia(provincia);
    departamentos.departamentos.forEach(ubicacionGeografica -> System.out.print(ubicacionGeografica.nombre));
    Assertions.assertEquals(15,departamentos.departamentos.toArray().length);
  }

  @Test
  public void municipiosTest() throws IOException {
    GeorefServiceImpl serv = GeorefServiceImpl.instancia();
    Provincias provincias = serv.listadoDeProvincias();
    UbicacionGeografica provincia = provincias.getProvinciaDeNombre("Buenos Aires");
    Municipios municipios = serv.listadoDeMunicipiosDeProvincia(provincia);
    municipios.municipios.forEach(ubicacionGeografica -> System.out.print(ubicacionGeografica.nombre));
    Assertions.assertEquals(135,municipios.municipios.toArray().length);
  }
}
