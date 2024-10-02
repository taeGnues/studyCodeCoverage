package com.suresoft;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// 클래스까지 유지할 필요 없음. 소스레벨에서 읽어서 소스파일 만들고 컴파일하게만 하면 끝. 런타임, 바이트코드레벨까지 필요없음.
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.SOURCE)
public @interface Magic {

}
