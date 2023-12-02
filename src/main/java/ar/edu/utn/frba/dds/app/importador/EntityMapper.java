package ar.edu.utn.frba.dds.app.importador;

public interface EntityMapper<T> {
    T mapearDesdeCSV(String[] datos);
}
