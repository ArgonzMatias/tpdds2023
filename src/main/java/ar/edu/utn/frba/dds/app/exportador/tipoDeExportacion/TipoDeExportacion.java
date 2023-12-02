package ar.edu.utn.frba.dds.app.exportador.tipoDeExportacion;

import ar.edu.utn.frba.dds.app.exportador.exportable.Exportable;

public interface TipoDeExportacion {
    String exportar(Exportable exportable);
}
