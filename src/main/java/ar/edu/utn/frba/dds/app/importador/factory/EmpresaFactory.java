package ar.edu.utn.frba.dds.app.importador.factory;

import ar.edu.utn.frba.dds.app.domain.Entidad;
import ar.edu.utn.frba.dds.app.domain.prestacion.Empresa;
import ar.edu.utn.frba.dds.app.importador.EntityMapper;
import ar.edu.utn.frba.dds.app.importador.IEntityRepository;
import ar.edu.utn.frba.dds.app.importador.ImportadorCSV;
import ar.edu.utn.frba.dds.app.importador.adapters.EmpresaAdapter;

public class EmpresaFactory extends ImportadorCSV<Empresa> {

    private final IEntityRepository<Entidad> entidadesRepository;

    public EmpresaFactory(IEntityRepository<Entidad> entidadesRepository) {
        this.entidadesRepository = entidadesRepository;
    }

    @Override
    public EntityMapper<Empresa> getEntidadCargable() {
        return new EmpresaAdapter(entidadesRepository);
    }
}