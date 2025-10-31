package com.teste.ecommerce_api.demo.config;

import com.teste.ecommerce_api.demo.entity.RoleEntity;
import com.teste.ecommerce_api.demo.entity.User;
import com.teste.ecommerce_api.demo.repository.RoleRepository;
import com.teste.ecommerce_api.demo.repository.UserRepository;
import com.teste.ecommerce_api.demo.service.AwsParameterCache;
import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Set;

@Configuration
public class AdminUserConfig implements CommandLineRunner {

    private RoleRepository roleRepository;

    private UserRepository userRepository;

    private BCryptPasswordEncoder passwordEncoder;

    private final AwsParameterCache awsParameterCache;

    public AdminUserConfig(RoleRepository roleRepository, UserRepository userRepository, BCryptPasswordEncoder passwordEncoder, AwsParameterCache awsParameterCache) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.awsParameterCache = awsParameterCache;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {

        var roleAdmin = roleRepository.findByName(RoleEntity.Value.ADMIN.name());

        var userAdmin = userRepository.findByUsername("admin");

        userAdmin.ifPresentOrElse(user -> {
                    System.out.print("user admin already exists");
                },
                () -> {
                    var user = new User();
                    user.setUsername(awsParameterCache.get("/ecommerce-api/db-admin"));
                    user.setPassword(awsParameterCache.get("/ecommerce-api/db-admin/password"));
                    user.setRoles(Set.of(roleAdmin));
                    userRepository.save(user);
                }
        );

    }
}
