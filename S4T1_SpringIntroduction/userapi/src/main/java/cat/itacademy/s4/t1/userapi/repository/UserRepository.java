package cat.itacademy.s4.t1.userapi.repository;

import cat.itacademy.s4.t1.userapi.model.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository {

    User save(User user);
    List<User> findAll();
    Optional<User> findById(UUID id);
    List<User> findByName(String name);
    boolean existsByEmail(String email);
    void clear();
}
