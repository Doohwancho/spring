package com.tdd.tddTest.junit5.repeat.forloop;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.Test;

public class RepeatedTestTest {
    @Test
    public void testRepeated(){
        for(int i = 0; i < 10; i++){
            Assertions.assertTrue(true);
        }
    }

    //@Test //Error! @RepeatedTest와 중복
    @RepeatedTest(
            value = 10,
            name = "current loop at {currentRepetition} / {totalRepetitions}"
    )
    public void testRepeatedWithAnnotation(RepetitionInfo repetitionInfo){ //파라미터의 몇번 iterate할지 정보를 받아와서
        int iterateCycle = repetitionInfo.getCurrentRepetition(); //10번 iterate할걸 값 할당 후, 사용
        for(int i = 0; i < iterateCycle; i++){
            Assertions.assertTrue(true);
        }
    }
}

/*

---
why use?

1. 더 직관적
2. 그냥 loop쓰면, loop 안에 어느 테스트에서 깨진지 로그에 안나오는데, @RepeatedTest 쓰면, 모든 iteration test의 과정, 테스트 결과를 보여줌



---
test screen

RepeatedTestTest
    testRepeated()
    testRepeatedWithAnnotation()
        current loop at 1 / 10
        current loop at 2 / 10
        current loop at 3 / 10
        current loop at 4 / 10
        current loop at 5 / 10
        current loop at 6 / 10
        current loop at 7 / 10
        current loop at 8 / 10
        current loop at 9 / 10
        current loop at 10 / 10


아래가 더 자세하고 명확하게 알려준다.


 */