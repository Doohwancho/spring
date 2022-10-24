package org.zerock.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
//import org.apache.ibatis.annotations.Select;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;


public interface BoardMapper {

	//@Select("select * from tbl_board where bno > 0")
	public List<BoardVO> getList();
	
	public List<BoardVO> getListWithPaging(Criteria cri);
	
	public void insert(BoardVO board); //insert만 처리되고 생성된 pk값을 알 필요가 없을 경우
	
	public void insertSelectKey(BoardVO board); //insert문이 실행되고 생성된 pk값을 알아야 할 경우
	
	public BoardVO read(Long bno);
	
	public int delete(Long bno); //삭제 성공시 1반환, 아니면 0반환.
	
	public int update(BoardVO board); //delete와 같은 논리
	
	public int getTotalCount(Criteria cri);
	
	public void updateReplyCnt(@Param("bno") Long bno, @Param("amount") int amount); //2
}
