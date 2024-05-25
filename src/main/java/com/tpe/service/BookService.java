package com.tpe.service;

import com.tpe.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class BookService {

    @Autowired
    private BookRepository repository;

    private final String GOOGLE_BOOKS_API_URL = "https://www.googleapis.com/books/v1/volumes?q=";

    private final RestTemplate restTemplate = new RestTemplate();
    public void fetchAndSaveBooks(String query) {

        String url = GOOGLE_BOOKS_API_URL+query;

    }
}
