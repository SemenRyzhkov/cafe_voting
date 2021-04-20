package com.ryzhkov.cafe_vote.controller.user_controller;

import com.ryzhkov.cafe_vote.dto.UserDto;
import com.ryzhkov.cafe_vote.model.User;
import com.ryzhkov.cafe_vote.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = AdminController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class AdminController extends AbstractUserController {

    static final String REST_URL = "/api/users";


    protected AdminController(UserService service) {
        super(service);
    }

    @GetMapping
    public List<User> showAllUsers() {
        return super.getAll();
    }

    @GetMapping("{id}")
    public User get(@PathVariable int id) {
        return super.get(id);
    }

    @GetMapping("/email/{email}")
    public User getByEmail(@PathVariable String email) {
        return super.getByMail(email);
    }

    @PostMapping

    public User create(@RequestBody User user) {
        super.create(user);
        return user;
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody User user, @PathVariable int id) {
        super.update(user, id);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        super.delete(id);
    }
}
