package com.suresoft;

import com.suresoft.di.ContainerService;

public class Main {
    public static void main(String[] args) {
        BookService bookService = ContainerService.getObject(BookService.class);
        bookService.join();


    }
}