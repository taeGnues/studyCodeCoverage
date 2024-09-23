package com.suresoft;

import java.util.Arrays;

public class App {
    public static void main(String[] args) throws ClassNotFoundException {

        // 클래스를 가져오는 3가지 방식

        // 1. type을 가지고 가져오는 방식.
        Class<Book> bookClass = Book.class;
        // 클래스 로딩이 끝나면, 클래스 타입의 인스턴스를 만들어서 힙에다 넣어줌.

        // 2. 생성된 객체를 통해 가져오는 방식.
        Book book = new Book();
        Class<? extends Book> aClass = book.getClass(); // 기존에 생성된 클래스를 통해 접근하는 방식.

        // 3. FQCN을 이용해 가져오는 방식.
        Class<?> aClass2 = Class.forName("com.suresoft.Book"); // Full Qualified Class Name (패키지 종류까지 포함하는 이름)

        // 필드 가져오기
        Arrays.stream(bookClass.getFields()).forEach(System.out::println); // => 접근 지시자에 의해 가져옴. (public)
        Arrays.stream(bookClass.getDeclaredFields()).forEach(System.out::println); // => 접근 지시자 상관없이 다 가져오기

        // 필드에 들어있는 값까지 가져오기
        Arrays.stream(aClass.getDeclaredFields()).forEach(f -> {
            f.setAccessible(true);
            try {
                System.out.printf("%s %s\n", f, f.get(book));
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        });

        System.out.println("==메소드 가져오기==");
        Arrays.stream(aClass.getDeclaredMethods()).forEach(System.out::println);
        System.out.println("==생성자 가져오기==");
        Arrays.stream(aClass.getConstructors()).forEach(System.out::println);
        System.out.println("==인터페이스 가져오기==");
        Arrays.stream(MyBook.class.getInterfaces()).forEach(System.out::println);
    }
}