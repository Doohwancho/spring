package org.zerock.controller;

import org.springframework.http.MediaType;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.zerock.domain.Criteria;

import lombok.extern.log4j.Log4j;


@RequestMapping(value = "/sample/*", method= {RequestMethod.GET, RequestMethod.POST})
@Controller
//@RestController //@Controller + @ResponseBody. json으로 반환할때 사용해. 일반 .jsp페이지 보여주고 싶으면 그냥 @Controller사용해
@Log4j 
public class SampleController {
	
	@GetMapping(value= "/getText", produces = "text/plain; charset=UTF-8")
	public String getText() {
		log.info("MIME TYPE: "+ MediaType.TEXT_PLAIN_VALUE);
		return "�븞�뀞�븯�꽭�슂";
	}
	
	/*
	 * Media type?
	 * 
	 * client - request -> server(spring-controller's GetMapping/RequestMapping, tomcat)
	 * server(spring-controller, tomcat) - response -> client
	 * 
	 * MediaType.APPLICATION_JSON_UTF8_VALUE�씪硫�, �빖�뱾�윭媛� �슂泥�怨� �쓳�떟�쓣 蹂대궪 �븣, �듅�젙���엯�쑝濡쒕쭔 �쓳�떟�븯寃� 留뚮뱾湲� 媛��뒫.
	 * 
	 * request�뿉�뒗 consumes, response�뿉�뒗 produces瑜� �넻�빐 媛��뒫.
	 * 
	 * 留뚯빟 consumes = MediaType.APPLICATION_JSON_UTF8_VALUE �씪硫�, 
	 * 
	 * json �뜲�씠�꽣留� �떞怨좎엳�뒗�븷留� 泥섎━�븯寃좊떎�뒗 �쑜.
	 * 
	 * produces�냽�꽦�� �깮�왂媛��뒫.
	 */
	
//	@GetMapping(value= "/getSample", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE, 
//												 MediaType.APPLICATION_XML_VALUE }) //Media type?
//	public SampleVO getSample() {
//		return new SampleVO(112, "A", "B");
//	}
	
	/*
		1. localhost:5551/sample/getSample
		
		<SampleVO>
			<mno>112</mno>
			<firstName>�뒪��</firstName>
			<lastName>濡쒕뱶</lastName>
		</SampleVO>
		
		2. 1. localhost:5551/sample/getSample.json
		{"mno":112,"firstName":"�뒪��","lastName":"濡쒕뱶"}
	 */
	
	//@PathVariable - url�뿉 理쒕��븳 留롮� �젙蹂대�� �떞怨� �떢�쓣�븣 �궗�슜�븯�뒗 寃�
	//http://localhost:5551/sample/product/hello/204
	@GetMapping("/product/{cat}/{pid}") 
	public String[] getPath(@PathVariable String cat, @PathVariable Integer pid) {
		return new String[] {"category: "+ cat, "productid: "+pid};
	}

	
	
	//spring-security-practice
	@GetMapping("/all")
	public void doAll(Criteria cri) {
		log.info("do all can access everybody!");
	}
	
	@GetMapping("/member")
	public void doMember(Criteria cri) {
		log.info("logined member!");
	}
	
	@GetMapping("/admin")
	public void doAdmin() {
		log.info("admin only");
	}
	
	
	//37.어노테이션을 이용하는 스프링 시큐리티 설정
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MEMBER')")
	@GetMapping("/annoMember")
	public void doMember2() {
		log.info("logined annotation member!");
	}
	
	@Secured({"ROLE_ADMIN"}) 
	@GetMapping("/annoAdmin")
	public void doAdmin2() {
		log.info("admin annotation only");
	}
}
