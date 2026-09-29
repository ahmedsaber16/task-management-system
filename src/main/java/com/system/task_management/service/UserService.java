package com.system.task_management.service;

import com.system.task_management.config.JwtService;
import com.system.task_management.dto.LoginRes;
import com.system.task_management.dto.UserReq;
import com.system.task_management.dto.UserRes;
import com.system.task_management.exception.InvalidCredentialException;
import com.system.task_management.exception.UserNotFoundException;
import com.system.task_management.model.User;
import com.system.task_management.repository.UserRepo;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private UserRepo userRepo;
    private PasswordEncoder passwordEncoder;
    private JwtService jwtService;


    public UserService(UserRepo userRepo , PasswordEncoder passwordEncoder ,JwtService jwtService) {

        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public LoginRes login(String userName , String password){
        User user = userRepo.findByUserName(userName)
                .orElseThrow(()-> new InvalidCredentialException("Invalid username or password"));
        if(!passwordEncoder.matches(password , user.getPassword())){
            throw new InvalidCredentialException("Invalid username or password");
        }
       String token = jwtService.generateToken(userName);
        return new LoginRes(userName , token);
    }

    public UserRes createUser (UserReq req){
        User user = convertToUsrEntity(req);
        User savedUser = userRepo.save(user);
        return convertToUsrResponse(savedUser);
    }

    public UserRes getUserById(Long id){
        User user = userRepo.findById(id)
                .orElseThrow(()-> new UserNotFoundException("User not found"));
        return convertToUsrResponse(user);
    }

    public List<UserRes> getAllUsers (){
        List<User> allUsers = userRepo.findAll();
        return allUsers.stream().map(this::convertToUsrResponse).toList();
    }




    public User convertToUsrEntity(UserReq req){
        User user = new User();
        user.setUserName(req.getUserName());
        user.setEmail(req.getEmail());
        user.setPassword(passwordEncoder.encode(req.getPassword()));
        return user;
    }

    public UserRes convertToUsrResponse(User user){
        return new UserRes(user.getId() , user.getUserName(), user.getEmail());
    }
}
