package ar.edu.utn.frba.dds.app.importador.factory;

import ar.edu.utn.frba.dds.app.domain.prestacion.Empresa;
import ar.edu.utn.frba.dds.app.domain.prestacion.OrganismoDeControl;
import ar.edu.utn.frba.dds.app.importador.EntityMapper;
import ar.edu.utn.frba.dds.app.importador.IEntityRepository;
import ar.edu.utn.frba.dds.app.importador.ImportadorCSV;
import ar.edu.utn.frba.dds.app.importador.adapters.OrganismoDeControlAdapter;

public class OrganismoDeControlFactory extends ImportadorCSV<OrganismoDeControl>  {

    private final IEntityRepository<Empresa> entidadesRepository;

    public OrganismoDeControlFactory(IEntityRepository<Empresa> entidadesRepository) {
        this.entidadesRepository = entidadesRepository;
    }

    @Override
    public EntityMapper<OrganismoDeControl> getEntidadCargable() {
        return new OrganismoDeControlAdapter(entidadesRepository);
    }
}
