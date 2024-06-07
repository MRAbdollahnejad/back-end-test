package com.example.demo.controller;

import com.example.demo.dto.*;
import com.example.demo.entity.SearchHistory;
import com.example.demo.entity.UserEntity;
import com.example.demo.exception.local.SignUpException;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v0/users")
public class UserController {
    private final UserService userService;

    @PostMapping("/sign-up")
    public ResponseEntity<ProjectResponse> signUpUser(@RequestBody @Validated SignUpDto signUpDto){
        if(signUpDto.getUsername().contains(",")|| signUpDto.getPassword().contains(",")){
            throw new SignUpException("DONT USE , IN USERNAME OR PASS ");
        }
        UserEntity newUser=new UserEntity();
        newUser.setUsername(signUpDto.getUsername());
        newUser.setEmail(signUpDto.getEmail());
        newUser.setPassword(signUpDto.getPassword());
        ProjectResponse projectResponse = userService.signUpMethod(newUser);
        return ResponseEntity.ok().body(projectResponse );
    }

    @GetMapping("/login")
    public ResponseEntity<ProjectResponse> login(@RequestBody @Validated LoginDto loginDto){
        UserEntity newUser=new UserEntity();
        newUser.setUsername(loginDto.getUsername());
        newUser.setPassword(loginDto.getPassword());
        ProjectResponse projectResponse=userService.loginMethod(newUser);
        return ResponseEntity.ok().body(projectResponse);
    }

    @PostMapping("/add-to-history")
    public ResponseEntity<ProjectResponse> addToHistory(@RequestBody @Validated AddCityDto dto){
        String[] split = dto.getToken().split(",");
        if(split.length!=2){
            throw new SignUpException("WRONG TOKEN");
        }
        UserEntity newUser=new UserEntity();
        newUser.setUsername(split[0]);
        newUser.setPassword(split[1]);
        newUser.setSearchHistoryList(List.of(new SearchHistory(dto.getCityName())));
        ProjectResponse projectResponse = userService.addToHistory(newUser);
        return ResponseEntity.ok().body(projectResponse);
    }

    @GetMapping("/user-history")
    public ResponseEntity<HistoryDto> userHistory(@RequestBody @Validated AddCityDto dto){
        String[] split = dto.getToken().split(",");
        if(split.length!=2){
            throw new SignUpException("WRONG TOKEN");
        }
        UserEntity newUser=new UserEntity();
        newUser.setUsername(split[0]);
        newUser.setPassword(split[1]);
        HistoryDto historyDto = userService.userHistory(newUser);
        return ResponseEntity.ok().body(historyDto);
    }


}
