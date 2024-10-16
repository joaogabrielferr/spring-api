package com.joaogabrielferr.spring_api.controllers;

import com.joaogabrielferr.spring_api.data.VO.v1.security.LoginVO;
import com.joaogabrielferr.spring_api.data.VO.v1.security.SignupVO;
import com.joaogabrielferr.spring_api.data.VO.v1.security.TokenVO;
import com.joaogabrielferr.spring_api.exceptions.InvalidJWTAuthenticationException;
import com.joaogabrielferr.spring_api.exceptions.UsernameAlreadyInUseException;
import com.joaogabrielferr.spring_api.model.User;
import com.joaogabrielferr.spring_api.repositories.UserRepository;
import com.joaogabrielferr.spring_api.security.jwt.JwtTokenProvider;
import com.joaogabrielferr.spring_api.services.AuthenticationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.antlr.v4.runtime.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@Tag(name = "Authentication controller")
public class AuthenticationController {


    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    UserRepository userRepository;
    @Autowired
    JwtTokenProvider jwtTokenProvider;




    @PostMapping("/login")
    public ResponseEntity<TokenVO> login(@RequestBody @Valid LoginVO data){

        var usernamePassword = new UsernamePasswordAuthenticationToken(data.getUserName(),data.getPassword());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        String token = jwtTokenProvider.generateToken((User)auth.getPrincipal());

        TokenVO tokenVO = new TokenVO();
        tokenVO.setAccessToken(token);

        return ResponseEntity.ok(tokenVO);

    }

    @PostMapping("/signup")
    public ResponseEntity signup(@RequestBody @Valid SignupVO data){

        if(data.getUserName() == null || data.getPassword() == null || data.getRole() == null){
            throw new BadCredentialsException("Invalid credentials");
        }

        if(this.userRepository.findByUsername(data.getUserName()) != null){
            throw new UsernameAlreadyInUseException("The username provided is already in use");
        }

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.getPassword());
        User user = new User(data.getUserName(),encryptedPassword,data.getRole());

        this.userRepository.save(user);

        return ResponseEntity.ok().build();

    }


}
