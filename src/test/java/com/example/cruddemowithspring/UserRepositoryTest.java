package com.example.cruddemowithspring;

import com.example.cruddemowithspring.user.User;
import com.example.cruddemowithspring.user.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class UserRepositoryTest {
        @Autowired

    private UserRepository _repo;
    @Test
        public void testAddNewIndex(){
            User user = new User();
            user.setEmail("ngophuoc@gmail.com");
            user.setFirstName("Ngo Phuoc");
            user.setLastName("Lộc");
            user.setPassword("123456");
            User userUpdated = _repo.save(user);
            // Kiểm tra retrievedUser có khác null không
            User retrievedUser = _repo.findById(userUpdated.getId()).orElse(null);
            Assertions.assertNotNull(retrievedUser);
            Assertions.assertEquals("ngophuoc@gmail.com", retrievedUser.getEmail());
            Assertions.assertEquals("Ngo Phuoc", retrievedUser.getFirstName());
            Assertions.assertEquals("Lộc", retrievedUser.getLastName());
            Assertions.assertEquals("123456", retrievedUser.getPassword());
        }
    @Test
        public  void testCallUser(){
        Optional<User> optionalUser = _repo.findById(1);
        Assertions.assertTrue(optionalUser.isPresent());

        // Cập nhật thông tin người dùng
        User userToUpdate = optionalUser.get();
        userToUpdate.setFirstName("New First Name");
        userToUpdate.setLastName("New Last Name");
        _repo.save(userToUpdate);

        // Kiểm tra xem thông tin người dùng đã được cập nhật chưa
        Optional<User> updatedUserOptional = _repo.findById(1);
        Assertions.assertTrue(updatedUserOptional.isPresent());

        User updatedUser = updatedUserOptional.get();
        Assertions.assertEquals("New First Name", updatedUser.getFirstName());
        Assertions.assertEquals("New Last Name", updatedUser.getLastName());
        }
}
