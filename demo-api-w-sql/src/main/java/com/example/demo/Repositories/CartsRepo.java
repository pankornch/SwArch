package com.example.demo.Repositories;

import com.example.demo.doa.Carts;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class CartsRepo {
    @PersistenceContext
    EntityManager entityManager;

    public List<Carts> findAll() {
        Query q = entityManager.createQuery("from Carts");
        return q.getResultList();
    }

    public Carts findById(Integer id) {
        return entityManager.find(Carts.class, id);
    }

    @Transactional
    public Carts save(Carts carts) {
        entityManager.persist(carts);
        return carts;
    }

    @Transactional
    public void delete(Carts carts) {
        entityManager.remove(carts);

        entityManager.flush();
        entityManager.clear();
    }
}
