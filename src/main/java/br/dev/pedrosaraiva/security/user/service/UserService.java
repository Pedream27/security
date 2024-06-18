package br.dev.pedrosaraiva.security.user.service;

import br.dev.pedrosaraiva.security.user.model.User;

public interface UserService {

    User create(User user) throws Exception;
}
