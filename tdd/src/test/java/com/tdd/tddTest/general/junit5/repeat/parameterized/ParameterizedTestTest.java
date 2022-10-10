package com.tdd.tddTest.general.junit5.repeat.parameterized;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ParameterizedTestTest {


    @ValueSource(ints = {0,1,2})
    @ParameterizedTest(name = "when param is {0},")
    public void valueSourceTest(int param){
//        assertTrue(param == 1); //when param is 1 returns true, else false
    }

    /*********************************************/

    @ParameterizedTest
    @CsvSource(value={
            "ABC",
            "가나다"
        })
    public void csvSourceTest(String str){
//        assertTrue(str.equals("가나다")); //when param is "가나다" returns true, else false
    }

    /*********************************************/


    private static List<Arguments> fnFunction(){
        return Arrays.asList(
            Arguments.of(1, "one"),
            Arguments.of(2, "two")
        );
    }

    @ParameterizedTest
    @MethodSource("fnFunction")
    public void methodSourceTest(int input, String expectedOutput){
        //TODO
    }

    /*********************************************/

}

/*

---
covered

@ValueSource
@CsvSource
@MethodSource


---
not covered

@CsvFileSource
@EnumSource



 */