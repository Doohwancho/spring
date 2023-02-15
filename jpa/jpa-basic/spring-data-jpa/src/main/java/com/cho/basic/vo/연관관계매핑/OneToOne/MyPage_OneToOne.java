package com.cho.basic.vo.연관관계매핑.OneToOne;

import com.cho.basic.vo.연관관계매핑.Member;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

//@OneToOne 관계, (Member.java)
//Member : MyPage = 1 : 1
//왜?
//멤버 1명당 필연적으로 1개의 마이페이지 가지고 있으니까.
@Entity
public class MyPage_OneToOne {
    @javax.persistence.Id
    private Long Id;

    @OneToOne(mappedBy = "mypage")
    private Member member;

}