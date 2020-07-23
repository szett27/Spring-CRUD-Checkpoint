package com.example.CRUDCheckpoint;


import org.springframework.web.bind.annotation.*;

@RestController
    @RequestMapping("/users")
    public class UserController {

        private final UserRepository repository;

        public UserController(UserRepository repository) {
            this.repository = repository;
        }

        @GetMapping("")
        public Iterable<User> all() {
            return this.repository.findAll();
        }

        @PostMapping("")
        public User create(@RequestBody User user) {
            return this.repository.save(user);
        }

    }
