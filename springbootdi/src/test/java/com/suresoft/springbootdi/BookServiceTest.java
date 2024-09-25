package com.suresoft.springbootdi;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class BookServiceTest {
    @Autowired @Qualifier("bookServiceProxy")
    BookService bookService;

//    @Test
//    public void di() {
//        assertNotNull(bookService);
//        assertNotNull(bookService.bookRepository);
//        // Spring이 bean을 만들어서 service에 넣었는지 확인하기.
//    }

    @Test
    public void rent(){
        Book book = new Book();
        book.setTitle("spring");
        bookService.rent(book);
    }

    // 만약 rent라는 코드를 건들지 않고 앞뒤로 메세지를 추가하고 싶은 경우, Proxy를 사용할 수 있음.




}