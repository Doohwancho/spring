package _2_oop._1_상태_데이터의_캡슐화.enum_.how.enum_필드로_같은의미끼리_묶기.ex1;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.jupiter.api.Test;

public class TableStatusTest {
  private TableStatus selectFromOriginTable(){
    return TableStatus.Y;
  }

  @Test
  public void origin테이블에서_조회한_데이터를_다른_2테이블에_등록한다() throws Exception {
    //given
    TableStatus origin = selectFromOriginTable();

    //then
    String table1Value = origin.getTable1Value();
    boolean table2Value = origin.isTable2Value();

    assertThat(origin, is(TableStatus.Y));
    assertThat(table1Value, is("1"));
    assertThat(table2Value, is(true));
  }


}
