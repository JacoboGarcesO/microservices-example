package org.example.authservice.service;

import org.example.authservice.dto.RegisterRequest;
import org.example.authservice.model.User;
import org.example.authservice.repository.IUserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class UserService {
  private final IUserRepository repository;
  private final PasswordEncoder encoder;

  public UserService(IUserRepository repository, PasswordEncoder encoder) {
    this.repository = repository;
    this.encoder = encoder;
  }

  public Mono<User> register(RegisterRequest request) {
    return repository.findByUsername(request.username())
      .switchIfEmpty(repository.save(new User(null, request.username(), encoder.encode(request.password()), request.role())))
      .flatMap(existing -> Mono.error(new RuntimeException("User already exists")));
  }
}
