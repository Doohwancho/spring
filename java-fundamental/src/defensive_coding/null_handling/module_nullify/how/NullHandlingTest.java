package defensive_coding.null_handling.how;


import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import defensive_coding.null_handling.util.Nullify;

public class NullHandlingTest {

  @Test
  void TestNulls() {
    //given
    List<Integer> nullList = null;

    //when
    List<Integer> verifiedList = Nullify.of(nullList);

    //then
    Assertions.assertEquals(verifiedList, null);
  }

}
