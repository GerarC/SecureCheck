package co.edu.udea.securecheck.adapter.driven.jpa.adapters;

import co.edu.udea.securecheck.adapter.driven.jpa.entity.RoleEntity;
import co.edu.udea.securecheck.adapter.driven.jpa.entity.UserEntity;
import co.edu.udea.securecheck.adapter.driven.jpa.mapper.UserEntityMapper;
import co.edu.udea.securecheck.adapter.driven.jpa.repository.UserRepository;
import co.edu.udea.securecheck.domain.model.Role;
import co.edu.udea.securecheck.domain.model.User;
import co.edu.udea.securecheck.domain.utils.enums.RoleName;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class UserJpaAdapterTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserEntityMapper userEntityMapper;

    @InjectMocks
    private UserJpaAdapter userJpaAdapter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void save() {
        // Declare mock data
        UserEntity userEntity = UserEntity.builder()
                .name("admin")
                .lastname("admin")
                .email("admin@admin.com")
                .birthdate(LocalDateTime.MIN)
                .identityDocument("0000000001")
                .phone("+573332223232")
                .password("password")
                .role(RoleEntity.builder().name(RoleName.ADMIN).build())
                .build();
        User user = new User(
                null,
                "admin",
                "admin",
                "0000000001",
                LocalDateTime.MIN,
                "+573332223232",
                "admin@admin.com",
                "password",
                new Role(null, RoleName.ADMIN),
                null
        );

        // Define what should happen
        when(userEntityMapper.toEntity(user)).thenReturn(userEntity);
        when(userEntityMapper.toDomain(any())).thenReturn(user);
        when(userRepository.save(any())).thenReturn(userEntity);

        // Test
        User returnedUser = userJpaAdapter.save(user);

        //Verify and assert
        verify(userRepository).save(any());
        assertEquals(user.getEmail(), returnedUser.getEmail());
    }

    @Test
    void existsByEmail() {
        String email = "admin@admin.com";

        when(userRepository.existsByEmail(email)).thenReturn(true);

        assertTrue(userJpaAdapter.existsByEmail(email));
        verify(userRepository).existsByEmail(email);
    }

    @Test
    void existsByIdentityDocument() {
        String document = "0000000001";

        when(userRepository.existsByIdentityDocument(document)).thenReturn(true);

        assertTrue(userJpaAdapter.existsByIdentityDocument(document));
        verify(userRepository).existsByIdentityDocument(document);
    }
}