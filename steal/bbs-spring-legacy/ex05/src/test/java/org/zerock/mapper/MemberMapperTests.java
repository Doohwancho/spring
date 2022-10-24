package org.zerock.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.domain.MemberVO;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class MemberMapperTests {
	
	@Setter(onMethod_=@Autowired)
	private MemberMapper mapper;
	
	@Test
	public void testRead() {
		MemberVO vo = mapper.read("admin90");
		
		log.info(vo);
		
		vo.getAuthList().forEach(authVO -> log.info(authVO));
		
//		|--------|-------------------------------------------------------------|---------|---------|----------------------|----------------------|-----------|
//		|userid  |userpw                                                       |username |enabled  |regdate               |updatedate            |auth       |
//		|--------|-------------------------------------------------------------|---------|---------|----------------------|----------------------|-----------|
//		|admin90 |$2a$10$9nWBfvzMgcTtbYcLcjwz9eQm2iLV6D5djURruovmugQHBeBcMCzuy |관리자90    |[unread] |2020-07-08 20:10:28.0 |2020-07-08 20:10:28.0 |ROLE_ADMIN |
//		|--------|-------------------------------------------------------------|---------|---------|----------------------|----------------------|-----------|
	}
}
