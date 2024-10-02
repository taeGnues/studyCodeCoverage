package com.suresoft.springbootdi;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.InvocationHandlerAdapter;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static net.bytebuddy.matcher.ElementMatchers.named;
import static org.junit.jupiter.api.Assertions.*;

class AuthorServiceTest {
    @Test
    public void di(){
        MethodInterceptor handler = new MethodInterceptor() {
            AuthorService authorService = new AuthorService();
            @Override
            public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
                if(method.getName().equals("getAuthorName")){
                    System.out.println("Hello");
                    Object invoke = method.invoke(authorService, args);
                    System.out.println("world");

                    return invoke;
                }

                return method.invoke(authorService, args);
            }
        };
        // java 9부터는 접근제어가 생겼기 때문에 이 코드가 바로 실행되진 않는다.

        AuthorService authorService = (AuthorService) Enhancer.create(AuthorService.class, handler); // handler 등록
        Author author = new Author();
        author.setName("hi");
        author.setId(1L);
        authorService.getAuthorName(author);
        authorService.startWriting(author);
    }


    @Test
    public void di2() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class<? extends AuthorService> proxyClass = new ByteBuddy().subclass(AuthorService.class)
                .method(named("getAuthorName")).intercept(InvocationHandlerAdapter.of(new InvocationHandler() {
                    AuthorService authorService = new AuthorService();

                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        System.out.println("bbbb");
                        Object invoke = method.invoke(authorService, args);
                        System.out.println("aaaa");
                        return invoke;
                    }
                }))
                .make().load(AuthorService.class.getClassLoader()).getLoaded();
        // 클래스 프록시
        // 클래스 기반의 bean AOP를 적용할 때 이런식으로 적용됨.
        // 만약, 클래스가 final로 선언되는 등, 기본생성자를 private으로 막는 등, 상속을 불가능하게 만드는 경우는 만들 수 없음.
        // 따라서 가능한 interface를 만들어서 사용하는 것으로 관리하는 것이 좋음.


        AuthorService authorService = proxyClass.getConstructor(null).newInstance();

        Author author = new Author();
        author.setName("hi");
        author.setId(1L);

        authorService.getAuthorName(author);
        authorService.startWriting(author);

    }

}