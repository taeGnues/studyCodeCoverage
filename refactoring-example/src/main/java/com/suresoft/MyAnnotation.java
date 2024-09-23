package com.suresoft;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.FIELD})
@Inherited // MyBook의 상위 클래스인 Book으로부터
public @interface MyAnnotation {
    String value() default "";
    String name() default "hello";
}
