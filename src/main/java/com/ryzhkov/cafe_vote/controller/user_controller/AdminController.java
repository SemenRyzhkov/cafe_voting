package com.ryzhkov.cafe_vote.controller.user_controller;

import com.ryzhkov.cafe_vote.dto.UserDto;
import com.ryzhkov.cafe_vote.model.User;
import com.ryzhkov.cafe_vote.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AdminController extends AbstractUserController {

    protected AdminController(UserService service) {
        super(service);
    }

    @GetMapping("/users")
    public List<User> showAllUsers() {
        return super.getAll();
    }

    @GetMapping("/users/{id}")
    public UserDto get(@PathVariable int id) {
        return super.get(id);
    }

    @GetMapping("/users/email/{email}")
    public User getByEmail(@PathVariable String email) {
        return super.getByMail(email);
    }

    @PostMapping("/users")
    public User create(@RequestBody User user) {
        super.create(user);
        return user;
    }

    @PutMapping("/users/{id}")
    public User update(@RequestBody User user, @PathVariable int id) {
        super.update(user, id);
        return user;
    }

    @DeleteMapping("/users/{id}")
    public void delete(@PathVariable int id) {
        super.delete(id);
    }
}
