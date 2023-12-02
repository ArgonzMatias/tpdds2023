package ar.edu.utn.frba.dds.app.exportador;

import ar.edu.utn.frba.dds.app.config.Config;
import ar.edu.utn.frba.dds.app.exportador.Exportador;
import ar.edu.utn.frba.dds.app.exportador.exportable.Documento;
import ar.edu.utn.frba.dds.app.exportador.tipoDeExportacion.pdf.AdapterApachePDFBox;
import ar.edu.utn.frba.dds.app.exportador.tipoDeExportacion.pdf.ExportarAPDF;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ExportadorTest {
    private Exportador exportador;
    private Documento documento;

    @BeforeEach
    public void init(){
        this.exportador = new Exportador(new ExportarAPDF(new AdapterApachePDFBox("ranking_incidentes.pdf")));
        Documento documento  = new Documento();
        documento.agregarDato("0", "Ranking", "Entidad", "Valor");
        this.documento = documento;
    }

    @Test
    @DisplayName("Se exporta a PDF y genera el archivo de manera correcta")
    public void exportarAPDFGeneraArchivo(){
        this.documento.agregarDato("1", "Promedio de tiempo de cierre de incidentes", "Entidad 1", "2hs");
        this.documento.agregarDato("2", "Promedio de tiempo de cierre de incidentes", "Entidad 3", "3hs");
        this.documento.agregarDato("3", "Mayor cantidad de incidentes reportados", "Entidad 4", "10 incidentes");
        this.documento.agregarDato("4", "Mayor cantidad de incidentes reportados", "Entidad 1", "5 incidentes");
        this.exportador.setExportable(this.documento);
        Assertions.assertEquals(Config.RUTA_EXPORTACION + "ranking_incidentes.pdf", this.exportador.exportar());
    }
}
