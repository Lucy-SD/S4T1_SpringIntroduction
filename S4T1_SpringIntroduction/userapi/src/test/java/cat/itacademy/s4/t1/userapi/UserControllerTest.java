package cat.itacademy.s4.t1.userapi;

import cat.itacademy.s4.t1.userapi.model.User;
import cat.itacademy.s4.t1.userapi.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        userRepository.clear();
    }

    @Test
    void getUsers_returnsEmptyListInitially() throws Exception {
        mockMvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", empty()))
                .andExpect(jsonPath("$.length()", is(0)));
    }

    @Test
    void createUser_returnsUserWithId() throws Exception {
        String jsonUser = "{\"name\":\"Ada Lovelace\",\"email\":\"ada@example.com\"}";

        mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonUser))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", notNullValue()))
                .andExpect(jsonPath("$.name", is("Ada Lovelace")))
                .andExpect(jsonPath("$.email", is("ada@example.com")));
    }

    @Test
    void getUserById_returnsCorrectUser() throws Exception {
        String jsonUser = "{\"name\":\"Ada Lovelace\",\"email\":\"ada@example.com\"}";

        String returns = mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonUser))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        User newUser = objectMapper.readValue(returns, User.class);
        String userId = newUser.getId().toString();

        mockMvc.perform(get("/users/{id}", userId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(userId)))
                .andExpect(jsonPath("$.name", is("Ada Lovelace")))
                .andExpect(jsonPath("$.email", is("ada@example.com")));
    }

    @Test
    void getUserById_returnsNotFoundIfMissing() throws Exception {
        String nonExistingId = UUID.randomUUID().toString();

        mockMvc.perform(get("/users/{id}", nonExistingId))
                .andExpect(status().isNotFound());
    }

    @Test
    void getUsers_withNameParam_returnsFilteredUsers() throws Exception {
        mockMvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"Ada Lovelace\",\"email\":\"ada@example.com\"}"));

        mockMvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"Jose Sosa\",\"email\":\"sosa@example.com\"}"));

        mockMvc.perform(get("/users").param("name","jo"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()", is(1)))
                .andExpect(jsonPath("$[0].name", is("Jose Sosa")))
                .andExpect(jsonPath("$[0].email", is("sosa@example.com")));

    }
}
