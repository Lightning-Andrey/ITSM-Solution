package by.itsm;

import by.itsm.authentication.dto.JwtAuthenticationResponse;
import by.itsm.authentication.dto.SignInRequest;
import by.itsm.authentication.dto.SignUpRequest;
import by.itsm.authentication.service.AuthenticationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class ItsmApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private AuthenticationService authenticationService;

    @Test
    void testSignUpSuccess() throws Exception {
        SignUpRequest signUpRequest = new SignUpRequest();
        signUpRequest.setUsername("testuser");
        signUpRequest.setEmail("test@example.com");
        signUpRequest.setPassword("password123");
        signUpRequest.setFirstName("Test");
        signUpRequest.setLastName("User");

        mockMvc.perform(MockMvcRequestBuilders.post("/api/auth/sign-up")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(signUpRequest)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.token").exists());
    }

    @Test
    void testSignUpUserAlreadyExists() throws Exception {
        SignUpRequest signUpRequest = new SignUpRequest();
        signUpRequest.setUsername("existinguser");
        signUpRequest.setEmail("existing@example.com");
        signUpRequest.setPassword("password123");
        signUpRequest.setFirstName("Existing");
        signUpRequest.setLastName("User");

        authenticationService.signUp(signUpRequest); // Создаём пользователя

        mockMvc.perform(MockMvcRequestBuilders.post("/api/auth/sign-up")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(signUpRequest)))
                .andExpect(status().isConflict());
    }

    @Test
    void testSignInSuccess() throws Exception {
        SignInRequest signInRequest = new SignInRequest();
        signInRequest.setUsername("testuser");
        signInRequest.setPassword("password123");

        mockMvc.perform(MockMvcRequestBuilders.post("/api/auth/sign-in")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(signInRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").exists());
    }

    @Test
    void testSignInUserNotFound() throws Exception {
        SignInRequest signInRequest = new SignInRequest();
        signInRequest.setUsername("nonexistent");
        signInRequest.setPassword("wrongpassword");

        mockMvc.perform(MockMvcRequestBuilders.post("/api/auth/sign-in")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(signInRequest)))
                .andExpect(status().isNotFound());
    }
}
