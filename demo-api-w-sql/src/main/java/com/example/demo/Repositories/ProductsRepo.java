package com.example.demo.Repositories;

import com.example.demo.doa.Products;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
public class ProductsRepo {
    @PersistenceContext
    EntityManager entityManager;

    @Transactional
    public Products save(Products products) {
        entityManager.persist(products);
        return products;
    }

    @Transactional
    public void delete(Products products) {
        entityManager.remove(products);

        entityManager.flush();
        entityManager.clear();
    }
}
