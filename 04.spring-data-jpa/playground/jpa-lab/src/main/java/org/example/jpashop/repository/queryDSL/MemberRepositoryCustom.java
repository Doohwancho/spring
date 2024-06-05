package org.example.jpashop.repository.queryDSL;

import org.example.jpashop.controller.MemberQueryDSLController.MemberSearchCondition;
import org.example.jpashop.dto.MemberTeamDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/*
    TODO - Q. 왜 MemberRepositoryCustomImpl implements MemberRepositoryCustom 했지?
    
    
    그냥 MemberJpaRepository.java에 모든 메서드 정의하면 되지,
    왜 굳이 자식 클래스 만들어서 상속받고 오버라이드 했지?

    MemberRepositoryCustom 얘는 약간 search(based on condition) 관련된 애들 위주로 모아둔 것 같긴 한데..
 */

public interface MemberRepositoryCustom {

    List<MemberTeamDto> search(MemberSearchCondition condition);

    Page<MemberTeamDto> searchPageSimple(MemberSearchCondition condition, Pageable pageable);

    Page<MemberTeamDto> searchPageComplex(MemberSearchCondition condition, Pageable pageable);
}
