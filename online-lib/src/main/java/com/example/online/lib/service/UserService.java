package com.example.online.lib.service;

import com.example.online.lib.dto.UserRegisterDto;
import com.example.online.lib.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService
{
    User save(UserRegisterDto userRegisterDto);
}
