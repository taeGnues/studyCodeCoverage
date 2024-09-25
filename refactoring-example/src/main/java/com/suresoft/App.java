package com.suresoft;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

public class App {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchFieldException {

        // ----------- 생성자

        Class<?> bookClass = Class.forName("com.suresoft.Book"); // 클래스 로딩 발생
        Constructor<?> constructor = bookClass.getConstructor(null); // 기본 생성자 가져오기 (null를 파라미터로 받음)
        Constructor<?> constructorString = bookClass.getConstructor(String.class);
        Book book = (Book) constructor.newInstance(); // 객체 생성
        Book book2 = (Book) constructorString.newInstance("myBook");
        System.out.println(book);
        System.out.println(book2);

        // ----------- 필드

        Field a = Book.class.getDeclaredField("A"); // A는 static한 필드임.
        System.out.println(a.get(null));
        a.set(null,"AAAAA");
        System.out.println(a.get(null));

        Field b = Book.class.getDeclaredField("B"); // B는 인스턴스의 필드임. 따라서 바로 가져오지 못함.
        b.setAccessible(true); // 접근지시자 무시
        System.out.println(b.get(book));
        b.set(book, "BBBBB");
        System.out.println(b.get(book));

        // ----------- 메소드

        Method c = Book.class.getDeclaredMethod("c");
        c.setAccessible(true);
        c.invoke(book);  // 특정 인스턴스에 해당하는 메소드 실행. 단, private이므로 수정필요

        Method d = Book.class.getDeclaredMethod("sum", int.class, int.class); // 파라미터 타입을 줘야함.
        int invoke = (int) d.invoke(book, 1, 2);
        System.out.println(invoke);
    }
}