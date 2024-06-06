package com.example.demo.service;

import com.example.demo.repository.SearchHistoryRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SearchHistoryService {
    final SearchHistoryRepository searchHistoryRepository;
}
