package ru.kata.spring.boot_security.demo.dao;

import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.Role;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

@Repository
public class RoleDaoImpl implements RoleDao {
    @PersistenceContext
    private EntityManager em;
    @Transactional
    @Override
    public Role getRoleByName(String role) {
        Query query = em.createQuery("SELECT r FROM Role r WHERE r.role = : role");
        query.setParameter("role", role);
        return (Role) query.getSingleResult();
    }

    @Override
    @Transactional
    public void addRole(Role role) {
        em.persist(role);
    }
}