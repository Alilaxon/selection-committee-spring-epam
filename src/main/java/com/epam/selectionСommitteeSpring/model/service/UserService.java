package com.epam.selectionСommitteeSpring.model.service;

import com.epam.selectionСommitteeSpring.model.DTO.UserForm;
import com.epam.selectionСommitteeSpring.model.Entity.Role;
import com.epam.selectionСommitteeSpring.model.Entity.User;
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
        user.setBlocked(false);

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
        return user;
//                org.springframework.security.core.userdetails.User(
//                user.getUsername(),
//                user.getPassword(),
//                getAuthorities(user.getRole()));
    }


    public List<User> getAllUsers(){

        return userRepository.findAllByRole();
    }

    public User findByName(String name){
        return userRepository.findByUsername(name);
    }

    public User blockUserById(Long id){
        User user = userRepository.findById(id).get();
        user.setBlocked(true);
        return userRepository.save(user);
    }
    public User unblockUserById(Long id){
        User user = userRepository.findById(id).get();
        user.setBlocked(false);
        return userRepository.save(user);
    }
//    public Collection<? extends GrantedAuthority> getAuthorities(Role role) {
//        return Collections.singleton(new SimpleGrantedAuthority(role.getRoleName().name()));
//    }
}
