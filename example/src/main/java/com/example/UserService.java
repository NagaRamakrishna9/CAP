package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public void createUser(String name, String email) {
        userDao.createUser(name, email);
    }

    public User getUserById(Long id) {
        return userDao.getUserById(id);
    }

    public void updateUser(Long id, String name, String email) {
        userDao.updateUser(id, name, email);
    }

    public void deleteUser(Long id) {
        userDao.deleteUser(id);
    }
}
