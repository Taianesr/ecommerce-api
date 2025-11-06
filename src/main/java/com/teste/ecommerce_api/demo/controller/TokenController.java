package com.teste.ecommerce_api.demo.controller;


//
//@RestController
//public class TokenController {
//
//    private final JwtEncoder jwtEncoder;
//
//    private final UserRepository userRepository;
//
//    private final BCryptPasswordEncoder bCryptPasswordEncoder;
//
//    public TokenController(JwtEncoder jwtEncoder, UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
//        this.jwtEncoder = jwtEncoder;
//        this.userRepository = userRepository;
//        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
//    }
//
//    @PostMapping("/login")
//    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto loginRequestDto) {
//        var user = userRepository.findByUsername(loginRequestDto.user());
//
//        if (user.isEmpty() || !user.get().isLoginCorrect(loginRequestDto, bCryptPasswordEncoder)){
//            throw new BadCredentialsException("user or password is invalid!");
//        }
//
//        var now= Instant.now();
//        var expiresIn= 3000;
//
//        var scopes= user.get().getRoles()
//                .stream()
//                .map(RoleEntity::getName)
//                .collect(Collectors.joining(" "));
//
//        var claims= JwtClaimsSet.builder()
//                .issuer("MyBackEnd")
//                .subject(user.get().getUserId().toString())
//                .expiresAt(now.plusSeconds(expiresIn))
//                .claim("scope", scopes);
//
//        var jwtValue= jwtEncoder.encode(JwtEncoderParameters.from(claims.build())).getTokenValue();
//
//        return ResponseEntity.ok(new LoginResponseDto(jwtValue, (long) expiresIn));
//    }
//
//}
