package com.suresoft.springbootdi;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import static org.junit.jupiter.api.Assertions.*;

class BookServiceTest {

    BookService bookService = (BookService) Proxy.newProxyInstance(
            BookService.class.getClassLoader(),
            new Class[]{BookService.class},
            new InvocationHandler() {
                BookService bookService = new DefaultBookService();


                @Override
                public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                    System.out.println("시작");
                    Object invoke = method.invoke(bookService, args);
                    System.out.println("끝");
                    return invoke;
                } // 동적으로 구현된 프록시 단, 이 방식은 클래스는 반드시 인터페이스여야함!!!
            }

    ); // 이걸 Spring에서 편하게 쓰기 위해 도입된 것이 proxy 기반의 Spring AOP임!

    @Test
    public void rent(){
        Book book = new Book();
        book.setTitle("spring");
        bookService.rent(book);
        System.out.println("===");
        bookService.returnBook();
    }

    // 만약 rent라는 코드를 건들지 않고 앞뒤로 메세지를 추가하고 싶은 경우, Proxy를 사용할 수 있음.




}