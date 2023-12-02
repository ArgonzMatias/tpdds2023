package ar.edu.utn.frba.dds.app.repositories;

import ar.edu.utn.frba.dds.app.domain.sociedad.Usuario;

public class UsuariosRepository extends Repository {
    public Usuario buscarUsuarioPorCorreo(String correo) {
        return entityManager()
                .createQuery("from Usuario where correoElectronico = :correo", Usuario.class)
                .setParameter("correo", correo)
                .getSingleResult();
    }
}
