package org.zerock.service;

import org.zerock.domain.MemberVO;

public interface MemberService {
	
	public MemberVO read(String userid);
	
	public int register(MemberVO vo) throws Exception;
	
	public int memberUpdate(MemberVO vo) throws Exception;
	
	public int memberDelete(MemberVO vo) throws Exception;
}
