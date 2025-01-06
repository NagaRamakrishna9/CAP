package com.example.Validation;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    private ClientDao clientDao;

    public LoginResponse validateLogin(String clientname, String password) {
        int clientStatus = clientDao.validateClientLogin(clientname, password);

        if (clientStatus == -1) {
            return new LoginResponse("Invalid credentials", false);
        } else if (clientStatus == 0) {
            return new LoginResponse("The client is inactive", false);
        } else {
            return new LoginResponse("Authentication successful", true);
        }
    }
}
