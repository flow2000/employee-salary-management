package com.salary.service;

import com.salary.entity.User;
import org.springframework.stereotype.Service;

@Service
public interface TokenService {

    String getToken(User user);

    Boolean verifyToken(String login_name, String token);

}
