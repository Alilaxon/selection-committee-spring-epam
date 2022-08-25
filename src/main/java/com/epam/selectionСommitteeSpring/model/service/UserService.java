package com.epam.selectionСommitteeSpring.model.service;

import com.epam.selectionСommitteeSpring.model.builders.UserBuilder;
import com.epam.selectionСommitteeSpring.model.dto.UserForm;
import com.epam.selectionСommitteeSpring.model.entity.Role;
import com.epam.selectionСommitteeSpring.model.entity.User;
import com.epam.selectionСommitteeSpring.model.exception.EmailIsReservedException;
import com.epam.selectionСommitteeSpring.model.exception.UsernameIsReservedException;
import com.epam.selectionСommitteeSpring.model.repository.RoleRepository;
import com.epam.selectionСommitteeSpring.model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;


@Service
public class UserService implements UserDetailsService {
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

    private void checkUsername(String login)
            throws UsernameIsReservedException {

        if (userRepository.existsUserByUsername(login)) {
            throw new UsernameIsReservedException();
        }
    }

    private void checkEmail(String email)
            throws EmailIsReservedException {

        if (userRepository.existsByEmail(email)) {
            throw new EmailIsReservedException();
        }
    }

    private User findByUsername(String username) {

        return userRepository.findByUsername(username);

    }

    public User findUserById(Long userId) {
        return userRepository.findById(userId).get();
    }

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        User user = findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return user;
//                org.springframework.security.core.userdetails.User(
//                user.getUsername(),
//                user.getPassword(),
//                getAuthorities(user.getRole()));
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
        return userRepository.save(user);
    }

    public User unblockUserById(Long id) {
        User user = userRepository.findById(id).get();
        user.setBlocked(false);
        return userRepository.save(user);
    }
//    public Collection<? extends GrantedAuthority> getAuthorities(Role role) {
//        return Collections.singleton(new SimpleGrantedAuthority(role.getRoleName().name()));
//    }
}
