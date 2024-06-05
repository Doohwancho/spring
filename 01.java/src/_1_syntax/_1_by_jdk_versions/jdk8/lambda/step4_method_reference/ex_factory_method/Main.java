package _1_syntax._1_by_jdk_versions.jdk8.lambda.step4_method_reference.ex_factory_method;

import _1_syntax._1_by_jdk_versions.jdk8.lambda.step4_method_reference.ex_factory_method.step1_regular_factory.Factory;
import _1_syntax._1_by_jdk_versions.jdk8.lambda.step4_method_reference.ex_factory_method.step2_lambda_factory.LambdaFactory;

public class Main {

  public static void main(String[] args) {
    //step1) regular factory
    Factory factory = new Factory();
    IType result1 = factory.getNewClass(1); //문제 - 이 떄, new A()만 했는데, new B(), new C(), new D() 가 다 됨. eager initilization

    //step2) lambda factory
    LambdaFactory lambdaFactory = new LambdaFactory();
    IType result2 = lambdaFactory.getNewClass(3); //Supplier, lambda를 이용하면 lazy initilization 가능. 호출된 C만 new C()되고, 나머진 객체 생성이 안된 상태.

  }

}
