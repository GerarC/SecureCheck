package co.edu.udea.securecheck.adapter.driving.rest.v1.controller;

import co.edu.udea.securecheck.adapter.driving.rest.v1.dto.request.UserRequest;
import co.edu.udea.securecheck.adapter.driving.rest.v1.dto.response.UserResponse;
import co.edu.udea.securecheck.adapter.driving.rest.v1.service.UserService;
import co.edu.udea.securecheck.configuration.utils.JsonParser;
import co.edu.udea.securecheck.domain.exceptions.EmailAlreadyExistsException;
import co.edu.udea.securecheck.domain.exceptions.IdentityDocumentAlreadyExistsException;
import co.edu.udea.securecheck.domain.exceptions.UnderageUserException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = AuthenticationController.class)
@AutoConfigureMockMvc
class AuthenticationControllerTest {

    private MockMvc mockMvc;

    private final WebApplicationContext webApplicationContext;

    @MockBean
    private UserService userService;

    @Autowired
    public AuthenticationControllerTest(WebApplicationContext webApplicationContext) {
        this.webApplicationContext = webApplicationContext;
    }

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void registerAuditor() throws Exception {
        // Mock data
        UserResponse userResponse = UserResponse.builder()
                .name("admin")
                .lastname("admin")
                .build();
        UserRequest userRequest = UserRequest.builder()
                .name("admin")
                .lastname("admin")
                .email("admin@admin.com")
                .birthdate(LocalDateTime.MIN)
                .identityDocument("0000000001")
                .phone("+573332223232")
                .password("password")
                .build();
        // Define what should happen
        when(userService.registerAuditor(userRequest)).thenReturn(userResponse);

        // Perform Test
        this.mockMvc.perform(post("/v1/auth/register/auditor")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonParser.toJson(userRequest)))
        // Verify
                .andExpect(status().isCreated())
                .andExpect(content().json(JsonParser.toJson(userResponse)));

        verify(userService).registerAuditor(any());
    }

    @Test
    void registerAuditorEmailAlreadyExists() throws Exception {
        UserRequest userRequest = UserRequest.builder()
                .name("admin")
                .lastname("admin")
                .email("admin@admin.com")
                .birthdate(LocalDateTime.MIN)
                .identityDocument("0000000001")
                .phone("+573332223232")
                .password("password")
                .build();
        doThrow(new EmailAlreadyExistsException("admin@admin.com"))
                .when(userService).registerAuditor(userRequest);
        this.mockMvc.perform(post("/v1/auth/register/auditor")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonParser.toJson(userRequest)))
                .andExpect(status().isConflict());
        verify(userService).registerAuditor(any());
    }

    @Test
    void registerAuditorDocumentAlreadyExists() throws Exception {
        UserRequest userRequest = UserRequest.builder()
                .name("admin")
                .lastname("admin")
                .email("admin@admin.com")
                .birthdate(LocalDateTime.MIN)
                .identityDocument("0000000001")
                .phone("+573332223232")
                .password("password")
                .build();
        doThrow(new IdentityDocumentAlreadyExistsException("0000000001"))
                .when(userService).registerAuditor(userRequest);
        this.mockMvc.perform(post("/v1/auth/register/auditor")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonParser.toJson(userRequest)))
                .andExpect(status().isConflict());
        verify(userService).registerAuditor(any());
    }

    @Test
    void registerAuditorUnderAgedUser() throws Exception {
        UserRequest userRequest = UserRequest.builder()
                .name("admin")
                .lastname("admin")
                .email("admin@admin.com")
                .birthdate(LocalDateTime.now())
                .identityDocument("0000000001")
                .phone("+573332223232")
                .password("password")
                .build();
        doThrow(new UnderageUserException(LocalDateTime.now()))
                .when(userService).registerAuditor(userRequest);
        this.mockMvc.perform(post("/v1/auth/register/auditor")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonParser.toJson(userRequest)))
                .andExpect(status().isConflict());
        verify(userService).registerAuditor(any());
    }
}