package com.epam.selectioncommittee.spring.model.service;

import com.epam.selectioncommittee.spring.model.builders.UserBuilder;
import com.epam.selectioncommittee.spring.model.builders.UserFormBuilder;
import com.epam.selectioncommittee.spring.model.dto.UserForm;
import com.epam.selectioncommittee.spring.model.entity.Role;
import com.epam.selectioncommittee.spring.model.entity.User;
import com.epam.selectioncommittee.spring.model.exception.EmailIsReservedException;
import com.epam.selectioncommittee.spring.model.exception.UsernameIsReservedException;
import com.epam.selectioncommittee.spring.model.repository.RoleRepository;
import com.epam.selectioncommittee.spring.model.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;


@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class UserServiceTest {

    @Autowired
    UserService userService;
    @MockBean
    private UserRepository userRepository;
    @MockBean
    private RoleRepository roleRepository;
    @MockBean
    private PasswordEncoder passwordEncoder;

    private  User USER;

    private UserForm USER_FORM;

    private final static Long ID = 1L;

    private final static String USERNAME = "TestUser";

    private final static String PASSWORD = "qwerty12";

    private final static String PASSWORD_ENCODED = "$2a$10$N6TSClHcE.f/AvwwJNruT.wrZVNIW6cqNIN9h97BnyTwp4TNnf53K";

    private final static String EMAIL = "HarryPotter@gmail.com";

    private final static String FIRSTNAME = "Harry";

    private final static String SURNAME = "Potter";

    private final static String CITY = "London";

    private final static String REGION = "England";

    private final static String INSTITUTION = "Hogwarts";

    private final static Role ROLE = new Role(Role.RoleName.USER);

    @BeforeEach
    void setUp() {

        USER = UserBuilder.builder()
                .username(USERNAME)
                .password(PASSWORD_ENCODED)
                .email(EMAIL)
                .firstname(FIRSTNAME)
                .surname(SURNAME)
                .city(CITY)
                .region(REGION)
                .institution(INSTITUTION)
                .role(ROLE)
                .blocked(false)
                .build();

        USER_FORM = UserFormBuilder.builder()
                .username(USERNAME)
                .password(PASSWORD)
                .email(EMAIL)
                .firstname(FIRSTNAME)
                .surname(SURNAME)
                .city(CITY)
                .region(REGION)
                .institution(INSTITUTION)
                .build();
    }


    @Test
    void createUser() throws EmailIsReservedException, UsernameIsReservedException {
        when(userRepository.existsUserByUsername(USERNAME)).thenReturn(false);
        when(userRepository.existsByEmail(EMAIL)).thenReturn(false);
        when(passwordEncoder.encode(PASSWORD)).thenReturn(PASSWORD_ENCODED);
        when(roleRepository.findByRoleName(Role.RoleName.USER)).thenReturn(ROLE);
        when(userRepository.save(USER)).thenReturn(USER);

        assertEquals(userService.createUser(USER_FORM),USER);
        verify(userRepository,times(1)).save(USER);

    }

    @Test
    void createUserThrowsUsernameIsReservedException() {
        when(userRepository.existsUserByUsername(USERNAME)).thenReturn(true);
        assertThrows(UsernameIsReservedException.class ,() -> userService.createUser(USER_FORM));
    }

    @Test
    void CreateUserThrowsEmailIsReservedException() {
        when(userRepository.existsByEmail(EMAIL)).thenReturn(true);
        assertThrows(EmailIsReservedException.class ,() -> userService.createUser(USER_FORM));
    }

    @Test
    void findUserById() {
        when(userRepository.findById(ID)).thenReturn(Optional.of(USER));
        assertEquals(userService.findUserById(ID),USER);
        verify(userRepository,times(1)).findById(ID);
    }

    @Test
    void loadUserByUsername() {
        when(userRepository.findByUsername(USERNAME)).thenReturn(USER);
        assertEquals(userService.loadUserByUsername(USERNAME),USER);

    }

    @Test
    void loadUserByUsernameThrowsUsernameNotFoundException() {
        when(userRepository.findByUsername(USERNAME)).thenReturn(null);
        assertThrows(UsernameNotFoundException.class,()->userService.loadUserByUsername(USERNAME));
    }

    @Test
    void getAllUsers() {
        when(userRepository.findAllByRole()).thenReturn(List.of(USER));
        assertEquals(userService.getAllUsers(),List.of(USER));

    }

    @Test
    void findByName() {
        when(userRepository.findByUsername(USERNAME)).thenReturn(USER);
        assertEquals(userService.findByName(USERNAME),USER);

    }
}