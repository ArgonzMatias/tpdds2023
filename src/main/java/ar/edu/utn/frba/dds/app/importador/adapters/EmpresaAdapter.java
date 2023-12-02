package ar.edu.utn.frba.dds.app.importador.adapters;

import ar.edu.utn.frba.dds.app.domain.Entidad;
import ar.edu.utn.frba.dds.app.domain.prestacion.Empresa;
import ar.edu.utn.frba.dds.app.domain.sociedad.Usuario;
import ar.edu.utn.frba.dds.app.exceptions.ImportadorCSVException;
import ar.edu.utn.frba.dds.app.importador.EntityMapper;
import ar.edu.utn.frba.dds.app.importador.IEntityRepository;

public class EmpresaAdapter implements EntityMapper<Empresa> {

    private final IEntityRepository<Entidad> entidadesRepository;

    public EmpresaAdapter(IEntityRepository<Entidad> entidadesRepository) {
        this.entidadesRepository = entidadesRepository;
    }

    @Override
    public Empresa mapearDesdeCSV(String[] datos) {
        String codigo = datos[0];
        String nombre = datos[1];
        String mail = datos[2];
        Usuario usuario = new Usuario();
        usuario.setCorreoElectronico(mail);
        Empresa empresa = new Empresa(codigo,nombre, usuario);

        String[] codigosEntidades = datos[3].split(",");
        for (String codigoEntidad : codigosEntidades) {
            String codigoEntidadFormat = codigoEntidad.trim();
            Entidad entidad = entidadesRepository.getByCodigo(codigoEntidadFormat);
            if (entidad == null) {
                throw new ImportadorCSVException("La entidad con ID " + codigoEntidadFormat + " no existe.");
            }
            empresa.agregarEntidad(entidad);
        }
        return empresa;
    }
}
