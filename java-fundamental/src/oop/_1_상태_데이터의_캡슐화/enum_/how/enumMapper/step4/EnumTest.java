package oop._1_상태_데이터의_캡슐화.enum_.how.enumMapper.step4;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
public class EnumTest {
//  @Autowired
  private EnumMapper enumMapper;

  EnumTest(){
    enumMapper = new EnumMapper();
  }

  @Disabled
  @Test
  public void get() {
    String key = "commissionType";

    // Controller에 key를 던져 값을 가져왔다고 가정
    Map<String, List<EnumValue>> controllerResult = enumMapper.get(key);

    // JS에서 Controller에서 받은 값에서 원하는 enum type을 가져왔다고 가정
    List<EnumValue> viewResult = controllerResult.get(key);

    EnumValue percent = viewResult.get(0);

    assertThat(percent.getKey(), is("PERCENT"));
    assertThat(percent.getValue(), is("percent"));
  }

  @Test
  public void EnumModel() {

    List<EnumModel> enumModels = Arrays
        .stream(EnumContract.CommissionType.class.getEnumConstants())
        .collect(Collectors.toList());

    assertThat(enumModels.get(0).getKey(), is("PERCENT"));
  }
}
