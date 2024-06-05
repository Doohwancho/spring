package org.example.jpashop.service;

import lombok.RequiredArgsConstructor;
import org.example.jpashop.domain.Member;
import org.example.jpashop.repository.queryDSL.MemberJpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * why readonly = true for @Transactional?
 *
 * 1. transaction 단계에서, read말고 isnert,update,delete는 동시성 문제로 인해 extra step들이 많은데, read만 할 거면, 이 단계 모두 스킵 가능 -> 성능 빨라짐
 * 2. ensure it's read only -> read 외에 다른 헛짓거리 못하게 됨. secure coding.
 */


@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberJpaRepository memberRepository;

    @Transactional
    public Long join(Member member) {
        validateDuplicatedMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicatedMember(Member member) {
        List<Member> findMembers = memberRepository.findByUserName(member.getUserName());
        if (!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Member findOne(Long memberId) {
        return memberRepository.findOne(memberId);
    }

    /*
        TODO - update()시 팁: merge 보다는 find() 후 setter 하고 냅둬

        Q. 이렇게 안하고 아래처럼(find() -> .setter()) 하는 이유?
        
        @Transactional
        void update(Member param) {
            Member member = em.merge(param);
        }
        
        이렇게 해도 가능은 한데, 비추인 이유

        merge() 메서드는 파라미터로 받은 준영속 엔티티의 id(식별자) 값으로 엔티티를 조회한다. 만일, 영속성 컨텍스트에서 찾았다면 해당 엔티티를 반환하고 없으면 DB에서 해당 식별자로 조회해온 후 영속성 컨텍스트에 올린 후 반환한다.
        조회한 영속 엔티티를 준영속 엔티티 값으로 모두 교체(병합)
        여기가 문제가 되는 지점
        원하는 필드만 바꿀 수 있는게 아니라 모든 속성이 준영속 엔티티 값으로 교체됨
        만일 정책상 상품 이름은 등록 후 수정이 안된다고 할 때 수정 필드가 없을텐데 그러면 필드에서 받은 값으로 만든 준영속 상태 엔티티는 name 이 비어있을 것.
        그러면 영속 상태의 엔티티도 null로 업데이트 쳐버린다.
        영속 상태인 병합된 엔티티를 반환
        그러니까, update시, merge() 하지 말고, 아래처럼,
        
        find로 영속성 컨텍스트에서 entity 조회 후, setter로 원하는 필드만 바꾸면, transaction이 끝나는 시점에 flush()됨.

        그리고 이 방법을 쓰면, controller -> service 단으로 Member 객체 정보 다 담을 필요 없고, DTO에 MemberId랑 업데이트 필요한 attribute만 담아 보내면 됨.

     */
    @Transactional
    public void update(Long id, String userName) {
        Member member = memberRepository.findOne(id);
        member.setUserName(userName);
    }
}
