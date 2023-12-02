package ar.edu.utn.frba.dds.app.exportador;

import ar.edu.utn.frba.dds.app.exportador.exportable.Exportable;
import ar.edu.utn.frba.dds.app.exportador.tipoDeExportacion.TipoDeExportacion;

public class Exportador {
    private TipoDeExportacion tipoDeExportacion;
    private Exportable exportable;

    public Exportador(TipoDeExportacion tipoDeExportacion){
        this.tipoDeExportacion = tipoDeExportacion;
    }

    public void setExportable(Exportable exportable) {
        this.exportable = exportable;
    }

    public void setTipoDeExportacion(TipoDeExportacion tipoDeExportacion) {
        this.tipoDeExportacion = tipoDeExportacion;
    }

    public String exportar(){
        return this.tipoDeExportacion.exportar(this.exportable);
    }
}
