package com.suresoft;

import com.google.auto.service.AutoService;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.*;
import javax.tools.Diagnostic;
import java.io.IOException;
import java.util.Set;

@AutoService(Processor.class)
public class MagicMojaProcessor extends AbstractProcessor {

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        return Set.of(Magic.class.getName()); // magic이라는 element를 처리한다. (소스코드의 구성요소를 element라고함. 각 element에 대한 정보를 참조 가능)
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {



        Set<? extends Element> elements = roundEnv.getElementsAnnotatedWith(Magic.class);
        for (Element element : elements) {
            Name simpleName = element.getSimpleName();
            if(element.getKind() != ElementKind.INTERFACE){
                processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR, "Magic annotation can not be used on " + simpleName);
            }else{
                processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE, "Processing " + simpleName);
            }

            TypeElement typeElement = (TypeElement) element;
            ClassName className = ClassName.get(typeElement); // 패키지에 있는 정보들을 참조할 수 있음.
            // 1. 메소드를 생성함.
            MethodSpec pullOut = MethodSpec.methodBuilder("pullOut")
                    .addModifiers(Modifier.PUBLIC)
                    .returns(String.class)
                    .addStatement("return $S", "Rabbit!")
                    .build();

            // 2. MagicMoja 클래스에 대해 위에서 만든 메소드를 넣어줌.
            TypeSpec magicMoja = TypeSpec.classBuilder("MagicMoja")
                    .addModifiers(Modifier.PUBLIC)
                    .addSuperinterface(className) // magic이라는 어노테이션 클래스를 실제로 구현하게함. 즉, Moja moja = new MagicMoja()로 쓸 수 있다는 의미!
                    .addMethod(pullOut)
                    .build();

            // 3. 메모리 상 클래스로 객체로만 정의한 것이고, 실제 소스를 소스코드에 넣어야함. 즉, 소스 파일을 만들어서 바이트코드가 되도록 해야함.
            Filer filer = processingEnv.getFiler(); // 소스코드, 클래스코드, 리소스를 생성할 수 있는 인터페이스


            try {
                JavaFile.builder(className.packageName(), magicMoja)
                        .build()
                        .writeTo(filer);
                // 컴파일할 클래스가 생성됨!
            } catch (IOException e) {
                processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR, e.getMessage());
            }


        }
        // v1 기능 : @Magic이라는 어노테이션이 붙어있다면, 해당 클래스가 interface이면 로그를 출력하는 기능.

        // 여러 라운드에 거쳐서 엘리멘트를 처리함.
        // 처리된 엘리멘트는 다른 라운드에서 또 처리 가능(필터체인처럼)

        return true; // true를 리턴하면 다른 프로세스에서 더 처리하지 않음.
    }
}
