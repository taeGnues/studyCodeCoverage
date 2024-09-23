package com.suresoft;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.FixedValue;

import java.io.File;
import java.io.IOException;

import static net.bytebuddy.matcher.ElementMatchers.named;

public class Masulsa {
    public static void main(String[] args) throws IOException {
        System.out.println(new Moja().pullOut());

//        new ByteBuddy().redefine(Moja.class)
//                .method(named("pullOut")).intercept(FixedValue.value("Rabbit!"))
//                .make().saveIn(new File("C:\\Users\\sure\\IdeaProjects\\studyCodeCoverage\\studyCodeCoverage\\target\\classes"));
    }
}

/*
Moja라는 클래스를 application class loader가 읽고,

byte buddy가 만들어주는 class loader로 moja라는 클래스가 있음.

moja라는 클래스는 1개지만, 실제로는 JVM에서는 2개임. (Full package 같지만, 서로 다름)
 */

/*
코드 커버리지 측정은, 우리가 실제로 실행하지 않았는데, 바이트코드는 조작이 됐고, 커버리지가 측정됨.

토끼를 꺼내는 마술도, 코드를 실행하지 않고 바로 측정되게 할 수 있음. (즉, Bytebuddy로 쓰지 않고 바로 Moja().pullout()으로 되게)


 */