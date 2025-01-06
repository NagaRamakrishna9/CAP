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

    /**
     * Method to validate client login by calling stored procedure.
     *
     * @param clientname the client name
     * @param password the client password
     * @return status code: -1 for invalid credentials, client status (0 or 1) otherwise
     */
    public int validateClientLogin(String clientname, String password) {
        // Create a StoredProcedureQuery object to call the stored procedure "VALIDATE_CLIENT_LOGIN"
        StoredProcedureQuery storedProcedure = entityManager.createStoredProcedureQuery("VALIDATE_CLIENT_LOGIN");

        // Register input parameters for the stored procedure
        storedProcedure.registerStoredProcedureParameter("p_clientname", String.class, ParameterMode.IN);
        storedProcedure.registerStoredProcedureParameter("p_password", String.class, ParameterMode.IN);

        // Register output parameters for the stored procedure
        storedProcedure.registerStoredProcedureParameter("p_status", Integer.class, ParameterMode.OUT);
        storedProcedure.registerStoredProcedureParameter("p_message", String.class, ParameterMode.OUT);

        // Set the input parameters (clientname, password)
        storedProcedure.setParameter("p_clientname", clientname);
        storedProcedure.setParameter("p_password", password);

        // Execute the stored procedure
        storedProcedure.execute();

        // Retrieve the output parameters
        Integer status = (Integer) storedProcedure.getOutputParameterValue("p_status");
        String message = (String) storedProcedure.getOutputParameterValue("p_message");

        // Handle the result: check if credentials are valid and return appropriate status
        if (status != null && status == 0) {
            // Invalid credentials
            return -1;
        }

        // At this point, credentials are valid. We now return the client status (0 = inactive, 1 = active)
        if (status != null && status == 1) {
            // Success case: Active client
            return 1;  // Active client
        }

        // If the status is null or unexpected, return -1 as an error state
        return -1;
    }
}
