package com.teste.ecommerce_api.demo.controller;

import com.teste.ecommerce_api.demo.controller.dto.login.CreateUserDto;
import com.teste.ecommerce_api.demo.entity.RoleEntity;
import com.teste.ecommerce_api.demo.entity.User;
import com.teste.ecommerce_api.demo.repository.RoleRepository;
import com.teste.ecommerce_api.demo.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Set;

//@RestController
//public class UserController {
//
//    private final UserRepository userRepository;
//    private final RoleRepository roleRepository;
//    private final BCryptPasswordEncoder bCryptPasswordEncoder;
//
//    public UserController(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
//        this.userRepository = userRepository;
//        this.roleRepository = roleRepository;
//        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
//    }
//
//    @PostMapping("/users")
//    public ResponseEntity<Void> newUser(@RequestBody CreateUserDto createUserDto) {
//
//        var basicRole = roleRepository.findByName(RoleEntity.Value.BASIC.name());
//
//        var userFromDb = userRepository.findByUsername(createUserDto.username());
//
//        if (userFromDb.isPresent()){
//            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY);
//        }
//
//        var newUser= new User();
//        newUser.setUsername(createUserDto.username());
//        newUser.setPassword(bCryptPasswordEncoder.encode(createUserDto.password()));
//        newUser.setRoles(Set.of(basicRole));
//
//        userRepository.save(newUser);
//
//        return ResponseEntity.ok().build();
//    }
//
//    @GetMapping("/users")
//    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
//    public ResponseEntity<List<User>> listUsers(){
//        var users= userRepository.findAll();
//        return ResponseEntity.ok(users);
//    }
//
//
//}
