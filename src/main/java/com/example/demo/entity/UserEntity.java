package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="users_entity")
@Entity
public class UserEntity extends BaseEntity<Long>{
    String username;
    String password;
    String email;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "users_entity_id",referencedColumnName = "id")
    List<SearchHistory> searchHistoryList=new ArrayList<>();

}
