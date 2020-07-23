package com.example.CRUDCheckpoint;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;

public interface UserRepository extends CrudRepository<User, Long> {
    @Query(value = "SELECT * FROM users WHERE id = :id", nativeQuery = true)
    public User byId(Long id);

//    @Query(value = "SELECT * FROM lessons WHERE title = :title", nativeQuery = true)
//    public Lesson byTitle(String title);

    @Query(value = "DELETE * FROM lessons WHERE id = :id", nativeQuery = true)
    public User deletebyId(Long id);

    @Query(value = "SELECT COUNT(*) FROM users;", nativeQuery = true)
    public int getCount();

    @Query(value = "SELECT password FROM users WHERE email = :email;", nativeQuery = true)
    public String retrievePW(String email);
    ;

}


