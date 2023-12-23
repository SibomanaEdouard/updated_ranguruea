package com.backend.rangurura.auth;

import com.backend.rangurura.dtos.LoginDto;
import com.backend.rangurura.entities.User;
import com.backend.rangurura.exceptions.BadRequestException;
import com.backend.rangurura.exceptions.NotFoundException;
import com.backend.rangurura.exceptions.ServiceException;
import com.backend.rangurura.repositories.UserRepository;
import com.backend.rangurura.response.ApiResponse;
import com.backend.rangurura.services.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public ApiResponse<Object> loginUser(LoginDto dto) throws Exception {
            if (dto.getNationalId() == null || dto.getPassword() == null) {
                throw new BadRequestException("All credentials are required!");
            }

            var auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(dto.getNationalId(), dto.getPassword()));

            if (!auth.isAuthenticated()) {
                throw new BadRequestException("Authentication failed");
            }

            User user = userRepository.findByNationalId(dto.getNationalId()).orElseThrow(() -> new NotFoundException("User not found!"));

            var token = jwtService.generateToken(user);
            return ApiResponse.builder()
                    .success(true)
                    .data(token)
                    .build();

    }
}

