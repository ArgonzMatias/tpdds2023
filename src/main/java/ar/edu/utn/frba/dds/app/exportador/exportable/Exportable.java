package ar.edu.utn.frba.dds.app.exportador.exportable;

import java.util.List;
import java.util.Map;

public interface Exportable {
    Map<String, List<String>> datos();
}
