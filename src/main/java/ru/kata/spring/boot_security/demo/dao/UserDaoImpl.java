package ru.kata.spring.boot_security.demo.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.model.User;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public void add(User user) {
        em.persist(user);
    }

    @Override
    @Transactional
    public User getUserById(Integer id) {
        return em.find(User.class, id);
    }

    @Override
    @Transactional
    public User getUserByEmail(String email) {
        Query query = em.createQuery("SELECT u FROM User u WHERE u.email = : e");
        query.setParameter("e", email);
        return (User) query.getSingleResult();
    }

    @Override
    @Transactional
    public List<User> listUsers() {
        return em.createQuery("select u from User u", User.class).getResultList();
    }

    @Override
    @Transactional
    public void deleteUserById(Integer id) {
        User u = em.find(User.class, id);
        em.remove(u);
    }

    @Override
    @Transactional
    public void updateUser(Integer id, User user) {
        User userToBeUpdated = em.find(User.class, id);
        userToBeUpdated.setAge(user.getAge());
        userToBeUpdated.setFirstName(user.getFirstName());
        userToBeUpdated.setSecondName(user.getSecondName());
        userToBeUpdated.setEmail(user.getEmail());
        userToBeUpdated.setPassword(user.getPassword());
        userToBeUpdated.setRoles(user.getRoles());
        em.merge(userToBeUpdated);
    }
}
