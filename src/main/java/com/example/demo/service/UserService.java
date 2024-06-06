package com.example.demo.service;

import com.example.demo.dto.HistoryDto;
import com.example.demo.dto.ProjectResponse;
import com.example.demo.entity.SearchHistory;
import com.example.demo.entity.UserEntity;
import com.example.demo.exception.local.SignUpException;
import com.example.demo.repository.SearchHistoryRepository;
import com.example.demo.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {
    final UserRepository userRepository;
    final SearchHistoryRepository searchHistoryRepository;

    @Transactional
    public ProjectResponse signUpMethod(UserEntity userEntity){
        if (userRepository.findByEmail(userEntity.getEmail()).isPresent()){
            throw new SignUpException("this email is already registered");
        }
        Optional<UserEntity> foundedByUsername = userRepository.findByUsername(userEntity.getUsername());
        if (foundedByUsername.isPresent()){
            throw new SignUpException("this username is already registered");
        }
        userRepository.save(userEntity);
        String token = userEntity.getUsername()+","+userEntity.getPassword();
        return new ProjectResponse("00","sign up success",token);
    }

    public ProjectResponse loginMethod(UserEntity newUser) {
        Optional<UserEntity> foundedUser = userRepository.findByUsername(newUser.getUsername());
        if (foundedUser.isEmpty()){
            throw new SignUpException("username or password is wrong");
        }
        if(!foundedUser.get().getPassword().equals(newUser.getPassword())){
            throw new SignUpException("username or password is wrong");
        }
        String token = newUser.getUsername()+","+newUser.getPassword();
        return new ProjectResponse("00","login success",token);
    }

    @Transactional
    public ProjectResponse addToHistory(UserEntity newUser) {
        Optional<UserEntity> foundedUser = userRepository.findByUsername(newUser.getUsername());
        if (foundedUser.isEmpty()){
            throw new SignUpException("WRONG TOKEN");
        }
        if(!foundedUser.get().getPassword().equals(newUser.getPassword())){
            throw new SignUpException("WRONG TOKEN");
        }

        foundedUser.get().getSearchHistoryList().add(newUser.getSearchHistoryList().get(0));
        return new ProjectResponse("00","saved successfully",newUser.getUsername()+","+newUser.getPassword());
    }

    public HistoryDto userHistory(UserEntity newUser) {
        Optional<UserEntity> foundedUser = userRepository.findByUsername(newUser.getUsername());
        if (foundedUser.isEmpty()){
            throw new SignUpException("WRONG TOKEN");
        }
        if(!foundedUser.get().getPassword().equals(newUser.getPassword())){
            throw new SignUpException("WRONG TOKEN");
        }
        Optional<List<SearchHistory>> searchHistoryList = searchHistoryRepository.customFindAll(foundedUser.get().getId());
        return new HistoryDto(searchHistoryList.get());
    }
}
