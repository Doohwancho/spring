package org.zerock.controller;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.domain.BoardAttachVO;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;
import org.zerock.domain.PageDTO;
import org.zerock.service.BoardService;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Controller //스프링의 빈으로 인식될 수 있게끔
@Log4j
@RequestMapping(value= {"","/board/*"}) //board로 시작하면 무조건 이 컨트롤러로
//@AllArgsConstructor       //BoardController는 BoardService에 의존적이므로, 이걸로 자동주입. 이거 안하면 @Setter(onMethod_={@Autowired}) 해
public class BoardController {
	
	@Setter(onMethod_={@Autowired})
	private BoardService service;
	
//	@GetMapping("/list")
//	public void list(Model model) { //list()는 나중에 게시글 목록 전달해야 하니까 Model을 파라미터로 지정
//		log.info("list");
//		log.info(service.getList());
//		model.addAttribute("listItems", service.getList()); //모델의 attribute에 list라는 이름으로 담는다.
//	}
	
//	@GetMapping("/list") 
	@RequestMapping(value= {"/","/list","/home"}, method = RequestMethod.GET)
	public String list(Criteria cri, Model model) { //list()는 나중에 게시글 목록 전달해야 하니까 Model을 파라미터로 지정
		log.info("list: "+ cri);
		model.addAttribute("list", service.getList(cri)); //모델의 attribute에 list라는 이름으로 담는다.
//		model.addAttribute("pageMaker", new PageDTO(cri, 15));
		
		log.info("cri.getType: " + cri.getType());
		log.info("cri.getKeyword: "+ cri.getKeyword());
		log.info("cri.getPageNum: "+ cri.getPageNum());
		log.info("cri.getAmount: "+ cri.getAmount());
		
		int total = service.getTotal(cri);
		log.info("total: "+total);
		model.addAttribute("pageMaker", new PageDTO(cri, total)); 
		
		log.info(model);
		log.info("##########################"); 
		return "board/list"; 
	}
	
	
	@GetMapping("/register")
	@PreAuthorize("isAuthenticated()")
	public void register() {
		log.info("get to /register!");
	} 
	
	@PostMapping("/register")
	@PreAuthorize("isAuthenticated()")
	public String register(BoardVO board, RedirectAttributes rttr) {
		log.info("register: "+board); 
		
		if(board.getAttachList() != null) {
			board.getAttachList().forEach(attach -> log.info(attach)); 
		}
		
		log.info("==========POST한다!=============="); 
		
		
		service.register(board);
		
		rttr.addFlashAttribute("result", board.getBno());
		
		return "redirect:/board/list";
	}
	

	
	@GetMapping({"/get", "/modify"})
	public void get(@RequestParam("bno") Long bno, @ModelAttribute("cri") Criteria cri, Model model) { //@ModelAttribute("cri")  //@ModelAttribute는 변수이름 바꿀때 쓰임. https://hongku.tistory.com/123
		log.info("/get or /modify");
		
		log.info("cri.getType: " + cri.getType());
		log.info("cri.getKeyword: "+ cri.getKeyword());
		log.info("cri.getPageNum: "+ cri.getPageNum());
		log.info("cri.getAmount: "+ cri.getAmount());
		int total = service.getTotal(cri);
		log.info("total: "+total);
		
		log.info(model);
		log.info("##########################");
		
		model.addAttribute("board", service.get(bno));
	}
	
	@PreAuthorize("principal.username == #board.writer")
	@PostMapping("/modify")
	public String modify(BoardVO board, Criteria cri, RedirectAttributes rttr) {
		log.info("modify: "+board);
		log.info("cri: "+cri);
		log.info("##################");
		
		if(service.modify(board)) {
			log.info("service.modify(board) inside");
			rttr.addFlashAttribute("result", "success");
		}
		log.info("service.modify(board) ended");
		log.info(cri.getListLink());
		log.info("&&&&&&&&&&&&&&&&&&&&&&&&&&");
//		rttr.addAttribute("pageNum", cri.getPageNum());
//		rttr.addAttribute("amount", cri.getAmount());
//		rttr.addAttribute("type", cri.getType());
//		rttr.addAttribute("keyword", cri.getKeyword());
		return "redirect:/board/list"+cri.getListLink();
//		return "redirect:/board/list";
	}
	
	@PreAuthorize("principal.username == #writer")
	@PostMapping("/remove") //삭제는 반드시 post방식으로만!!
	public String remove(@RequestParam("bno") Long bno, Criteria cri, RedirectAttributes rttr, String writer) {
		log.info("remove..."+bno);
		
		log.info("위배1");
		
		List<BoardAttachVO> attachList = service.getAttachList(bno);
		
		log.info("위배2"+" bno: "+bno);
		
		if(service.remove(bno)) {
			log.info("위배5");
			deleteFiles(attachList);
			log.info("위배6");
			rttr.addFlashAttribute("result", "success");
		}
//		rttr.addAttribute("pageNum", cri.getPageNum());
//		rttr.addAttribute("amount", cri.getAmount());
//		rttr.addAttribute("type", cri.getType());
//		rttr.addAttribute("keyword", cri.getKeyword());
		log.info("위배5");
		
		return "redirect:/board/list"+cri.getListLink(); 
	}
	
	@GetMapping(value="/getAttachList", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody //BoardController.java는 @RestController가 아니라 그냥 @Controller라서 직접 @ResponseBody작성. @ResponseBody는 json type으로 return시 사용. api에서.
	public ResponseEntity<List<BoardAttachVO>> getAttachList(Long bno){
		log.info("boardController - getAttachList");
		return new ResponseEntity<>(service.getAttachList(bno), HttpStatus.OK);
	}
	
	private void deleteFiles(List<BoardAttachVO> attachList) {
		if(attachList == null || attachList.size() == 0) return;
		
		log.info("delete attach files.........");
		log.info(attachList);
		
		attachList.forEach(attach -> {
			try {
				Path file = Paths.get("C:\\upload\\"+attach.getUploadPath()+"\\"+attach.getUuid()+"_"+attach.getFileName());
				
				Files.deleteIfExists(file);
				
				if(Files.probeContentType(file).startsWith("image")) {
					Path thumbNail = Paths.get("C:\\upload\\"+attach.getUploadPath()+"\\s_"+attach.getUuid()+"_"+attach.getFileName());
					
					Files.delete(thumbNail);
				}
				
			} catch(Exception e) {
				log.error("delete file error"+ e.getMessage());
			}
		});
	}
}
