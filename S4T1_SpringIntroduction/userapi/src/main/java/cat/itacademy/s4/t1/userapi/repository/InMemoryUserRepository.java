package cat.itacademy.s4.t1.userapi.repository;

import cat.itacademy.s4.t1.userapi.model.User;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class InMemoryUserRepository implements UserRepository {

    private final Map<UUID, User> users = new HashMap<>();

    @Override
    public User save(User user) {
        if (user.getId() == null) {
            user.setId(UUID.randomUUID());
        }
        users.put(user.getId(), user);
        return user;
    }

    @Override
    public List<User> findAll() {
        return new ArrayList<>(users.values());
    }

    @Override
    public Optional<User> findById(UUID id) {
        return Optional.ofNullable(users.get(id));
    }

    @Override
    public List<User> findByName(String name) {
        if (name == null || name.isBlank()) {
            return new ArrayList<>(users.values());
        }
        return users.values().stream()
                .filter(user -> user.getName().toLowerCase()
                        .contains(name.toLowerCase())).toList();
    }

    @Override
    public boolean existsByEmail(String email) {
        return users.values().stream()
                .anyMatch(user -> user.getEmail().equalsIgnoreCase(email));
    }

    @Override
    public void clear() {
        users.clear();
    }
}
