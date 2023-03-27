package org.zerock.domain;

import org.springframework.web.util.UriComponentsBuilder;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Criteria {
	
	private int pageNum;
	private int amount;
	
	private String type;
	private String keyword;
	
	public Criteria() {
		this(1, 10);
	}
	
	public Criteria(int pageNum, int amount) {
		this.pageNum = pageNum;
		this.amount = amount;
	}
	
	public String[] getTypeArr() {
		return type == null ? new String[] {}: type.split(""); //T,W,C를 띄어줌
	}
	
	public String getListLink() { 
		UriComponentsBuilder builder = UriComponentsBuilder.fromPath("")
				.queryParam("pageNum", this.pageNum)
				.queryParam("amount", this.getAmount())
				.queryParam("type", this.getType())
				.queryParam("keyword", this.getKeyword());
		//UriComponentsBuilder는 여러개의 파라미터를 연결해서 url의 형태로 만들어주는 기능을 가짐
		//리다이렉트 하거나 form태그 사용하는 상황을 많이 줄여준다.
		//그리고 한글처리도 자동으로 해준다.
		//UriComponentsBuilder는 화면에서도 유용하게 사용될 수 있는데, 주로 Javascript를 사용할 수 없는 상황에서 링크를 처리해야 하는 상황에서 사용됨
		
		//./board/list에 + cri.getListLinks(); 하면,
		//?pageNum=3&amount=10&type=TC&keyword=f
		//http://localhost:5551/board/list?pageNum=3&amount=10&type=TC&keyword=f
		
		return builder.toUriString();
	}
}
