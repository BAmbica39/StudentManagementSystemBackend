package com.demo.serviceImpl;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.demo.dto.AuthRequest;
import com.demo.dto.UserDTO;
import com.demo.mapper.UserMapper;
import com.demo.model.User;
import com.demo.repository.UserRepository;
import com.demo.service.AuthService;
import com.demo.util.JwtUtil;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserMapper userMapper;
    

    @Override
    public UserDTO register(UserDTO dto) 
    {
        // Validate unique userName
        if (userRepository.existsByUsername(dto.getUsername())) 
        {
            throw new RuntimeException("UserName '" + dto.getUsername() + "' already exists");
        }
        
        // Validate unique email
        if (userRepository.existsByEmail(dto.getEmail())) 
        {
            throw new RuntimeException("Email '" + dto.getEmail() + "' already exists");
        }

        // Map DTO to Entity and encode password
        User user = userMapper.toEntity(dto);
        user.setPassword(passwordEncoder.encode(dto.getPassword()));

        // Save to DB
        User savedUser = userRepository.save(user);

        // Return DTO
        return userMapper.toDTO(savedUser);
    }

    
    @Override
    public String login(AuthRequest request) 
    {
        Optional<User> userOpt = userRepository.findByUsername(request.getUsername());
        if (userOpt.isPresent()) 
        {
            User user = userOpt.get();
            // Check if password matches
            if (passwordEncoder.matches(request.getPassword(), user.getPassword())) 
            {
                return jwtUtil.generateToken(user.getUsername(), user.getRole());
            } 
            else 
            {
                throw new RuntimeException("Password mismatch");
            }
        }

        throw new RuntimeException("User not found with userName: " + request.getUsername());
    }
    
    
}
