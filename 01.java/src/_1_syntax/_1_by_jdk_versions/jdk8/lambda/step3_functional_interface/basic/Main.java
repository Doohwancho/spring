package _1_syntax._1_by_jdk_versions.jdk8.lambda.step3_functional_interface.basic;

public class Main {
    public static void main(String[] args) {
        FunctionalInterfaceExample yunhoInterface = new Hello();
        yunhoInterface.printMsg("안녕하세요");
        yunhoInterface.defaultMethod(); //그냥 인터페이스에서 default로 메서드 선언 가능하게 바뀐거 이용하는거 뿐이잖아?
        FunctionalInterfaceExample.staticMethod(); //interface에서 static method도 선언 가능하게 바뀌었나보네? 위에 기능 추가될 때. 
    }
}
