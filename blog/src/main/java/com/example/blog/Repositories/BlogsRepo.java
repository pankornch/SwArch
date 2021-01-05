package com.example.blog.Repositories;

import com.example.blog.dao.Blogs;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class BlogsRepo {
    @PersistenceContext
    private EntityManager entityManager;

    public List<Blogs> findAll() {
        Query q = entityManager.createQuery("from Blogs");
        return q.getResultList();
    }

    public Blogs findById(Integer id) {
        return entityManager.find(Blogs.class, id);
    }

    @Transactional
    public Blogs save(Blogs blogs) {
         entityManager.persist(blogs);
         return blogs;
    }

    @Transactional
    public void delete(Blogs blogs) {
        entityManager.remove(blogs);

        // Clear
        entityManager.flush();
        entityManager.clear();
    }

}
