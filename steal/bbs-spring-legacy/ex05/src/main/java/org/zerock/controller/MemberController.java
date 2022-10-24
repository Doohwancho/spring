package org.zerock.controller;

import java.security.Principal;

import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.domain.MemberVO;
import org.zerock.service.MemberService;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Controller //스프링의 빈으로 인식될 수 있게끔
@RequestMapping("/member/*") //board로 시작하면 무조건 이 컨트롤러로
@Log4j
public class MemberController {
	
	@Setter(onMethod_=@Autowired)
	private PasswordEncoder pwencoder;
	
	@Setter(onMethod_=@Autowired)
	private DataSource ds;
	
	@Setter(onMethod_=@Autowired)
	private MemberService memberService;
	
	@GetMapping("/register")
	public String register() {
		
		return "member/customRegister"; 
	}
	
	@PostMapping("/register")
	public String register(MemberVO vo) throws Exception{
		log.info("/member/register..........................");
		log.info("register member.................................: "+vo);
		 
		try {
			String encodedPw = pwencoder.encode(vo.getUserpw());
			vo.setUserpw(encodedPw);
			memberService.register(vo);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return "redirect:/customLogin";
		
		
		/*
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			String sql = "insert into tbl_member(userid, userpw, username) values (?,?,?)";
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, vo.getUserid());
			pstmt.setString(2, pwencoder.encode(vo.getUserpw()));
//			pstmt.setString(2, vo.getUserpw()); 
			pstmt.setString(3, vo.getUserName());
			pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch(Exception e) {
					
				}
			}
			if(con != null) {
				try {
					con.close();
				} catch(Exception e) {
					
				}
			}
		}
		
		con = null;
		pstmt = null;
		try {
			String sql_auth = "insert into tbl_member_auth(userid, auth) values(?,?)";
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql_auth);
			
			pstmt.setString(1, vo.getUserid());
			pstmt.setString(2, "ROLE_USER");
			pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch(Exception e) {
					
				}
			}
			if(con != null) {
				try {
					con.close();
				} catch(Exception e) {
					
				}
			}
		}
		*/
	}
	
	@GetMapping("/memberUpdate")
	public String memberUpdate(Model model, Principal principal) {
		
		model.addAttribute("member", memberService.read(principal.getName()));
		
		return "member/memberInfo"; 
	}
	
	
//	@RequestMapping(
//			method = {RequestMethod.PUT, RequestMethod.PATCH },
//			consumes = "application/json",
//			produces = {MediaType.TEXT_PLAIN_VALUE}
//			)
	
	/*	memberInfo.jsp에서 MemberController.java의 memberUpdate()에 POST방식으로 정보를 보낼 때, MemberVO vo객체안에 담아서 보내는 법
	 * 
	 *  modifyBtn.on("click", function(e){
				
				userpw= $('#userpw').val();
				userName = $('#userName').val();
				
				console.log("user password: "+userpw+" userName: "+userName);
				
				vo = {userid : userid, 
					  userpw: userpw,
					  userName: userName};
				
				if(!userpw || !userName){
					alert("정보를 입력하지 않으셨습니다!");
					return;
				}
				
				console.log("vo: "+vo);
				console.log(vo.userpw+"   "+vo.userName);
				
				memberService.update(vo, function(result){
					alert(result);
				});
			});
			
			
			
		member.js
		
		var memberService = (function(){
	
		function update(vo, callback, error){
			
			$.ajax({
				type:'post', 
				url:'/member/memberUpdate',
				data : JSON.stringify(vo),
				contentType : "application/json; charset=utf-8", 
				success : function(result, status, xhr){
					if(callback){
						callback(result);
					}
				},
				error : function(xhr, status, er){
					if(error){
						error(er);
					}
				}
			})
		}
		
		파라미터 받을때 @RequestBody MemberVo vo에서 파라미터로 받을 DTO인 MemberVO와  @RequestBody 꼭 필요! 
		DTO는 json구조와 같아야 함!
		그러면 스프링이 Jackson을 통해 Json값을 읽어서 DTO에 잘 넣어준다 ㅎㅎ
	 * 
	 */
	@PostMapping("/memberUpdate")
	public String memberUpdate(@RequestBody MemberVO vo, Principal principal, HttpSession session, RedirectAttributes rttr) throws Exception{
		
		MemberVO prev = memberService.read(principal.getName());
		
		log.info("$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
		log.info(vo);
		
		prev.setUserpw(pwencoder.encode(vo.getUserpw())); 
		prev.setUserName(vo.getUserName());
		
		log.info("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
		log.info(prev);
		
		memberService.memberUpdate(prev);
		
		log.info("after memberService.memberUpdate(prev).....");
//		session.invalidate(); //회원정보가 바뀌어서 invalidate하는거 같은데 안하면 안돼?
//		return "redirect:/"; //redirect언제써? //ERR_TOO_MANY_REDIRECTS
		return "redirect:/board/list";
//		return "/";
	}
	
	@GetMapping("/member/memberDeleteView")
	public String memberDeleteView(Model model, Principal principal) {
		
		model.addAttribute("member", memberService.read(principal.getName()));
		
		return "member/memberDeleteView"; 
	}
	
	
	@PostMapping("/memberDelete")
	public String memberDelete(@RequestBody MemberVO vo, Principal principal, HttpSession session, RedirectAttributes rttr) throws Exception{
		
		MemberVO prev = memberService.read(principal.getName());
		
		log.info("$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
		log.info("vo: "+vo);
		
//		String prev_pw = prev.getUserpw();
//		String vo_pw = pwencoder.encode(vo.getUserpw());
		
//		if(!prev_pw.equals(vo_pw)) {
		if(!pwencoder.matches(vo.getUserpw(), prev.getUserpw())) {
			log.info("패스워드가 다릅니다요~");
			rttr.addFlashAttribute("msg", false);
			return "redirect:/member/memberDeleteView";
		}
		
		log.info("패스워드 일치.. 계정 삭제 진행");
		memberService.memberDelete(prev);
		
		log.info("after memberService.memberDelete(prev).....");
		session.invalidate(); //회원정보가 바뀌어서 invalidate하는거 같은데 안하면 안돼?
//		return "redirect:/"; //redirect언제써? //ERR_TOO_MANY_REDIRECTS
		return "redirect:/board/list";
//		return "/";
	}
}
