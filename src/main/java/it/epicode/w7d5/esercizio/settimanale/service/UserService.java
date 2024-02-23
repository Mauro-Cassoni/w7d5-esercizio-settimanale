package it.epicode.w7d5.esercizio.settimanale.service;

import it.epicode.w7d5.esercizio.settimanale.enums.Role;
import it.epicode.w7d5.esercizio.settimanale.exception.NotFoundException;
import it.epicode.w7d5.esercizio.settimanale.model.User;
import it.epicode.w7d5.esercizio.settimanale.repository.UserRepository;
import it.epicode.w7d5.esercizio.settimanale.request.UserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Page<User> findAllUsers(Pageable pageable){

        return userRepository.findAll(pageable);
    }

    public User getUserById(int id) throws NotFoundException {
        return userRepository.findById(id).orElseThrow(()->new NotFoundException("User by id= " + id + " not found"));
    }

    public User getUserByEmail(String email) throws NotFoundException {
        return userRepository.findByEmail(email).orElseThrow(()->new NotFoundException("User by email= " + email + " not found"));
    }

    public User saveUser(UserRequest userRequest){
        User x = new User();
        x.setName(userRequest.getName());
        x.setSurname(userRequest.getSurname());
        x.setEmail(userRequest.getMail());
        x.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        x.setRole(Role.NORMAL_USER);


        return userRepository.save(x);
    }

    public User updateUser(int id, UserRequest userRequest) throws NotFoundException{
        User x = getUserById(id);
        x.setName(userRequest.getName());
        x.setSurname(userRequest.getSurname());
        x.setEmail(userRequest.getMail());
        x.setPassword(passwordEncoder.encode(userRequest.getPassword()));

        return userRepository.save(x);
    }

    public void deleteUserById(int id) throws NotFoundException{
        User a = getUserById(id);
        userRepository.delete(a);
    }

    public User updateRoleUser(String email,String role){
        User user = getUserByEmail(email);
        user.setRole(Role.valueOf(role));
        return userRepository.save(user);
    }

    public void deleteUserByEmail(String email){
        userRepository.deleteByEmail(email).orElseThrow(()->new NotFoundException("Email not found"));
    }
}
