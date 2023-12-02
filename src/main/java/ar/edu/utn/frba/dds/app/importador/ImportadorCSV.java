package ar.edu.utn.frba.dds.app.importador;

import ar.edu.utn.frba.dds.app.exceptions.ImportadorCSVException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public abstract class ImportadorCSV<T> {

    public abstract EntityMapper<T> getEntidadCargable();

    public List<T> importar(String rutaArchivo) {
        List<T> entidadesCargadas = new ArrayList<>();
        try {
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream(rutaArchivo);
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(";");
                T entidad = getEntidadCargable().mapearDesdeCSV(datos);
                entidadesCargadas.add(entidad);
            }
            br.close();
        } catch (IOException e) {
            throw new ImportadorCSVException("Ocurrio un error al importar como csv: " + e);
        }
        return entidadesCargadas;
    }
}
