package com.example.CRUDCheckpoint;


import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
    @RequestMapping("/users")
    public class UserController {

        private final UserRepository repository;

        public UserController(UserRepository repository) {
            this.repository = repository;
        }

        //Checkpoint #1 - works
        @GetMapping("")
        public Iterable<User> all() {
            return this.repository.findAll();
        }

        //Checkpoint #2 - works
        @PostMapping("")
        public User create(@RequestBody User user) {
            return this.repository.save(user);
        }

        //Checkpoint #3 - works
         @GetMapping("/{id}")
            public User getUser(@PathVariable("id") Long id) {
            return this.repository.byId(id);
            }

        //Checkpoint #5 - works
        @DeleteMapping("/{id}")
        public Map<String, Integer> deleteUser(@PathVariable("id") Long id) {
            this.repository.deleteById(id);

            Map<String, Integer> count = new HashMap<>();
            count.put("count", this.repository.getCount());
            return (count);
        }

        //Checkpoint #4 - works
        @PatchMapping("/{id}")
        public User updateId(@PathVariable String id, @RequestBody User u, HttpServletResponse response) {
            try {
                Optional<User> rec = this.repository.findById(Long.valueOf(id));
                if (rec.isPresent()) {
                    User update = rec.get();
                    update.setEmail(u.getEmail());
                    update.setPassword(u.getPassword());
                    return this.repository.save(update);
                }
                else {
                    response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                    return null;
                }
            } catch (Exception e) {
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                return null;
            }
        }

        //Checkpoint #6 - intermediate step
        @GetMapping("/password")
        public String getPW(@RequestBody User u){
            //retrieved elements from body fine
            //issue with retrieving pw from db

             return this.repository.retrievePW(u.getEmail());

        }

        @PostMapping("/authenticate")
        public Map<String, Object> authenticate(@RequestBody User u){
            String password = u.getPassword();
            Boolean authenticate = false;

            //Get password by User
            String dbpassword = this.repository.retrievePW(u.getEmail());

            if(password.equals(dbpassword)){
                authenticate = true;
            }

            Map<String, Object> authenticated = new HashMap<>();
            if(authenticate.equals("true")) {
                authenticated.put("authenicated: ", authenticate);
                authenticated.put("user: ", u.toString());
            } else {
                authenticated.put("authenicated: ", authenticate);

            }

            return authenticated;

        }





}
