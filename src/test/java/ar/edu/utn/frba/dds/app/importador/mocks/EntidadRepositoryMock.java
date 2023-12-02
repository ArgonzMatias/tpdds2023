package ar.edu.utn.frba.dds.app.importador.mocks;

import ar.edu.utn.frba.dds.app.domain.Entidad;
import ar.edu.utn.frba.dds.app.importador.IEntityRepository;
import java.util.ArrayList;
import java.util.List;

public class EntidadRepositoryMock implements IEntityRepository<Entidad> {

    List<Entidad> entidades = new ArrayList<>();

    @Override
    public Entidad getByCodigo(String codigo) {
        return entidades.get(0);
    }

    public void agregarEntidad(Entidad entidad) {
        entidades.add(entidad);
    }
}
