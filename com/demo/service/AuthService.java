package com.demo.service;

import com.demo.dto.AuthRequest;
import com.demo.dto.UserDTO;

public interface AuthService 
{
    UserDTO register(UserDTO dto);
    String login(AuthRequest request);
}
