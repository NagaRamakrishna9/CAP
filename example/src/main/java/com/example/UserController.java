package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public void createUser(@RequestBody User user) {
        userService.createUser(user.getName(), user.getEmail());
    }
    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @PutMapping("/update/{id}")
    public void updateUser(@PathVariable Long id, @RequestParam String name, @RequestParam String email) {
        userService.updateUser(id, name, email);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }
}
