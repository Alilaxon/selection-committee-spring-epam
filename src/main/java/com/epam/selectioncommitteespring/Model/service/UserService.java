package com.epam.selectioncommitteespring.Model.service;

import com.epam.selectioncommitteespring.Model.DTO.UserForm;
import com.epam.selectioncommitteespring.Model.Entity.Role;
import com.epam.selectioncommitteespring.Model.Entity.User;
import com.epam.selectioncommitteespring.Model.exception.EmailIsReservedException;
import com.epam.selectioncommitteespring.Model.exception.UsernameIsReservedException;
import com.epam.selectioncommitteespring.Model.repository.RoleRepository;
import com.epam.selectioncommitteespring.Model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


import java.util.Collection;
import java.util.Collections;
import java.util.List;



@Component
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
        this.passwordEncoder =passwordEncoder;
    }

    public User addNewUser(UserForm userForm)
            throws UsernameIsReservedException, EmailIsReservedException {

         checkUsername(userForm.getUsername());
         checkEmail(userForm.getEmail());

        User user = new User();
        user.setUsername(userForm.getUsername());
        user.setEmail(userForm.getEmail());
        user.setPassword(passwordEncoder.encode(userForm.getPassword()));
        user.setRole(roleRepository.findByRoleName(Role.RoleName.USER));

//    user.setFirstname(userForm.getFirstname());
//    user.setSurname(userForm.getSurname());
//    user.setCity(userForm.getCity());
//    user.setRegion(userForm.getCity());
//    user.setInstitution(userForm.getInstitution());
        return userRepository.save(user);
    }
    public void checkUsername(String login)
            throws UsernameIsReservedException {

        if(userRepository.existsUserByUsername(login)){
           throw new UsernameIsReservedException();
        }
    }
    public void checkEmail (String email)
            throws EmailIsReservedException {

       if(userRepository.existsByEmail(email)) {
           throw new EmailIsReservedException();
       }
    }

    public User findByUsername(String username){

        return userRepository.findByUsername(username);

    }

    public User findUserById(Long userId){
       return userRepository.findById(userId).get();
    }

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        User user = findByUsername(username);
        if(user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                getAuthorities(user.getRole()));
    }


    public List<User> getAllUsers(){
        return userRepository.findAllByRole();
    }
    public Collection<? extends GrantedAuthority> getAuthorities(Role role) {
        return Collections.singleton(new SimpleGrantedAuthority(role.getRoleName().name()));
    }
}
