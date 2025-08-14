package com.demo.mapper;

import org.springframework.stereotype.Component;
import com.demo.dto.UserDTO;
import com.demo.model.User;

@Component
public class UserMapper {

    // Convert Entity to DTO
    public UserDTO toDTO(User user) 
    {
        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());
        dto.setPassword(user.getPassword());
        dto.setRole(user.getRole());
        return dto;
    }

    // Convert DTO to Entity
    public User toEntity(UserDTO dto) 
    {
        User user = new User();
        
        // Only set ID if it's not null (helps in update scenarios)
        if (dto.getId() != null) 
        {
            user.setId(dto.getId());
        }
        
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setRole(dto.getRole());
        return user;
    }
    
    
}
