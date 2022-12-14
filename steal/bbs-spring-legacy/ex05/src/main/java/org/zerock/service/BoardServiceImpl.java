package org.zerock.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.domain.BoardAttachVO;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;
import org.zerock.mapper.BoardAttachMapper;
import org.zerock.mapper.BoardMapper;
import org.zerock.mapper.ReplyMapper;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Log4j
@Service //비즈니스 영역 담당하는 객체임을 표시하기 위해 사용
//@AllArgsConstructor
public class BoardServiceImpl implements BoardService{
	
	@Setter(onMethod_=@Autowired)
	private BoardMapper mapper; //spring 4.3이상에선 자동처리(단일 파라미터라면). 
	
	@Setter(onMethod_=@Autowired)
	private BoardAttachMapper attachMapper;
	
	@Setter(onMethod_=@Autowired)
	private ReplyMapper replyMapper;
	
	@Transactional
	@Override
	public void register(BoardVO board) {
		log.info("register..."+board);
		mapper.insertSelectKey(board);
		
		if(board.getAttachList() == null || board.getAttachList().size() <= 0) {
			return;
		}
		
		board.getAttachList().forEach(attach -> {
			attach.setBno(board.getBno());
			attachMapper.insert(attach);
		});
	}
	
	@Override
	public BoardVO get(Long bno) {
		log.info("read.......");
		return mapper.read(bno);
	}
	
	@Transactional
	@Override
	public boolean modify(BoardVO board) {
		log.info("modify....."+board);
		
		attachMapper.deleteAll(board.getBno());
		
		boolean modifyResult = mapper.update(board) == 1;
		
		log.info("^^^^^^^^^^^^^^^^^^^^^ modifyResult: "+modifyResult+"  "+board.getAttachList());
		
		if(modifyResult && board.getAttachList() != null && board.getAttachList().size() > 0) {
			board.getAttachList().forEach(attach -> {
				attach.setBno(board.getBno());
				attachMapper.insert(attach);
			});
		}
		
		return modifyResult;
	}
	
	@Transactional
	@Override
	public boolean remove(Long bno) {
		log.info("위배3"+" bno: "+bno);
		log.info("remove...");
		attachMapper.deleteAll(bno);
		log.info("위배4"+" bno: "+bno);
		
		return mapper.delete(bno) == 1;
	}
//	@Override
//	public List<BoardVO> getList(){
//		log.info("get list....");
//		return mapper.getList();
//	}
	
	@Override
	public List<BoardVO> getList(Criteria cri) {
		log.info("get list with criteria: "+cri);
		return mapper.getListWithPaging(cri);
	}
	
	@Override
	public int getTotal(Criteria cri) {
		log.info("get total count");
		return mapper.getTotalCount(cri);
	}
	
	@Override
	public List<BoardAttachVO> getAttachList(Long bno){
		log.info("serviceImpl - getAttachList");
		return attachMapper.findByBno(bno);
	}
}
