package com.example.Validation;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.Validation.LoginService;
import com.example.Validation.LoginResponse;

@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/api/auth/login")
    public ResponseEntity<LoginResponse> validateLogin(@RequestBody Client client) {
        LoginResponse response = loginService.validateLogin(client.getClientname(), client.getPassword());

        if (response.isSuccess()) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else if (response.getMessage().equals("The client is inactive")) {
            return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
        } else {
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        }
    }
}
