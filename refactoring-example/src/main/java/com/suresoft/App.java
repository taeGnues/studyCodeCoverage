package com.suresoft;

import java.util.Arrays;

public class App {
    public static void main(String[] args) throws ClassNotFoundException {

        // 생성된 객체를 통해 가져오는 방식.
        Book book = new Book();
        Class<? extends Book> aClass = book.getClass(); // 기존에 생성된 클래스를 통해 접근하는 방식.


        // BookClass에 어노테이션을 붙인 후 어노테이션을 조회하면 실행이 될까? --> 아무것도 안나옴.
        Arrays.stream(Book.class.getAnnotations()).forEach(System.out::println);

        // 어노테이션은 기본적으로 주석(commnent)와 같은 취급을 받음. 기본적으론 클래스까지 나오지만, 바이트코드를 로딩할 때는, 메모리에 남지 않고, 어노테이션은 빼고 읽어옴.
        // 따라서 만약 런타임 시에도 남겨두고 싶다면, @Retention(RetentionPolicy.RUNTIME)을 선언해야함.

        Arrays.stream(aClass.getDeclaredFields()).forEach(f -> {
            Arrays.stream(f.getAnnotations()).forEach(a -> {
                if (a instanceof MyAnnotation){ // 필드에 어노테이션이 달려있는지 확인해서 가져옴.
                    MyAnnotation myAnnotation = (MyAnnotation) a;
                    System.out.println(myAnnotation.value());
                    System.out.println(myAnnotation.name());
                }

                }
            );


        });

    }
}