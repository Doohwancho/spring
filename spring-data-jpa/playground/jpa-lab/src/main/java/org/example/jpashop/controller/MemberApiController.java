package org.example.jpashop.controller;

import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.example.jpashop.domain.Member;
import org.example.jpashop.dto.MemberDto;
import org.example.jpashop.service.MemberService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberApiController {
    
    private final MemberService memberService;
    
    @GetMapping("/api/v1/members")
    public List<Member> membersV1() {
        return memberService.findMembers();
    }
    
    @GetMapping("/api/v2/members")
    public Result membersV2() {
        List<Member> findMembers = memberService.findMembers();
        List<MemberTestDto> collect = findMembers.stream()
            .map(m -> new MemberTestDto(m.getUserName()))
            .collect(Collectors.toList());
        
        return new Result(collect.size(), collect);
    }
    
    @Data
    @AllArgsConstructor
    static class Result<T> { //TODO - java generics on Controller layer
        
        private int count;
        private T data;
    }
    
    @PostMapping("/api/v1/members")
    public CreateMemberResponse saveMemberV1(@RequestBody @Valid Member member) {
        Long id = memberService.join(member);
        return new CreateMemberResponse(id);
    }
    
    @PostMapping("/api/v2/members")
    public CreateMemberResponse saveMemberV2(@RequestBody @Valid CreateMemberRequest request) {
        
        Member member = new Member();
        member.setUserName(request.getName());
        
        Long id = memberService.join(member);
        return new CreateMemberResponse(id);
    }
    
    @PatchMapping("/api/v2/members/{id}")
    public UpdateMemberResponse updateMemberV2(
        @PathVariable("id") Long id,
        @RequestBody @Valid UpdateMemberRequest request
    ) {
        memberService.update(id, request.getName());
        Member findMember = memberService.findOne(id);
        return new UpdateMemberResponse(findMember.getId(), findMember.getUserName());
    }
    
    @Data
    static class UpdateMemberRequest {
        
        private String name;
    }
    
    @Data
    @AllArgsConstructor
    static class UpdateMemberResponse {
        
        private Long id;
        private String name;
    }
    
    @Data
    static class CreateMemberRequest {
        
        @NotEmpty
        private String name;
    }
    
    @Data
    static class CreateMemberResponse {
        
        private Long id;
        
        public CreateMemberResponse(Long id) {
            this.id = id;
        }
    }
    
    @Data
    private class MemberTestDto {
        
        String userName;
        public MemberTestDto(String userName) {
            this.userName = userName;
        }
    }
}
