package org.zerock.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class SampleTxServiceTests {
	@Setter(onMethod_ = @Autowired)
	private SampleTxService service;
	
	
	@Test
	public void testAddData() throws Exception {
		String str = "Starry Starry night Paint your palette blue and grey Look out on a summer's day";
		log.info("SampleTxServiceTests Started!");		
		service.addData(str); //java.sql.SQLException: ORA-12899: "BOOK_EX"."TBL_SAMPLE2"."COL1" 열에 대한 값이 너무 큼(실제: 79, 최대값: 50)
		//하지만 transaction처리를 안했기 때문에, 확인해본 결과, tbl_sample1에는 들어가고 tbl_sample2엔 안들어갔다.
		//SampleTxServiceImpl에 @Transactional 어노테이션 추가 후에는 tbl_sample1에 데이터가 안들어간 걸 확인 가능.
		log.info("SampleTxServiceTests ended!");
	}
}
