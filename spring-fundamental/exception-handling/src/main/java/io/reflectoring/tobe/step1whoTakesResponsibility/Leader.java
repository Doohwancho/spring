package io.reflectoring.tobe.step1whoTakesResponsibility;

import java.io.IOException;

public class Leader {
    public void makeReport() {
        SecondLeader secondLeader = new SecondLeader();
        try {
            secondLeader.makeReport();
        } catch (IOException e) { //TODO - a-4. Employee가 던지는 Exception을 SecondLeader -> Leader로 던져 Leader가 Exception 처리
            System.out.println("Leader: 제가 책임지겠습니다! " + e.getMessage());
        }
    }
}
