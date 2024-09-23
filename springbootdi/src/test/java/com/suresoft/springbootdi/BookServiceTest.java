package com.suresoft.springbootdi;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class BookServiceTest {
    @Autowired
    BookService bookService;

    @Test
    public void di() {
        assertNotNull(bookService);
        assertNotNull(bookService.bookRepository);
        // Spring이 bean을 만들어서 service에 넣었는지 확인하기.
    }




}