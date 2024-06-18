package br.dev.pedrosaraiva.security.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.dev.pedrosaraiva.security.user.model.User;
import br.dev.pedrosaraiva.security.user.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository _Repository;

    @Override
    public User create(User user) throws Exception {

        User existUser = (User) _Repository.findByUsername(user.getUsername());

        if (existUser != null) {
            throw new Exception("Usuario ja existe");
        }

        user.setPassword(passwordEnconder().encode(user.getPassword()));
        User createUser = _Repository.save(user);

        return createUser;
    }

    private PasswordEncoder passwordEnconder() {
        return new BCryptPasswordEncoder();
    }

}
