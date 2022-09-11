package com.epam.selectioncommittee.spring.model.service;

import com.epam.selectioncommittee.spring.model.repository.RoleRepository;
import com.epam.selectioncommittee.spring.model.repository.UserRepository;
import com.epam.selectioncommittee.spring.model.builders.UserBuilder;
import com.epam.selectioncommittee.spring.model.dto.UserForm;
import com.epam.selectioncommittee.spring.model.entity.Role;
import com.epam.selectioncommittee.spring.model.entity.User;
import com.epam.selectioncommittee.spring.model.exception.EmailIsReservedException;
import com.epam.selectioncommittee.spring.model.exception.UsernameIsReservedException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.List;


@Service
public class UserService implements UserDetailsService {

    private static final Logger log = LogManager.getLogger(UserService.class);
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;


    @Autowired
    public UserService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder,
                       RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User createUser(UserForm userForm)
            throws UsernameIsReservedException, EmailIsReservedException {

        checkUsername(userForm.getUsername());
        checkEmail(userForm.getEmail());


        log.info("User '{}' was created", userForm.getUsername());

        return userRepository.save(UserBuilder.builder()
                .username(userForm.getUsername())
                .email(userForm.getEmail())
                .password(passwordEncoder.encode(userForm.getPassword()))
                .role(roleRepository.findByRoleName(Role.RoleName.USER))
                .firstname(userForm.getFirstname())
                .surname(userForm.getSurname())
                .city(userForm.getCity())
                .region(userForm.getRegion())
                .institution(userForm.getInstitution())
                .blocked(false)
                .build());
    }

    private void checkUsername(String login) throws UsernameIsReservedException {

        if (userRepository.existsByUsername(login)) {

            log.warn(" '{}' is reserved", login);
            throw new UsernameIsReservedException();

        }
        System.out.println("false");
    }

    private void checkEmail(String email)
            throws EmailIsReservedException {

        if (userRepository.existsByEmail(email)) {

            log.warn(" '{}' is reserved", email);

            throw new EmailIsReservedException();
        }

    }

    private User findByUsername(String username) {

        return userRepository.findByUsername(username);

    }

    public User findUserById(Long id) {

        return userRepository.findById(id).get();
    }

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        User user = findByUsername(username);
        if (user == null) {
            log.warn("User '{}' not found", username);
            throw new UsernameNotFoundException("User not found");
        }
        return user;
    }


    public List<User> getAllUsers() {

        return userRepository.findAllByRole();
    }

    public User findByName(String name) {

        return userRepository.findByUsername(name);
    }

    public User blockUserById(Long id) {

        User user = userRepository.findById(id).get();
        user.setBlocked(true);
        log.info(" '{}' is blocked", user.getUsername());
        return userRepository.save(user);
    }

    public User unblockUserById(Long id) {

        User user = userRepository.findById(id).get();
        user.setBlocked(false);
        log.info(" '{}' is unblocked", user.getUsername());

        return userRepository.save(user);

    }
}
