package _1_syntax._1_by_jdk_versions.jdk8.lambda.step4_method_reference.ex_factory_method.step1_regular_factory;

import _1_syntax._1_by_jdk_versions.jdk8.lambda.step4_method_reference.ex_factory_method.A;
import _1_syntax._1_by_jdk_versions.jdk8.lambda.step4_method_reference.ex_factory_method.B;
import _1_syntax._1_by_jdk_versions.jdk8.lambda.step4_method_reference.ex_factory_method.C;
import _1_syntax._1_by_jdk_versions.jdk8.lambda.step4_method_reference.ex_factory_method.D;
import _1_syntax._1_by_jdk_versions.jdk8.lambda.step4_method_reference.ex_factory_method.IType;

public class Factory {
  public static final int A_TYPE = 0;
  public static final int B_TYPE = 1;
  public static final int C_TYPE = 2;
  public static final int D_TYPE = 3;



  public IType getNewClass(int type) {

    switch(type) {

      case A_TYPE:
        return new A(); //regular Factory는 new Factory()하고 getNewClass() 하면, new A(), new B(), new C(), new D() 다 eager initialization 되는데, lambda factory의 경우....

      case B_TYPE:
        return new B();

      case C_TYPE:
        return new C();

      case D_TYPE:
        return new D();

      default:
        return new D();
    }
  }

}
