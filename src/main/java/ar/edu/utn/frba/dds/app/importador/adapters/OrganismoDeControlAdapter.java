package ar.edu.utn.frba.dds.app.importador.adapters;

import ar.edu.utn.frba.dds.app.domain.prestacion.Empresa;
import ar.edu.utn.frba.dds.app.domain.prestacion.OrganismoDeControl;
import ar.edu.utn.frba.dds.app.domain.sociedad.Usuario;
import ar.edu.utn.frba.dds.app.exceptions.ImportadorCSVException;
import ar.edu.utn.frba.dds.app.importador.EntityMapper;
import ar.edu.utn.frba.dds.app.importador.IEntityRepository;

public class OrganismoDeControlAdapter implements EntityMapper<OrganismoDeControl> {

    private final IEntityRepository<Empresa> entidadesRepository;

    public OrganismoDeControlAdapter(IEntityRepository<Empresa> entidadesRepository) {
        this.entidadesRepository = entidadesRepository;
    }

    @Override
    public OrganismoDeControl mapearDesdeCSV(String[] datos) {
        String codigo = datos[0];
        String nombre = datos[1];
        String mail = datos[2];
        Usuario usuario = new Usuario();
        usuario.setCorreoElectronico(mail);
        OrganismoDeControl organismoDeControl = new OrganismoDeControl(codigo, nombre, usuario);

        String[] codigosEmpresas = datos[3].split(",");
        for (String codigoEmpresa : codigosEmpresas) {
            String codigoEmpresaFormat = codigoEmpresa.trim();
            Empresa empresa = entidadesRepository.getByCodigo(codigoEmpresaFormat);
            if (empresa == null) {
                throw new ImportadorCSVException("La empresa con ID " + codigoEmpresaFormat + " no existe.");
            }
            organismoDeControl.agregarEmpresaPrestadora(empresa);
        }
        return organismoDeControl;
    }
}
