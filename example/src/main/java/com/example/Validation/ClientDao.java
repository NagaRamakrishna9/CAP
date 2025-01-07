package com.example.Validation;

import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.StoredProcedureQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ClientDao {

    @Autowired
    private EntityManager entityManager;

    public int validateClientLogin(String clientname, String password) {
      
        StoredProcedureQuery storedProcedure = entityManager.createStoredProcedureQuery("VALIDATE_CLIENT_LOGIN");
        storedProcedure.registerStoredProcedureParameter("p_clientname", String.class, ParameterMode.IN);
        storedProcedure.registerStoredProcedureParameter("p_password", String.class, ParameterMode.IN);  
        storedProcedure.registerStoredProcedureParameter("p_status", Integer.class, ParameterMode.OUT);
        storedProcedure.registerStoredProcedureParameter("p_message", String.class, ParameterMode.OUT);     
        storedProcedure.setParameter("p_clientname", clientname);
        storedProcedure.setParameter("p_password", password);
        storedProcedure.execute();

        Integer status = (Integer) storedProcedure.getOutputParameterValue("p_status");
        String message = (String) storedProcedure.getOutputParameterValue("p_message"); 
        if (status != null && status == 0) {
            return -1;
        }
        if (status != null && status == 1) {    
            return 1;  
        }
        return -1;
    }
}
