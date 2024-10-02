package com.suresoft.springbootdi;

public class AuthorService {
    public AuthorService(){}

    public String getAuthorName(Author author){
        return author.getName();
    }

    public void setAuthorName(Author author, String name){
        author.setName(name);
    }

    public void startWriting(Author author){
        System.out.println("WRITING START");
        System.out.println("WRITING END");
    }

}
