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
import ar.edu.utn.frba.dds.app.exportador.exportable.Exportable;
import ar.edu.utn.frba.dds.app.exportador.tipoDeExportacion.pdf.AdapterApachePDFBox;
import ar.edu.utn.frba.dds.app.exportador.tipoDeExportacion.pdf.ExportarAPDF;
import ar.edu.utn.frba.dds.app.rankeador.criterios.CriterioRanking;
import ar.edu.utn.frba.dds.app.rankeador.criterios.RankingMasIncidentes;
import ar.edu.utn.frba.dds.app.rankeador.criterios.RankingMayorGradoDeImpacto;
import ar.edu.utn.frba.dds.app.rankeador.criterios.RankingMayorPromedio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static ar.edu.utn.frba.dds.app.domain.enums.Estado.DESHABILITADO;
import static ar.edu.utn.frba.dds.app.domain.enums.RolMiembro.AFECTADO;

public class RankingMayorGradoImpactoTest {
    private Exportador exportador;
    List<Entidad> entidadesPrueba1 = new ArrayList<>();
    List<Incidente> incidentesPrueba1 = new ArrayList<>();
    Entidad entidad1;
    Entidad entidad2;
    Entidad entidad3;
    Entidad entidad4;
    Incidente incidente1;
    Incidente incidente2;
    Incidente incidente3;
    Incidente incidenteSinResolver1;
    Incidente incidenteSinResolver2;
    Incidente incidenteSinResolver3;
    Incidente incidenteSinResolver4;

    Establecimiento establecimiento1;
    Establecimiento establecimiento2;
    Establecimiento establecimiento3;
    Miembro bart;
    Miembro homero;
    Miembro lisa;
    Comunidad comunnidadVacia;
    Comunidad comunidadReLlena;
    Comunidad comunidadMediaLlena;
    PrestacionDeServicio prestacionDeServicio1;
    PrestacionDeServicio prestacionDeServicio2;
    @BeforeEach
    public void init() {


        entidad1 = new Entidad("0001", "Trenes Argentinos", new TipoDeEntidad("Transporte", "Test"));
        entidad2 = new Entidad("0002", "Linea 103", new TipoDeEntidad("Transporte", "Test"));
        entidad3 = new Entidad("0003", "Linea 2", new TipoDeEntidad("Transporte", "Test"));
        entidad4 = new Entidad("0004", "Subte A - Acoyte", new TipoDeEntidad("Transporte", "Test"));



        comunnidadVacia = new Comunidad("comunidad desolada");
        comunidadReLlena = new Comunidad("comunidad loca");
        comunidadMediaLlena = new Comunidad("comunidad loca");

         bart = new Miembro(new Persona("bart", "simpson", null, null, null), comunidadReLlena, AFECTADO);
         homero = new Miembro(new Persona("homero", "simpson", null, null, null), comunidadReLlena, AFECTADO);
         lisa = new Miembro(new Persona("lisa", "simpson", null, null, null), comunidadReLlena, AFECTADO);




         establecimiento1 = new Establecimiento("1", null, entidad1);
         establecimiento2 = new Establecimiento("1", null, entidad2);
        establecimiento3 = new Establecimiento("1", null, entidad4);

        PrestacionDeServicio prestacionDeServicio1 = new PrestacionDeServicio(DESHABILITADO, null, establecimiento1);
        PrestacionDeServicio prestacionDeServicio2 = new PrestacionDeServicio(DESHABILITADO, null, establecimiento2);
        PrestacionDeServicio prestacionDeServicio3 = new PrestacionDeServicio(DESHABILITADO, null, establecimiento3);

        prestacionDeServicio2.agregarComunidadInteresada(comunidadMediaLlena);
        prestacionDeServicio3.agregarComunidadInteresada(comunnidadVacia);
        prestacionDeServicio1.agregarComunidadInteresada(comunidadMediaLlena);
        prestacionDeServicio1.agregarComunidadInteresada(comunnidadVacia);


        incidente1 = new Incidente("detalle re detallado", bart, prestacionDeServicio1);
        incidente2 = new Incidente("detalle re detallado", bart, prestacionDeServicio2);
        incidente3 = new Incidente("detalle re detallado", bart, prestacionDeServicio3);

        incidenteSinResolver1 = new Incidente("detalle re detallado", bart, prestacionDeServicio3);
        incidenteSinResolver2 = new Incidente("detalle re detallado", bart, prestacionDeServicio3);
        incidenteSinResolver3 = new Incidente("detalle re detallado", bart, prestacionDeServicio3);
        incidenteSinResolver4 = new Incidente("detalle re detallado", bart, prestacionDeServicio3);
        this.exportador = new Exportador(new ExportarAPDF(new AdapterApachePDFBox("ranking_impacto_mas_alto.pdf")));



    }

    @Test
        public void rankearIncidentesCasoNormal(){
        comunidadReLlena.agregarMiembro(bart);
        comunidadReLlena.agregarMiembro(homero);
        comunidadReLlena.agregarMiembro(lisa);
        comunidadMediaLlena.agregarMiembro(bart);
        comunidadMediaLlena.agregarMiembro(lisa);

        entidad1.agregarEstablecimiento(establecimiento1);
        entidad1.agregarEstablecimiento(establecimiento2);

        entidad2.agregarEstablecimiento(establecimiento1);

        entidad3.agregarEstablecimiento(establecimiento2);

        incidente2.cerrar(LocalDateTime.of(2023, 9, 12, 15, 45, 30), bart);

        entidadesPrueba1.add(entidad1);
        entidadesPrueba1.add(entidad2);
        entidadesPrueba1.add(entidad3);
        incidentesPrueba1.add(incidente1);
        incidentesPrueba1.add(incidente2);

            RankingMayorGradoDeImpacto ranking1 = new RankingMayorGradoDeImpacto();
            ranking1.generarRanking(incidentesPrueba1, entidadesPrueba1);
        }
        @Test
        public void rankingUnMiembro(){
            comunnidadVacia.agregarMiembro(bart);
            entidad1.agregarEstablecimiento(establecimiento1);
            entidad1.agregarEstablecimiento(establecimiento2);
            entidadesPrueba1.add(entidad1);
            incidentesPrueba1.add(incidente1);
            RankingMayorGradoDeImpacto ranking = new RankingMayorGradoDeImpacto();
            ranking.generarRanking(incidentesPrueba1, entidadesPrueba1);
        }
        // (0 incidentes cerrados + (1 incidente abierto * 1 cnf)) * 1 miembro interesado  = 1

    @Test
    public void rankingConIncidentesCerrados(){
        comunidadMediaLlena.agregarMiembro(bart);
        comunidadMediaLlena.agregarMiembro(lisa);

        entidad1.agregarEstablecimiento(establecimiento1);
        entidad1.agregarEstablecimiento(establecimiento2);



        incidente1.cerrar(LocalDateTime.of(2023, 9, 10, 15, 48, 32), lisa);

        entidadesPrueba1.add(entidad1);
        incidentesPrueba1.add(incidente1);

        RankingMayorGradoDeImpacto ranking1 = new RankingMayorGradoDeImpacto();
        ranking1.generarRanking(incidentesPrueba1, entidadesPrueba1);
        // ( 3 + (0 incidentes abiertos * 1)  )* (2 miembros * 1 incidentes que estan interesados) = 6 ranking
    }
    @Test
    public void rankingIncidentesNoResueltos(){
        comunnidadVacia.agregarMiembro(homero);

        entidad4.agregarEstablecimiento(establecimiento3);

        entidadesPrueba1.add(entidad4);
        incidentesPrueba1.add(incidenteSinResolver1);
        incidentesPrueba1.add(incidenteSinResolver2);
        incidentesPrueba1.add(incidenteSinResolver3);
        incidentesPrueba1.add(incidenteSinResolver4);

        RankingMayorGradoDeImpacto ranking1 = new RankingMayorGradoDeImpacto();
        ranking1.generarRanking(incidentesPrueba1, entidadesPrueba1);
        // (0 incidentes cerrados + (4 abiertos * 1 cnf))* 4 miembros interesados = 16 ranking
        // 4 incidentes abiertos, y se cuenta a homero 4 veces porque es un miembro interesado en cada incidente que ocurre
    }
    @Test
    public void generadodorDePdfMasImpacto(){
        comunidadReLlena.agregarMiembro(bart);
        comunidadReLlena.agregarMiembro(homero);
        comunidadReLlena.agregarMiembro(lisa);
        comunidadMediaLlena.agregarMiembro(bart);
        comunidadMediaLlena.agregarMiembro(lisa);

        entidad1.agregarEstablecimiento(establecimiento1);
        entidad1.agregarEstablecimiento(establecimiento2);

        entidad2.agregarEstablecimiento(establecimiento1);

        entidad3.agregarEstablecimiento(establecimiento2);

        incidente2.cerrar(LocalDateTime.of(2023, 9, 12, 15, 45, 30), bart);

        entidadesPrueba1.add(entidad1);

        entidadesPrueba1.add(entidad3);
        incidentesPrueba1.add(incidente1);
        incidentesPrueba1.add(incidente2);
        comunnidadVacia.agregarMiembro(homero);

        entidad4.agregarEstablecimiento(establecimiento3);

        entidadesPrueba1.add(entidad4);
        incidentesPrueba1.add(incidenteSinResolver1);
        incidentesPrueba1.add(incidenteSinResolver2);
        incidentesPrueba1.add(incidenteSinResolver3);
        incidentesPrueba1.add(incidenteSinResolver4);

        RankeadorIncidentes ranking  = new RankeadorIncidentes(exportador);
        CriterioRanking rankingMayorGradoDeImpacto = new RankingMayorGradoDeImpacto();
        ranking.agregarCriterio(rankingMayorGradoDeImpacto);
        List<Exportable> aExportar = ranking.generarInforme(incidentesPrueba1, entidadesPrueba1);
        Exportable datos = aExportar.get(0);


        this.exportador.setExportable(datos);
        Assertions.assertEquals(Config.RUTA_EXPORTACION + "ranking_impacto_mas_alto.pdf", this.exportador.exportar());

        // (0 incidentes cerrados + (4 abiertos * 1 cnf))* 4 miembros interesados = 16 ranking
        // 4 incidentes abiertos, y se cuenta a homero 4 veces porque es un miembro interesado en cada incidente que ocurre
    }

    }
