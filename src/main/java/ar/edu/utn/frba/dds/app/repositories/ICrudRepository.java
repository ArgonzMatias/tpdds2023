package ar.edu.utn.frba.dds.app.repositories;

import java.util.List;

public interface ICrudRepository {
    List buscarTodos(String className);

    Object buscar(Class clase, Long id);

    void guardar(Object o);

    void actualizar(Object o);

    void eliminar(Object o);
}