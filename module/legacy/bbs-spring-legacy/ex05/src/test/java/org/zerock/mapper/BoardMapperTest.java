package org.zerock.mapper;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;                                        //?
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;           //?
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner; //?
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class BoardMapperTest {
	
	@Setter(onMethod_ = @Autowired)
	private BoardMapper mapper;
	
	
	@Test
	public void testGetList() {
		mapper.getList().forEach(board -> log.info(board));
	}
	/*
	@Test
	public void testInsert() {
		BoardVO board = new BoardVO();
		board.setTitle("d");
		board.setContent("e");
		board.setWriter("f");
		
//		mapper.insert(board);
		mapper.insertSelectKey(board);
		
		log.info(board);
	}

	@Test
	public void testRead() {
		BoardVO board = mapper.read(6L);
		log.info(board); //INFO : org.zerock.mapper.BoardMapperTest - BoardVO(bno=6, title=가, content=나, writer=다, regdate=Sun May 03 12:12:03 KST 2020, updatedate=Sun May 03 12:12:03 KST 2020)
	}
	
	
	@Test
	public void testDelete() {
		log.info("DELETE COUNT: "+ mapper.delete(10L)); //INFO : org.zerock.mapper.BoardMapperTest - DELETE COUNT: 1
	}
	
	@Test
	public void testUpdate() {
		BoardVO board = new BoardVO();
		board.setBno(3L);
		board.setTitle("수정된 제목");
		board.setContent("수정된 내용");
		board.setWriter("수정된 작성자");
		
		int count = mapper.update(board);
		log.info("UPDATE COUNT: "+count);
	}
	
	
	@Test
	public void testPaging() {
		Criteria cri = new Criteria();
		cri.setPageNum(2);
		cri.setAmount(3);
		List<BoardVO> list = mapper.getListWithPaging(cri);
		list.forEach(board->log.info(board.getBno()));
	}
	*/
	
	@Test
	public void testSearch() {
		Criteria cri = new Criteria();
		
		cri.setKeyword("오늘");
		cri.setType("TC");
		
		List<BoardVO> list = mapper.getListWithPaging(cri);
		
		list.forEach(board->log.info(board));
	}
}
