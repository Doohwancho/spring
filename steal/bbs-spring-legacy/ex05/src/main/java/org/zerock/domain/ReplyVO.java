package org.zerock.domain;

import java.util.Date;

import lombok.Data;

@Data
public class ReplyVO {
	private Long rno; //왜 Integer말고 long type으로 선언? primitive type으로 선언하면 null못넣어서 null pointer exception이 불가능하기 때문
	private Long bno;
	private String reply;
	private String replyer;
	private Date replyDate;
	private Date updateDate;
}
