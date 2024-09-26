package com.suresoft.springbootdi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

public class DefaultBookService implements BookService {

//    BookRepository bookRepository;
//
//    public DefaultBookService(BookRepository bookRepository) {
//        this.bookRepository = bookRepository;
//    }
    public DefaultBookService() {}

    @Override
    public void rent(Book book) {
        System.out.println(book.getTitle());
    }

    @Override
    public void returnBook() {
        System.out.println("return book");
    }
}