package ar.edu.utn.frba.dds.app.repositories;

import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;

import javax.persistence.EntityTransaction;
import java.util.List;

public class Repository implements ICrudRepository, WithSimplePersistenceUnit {
    @Override
    public List buscarTodos(String className) {
        return entityManager().createQuery("from " + className).getResultList();
    }

    @Override
    public Object buscar(Class clase, Long id) {
        return entityManager().find(clase, id);
    }

    @Override
    public void guardar(Object o) {
        entityManager().getTransaction().begin();
        EntityTransaction tx = entityManager().getTransaction();
        entityManager().persist(o);
        tx.commit();
    }

    @Override
    public void actualizar(Object o) {
        entityManager().getTransaction().begin();
        EntityTransaction tx = entityManager().getTransaction();
        entityManager().merge(o);
        tx.commit();
    }

    @Override
    public void eliminar(Object o) {
        EntityTransaction tx = entityManager().getTransaction();
        entityManager().remove(o);
        tx.commit();
    }
}
