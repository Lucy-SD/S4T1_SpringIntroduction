package cat.itacademy.s4.t1.userapi.service;

import cat.itacademy.s4.t1.userapi.exception.EmailAlreadyExistsException;
import cat.itacademy.s4.t1.userapi.exception.UserNotFoundException;
import cat.itacademy.s4.t1.userapi.model.User;
import cat.itacademy.s4.t1.userapi.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new EmailAlreadyExistsException();
        }
        return userRepository.save(user);
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User getUserById(UUID id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("No se encontró el usuario con ID: " + id + "."));
    }

    public List<User> getUsersByName(String name) {
        return userRepository.findByName(name);
    }

}
