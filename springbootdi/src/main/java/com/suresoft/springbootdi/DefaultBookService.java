package com.suresoft.springbootdi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DefaultBookService implements BookService {

    @Autowired
    BookRepository bookRepository;

    public void rent(Book book) {
        System.out.println(book.getTitle());
    }
}