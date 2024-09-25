package com.suresoft;
import com.suresoft.di.Inject;

public class BookService {
    @Inject
    BookRepository bookRepository;

    public void join(){
        bookRepository.save();
        System.out.println("join");
    }
}
