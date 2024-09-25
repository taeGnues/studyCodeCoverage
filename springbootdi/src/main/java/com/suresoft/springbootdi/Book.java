package com.suresoft.springbootdi;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Book {
    public Book(){}
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;


    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
}
