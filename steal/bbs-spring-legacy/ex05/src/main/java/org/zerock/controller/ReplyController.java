package org.zerock.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.domain.Criteria;
import org.zerock.domain.ReplyPageDTO;
import org.zerock.domain.ReplyVO;
import org.zerock.service.ReplyService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@RequestMapping("/replies")
@RestController
@Log4j
@AllArgsConstructor
public class ReplyController {
	private ReplyService service;
	
	@PreAuthorize("isAuthenticated()")
	@PostMapping(value ="/new", 
			consumes = "application/json",
			produces = {MediaType.TEXT_PLAIN_VALUE}
			)
	public ResponseEntity<String> create(@RequestBody ReplyVO vo){ //@RequestBody로 Json데이터 들어온걸 ReplyVO로 바꾼다.
		log.info("ReplyVO: "+vo);
		
		int insertCount = service.register(vo);
		
		log.info("Reply Insert Count: "+ insertCount);
		
		return insertCount == 1 ? new ResponseEntity<>("success", HttpStatus.OK)
								: new ResponseEntity<>("success", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	/*	
	http://localhost:5551/replies/pages/158/1 로 보내면, 
		
	<List>
	    <item>
	        <rno>5</rno>
	        <bno>158</bno>
	        <reply>Update reply!</reply>
	        <replyer>replyer 4</replyer>
	        <replyDate>1591168366000</replyDate>
	        <updateDate>1591177855000</updateDate>
	    </item>
	
	    <item>
	        <rno>32</rno>
	        <bno>158</bno>
	        <reply>홍길동이 돌아왔다!!!</reply>
	        <replyer>홍길동555</replyer>
	        <replyDate>1591180685000</replyDate>
	        <updateDate>1591180685000</updateDate>
	    </item>
	</List>
	
	
	json파일 원하면, 마지막에 .json붙이면 됨.
	
	http://localhost:5551/replies/pages/158/1.json
	*/
	@GetMapping(value = "/pages/{bno}/{page}",
				produces = {
						MediaType.APPLICATION_XML_VALUE,
						MediaType.APPLICATION_JSON_UTF8_VALUE
				}
			)
	public ResponseEntity<ReplyPageDTO> getList(@PathVariable("page") int page, 
												 @PathVariable("bno") Long bno
			){
		log.info("getList.................");
		Criteria cri = new Criteria(page, 10);
		log.info(cri);
		
		return new ResponseEntity<>(service.getListPage(cri, bno), HttpStatus.OK);
	}
	
	
	@GetMapping(value = "{rno}",
				produces = {
						MediaType.APPLICATION_XML_VALUE,
						MediaType.APPLICATION_JSON_UTF8_VALUE
				}
			)
	public ResponseEntity<ReplyVO> get(@PathVariable("rno") Long rno){
		
		return new ResponseEntity<>(service.get(rno), HttpStatus.OK);
	}
	
	
	@PreAuthorize("principal.username == #vo.replyer")
	@DeleteMapping(
			value ="/{rno}",
			produces = {MediaType.TEXT_PLAIN_VALUE}
			)
	public ResponseEntity<String> remove(@RequestBody ReplyVO vo, @PathVariable("rno") Long rno){
		log.info("remove rno: "+rno);
		
		log.info("replyer: "+vo.getReplyer());
		
		return service.remove(rno) == 1 ? new ResponseEntity<>("success", HttpStatus.OK)
								: new ResponseEntity<>("success", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
	/*
	 * PUT과 PATCH의 차이
	 * 
	 * put은 모든 파라미터를 다 채워야 함. 하나라도 안채우면 null됨.
	 * 
	 * patch는 바꾼것만 바꿔줌.
	 */
	
	@PreAuthorize("principal.username==#vo.replyer")
	@RequestMapping(
			method = {RequestMethod.PUT, RequestMethod.PATCH },
			value ="/{rno}",
			consumes = "application/json",
			produces = {MediaType.TEXT_PLAIN_VALUE}
			)
	public ResponseEntity<String> modify(@RequestBody ReplyVO vo, 
										 @PathVariable("rno") Long rno){
		vo.setRno(rno); 
		
		log.info("rno: "+rno);
		
		return service.modify(vo) == 1 ? new ResponseEntity<>("success", HttpStatus.OK)
								: new ResponseEntity<>("success", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
