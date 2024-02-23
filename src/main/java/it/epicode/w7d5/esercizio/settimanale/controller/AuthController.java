package it.epicode.w7d5.esercizio.settimanale.controller;

import it.epicode.w7d5.esercizio.settimanale.exception.BadRequestException;
import it.epicode.w7d5.esercizio.settimanale.exception.LoginFaultException;
import it.epicode.w7d5.esercizio.settimanale.model.User;
import it.epicode.w7d5.esercizio.settimanale.request.LoginRequest;
import it.epicode.w7d5.esercizio.settimanale.request.UserRequest;
import it.epicode.w7d5.esercizio.settimanale.security.JwtTools;
import it.epicode.w7d5.esercizio.settimanale.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")

public class AuthController {
    @Autowired
    private UserService userService;
    @Autowired
    private JwtTools jwtTools;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @PostMapping("/register")
    public User register(@RequestBody @Validated UserRequest userRequest, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new BadRequestException(bindingResult.getAllErrors().toString());
        }

        return userService.saveUser(userRequest);
    }

    @PostMapping("/login")
    public String login(@RequestBody @Validated LoginRequest loginRequest, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new BadRequestException(bindingResult.getAllErrors().toString());
        }

        User user = userService.getUserByEmail(loginRequest.getEmail());

        if(passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())){
            return jwtTools.createToken(user);
        }
        else{
            throw new LoginFaultException("email/password not valid");
        }

    }
}
