package ar.edu.utn.frba.dds.app.exportador.tipoDeExportacion.pdf;

import ar.edu.utn.frba.dds.app.exportador.exportable.Exportable;

public interface AdapterExportadorPDF {
    String exportar(Exportable exportable);
}
