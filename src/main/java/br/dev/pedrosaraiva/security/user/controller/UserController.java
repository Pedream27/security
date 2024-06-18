package br.dev.pedrosaraiva.security.user.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.dev.pedrosaraiva.security.user.model.User;
import br.dev.pedrosaraiva.security.user.service.UserService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService _Service;

    @PostMapping
    public User create(@RequestBody User user) throws Exception{
        return _Service.create(user);
    }

}
