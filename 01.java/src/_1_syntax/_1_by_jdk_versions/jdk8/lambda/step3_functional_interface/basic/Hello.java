package _1_syntax._1_by_jdk_versions.jdk8.lambda.step3_functional_interface.basic;

public class Hello implements FunctionalInterfaceExample{
    @Override
    public void printMsg(String msg) {
        System.out.println(msg);
    }
}
