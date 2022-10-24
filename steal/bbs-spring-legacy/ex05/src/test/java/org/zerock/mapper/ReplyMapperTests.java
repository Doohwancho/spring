package org.zerock.mapper;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.zerock.domain.Criteria;
import org.zerock.domain.ReplyVO;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class ReplyMapperTests {
	
	@Setter(onMethod_=@Autowired)
	private ReplyMapper mapper;
	
//	@Test
//	public void testMapper() {
//		log.info(mapper);
//	}
	
	/*
	//테스트 전, 해당 번호의 게시물이 존재하는지 반드시 확인할 것
	private Long[] bnoArr = {163L, 162L, 161L, 159L, 158L, 157L, 156L, 155L, 154L, 153L};
	
	@Test
	public void testCreate() {
		
		IntStream.rangeClosed(1, 10).forEach(i -> {
			
			ReplyVO vo = new ReplyVO();
			
			//게시물의 번호
			vo.setBno(bnoArr[i % 5]);
			vo.setReply("댓글 테스트 " + i);
			vo.setReplyer("replyer "+ i);
			
			mapper.insert(vo);
		});
	}
	*/
	
	/*
	@Test
	public void testRead() {
		Long targetRno = 5L;
		
		ReplyVO vo = mapper.read(targetRno);
		
		log.info(vo); //INFO : org.zerock.mapper.ReplyMapperTests - ReplyVO(rno=5, bno=158, reply=댓글 테스트 4, replyer=replyer 4, replyDate=Wed Jun 03 16:12:46 KST 2020, updateDate=Wed Jun 03 16:12:46 KST 2020)
	}
	*/
	
	/*
	@Test
	public void testDelete() {
		Long targetRno = 5L;
		
		mapper.delete(targetRno);
		
	}
	*/
	
	/*
	@Test
	public void testUpdate() {
		Long targetRno = 5L;
		
		ReplyVO vo = mapper.read(targetRno);
		
		vo.setReply("Update reply!");
		
		int count = mapper.update(vo);
		
		log.info("UPDATE COUNT: "+count);
	}
	*/
	
	/*
	@Test
	public void testList() {
		Criteria cri = new Criteria();
		
		//158L
		List<ReplyVO> replies = mapper.getListWithPaging(cri, 158L);
		
		replies.forEach(reply -> log.info(reply));
	}
	*/
	
	
	@Test
	public void testList2() {
		Criteria cri = new Criteria(2, 10);
		//163L
		List<ReplyVO> replies = mapper.getListWithPaging(cri, 163L);
		
		replies.forEach(reply -> log.info(reply));
	}
}
