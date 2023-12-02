package ar.edu.utn.frba.dds.app.exportador.tipoDeExportacion.pdf;

import ar.edu.utn.frba.dds.app.exportador.exportable.Exportable;
import ar.edu.utn.frba.dds.app.exportador.tipoDeExportacion.TipoDeExportacion;

public class ExportarAPDF implements TipoDeExportacion {
    private final AdapterExportadorPDF adapter;

    public ExportarAPDF(AdapterExportadorPDF adapter){
        this.adapter = adapter;
    }

    public String exportar(Exportable exportable) {
        return this.adapter.exportar(exportable);
    }
}