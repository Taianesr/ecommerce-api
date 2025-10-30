package com.teste.ecommerce_api.demo.controller;


import com.teste.ecommerce_api.demo.controller.dto.login.LoginRequestDto;
import com.teste.ecommerce_api.demo.controller.dto.login.LoginResponseDto;
import com.teste.ecommerce_api.demo.entity.RoleEntity;
import com.teste.ecommerce_api.demo.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.stream.Collectors;

@RestController
public class TokenController {

    private final JwtEncoder jwtEncoder;

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public TokenController(JwtEncoder jwtEncoder, UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.jwtEncoder = jwtEncoder;
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto loginRequestDto) {
        var user = userRepository.findByUsername(loginRequestDto.user());

        if (user.isEmpty() || !user.get().isLoginCorrect(loginRequestDto, bCryptPasswordEncoder)){
            throw new BadCredentialsException("user or password is invalid!");
        }

        var now= Instant.now();
        var expiresIn= 3000;

        var scopes= user.get().getRoles()
                .stream()
                .map(RoleEntity::getName)
                .collect(Collectors.joining(" "));

        var claims= JwtClaimsSet.builder()
                .issuer("MyBackEnd")
                .subject(user.get().getUserId().toString())
                .expiresAt(now.plusSeconds(expiresIn))
                .claim("scope", scopes);

        var jwtValue= jwtEncoder.encode(JwtEncoderParameters.from(claims.build())).getTokenValue();

        return ResponseEntity.ok(new LoginResponseDto(jwtValue, (long) expiresIn));
    }

}
