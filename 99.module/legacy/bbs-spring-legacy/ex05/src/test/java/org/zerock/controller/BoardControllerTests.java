package org.zerock.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring/root-context.xml",
	"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"}) //WAS안쓰고 mockmvc쓰는거라 servlet-context.xml꺼도 필요함
@Log4j
public class BoardControllerTests { //WAS없이 테스트
	@Setter(onMethod_= {@Autowired})
	private WebApplicationContext ctx;
	
	private MockMvc mockMvc; 
	
	@Before   //모든 @Test전 매번 실행되는 메서드. Junit으로 import.
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
	}
	
//	@Test
//	public void testList() throws Exception { //웹에 직접뿌리는게 아니어도, throws exception
//		log.info(
//				mockMvc.perform(MockMvcRequestBuilders.get("/board/list"))
//				.andReturn()
//				.getModelAndView() //모델을 여기서 씀
//				.getModelMap()
//				);
//	}
//	
//	@Test
//	public void testRegister() throws Exception{
//		String resultPage = mockMvc.perform(MockMvcRequestBuilders.post("/board/register")
//				.param("title", "new title")
//				.param("content", "new content")
//				.param("writer", "new writer")
//				).andReturn().getModelAndView().getViewName();
//		log.info(resultPage);
//	}
//	
//	@Test
//	public void tetGet() throws Exception{
//		log.info(mockMvc.perform(MockMvcRequestBuilders
//				.get("/board/get")
//				.param("bno","2")) //모든 파라미터는 String 형태로 넣는다. integer나 다른게 아니라.
//				.andReturn()
//				.getModelAndView().getModelMap()
//				);
//	}
//	
//	@Test
//	public void testModify() throws Exception{
//		String resultPage = mockMvc.perform(MockMvcRequestBuilders.post("/board/modify")
//				.param("bno", "1")
//				.param("title", "newly modified title")
//				.param("content", "newly modified content")
//				.param("writer", "newly modified writer"))
//				.andReturn().getModelAndView().getViewName();
//		
//		log.info(resultPage);
//	}
//	
//	@Test
//	public void testRemove() throws Exception{
//		String resultPage = mockMvc.perform(MockMvcRequestBuilders.post("/board/remove")
//				.param("bno", "1"))
//				.andReturn().getModelAndView().getViewName();
//		log.info(resultPage);
//	}
//	
	@Test
	public void testListPaging() throws Exception{
		log.info(mockMvc.perform(
					MockMvcRequestBuilders.get("/board/list")
					.param("pageNum", "2")
					.param("amount", "4"))
					.andReturn().getModelAndView().getModelMap()
				);
	}
}
