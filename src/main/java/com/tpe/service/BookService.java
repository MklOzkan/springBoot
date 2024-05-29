package com.tpe.service;

import com.tpe.domain.Book;
import com.tpe.google_respnse.GoogleBookItem;
import com.tpe.google_respnse.GoogleBookResponse;
import com.tpe.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;


@Service
public class BookService {
    @Autowired
    private BookRepository repository;

    private final String GOOGLE_BOOKS_API_URL = "https://www.googleapis.com/books/v1/volumes?q=";
    //RestTemplate is used to make GET request to the API, and deserialize the JSON response into some class
    private final RestTemplate restTemplate =  new RestTemplate();

    public void fetchAndSaveBooks(String query) {
        String url = GOOGLE_BOOKS_API_URL+query;

        GoogleBookResponse response =  restTemplate.getForObject(url, GoogleBookResponse.class);

        if(response != null && response.getItems()!=null){
            List<Book> books = new ArrayList<>();
            for(GoogleBookItem item: response.getItems()){
                Book book = new Book();

                book.setTitle(item.getVolumeInfo().getTitle());
                book.setAuthor(String.join(", ", item.getVolumeInfo().getAuthors()));
                book.setPublishedDate(item.getVolumeInfo().getPublishedDate());
                book.setIsbn(item.getVolumeInfo().getIndustryIdentifiers().get(0).getIdentifier());
                books.add(book);
            }

            repository.saveAll(books);
        }

    }
}
