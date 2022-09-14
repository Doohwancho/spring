package com.example.di.nextstep.stage4;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/*
---
@Target

custom annotation만들 때 쓰는 앤데, 이 어노테이션의 타입이 뭔지 알려줌

Type 	        Class, interface or enumeration
Field 	        Field
Method 	        Method
Constructor	    Constructor
Local_Variable 	Local variable
Annotation_Type Annotation Type
Package 	    PACKAGE
Type_Parameter	Type Parameter
Parameter 	    Formal Parameter



---
@Retention

- SOURCE : 어노테이션을 사실상 주석처럼 사용하는 것. 컴파일러가 컴파일할때 해당 어노테이션의 메모리를 버립니다.
- CLASS : 컴파일러가 컴파일에서는 어노테이션의 메모리를 가져가지만 실질적으로 런타임시에는 사라지게 됩니다. 런타임시에 사라진다는 것은 리플렉션으로 선언된 어노테이션 데이터를 가져올 수 없게 됩니다. 디폴트값입니다.
- RUNTIME : 어노테이션을 런타임시에까지 사용할 수 있습니다. JVM이 자바 바이트코드가 담긴 class 파일에서 런타임환경을 구성하고 런타임을 종료할 때까지 메모리는 살아있습니다.


 */

@Target(ElementType.FIELD) //the custom annotation can only annotate a field.
@Retention(RetentionPolicy.RUNTIME)
public @interface Inject {
}
