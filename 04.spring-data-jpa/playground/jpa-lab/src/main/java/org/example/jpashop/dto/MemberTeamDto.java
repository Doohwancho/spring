package org.example.jpashop.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;

//TODO - queryDSL의 select join member and team 한 결과물이 담기는 DTO.
//MemberJpaRepository.searchByBuilder() 참조
@Data
public class MemberTeamDto {
    
    private Long memberId;
    private String userName;
    private int age;
    private Long teamId;
    private String teamName;
    
    @QueryProjection //TODO - 얘가 붙은 클래스는 controller에 nested class로 넣으면 QClass가 생성이 안되더라?
    public MemberTeamDto(Long memberId, String userName, int age, Long teamId, String teamName) {
        this.memberId = memberId;
        this.userName = userName;
        this.age = age;
        this.teamId = teamId;
        this.teamName = teamName;
    }
}
