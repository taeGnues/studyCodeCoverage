package com.suresoft;

import com.google.auto.service.AutoService;
import com.sun.source.tree.Tree;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.Name;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;
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
            if(element.getKind() == ElementKind.INTERFACE){
                processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR, "Magic annotation can not be used on " + simpleName);
            }else{
                processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE, "Processing " + simpleName);
            }

        }
        // v1 기능 : @Magic이라는 어노테이션이 붙어있다면, 해당 클래스가 interface이면 로그를 출력하는 기능.

        // 여러 라운드에 거쳐서 엘리멘트를 처리함.
        // 처리된 엘리멘트는 다른 라운드에서 또 처리 가능(필터체인처럼)

        return true; // true를 리턴하면 다른 프로세스에서 더 처리하지 않음.
    }
}
