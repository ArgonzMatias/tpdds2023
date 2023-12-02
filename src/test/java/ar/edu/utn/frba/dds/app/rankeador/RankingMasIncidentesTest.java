package ar.edu.utn.frba.dds.app.rankeador;

import ar.edu.utn.frba.dds.app.config.Config;
import ar.edu.utn.frba.dds.app.domain.Entidad;
import ar.edu.utn.frba.dds.app.domain.Establecimiento;
import ar.edu.utn.frba.dds.app.domain.Miembro;
import ar.edu.utn.frba.dds.app.domain.TipoDeEntidad;
import ar.edu.utn.frba.dds.app.domain.prestacion.PrestacionDeServicio;
import ar.edu.utn.frba.dds.app.domain.sociedad.Comunidad;
import ar.edu.utn.frba.dds.app.domain.sociedad.Incidente;
import ar.edu.utn.frba.dds.app.domain.sociedad.Persona;

import ar.edu.utn.frba.dds.app.exportador.Exportador;
import ar.edu.utn.frba.dds.app.exportador.exportable.Documento;
import ar.edu.utn.frba.dds.app.exportador.exportable.Exportable;
import ar.edu.utn.frba.dds.app.exportador.tipoDeExportacion.pdf.AdapterApachePDFBox;
import ar.edu.utn.frba.dds.app.exportador.tipoDeExportacion.pdf.ExportarAPDF;
import ar.edu.utn.frba.dds.app.rankeador.criterios.CriterioRanking;
import ar.edu.utn.frba.dds.app.rankeador.criterios.RankingMasIncidentes;
import ar.edu.utn.frba.dds.app.rankeador.criterios.RankingMayorGradoDeImpacto;
import ar.edu.utn.frba.dds.app.rankeador.criterios.RankingMayorPromedio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static ar.edu.utn.frba.dds.app.domain.enums.Estado.DESHABILITADO;
import static ar.edu.utn.frba.dds.app.domain.enums.RolMiembro.AFECTADO;
import static org.mockito.Mockito.mock;

public class RankingMasIncidentesTest {

        private Exportador exportador;
        List<Entidad> entidadesPrueba1 = new ArrayList<>();
        List<Incidente> incidentesPrueba1 = new ArrayList<>();
        Entidad entidad1;
        Entidad entidad2;
        Entidad entidad3;
        Entidad entidad4;
    Entidad   entidad5;
    Entidad entidad6;
        Incidente incidente1;
        Incidente incidente2;
        Incidente incidente3;
    Incidente incidente4;
    Incidente incidente5;
    Incidente incidente6;

    Incidente incidente7;
    Incidente  incidente8;
    Incidente  incidente9;
    Incidente   incidente10;

        Establecimiento establecimiento1;
        Establecimiento establecimiento2;
        Establecimiento establecimiento3;
    Establecimiento establecimiento4;
    Establecimiento establecimiento5;
    Establecimiento establecimiento6;
    Establecimiento establecimiento7;
    Establecimiento  establecimiento8;
    Establecimiento establecimiento9 ;
    Establecimiento establecimiento10;
    Miembro bart;
        Miembro homero;
        Miembro lisa;
        Comunidad comunnidadVacia;
        Comunidad comunidadReLlena;
        Comunidad comunidadMediaLlena;
        PrestacionDeServicio prestacionDeServicio1;
        PrestacionDeServicio prestacionDeServicio2;
    PrestacionDeServicio prestacionDeServicio3;
    PrestacionDeServicio prestacionDeServicio4;
    PrestacionDeServicio prestacionDeServicio5;
    PrestacionDeServicio prestacionDeServicio6;
    PrestacionDeServicio prestacionDeServicio7;
    PrestacionDeServicio  prestacionDeServicio8;
    PrestacionDeServicio prestacionDeServicio9;
    PrestacionDeServicio prestacionDeServicio10;

    @BeforeEach
        public void init() {


            entidad1 = new Entidad("0001", "Trenes Argentinos", new TipoDeEntidad("Transporte", "Test"));
            entidad2 = new Entidad("0002", "Linea 103", new TipoDeEntidad("Transporte", "Test"));
            entidad3 = new Entidad("0003", "Linea 2", new TipoDeEntidad("Transporte", "Test"));
            entidad4 = new Entidad("0004", "Subte A - Acoyte", new TipoDeEntidad("Transporte", "Test"));
            entidad5 = new Entidad("0004", "Subte B - Alem", new TipoDeEntidad("Transporte", "Test"));
            entidad6 = new Entidad("0004", "Subte A - Av La Plata", new TipoDeEntidad("Transporte", "Test"));



        comunnidadVacia = new Comunidad("comunidad desolada");
            comunidadReLlena = new Comunidad("comunidad loca");
            comunidadMediaLlena = new Comunidad("comunidad loca");

            bart = new Miembro(new Persona("bart", "simpson", null, null, null), comunidadReLlena, AFECTADO);
            homero = new Miembro(new Persona("homero", "simpson", null, null, null), comunidadReLlena, AFECTADO);
            lisa = new Miembro(new Persona("lisa", "simpson", null, null, null), comunidadReLlena, AFECTADO);




            establecimiento1 = new Establecimiento("1", null, entidad1);
            establecimiento2 = new Establecimiento("1", null, entidad2);
            establecimiento4 = new Establecimiento("1", null, entidad4);
            establecimiento3 = new Establecimiento("1", null, entidad3);
            establecimiento4 = new Establecimiento("1", null, entidad6);
            establecimiento5 = new Establecimiento("1", null, entidad6);
            establecimiento6 = new Establecimiento("1", null, entidad6);
            establecimiento7 = new Establecimiento("1", null, entidad6);
            establecimiento8 = new Establecimiento("1", null, entidad6);
            establecimiento9 = new Establecimiento("1", null, entidad6);
            establecimiento10 = new Establecimiento("1", null, entidad6);


             prestacionDeServicio1 = new PrestacionDeServicio(DESHABILITADO, null, establecimiento1);
             prestacionDeServicio2 = new PrestacionDeServicio(DESHABILITADO, null, establecimiento2);
             prestacionDeServicio3 = new PrestacionDeServicio(DESHABILITADO, null, establecimiento3);
             prestacionDeServicio4= new PrestacionDeServicio(DESHABILITADO, null, establecimiento4);
            prestacionDeServicio5= new PrestacionDeServicio(DESHABILITADO, null, establecimiento5);
            prestacionDeServicio6= new PrestacionDeServicio(DESHABILITADO, null, establecimiento6);
            prestacionDeServicio7= new PrestacionDeServicio(DESHABILITADO, null, establecimiento7);
            prestacionDeServicio8= new PrestacionDeServicio(DESHABILITADO, null, establecimiento8);
            prestacionDeServicio9= new PrestacionDeServicio(DESHABILITADO, null, establecimiento9);
            prestacionDeServicio10= new PrestacionDeServicio(DESHABILITADO, null, establecimiento10);
            prestacionDeServicio2.agregarComunidadInteresada(comunidadMediaLlena);
            prestacionDeServicio3.agregarComunidadInteresada(comunnidadVacia);
            prestacionDeServicio1.agregarComunidadInteresada(comunidadMediaLlena);
            prestacionDeServicio1.agregarComunidadInteresada(comunnidadVacia);


            incidente1 = new Incidente("detalle re detallado", bart, prestacionDeServicio1);
            incidente2 = new Incidente("detalle re detallado", bart, prestacionDeServicio1);
            incidente3 = new Incidente("detalle re detallado", bart, prestacionDeServicio1);
             incidente4 = new Incidente("detalle re detallado", bart, prestacionDeServicio1);
             incidente5 = new Incidente("detalle re detallado", bart, prestacionDeServicio2);
             incidente6 = new Incidente("detalle re detallado", bart, prestacionDeServicio2);
             incidente7 = new Incidente("detalle re detallado", bart, prestacionDeServicio2);
             incidente8 = new Incidente("detalle re detallado", bart, prestacionDeServicio3);
             incidente9 = new Incidente("detalle re detallado", bart, prestacionDeServicio3);
             incidente10 = new Incidente("detalle re detallado", bart, prestacionDeServicio4);


            this.exportador = new Exportador(new ExportarAPDF(new AdapterApachePDFBox("ranking_promedio_tiempo.pdf")));
        }

    @Test
    public void rankingEntidadConIncidentes(){
        comunidadMediaLlena.agregarMiembro(bart);
        comunidadMediaLlena.agregarMiembro(lisa);
        RankeadorIncidentes ranking  = new RankeadorIncidentes(exportador);
        entidad1.agregarEstablecimiento(establecimiento1);
        entidad2.agregarEstablecimiento(establecimiento2);
        entidad3.agregarEstablecimiento(establecimiento3);
        entidad4.agregarEstablecimiento(establecimiento2);
        entidad6.agregarEstablecimiento(establecimiento1);
        entidad6.agregarEstablecimiento(establecimiento3);
        entidad6.agregarEstablecimiento(establecimiento3);
        entidad6.agregarEstablecimiento(establecimiento5);

        CriterioRanking rankingMasIncidentes = new RankingMasIncidentes();

        ranking.agregarCriterio(rankingMasIncidentes);

        entidadesPrueba1.add(entidad6);
        entidadesPrueba1.add(entidad1);
        entidadesPrueba1.add(entidad2);
        entidadesPrueba1.add(entidad3);
        entidadesPrueba1.add(entidad4);
        entidadesPrueba1.add(entidad5);



        incidentesPrueba1.add(incidente6);
        incidentesPrueba1.add(incidente7);
        incidentesPrueba1.add(incidente8);
        incidentesPrueba1.add(incidente9);
        incidentesPrueba1.add(incidente10);
        incidentesPrueba1.add(incidente1);
        incidentesPrueba1.add(incidente2);
        incidentesPrueba1.add(incidente3);
        incidentesPrueba1.add(incidente4);
        incidentesPrueba1.add(incidente5);



        List<Exportable> aExportar = ranking.generarInforme(incidentesPrueba1, entidadesPrueba1);
        Exportable datos = aExportar.get(0);


        this.exportador.setExportable(datos);
        Assertions.assertEquals(Config.RUTA_EXPORTACION + "ranking_promedio_tiempo.pdf", this.exportador.exportar());
    }

}
