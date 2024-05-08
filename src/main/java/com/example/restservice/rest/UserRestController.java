package com.example.restservice.rest;

import com.example.restservice.entities.RoleEntity;
import com.example.restservice.entities.UserEntity;
import com.example.restservice.exception.BadRequestException;
import com.example.restservice.exception.ErrorConstants;
import com.example.restservice.model.RoleType;
import com.example.restservice.model.User;
import com.example.restservice.repository.RoleRepository;
import com.example.restservice.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/users")
public class UserRestController {

    UserRepository userRepository;
    RoleRepository roleRepository;
    PasswordEncoder encoder;

    public UserRestController(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.encoder = encoder;
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody User user) {
        if (userRepository.existsByUsername(user.username())) {
            throw BadRequestException.badRequest(ErrorConstants.USER_NAME_EXISTS_CODE,
                    ErrorConstants.USER_NAME_EXISTS_MESSAGE);
        }

        // Create new user's account
        UserEntity userEntity = new UserEntity(user.username(), encoder.encode(user.password()));

        RoleType roleType = user.roleType() == null ? RoleType.ROLE_USER : user.roleType();
        RoleEntity userRole = roleRepository.findByName(roleType)
                .orElseThrow(() -> BadRequestException.badRequest(ErrorConstants.ROLE_DOES_NOT_EXIST_CODE,
                        ErrorConstants.ROLE_DOES_NOT_EXIST_MESSAGE));

        userEntity.setRole(userRole);
        userRepository.save(userEntity);
        return ResponseEntity.noContent().build();
    }
}
