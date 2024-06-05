package _1_syntax._1_by_jdk_versions.jdk8.lambda.step4_method_reference.ex_factory_method.step2_lambda_factory;

import java.util.HashMap;
import java.util.function.Supplier;
import _1_syntax._1_by_jdk_versions.jdk8.lambda.step4_method_reference.ex_factory_method.A;
import _1_syntax._1_by_jdk_versions.jdk8.lambda.step4_method_reference.ex_factory_method.B;
import _1_syntax._1_by_jdk_versions.jdk8.lambda.step4_method_reference.ex_factory_method.C;
import _1_syntax._1_by_jdk_versions.jdk8.lambda.step4_method_reference.ex_factory_method.D;
import _1_syntax._1_by_jdk_versions.jdk8.lambda.step4_method_reference.ex_factory_method.IType;

public class LambdaFactory {

  public static final int A_TYPE = 0;
  public static final int B_TYPE = 1;
  public static final int C_TYPE = 2;
  public static final int D_TYPE = 3;

  public static HashMap<Integer, Supplier<IType>> mTypeFactoryMap; //객체 new로 만드는게 Supplier(() -> T)라 lazy initialization 가능! 미리 만들어 놓는게 아니라 호출할 때 만들 수 있다.

  static {

    mTypeFactoryMap = new HashMap<>();

    mTypeFactoryMap.put(A_TYPE, A::new);
    mTypeFactoryMap.put(A_TYPE, B::new);
    mTypeFactoryMap.put(A_TYPE, C::new);
    mTypeFactoryMap.put(A_TYPE, D::new);

  }

  public IType getNewClass(int type) {

    int key = D_TYPE;

    if (mTypeFactoryMap.containsKey(type)) {
      key = type;
    }
    return mTypeFactoryMap.get(key).get();
  }
}
