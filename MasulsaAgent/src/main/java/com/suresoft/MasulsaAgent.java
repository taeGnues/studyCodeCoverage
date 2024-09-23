package com.suresoft;

import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.implementation.FixedValue;
import net.bytebuddy.matcher.ElementMatchers;
import net.bytebuddy.utility.JavaModule;

import java.lang.instrument.Instrumentation;
import java.security.ProtectionDomain;

import static net.bytebuddy.matcher.ElementMatchers.named;

public class MasulsaAgent {
    public static void premain(String agentArgs, Instrumentation inst) {
        new AgentBuilder.Default()
                .type(ElementMatchers.any())
                .transform(new AgentBuilder.Transformer() {
                    @Override
                    public DynamicType.Builder<?> transform(DynamicType.Builder<?> builder, TypeDescription typeDescription, ClassLoader classLoader, JavaModule javaModule, ProtectionDomain protectionDomain) {
                        return builder.method(named("pullOut")).intercept(FixedValue.value("Rabbit!"));
                    }
                }).installOn(inst);

    }
}

/*
이 방식은 파일 시스템의 Class 파일들을 건드리는 것이 아니라, 자바 에이전트가 클래스 로딩할 때 적용된다.
즉, 클래스 파일이 바뀌지 않음. 클래스 로딩 시, 자바에이전트에 의해 변경된 바이트코드가 메모리에 들어가게 됨.
 */
