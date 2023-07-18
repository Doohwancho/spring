package oop._1_상태_데이터의_캡슐화.enum_.how.enumMapper.step4;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class EnumMapper {
  private Map<String, List<EnumValue>> factory = new HashMap<>();

  private List<EnumValue> toEnumValues(Class<? extends EnumModel> e){

    // Java8이 아닐경우
//            List<EnumValue> enumValues = new ArrayList<>();
//            for (EnumModel enumType : e.getEnumConstants()) {
//                enumValues.add(new EnumValue(enumType));
//            }
//            return enumValues;

    return Arrays
        .stream(e.getEnumConstants())
        .map(EnumValue::new)
        .collect(Collectors.toList());
  }

  public void put(String key, Class<? extends EnumModel> e){
    factory.put(key, toEnumValues(e));
  }

  //이걸 어케 활용하지? -> EnumTest를 봐라
  public Map<String, List<EnumValue>> get(String keys){

    // Java8이 아닐경우
//            Map<String, List<EnumValue>> result = new LinkedHashMap<>();
//            for (String key : keys.split(",")) {
//                result.put(key, factory.get(key));
//            }
//
//            return result;

    return Arrays
        .stream(keys.split(","))
        .collect(Collectors.toMap(Function.identity(), key -> factory.get(key)));
  }

  public Map<String, List<EnumValue>> getAll(){
    return factory;
  }
}
