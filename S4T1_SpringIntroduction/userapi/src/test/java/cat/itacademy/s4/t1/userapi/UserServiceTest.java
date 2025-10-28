package cat.itacademy.s4.t1.userapi;

import cat.itacademy.s4.t1.userapi.exception.EmailAlreadyExistsException;
import cat.itacademy.s4.t1.userapi.exception.UserNotFoundException;
import cat.itacademy.s4.t1.userapi.model.User;
import cat.itacademy.s4.t1.userapi.repository.UserRepository;
import cat.itacademy.s4.t1.userapi.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    void createUser_shouldSaveUser_whenEmailDoesNotExist() {
        User newUser = new User(null, "Ada Lovelace", "ada@example.com");
        User savedUser = new User(UUID.randomUUID(), "Ada Lovelace", "ada@example.com");

        when(userRepository.existsByEmail("ada@example.com")).thenReturn(false);
        when(userRepository.save(any(User.class))).thenReturn(savedUser);

        User result = userService.createUser(newUser);

        assertNotNull(result);
        assertNotNull(result.getId());
        assertEquals("Ada Lovelace", result.getName());
        assertEquals("ada@example.com", result.getEmail());

        verify(userRepository).existsByEmail("ada@example.com");
        verify(userRepository).save(newUser);
    }

    @Test
    void createUser_shouldThrowExceptionWhenEmailAlreadyExists() {
        User user = new User(null, "Ada Lovelace", "ada@example.com");
        when(userRepository.existsByEmail("ada@example.com")).thenReturn(true);

        EmailAlreadyExistsException e = assertThrows(
                EmailAlreadyExistsException.class,
                () -> userService.createUser(user)
        );

        assertEquals("El correo electrónico ingresado ya existe.", e.getMessage());
        verify(userRepository).existsByEmail("ada@example.com");
        verify(userRepository, never()).save(any(User.class));

    }

    @Test
    void getUserById_shouldReturnUser_whenUserExists() {
        UUID id = UUID.randomUUID();
        User user = new User(id, "Ada Lovelace", "ada@example.com");
        when(userRepository.findById(id)).thenReturn(Optional.of(user));

        User result = userService.getUserById(id);

        assertNotNull(result);
        assertEquals(id, result.getId());
        assertEquals("Ada Lovelace", result.getName());
        assertEquals("ada@example.com", result.getEmail());
        verify(userRepository).findById(id);
    }

    @Test
    void getUserById_shouldThrowException_whenUserDoesNotExist() {
        UUID id = UUID.randomUUID();
        when(userRepository.findById(id)).thenReturn(Optional.empty());

        UserNotFoundException e = assertThrows(
                UserNotFoundException.class, () -> userService.getUserById(id)
        );

        assertTrue(e.getMessage().contains("No se encontró el usuario con ID: "));
        verify(userRepository).findById(id);
    }

    @Test
    void getUsers_shouldReturnAllUsers() {
        User user1 = new User(UUID.randomUUID(), "Ada Lovelace", "ada@example.com");
        User user2 = new User(UUID.randomUUID(), "Jose Sosa", "sosa@example.com");
        List<User> expectedUsers = List.of(user1, user2);

        when(userRepository.findAll()).thenReturn(expectedUsers);

        List<User> result = userService.getUsers();

        assertEquals(2, result.size());
        assertEquals("Ada Lovelace", result.get(0).getName());
        assertEquals("Jose Sosa", result.get(1).getName());
        verify(userRepository).findAll();
    }

    @Test
    void getUsersByName_shouldReturnFilteredUsers() {
        User user = new User(UUID.randomUUID(), "Ada Lovelace", "ada@example.com");
        List<User> expectedUsers = List.of(user);

        when(userRepository.findByName("Ada")).thenReturn(expectedUsers);

        List<User> result = userService.getUsersByName("Ada");

        assertEquals(1, result.size());
        assertEquals("Ada Lovelace", result.get(0).getName());
        assertEquals("ada@example.com", result.get(0).getEmail());
        verify(userRepository).findByName("Ada");
    }
}
