package org.zerock.mapper;

import org.zerock.domain.MemberVO;

public interface MemberMapper {
	
	public MemberVO read(String userid);
	
	public int registerUser(MemberVO vo);
	
	public int registerAuth(MemberVO vo);
	
	public int memberUpdate(MemberVO vo);
	
	public int memberDelete(MemberVO vo);
}
