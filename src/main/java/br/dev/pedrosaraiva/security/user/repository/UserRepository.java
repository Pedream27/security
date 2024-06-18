package br.dev.pedrosaraiva.security.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.dev.pedrosaraiva.security.user.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
