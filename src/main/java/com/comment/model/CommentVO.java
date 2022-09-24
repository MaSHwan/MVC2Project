package com.comment.model;

import java.sql.Timestamp;

//
//comment_num number(7) primary key,
//comment_writer varchar2(15) not null,
//regdate timestamp(6) default sysdate not null,
//comment_content varchar2(1000),
//comment_pnum not null references p_board(num)


public class CommentVO {
	private int num;
	private String writer;
	private Timestamp regdate;
	private String content;
	private int pnum;
	private int ref;
	private int step;
	private int depth;
	
	
	public int getRef() {
		return ref;
	}
	public void setRef(int ref) {
		this.ref = ref;
	}
	public int getStep() {
		return step;
	}
	public void setStep(int step) {
		this.step = step;
	}
	public int getDepth() {
		return depth;
	}
	public void setDepth(int depth) {
		this.depth = depth;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public Timestamp getRegdate() {
		return regdate;
	}
	public void setRegdate(Timestamp regdate) {
		this.regdate = regdate;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getPnum() {
		return pnum;
	}
	public void setPnum(int pnum) {
		this.pnum = pnum;
	}
	
	
}
