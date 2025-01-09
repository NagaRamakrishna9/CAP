package com.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.StoredProcedureQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void createUser(String name, String email) {
        StoredProcedureQuery storedProcedure = entityManager.createStoredProcedureQuery("create_user");
        storedProcedure.registerStoredProcedureParameter("p_name", String.class, ParameterMode.IN);
        storedProcedure.registerStoredProcedureParameter("p_email", String.class, ParameterMode.IN);
        storedProcedure.setParameter("p_name", name);
        storedProcedure.setParameter("p_email", email);
        storedProcedure.execute();
    }

    public User getUserById(Long id) {
        try {
            // StoredProcedureQuery storedProcedure = entityManager.createStoredProcedureQuery("get_user_by_id", User.class);
            StoredProcedureQuery storedProcedure = entityManager.createStoredProcedureQuery("get_user_by_id",User.class);
            storedProcedure.registerStoredProcedureParameter("p_id", Long.class, ParameterMode.IN);
            storedProcedure.setParameter("p_id", id);
            return (User) storedProcedure.getSingleResult();
        } catch (NoResultException e) {
            return null;  // Return null if no user is found
        }
    }

   
    @Transactional
    public void updateUser(Long id, String name, String email) {
        StoredProcedureQuery storedProcedure = entityManager.createStoredProcedureQuery("update_user");
        storedProcedure.registerStoredProcedureParameter("p_id", Long.class, ParameterMode.IN);
        storedProcedure.registerStoredProcedureParameter("p_name", String.class, ParameterMode.IN);
        storedProcedure.registerStoredProcedureParameter("p_email", String.class, ParameterMode.IN);
        storedProcedure.setParameter("p_id", id);
        storedProcedure.setParameter("p_name", name);
        storedProcedure.setParameter("p_email", email);
        storedProcedure.execute();
    }

    // Delete User using stored procedure
    @Transactional
    public void deleteUser(Long id) {
        StoredProcedureQuery storedProcedure = entityManager.createStoredProcedureQuery("delete_user");
        // storedProcedure.registerStoredProcedureParameter("p_id", Long.class, ParameterMode.IN);
        storedProcedure.registerStoredProcedureParameter("p_id",Long.class,ParameterMode.IN);
        storedProcedure.setParameter("p_id", id);
        storedProcedure.execute();
    }
}

