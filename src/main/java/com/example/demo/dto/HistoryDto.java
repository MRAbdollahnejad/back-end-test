package com.example.demo.dto;

import com.example.demo.entity.SearchHistory;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HistoryDto {
    List<SearchHistory> searchHistoryList;
}
